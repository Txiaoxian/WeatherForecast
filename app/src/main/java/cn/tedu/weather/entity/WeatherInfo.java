package cn.tedu.weather.entity;

/**
 * Created by lienz on 2018/12/6.
 */

public class WeatherInfo {
    String temp_info;//当前温度
    String humidity;//当前湿度
    String time;//发布时间
    public WeatherInfo(){

    }
    public WeatherInfo(String temp_info, String humidity, String time) {
        this.temp_info = temp_info;
        this.humidity = humidity;
        this.time = time;
    }

    public String getTemp_info() {
        return temp_info;
    }

    public void setTemp_info(String temp_info) {
        this.temp_info = temp_info;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "temp_info='" + temp_info + '\'' +
                ", humidity='" + humidity + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
