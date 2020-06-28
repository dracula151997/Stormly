package com.octopus.stormly.model.fivedayweather;

import com.google.gson.annotations.SerializedName;
import com.octopus.stormly.adapter.AbstractListItem;
import com.octopus.stormly.model.common.Clouds;
import com.octopus.stormly.model.common.WeatherItem;
import com.octopus.stormly.model.common.Wind;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ItemHourly extends AbstractListItem {

  @SerializedName("dt")
  private int dt;

  @SerializedName("dt_txt")
  private String dtTxt;

  @SerializedName("weather")
  private List<WeatherItem> weather;

  @SerializedName("main")
  private Main main;

  @SerializedName("clouds")
  private Clouds clouds;

  @SerializedName("sys")
  private Sys sys;

  @SerializedName("wind")
  private Wind wind;

  @SerializedName("rain")
  private Rain rain;

  public int getDt() {
    return dt;
  }

  public void setDt(int dt) {
    this.dt = dt;
  }

  public String getDtTxt() {
    return dtTxt;
  }

  public void setDtTxt(String dtTxt) {
    this.dtTxt = dtTxt;
  }

  public List<WeatherItem> getWeather() {
    return weather;
  }

  public void setWeather(List<WeatherItem> weather) {
    this.weather = weather;
  }

  public Main getMain() {
    return main;
  }

  public void setMain(Main main) {
    this.main = main;
  }

  public Clouds getClouds() {
    return clouds;
  }

  public void setClouds(Clouds clouds) {
    this.clouds = clouds;
  }

  public Sys getSys() {
    return sys;
  }

  public void setSys(Sys sys) {
    this.sys = sys;
  }

  public Wind getWind() {
    return wind;
  }

  public void setWind(Wind wind) {
    this.wind = wind;
  }

  public Rain getRain() {
    return rain;
  }

  public void setRain(Rain rain) {
    this.rain = rain;
  }

  public String getDay(){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
    return simpleDateFormat.format(dt);
  }

  public String getTime(){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    return simpleDateFormat.format(dt);
  }
}