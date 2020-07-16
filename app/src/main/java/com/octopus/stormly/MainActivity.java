package com.octopus.stormly;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextSwitcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.octopus.stormly.adapter.GenericAdapter;
import com.octopus.stormly.adapter.OnListItemClickListener;
import com.octopus.stormly.databinding.MainActivityBinding;
import com.octopus.stormly.enums.AssetsFile;
import com.octopus.stormly.model.currentweather.CurrentWeatherResponse;
import com.octopus.stormly.model.fivedayweather.FiveDayResponse;
import com.octopus.stormly.model.fivedayweather.ItemHourly;
import com.octopus.stormly.utils.Serializer;
import com.octopus.stormly.utils.TextViewSwitcher;
import com.octopus.stormly.utils.WeatherLogger;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnListItemClickListener {
    private static final String TAG = MainActivity.class.getName();
    private MainActivityBinding bind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(bind.activityMainToolbarLayout.appToolbar);

        initSearchView();
        initSwipeRefreshLayout();
        initTextSwitchers();
        initRecyclerView();

        showCurrentWeatherData();
        showFiveDaysWeatherData();


    }

    private void showFiveDaysWeatherData() {
        String hourlyJson = Serializer.readJsonFromAsset(this, AssetsFile.HOURLY_FORECAST_FILE.getFileName());
        List<FiveDayResponse> fiveDayResponses = Serializer.toListOfObject(hourlyJson, FiveDayResponse.class);
        Log.d(TAG, "showFiveDaysWeatherData: " + fiveDayResponses);
    }

    private void showCurrentWeatherData() {
        String currentWeatherFile =
                Serializer.readJsonFromAsset(this, AssetsFile.CURRENT_WEATHER_FILE.getFileName());
        CurrentWeatherResponse weatherItem = Serializer.toObject(currentWeatherFile, CurrentWeatherResponse.class);
        bind.activityMainContentMainLayout.setWeather(weatherItem);
        bind.activityMainToolbarLayout.setLocationName(weatherItem.getName() + ", " + weatherItem.getSys().getCountry());
    }

    private void initRecyclerView() {
        String jsonArray = Serializer.readJsonFromAsset(this, AssetsFile.HOURLY_FORECAST_FILE.getFileName());
        FiveDayResponse response = Serializer.toObject(jsonArray, FiveDayResponse.class);
        List<ItemHourly> list = response.getList();
        //TODO getting data from api and display it in the recyclerview
        GenericAdapter<ItemHourly> genericAdapter = new GenericAdapter<>(R.layout.list_item_weather_day);
        genericAdapter.setClickListener(this);
        genericAdapter.addList(list);

        bind.activityMainContentMainLayout.contentMainNextWeatherRecyclerView.setAdapter(genericAdapter);

    }

    private void initTextSwitchers() {
        TextSwitcher descTextSwitcher = bind.activityMainContentMainLayout.contentMainDescTextView;
        TextSwitcher humidityTextSwitcher = bind.activityMainContentMainLayout.contentMainHumidityTextView;
        TextSwitcher tempTextSwitcher = bind.activityMainContentMainLayout.contentMainTempTextView;
        TextSwitcher windTextSwitcher = bind.activityMainContentMainLayout.contentMainWindTextView;
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
        final SwipeRefreshLayout swipeLayout = bind.activityMainSwipeRefreshLayout;
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
        MaterialSearchView searchView = bind.activityMainToolbarLayout.activityMainSearchView;
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

    @Override
    public void onItemClicked(View view, int position) {
        Log.d(TAG, "onItemClicked: ");
        WeatherInfoBottomSheet bottomSheet = new WeatherInfoBottomSheet();
        bottomSheet.show(getSupportFragmentManager(), WeatherInfoBottomSheet.class.getName());

    }
}