package com.example.travisho.codebuilder;

import android.content.ClipData;
import android.view.View;

/**
 * Created by schoolwork on 4/9/17.
 */

/* Purpose of this listener is so that when the block is long clicked the shadow follows the finger */
class LongPressListener implements View.OnLongClickListener {
    @Override
    public boolean onLongClick(View view) {
        final ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, shadowBuilder, view, 0);
        view.setVisibility(View.INVISIBLE);
        return true;
    }
}