package com.octopus.stormly.utils;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextSwitcher;

import androidx.annotation.AnimRes;
import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.octopus.stormly.Colors;
import com.octopus.stormly.MainActivity;
import com.octopus.stormly.R;

import java.util.Random;

import static com.octopus.stormly.Colors.colors;

public class Bindable {

    @BindingAdapter(value = "switcherText")
    public static void setText(TextSwitcher switcher, String text){
        switcher.setText(text);

    }

    @BindingAdapter(value = "generateBackground")
    public static void generate(CardView view, boolean generate){
        if (generate){
            Log.d("Bindable", "generate: Generate");
            int[] colors = Colors.colors;
            Random random = new Random();
            int i = random.nextInt(colors.length - 1);
            int randomColor = colors[i];
            Log.d("Bindable", "generate: " + randomColor);
            view.setCardBackgroundColor(randomColor);
        }
    }

    @BindingAdapter(value = "setWeatherAnimation")
    public static void setWeatherAnimation(LottieAnimationView animationView, int weatherCode){
        Log.d("Bindable", "setWeatherAnimation: weather code " + weatherCode);
        int rawRes;
            if (weatherCode / 100 == 2) {
                rawRes =  R.raw.storm_weather;
            } else if (weatherCode / 100 == 3) {
                rawRes =  R.raw.rainy_weather;
            } else if (weatherCode / 100 == 5) {
                rawRes = R.raw.rainy_weather;
            } else if (weatherCode / 100 == 6) {
                rawRes = R.raw.snow_weather;
            } else if (weatherCode / 100 == 7) {
                rawRes = R.raw.unknown;
            } else if (weatherCode == 800) {
                rawRes = R.raw.clear_day;
            } else if (weatherCode == 801) {
                rawRes = R.raw.few_clouds;
            } else if (weatherCode == 803) {
                rawRes = R.raw.broken_clouds;
            } else if (weatherCode / 100 == 8) {
                rawRes = R.raw.cloudy_weather;
            }else{
                rawRes = R.raw.unknown;
            }

            animationView.setAnimation(rawRes);
            animationView.playAnimation();
        }

}
