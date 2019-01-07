package cn.tedu.weather.entity;

/**
 * Created by lienz on 2018/12/10.
 */

public class Result {
    private WeatherInfo weatherInfo;
    private WeatherToday weatherToday;

    public Result() {
    }

    public Result(WeatherInfo weatherInfo, WeatherToday weatherToday) {
        this.weatherInfo = weatherInfo;
        this.weatherToday = weatherToday;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public WeatherToday getWeatherToday() {
        return weatherToday;
    }

    public void setWeatherToday(WeatherToday weatherToday) {
        this.weatherToday = weatherToday;
    }

    @Override
    public String toString() {
        return "Result{" +
                "weatherInfo=" + weatherInfo +
                ", weatherToday=" + weatherToday +
                '}';
    }
}
