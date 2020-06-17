package com.octopus.stormly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextSwitcher;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.octopus.stormly.databinding.MainActivityBinding;
import com.octopus.stormly.utils.TextViewSwitcher;
import com.octopus.stormly.utils.WeatherLogger;

public class MainActivity extends AppCompatActivity {
    private MainActivityBinding mainActivityBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mainActivityBinding.activityMainToolbarLayout.appToolbar);
        initSearchView();
        initSwipeRefreshLayout();
        initTextSwitchers();

    }

    private void initTextSwitchers() {
        TextSwitcher descTextSwitcher = mainActivityBinding.activityMainContentMainLayout.contentMainDescTextView;
        TextSwitcher humidityTextSwitcher = mainActivityBinding.activityMainContentMainLayout.contentMainHumidityTextView;
        TextSwitcher tempTextSwitcher = mainActivityBinding.activityMainContentMainLayout.contentMainTempTextView;
        TextSwitcher windTextSwitcher = mainActivityBinding.activityMainContentMainLayout.contentMainWindTextView;
        setupTextSwitcher(descTextSwitcher, R.style.TempTextView);
        setupTextSwitcher(humidityTextSwitcher, R.style.TempTextView);
        setupTextSwitcher(tempTextSwitcher, R.style.TempTextView);
        setupTextSwitcher(windTextSwitcher, R.style.TempTextView);



    }

    private void setupTextSwitcher(TextSwitcher switcher, int style){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Vazir.ttf");
        switcher.setFactory(new TextViewSwitcher(this, style, true, typeface));
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