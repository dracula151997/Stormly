package com.octopus.stormly.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.StyleRes;

import static android.view.Gravity.CENTER;

public class TextViewSwitcher implements ViewSwitcher.ViewFactory {
    private final Context context;
    @StyleRes
    private final int styleId;
    private final boolean center;
    private final Typeface typeface;

    public TextViewSwitcher(Context context, int styleId, boolean center, Typeface typeface) {
        this.context = context;
        this.styleId = styleId;
        this.center = center;
        this.typeface = typeface;
    }

    @Override
    public View makeView() {
        TextView textView = new TextView(context);
        if (center){
            textView.setGravity(CENTER);
        }

        textView.setTextAppearance(styleId);
        textView.setTypeface(typeface);

        return textView;
    }
}
