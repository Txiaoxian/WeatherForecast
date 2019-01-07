package cn.tedu.weather.util;

import cn.tedu.weather.entity.WeatherFuture;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.tedu.weather.entity.WeatherInfo;
import cn.tedu.weather.entity.WeatherToday;
import cn.tedu.weather.entity.Weather_id;

/**
 * Created by lienz on 2018/12/10.
 */

public class JSONParser {
    public static WeatherInfo parserWeatherInfo(JSONObject obj) throws Exception{
        JSONObject result=obj.getJSONObject("result");
        //当前天气信息
        JSONObject sk=result.getJSONObject("sk");
        String temp_info=sk.getString("temp");//当前温度
        String humidity=sk.getString("humidity");//当前湿度
        String time=sk.getString("time");//发布时间
        //将数据封装到当前天气信息类中
        WeatherInfo weatherInfo=new WeatherInfo();
        weatherInfo.setTemp_info(temp_info);
        weatherInfo.setHumidity(humidity);
        weatherInfo.setTime(time);
        return weatherInfo;
    }
    public static WeatherToday parserWeatherToday(JSONObject obj)throws Exception{
        JSONObject result=obj.getJSONObject("result");
        JSONObject today=result.getJSONObject("today");
        String temperature =today.getString("temperature");//今日天气温度范围
        String climate=today.getString("weather");//天气概况(中雨转小雨)
        JSONObject weather_id=today.getJSONObject("weather_id");
        String fa=weather_id.getString("fa");
        String fb=weather_id.getString("fb");
        String wind=today.getString("wind");
        String week=today.getString("week");

        WeatherToday weatherToday=new WeatherToday();
        weatherToday.setTemperature(temperature);
        weatherToday.setClimate(climate);
        Weather_id id=new Weather_id();
        id.setFa(fa);
        id.setFb(fb);
        weatherToday.setWeather_id(id);
        weatherToday.setWind(wind);
        weatherToday.setWeek(week);
        return weatherToday;
    }

  public static WeatherFuture parserWeatherFuture(JSONObject obj,Integer a)throws Exception{
        JSONObject result=obj.getJSONObject("result");
        JSONArray future = result.getJSONArray("future");
        JSONObject future_0 = (JSONObject) future.get(a);

        String temperature =future_0.getString("temperature");//今日天气温度范围
        String climate=future_0.getString("weather");//天气概况(中雨转小雨)
        JSONObject weather_id=future_0.getJSONObject("weather_id");
        String fa=weather_id.getString("fa");
        String fb=weather_id.getString("fb");
        String wind=future_0.getString("wind");
        String week=future_0.getString("week");

        WeatherFuture weatherFuture= new WeatherFuture();
        weatherFuture .setTemperature(temperature);
        weatherFuture .setClimate(climate);
        Weather_id id=new Weather_id();
        id.setFa(fa);
        id.setFb(fb);
        weatherFuture .setWeather_id(id);
        weatherFuture .setWind(wind);
        weatherFuture .setWeek(week);
        return weatherFuture ;
    }
}
