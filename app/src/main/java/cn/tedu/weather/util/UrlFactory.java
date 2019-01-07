package cn.tedu.weather.util;

import java.net.URLEncoder;

/**
 * Created by lienz on 2018/12/10.
 * 封装所有请求的url
 */

public class UrlFactory {
    public static String getWeatherUrl(String cityName)throws Exception{
        //将字符串转换成16进制utf-8编码
        cityName= URLEncoder.encode(cityName,"utf-8");
        String path="http://v.juhe.cn/weather/index?format=2&cityname="+cityName+"&key=f7c36c0283d39546567cf0f264eec7ee";
        return path;
    }
}
