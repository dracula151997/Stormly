package com.octopus.stormly.adapter;

public class NextWeatherDay extends AbstractListItem {
    private String temp;
    private String day;
    private String minTemp;
    private String maxTemp;
    private int icon;

    public NextWeatherDay() {
    }

    public NextWeatherDay(String temp, String day, String minTemp, String maxTemp, int icon) {
        this.temp = temp;
        this.day = day;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.icon = icon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
