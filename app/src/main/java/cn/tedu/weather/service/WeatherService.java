package cn.tedu.weather.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;

import cn.tedu.weather.entity.Result;
import cn.tedu.weather.entity.WeatherInfo;
import cn.tedu.weather.entity.WeatherToday;
import cn.tedu.weather.util.Constant;
import cn.tedu.weather.util.HttpUtil;
import cn.tedu.weather.util.JSONParser;
import cn.tedu.weather.util.UrlFactory;

/**
 * Created by lienz on 2018/12/11.
 */

public class WeatherService extends Service{
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    //每次启动Service,onStartCommand方法都会执行,因为每次获取天气数据都需要发送网络请求,
    //所以将获取天气数据的方法写在onStartCommand方法中
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //发送网络请求,获取天气数据,显示到控件上(耗时操作)
       final String cityName=intent.getStringExtra(Constant.CITY_NAME);
        Thread t=new Thread(){
            public void run() {
                //获取天气信息
                try {
                    requestWeather(cityName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        return super.onStartCommand(intent, flags, startId);
    }

    //发送网络请求获取天气数据
    private void requestWeather(String cityName) throws Exception {
        String path= UrlFactory.getWeatherUrl(cityName);
        //获取服务端返回的输入流
        InputStream is= HttpUtil.getInputStream(path);
        //5.读取输入流中数据
        String data=HttpUtil.isToString(is);
        //把数据通过广播的形式发送给MainActivity
        Intent intent=new Intent();
        intent.setAction(Constant.UPDATE_WEATHER_DATA_COMPLETE);
        intent.putExtra(Constant.DATA,data);
        sendBroadcast(intent);
    }
}
