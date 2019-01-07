package cn.tedu.weather.entity;

public class WeatherFuture {

    private String temperature;//今日天气温度范围
    private String climate;//今日天气情况
    private String wind;//今日风力
    private String week;//星期
    private Weather_id weather_id;
    public WeatherFuture(){

    }
    public WeatherFuture(String temperature, String climate, String wind, String week, Weather_id weather_id) {
        this.temperature = temperature;
        this.climate = climate;
        this.wind = wind;
        this.week = week;
        this.weather_id = weather_id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Weather_id getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(Weather_id weather_id) {
        this.weather_id = weather_id;
    }

    @Override
    public String toString() {
        return "WeatherToday{" +
                "temperature='" + temperature + '\'' +
                ", climate='" + climate + '\'' +
                ", wind='" + wind + '\'' +
                ", week='" + week + '\'' +
                ", weather_id=" + weather_id +
                '}';
    }
}
