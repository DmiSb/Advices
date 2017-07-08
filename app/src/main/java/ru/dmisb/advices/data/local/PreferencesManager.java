package ru.dmisb.advices.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.dmisb.advices.data.dto.AdviceDto;

public class PreferencesManager {
    private final SharedPreferences sharedPreferences;

    private static final String ADVICE_KEY = "ADVICE_";

    public PreferencesManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private AdviceDto getAdviceById(String id) {
        String text = sharedPreferences.getString(ADVICE_KEY + id, "");
        if (!text.isEmpty())
            return new AdviceDto(id, text);
        else
            return null;
    }

    public boolean saveAdvice(AdviceDto advice) {
        if (getAdviceById(advice.getId()) == null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(ADVICE_KEY + advice.getId(), advice.getText());
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

    public List<AdviceDto> getAllAdvices() {
        List<AdviceDto> advices = new ArrayList<>();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String id = entry.getKey().replace(ADVICE_KEY, "");
            String text = entry.getValue().toString();
            AdviceDto advice = new AdviceDto(id, text);
            advices.add(advice);
        }
        return advices;
    }

    public boolean removeAdvice(String id) {
        if (getAdviceById(id) != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(ADVICE_KEY + id);
            editor.apply();
            return true;
        } else {
            return false;
        }
    }
}
