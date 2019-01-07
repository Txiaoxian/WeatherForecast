package cn.tedu.weather.entity;

/**
 * Created by lienz on 2018/12/6.
 */

public class Weather_id {
    private String fa;
    private String fb;
    public Weather_id(){

    }
    public Weather_id(String fa, String fb) {
        this.fa = fa;
        this.fb = fb;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    @Override
    public String toString() {
        return "Weather_id{" +
                "fa='" + fa + '\'' +
                ", fb='" + fb + '\'' +
                '}';
    }
}
