package com.octopus.stormly;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextSwitcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.octopus.stormly.adapter.GenericAdapter;
import com.octopus.stormly.adapter.NextWeatherDay;
import com.octopus.stormly.databinding.MainActivityBinding;
import com.octopus.stormly.utils.TextViewSwitcher;
import com.octopus.stormly.utils.WeatherLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private MainActivityBinding mainActivityBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mainActivityBinding.activityMainToolbarLayout.appToolbar);
        mainActivityBinding.activityMainToolbarLayout.setLocationName("Cairo, EG");
        initSearchView();
        initSwipeRefreshLayout();
        initTextSwitchers();
        initRecyclerView();

        settingDefaultData();
        LottieAnimationView lottieView = mainActivityBinding.activityMainContentMainLayout.contentMainWeatherAnimationView;
        lottieView.setAnimation(R.raw.rainy_weather);
        lottieView.playAnimation();


    }

    private void initRecyclerView() {
        List<NextWeatherDay> days = new ArrayList<>();
        days.add(new NextWeatherDay("10", "Friday", "5", "11", R.drawable.ic_baseline_menu_24, true));
        days.add(new NextWeatherDay("10", "Saturday", "2", "14", R.drawable.ic_baseline_menu_24, true));
        days.add(new NextWeatherDay("10", "Sunday", "-10", "6", R.drawable.ic_baseline_menu_24, true));

        GenericAdapter<NextWeatherDay> adapter = new GenericAdapter<>(R.layout.list_item_weather_day);
        adapter.addList(days);

        mainActivityBinding.activityMainContentMainLayout.contentMainNextWeatherRecyclerView.setAdapter(adapter);
    }

    private void settingDefaultData() {
        String tempValue = String.format(Locale.getDefault(), "%.0f", 10.5);
        String humidityValue = String.format(Locale.getDefault(), "%d%%", 5);
        String windSpeedValue = String.format(Locale.getDefault(), "%.0f km/hr", 10.0);
        mainActivityBinding.activityMainContentMainLayout.setDesc("Rainy");
        mainActivityBinding.activityMainContentMainLayout.setTemp(tempValue);
        mainActivityBinding.activityMainContentMainLayout.setHumidity(humidityValue);
        mainActivityBinding.activityMainContentMainLayout.setWind(windSpeedValue);
    }

    private void initTextSwitchers() {
        TextSwitcher descTextSwitcher = mainActivityBinding.activityMainContentMainLayout.contentMainDescTextView;
        TextSwitcher humidityTextSwitcher = mainActivityBinding.activityMainContentMainLayout.contentMainHumidityTextView;
        TextSwitcher tempTextSwitcher = mainActivityBinding.activityMainContentMainLayout.contentMainTempTextView;
        TextSwitcher windTextSwitcher = mainActivityBinding.activityMainContentMainLayout.contentMainWindTextView;
        setupTextSwitcher(descTextSwitcher, R.style.DescTextView);
        setupTextSwitcher(humidityTextSwitcher, R.style.HumidityTextView);
        setupTextSwitcher(tempTextSwitcher, R.style.TempTextView);
        setupTextSwitcher(windTextSwitcher, R.style.WindSpeedTextView);

    }

    private void setupTextSwitcher(TextSwitcher switcher, int style) {
        switcher.setFactory(new TextViewSwitcher(this, style, true, null));
        switcher.setInAnimation(MainActivity.this, R.anim.slide_in_right);
        switcher.setOutAnimation(MainActivity.this, R.anim.slide_out_left);
    }

    private void initSwipeRefreshLayout() {
        final SwipeRefreshLayout swipeLayout = mainActivityBinding.activityMainSwipeRefreshLayout;
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WeatherLogger.debug("onRefresh() called");
                //TODO write your code here...
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeLayout.isRefreshing())
                            swipeLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void initSearchView() {
        MaterialSearchView searchView = mainActivityBinding.activityMainToolbarLayout.activityMainSearchView;
        searchView.setHint("Search");
        searchView.setCursorDrawable(R.drawable.ic_baseline_search_24);
        searchView.setEllipsize(true);
        searchView.setVoiceSearch(false);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                WeatherLogger.debug("initSearchView(): onQueryTextSubmit:query " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                WeatherLogger.debug("initSearchView(): onQueryTextChange:");
                return false;
            }
        });

    }
}