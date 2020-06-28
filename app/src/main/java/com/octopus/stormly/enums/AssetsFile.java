package com.octopus.stormly.enums;

public enum AssetsFile {
    CURRENT_WEATHER_FILE("current_weather.json"),
    HOURLY_FORECAST_FILE("hourly_forecast.json");

    private String fileName;

    AssetsFile(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
