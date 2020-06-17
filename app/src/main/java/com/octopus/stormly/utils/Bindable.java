package com.octopus.stormly.utils;


import android.content.Context;
import android.widget.TextSwitcher;

import androidx.annotation.AnimRes;
import androidx.databinding.BindingAdapter;

import com.octopus.stormly.MainActivity;
import com.octopus.stormly.R;

public class Bindable {

    @BindingAdapter(value = "switcherText")
    public static void setText(TextSwitcher switcher, String text){
        switcher.setText(text);

    }
}
