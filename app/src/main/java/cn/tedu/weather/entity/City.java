package cn.tedu.weather.entity;

/**
 * Created by lienz on 2018/12/10.
 */

public class City extends Object {
    private String id;
    private String cityName;
    private String pid;

    public City() {
    }

    public City(String id, String cityName, String pid) {
        this.id = id;
        this.cityName = cityName;
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
