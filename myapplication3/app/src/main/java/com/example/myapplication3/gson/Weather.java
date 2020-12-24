package com.example.myapplication3.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
    public String status;  //返回是否成功返回值
    public Basic basic;
    public Forecas forecast;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecas> forecastList;  //解析出数组
}
