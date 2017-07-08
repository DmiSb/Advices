package ru.dmisb.advices.data.network;

import android.os.AsyncTask;
import android.text.Html;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.dmisb.advices.data.dto.AdviceDto;
import ru.dmisb.advices.utils.AppConfig;

public class HttpPostAsyncTask extends AsyncTask<Void, Void, String> {

    private String urlSpec;
    private AdviceCallback callback;

    public HttpPostAsyncTask(String urlSpec, AdviceCallback callback) {
        this.urlSpec = urlSpec;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(AppConfig.BASE_URL + urlSpec);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(AppConfig.MAX_CONNECTION_TIMEOUT);
            connection.setReadTimeout(AppConfig.MAX_READ_TIMEOUT);
            connection.setRequestMethod("GET");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return new String(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally{
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        JSONObject reader = null;
        try {
            reader = new JSONObject(s);
            String text = reader.getString("text");
            text = Html.fromHtml(text).toString();

            String id = reader.getString("id");
            callback.completionHandler(true, new AdviceDto(id, text));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
