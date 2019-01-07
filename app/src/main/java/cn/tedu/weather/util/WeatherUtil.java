package cn.tedu.weather.util;

import java.util.HashMap;

/**
 * Created by lienz on 2018/12/10.
 */

public class WeatherUtil {
    public static String getWeatherType(String fa){
        HashMap<String, String>maps=new HashMap<String, String>();
        maps.put("00","晴");
        maps.put("01","多云");
        maps.put("02","阴");
        maps.put("03","阵雨");
        maps.put("04","雷阵雨");
        maps.put("05","雷阵雨伴有冰雹");
        maps.put("06","雨夹雪");
        maps.put("07","小雨");
        maps.put("08","中雨");
        maps.put("09","大雨");
        maps.put("10","暴雨" );
        maps.put("11","大暴雨");
        maps.put("12","特大暴雨");
        maps.put("13","阵雪");
        maps.put("14","小雪");
        maps.put("15","中雪");
        maps.put("16","大雪");
        maps.put("17","暴雪");
        maps.put("18","雾"  );
        maps.put("19","冻雨");
        maps.put("20","沙尘暴");
        maps.put("21","小雨-中雨");
        maps.put("22","中雨-大雨");
        maps.put("23","大雨-暴雨");
        maps.put("24","暴雨-大暴雨");
        maps.put("25","大暴雨-特大暴雨");
        maps.put("26","小雪-中雪");
        maps.put("27","中雪-大雪");
        maps.put("28","大雪-暴雪");
        maps.put("29","浮尘");
        maps.put("30","扬沙");
        maps.put("31","强沙尘暴");
        maps.put("53","霾");
        return maps.get(fa);
    }
}
