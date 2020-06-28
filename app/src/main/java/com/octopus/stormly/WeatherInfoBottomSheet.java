package com.octopus.stormly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.octopus.stormly.model.fivedayweather.FiveDayResponse;
import com.octopus.stormly.model.fivedayweather.ItemHourly;

public class WeatherInfoBottomSheet extends BottomSheetDialogFragment {
    private ItemHourly itemHourly;

    public WeatherInfoBottomSheet(ItemHourly itemHourly) {
        this.itemHourly = itemHourly;
    }

    public WeatherInfoBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hourly_bottom_sheet_dialog,  container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
