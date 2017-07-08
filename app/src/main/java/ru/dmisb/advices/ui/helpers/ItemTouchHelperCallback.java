/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.dmisb.advices.ui.helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import ru.dmisb.advices.R;

public abstract class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private Drawable removeBackground;
    private Drawable removeIcon;
    private int markMargin;

    protected ItemTouchHelperCallback(Context context) {
        init(context);
    }

    private void init(Context context) {
        removeBackground = AppCompatDrawableManager.get().getDrawable(context, R.color.colorAccent);
        removeIcon = AppCompatDrawableManager.get().getDrawable(context, R.drawable.ic_clear);
        removeIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        markMargin = (int) context.getResources().getDimension(R.dimen.size_16);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public abstract void onSwiped(RecyclerView.ViewHolder viewHolder, int direction);

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;

        if (viewHolder.getAdapterPosition() == -1) {
            return;
        }

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            int itemHeight = itemView.getBottom() - itemView.getTop();
            int intrinsicWidth;
            int intrinsicHeight;
            int iconLeft;
            int iconRight;
            int iconTop;
            int iconBottom;


            if (dX < 0) {
                // draw background
                removeBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                removeBackground.draw(c);
                // draw remove icon
                intrinsicWidth = removeIcon.getIntrinsicWidth();
                intrinsicHeight = removeIcon.getIntrinsicWidth();

                iconLeft = itemView.getRight() - markMargin - intrinsicWidth;
                iconRight = itemView.getRight() - markMargin;
                iconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
                iconBottom = iconTop + intrinsicHeight;
                removeIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                removeIcon.draw(c);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
