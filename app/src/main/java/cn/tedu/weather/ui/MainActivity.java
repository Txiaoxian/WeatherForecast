package cn.tedu.weather.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.tedu.weather.entity.WeatherFuture;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;


import cn.tedu.weather.R;
import cn.tedu.weather.entity.WeatherInfo;
import cn.tedu.weather.entity.WeatherToday;
import cn.tedu.weather.entity.Weather_id;
import cn.tedu.weather.service.WeatherService;
import cn.tedu.weather.util.Constant;
import cn.tedu.weather.util.JSONParser;
import cn.tedu.weather.util.NetUtil;
import cn.tedu.weather.util.ToastUtil;
import cn.tedu.weather.util.WeatherUtil;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int UPDATE_WEATHER =0;
    //声明控件
    ImageView location;

    ImageView ivUpdate;
    TextView tvCity;
    TextView tvTime;
    TextView tvHumidity;
    TextView tvTempInfo;

    TextView tvWeek;
    TextView tvTemperature;
    TextView tvClimate;
    TextView tvWind;
    ImageView ivWeather;

    TextView tvWeek_1;
    TextView tvTemperature_1;
    TextView tvClimate_1;
    TextView tvWind_1;
    ImageView ivWeather_1;

    TextView tvWeek_2;
    TextView tvTemperature_2;
    TextView tvClimate_2;
    TextView tvWind_2;
    ImageView ivWeather_2;

    TextView tvWeek_3;
    TextView tvTemperature_3;
    TextView tvClimate_3;
    TextView tvWind_3;
    ImageView ivWeather_3;

    TextView tvWeek_4;
    TextView tvTemperature_4;
    TextView tvClimate_4;
    TextView tvWind_4;
    ImageView ivWeather_4;

    TextView tvWeek_5;
    TextView tvTemperature_5;
    TextView tvClimate_5;
    TextView tvWind_5;
    ImageView ivWeather_5;

    TextView tvWeek_6;
    TextView tvTemperature_6;
    TextView tvClimate_6;
    TextView tvWind_6;
    ImageView ivWeather_6;
    //声明Handler对象
    Handler handler;
     Intent service;
     InnerBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        location = (ImageView) findViewById(R.id.iv_title_location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ChooseCityActivity.class);
               MainActivity.this.startActivity(intent);
            }
        });

        ivUpdate= (ImageView) findViewById(R.id.iv_title_update);
        tvCity= (TextView) findViewById(R.id.tv_city);
        tvTime= (TextView) findViewById(R.id.tv_time);
        tvHumidity= (TextView) findViewById(R.id.tv_humidity);
        tvTempInfo= (TextView) findViewById(R.id.tv_temp_info);
        tvWeek= (TextView) findViewById(R.id.tv_week);
        tvTemperature= (TextView) findViewById(R.id.tv_temperature);
        tvClimate= (TextView) findViewById(R.id.tv_climate);
        tvWind= (TextView) findViewById(R.id.tv_wind);
        ivWeather= (ImageView) findViewById(R.id.iv_weather);

        //未来第一天
        tvWeek_1= (TextView) findViewById(R.id.tv_week_1);
        tvTemperature_1= (TextView) findViewById(R.id.tv_temperature_1);
        tvClimate_1= (TextView) findViewById(R.id.tv_climate_1);
        tvWind_1= (TextView) findViewById(R.id.tv_wind_1);
        ivWeather_1= (ImageView) findViewById(R.id.iv_weather_1);

        //未来第二天
        tvWeek_2= (TextView) findViewById(R.id.tv_week_2);
        tvTemperature_2= (TextView) findViewById(R.id.tv_temperature_2);
        tvClimate_2= (TextView) findViewById(R.id.tv_climate_2);
        tvWind_2= (TextView) findViewById(R.id.tv_wind_2);
        ivWeather_2= (ImageView) findViewById(R.id.iv_weather_2);

        //未来第三天
        tvWeek_3= (TextView) findViewById(R.id.tv_week_3);
        tvTemperature_3= (TextView) findViewById(R.id.tv_temperature_3);
        tvClimate_3= (TextView) findViewById(R.id.tv_climate_3);
        tvWind_3= (TextView) findViewById(R.id.tv_wind_3);
        ivWeather_3= (ImageView) findViewById(R.id.iv_weather_3);

        //未来第四天
        tvWeek_4= (TextView) findViewById(R.id.tv_week_4);
        tvTemperature_4= (TextView) findViewById(R.id.tv_temperature_4);
        tvClimate_4= (TextView) findViewById(R.id.tv_climate_4);
        tvWind_4= (TextView) findViewById(R.id.tv_wind_4);
        ivWeather_4= (ImageView) findViewById(R.id.iv_weather_4);

        //未来第五天
        tvWeek_5= (TextView) findViewById(R.id.tv_week_5);
        tvTemperature_5= (TextView) findViewById(R.id.tv_temperature_5);
        tvClimate_5= (TextView) findViewById(R.id.tv_climate_5);
        tvWind_5 = (TextView) findViewById(R.id.tv_wind_5);
        ivWeather_5= (ImageView) findViewById(R.id.iv_weather_5);

        //未来第六天
        tvWeek_6= (TextView) findViewById(R.id.tv_week_6);
        tvTemperature_6= (TextView) findViewById(R.id.tv_temperature_6);
        tvClimate_6= (TextView) findViewById(R.id.tv_climate_6);
        tvWind_6= (TextView) findViewById(R.id.tv_wind_6);
        ivWeather_6= (ImageView) findViewById(R.id.iv_weather_6);





        //为ImageView设置监听器
        ivUpdate.setOnClickListener(this);
        //内部类方式动态注册广播
         receiver=new InnerBroadcastReceiver();
        //过滤器,表示当前的广播接受者要接受哪些广播
        IntentFilter filter=new IntentFilter();
        filter.addAction(Constant.UPDATE_WEATHER_DATA_COMPLETE);
        registerReceiver(receiver,filter);
    }



    class InnerBroadcastReceiver extends BroadcastReceiver{
        //接受到广播后,该方法会执行
        @Override
        public void onReceive(Context context, Intent intent) {//运行在主线程
                String data = intent.getStringExtra(Constant.DATA);
                System.out.println(data);

            try {
                JSONObject obj = new JSONObject(data);
                //获取当前天气信息
                WeatherInfo weatherInfo = JSONParser.parserWeatherInfo(obj);
                //获取今日天气信息
                WeatherToday weatherToday = JSONParser.parserWeatherToday(obj);


                tvTime.setText("今天"+weatherInfo.getTime()+"发布");
                System.out.println(weatherInfo.getTime());
                tvHumidity.setText("湿度:"+weatherInfo.getHumidity());
                tvTempInfo.setText("温度:"+weatherInfo.getTemp_info()+"℃");

                tvWeek.setText(weatherToday.getWeek());
                tvTemperature.setText(weatherToday.getTemperature());
                tvClimate.setText(weatherToday.getClimate());
                tvWind.setText(weatherToday.getWind());

                Weather_id id = weatherToday.getWeather_id();
                String fa = id.getFa();//00晴

                String weatherType = WeatherUtil.getWeatherType(fa);
                switch (weatherType){
                    case "晴":
                        ivWeather.setImageResource(R.mipmap.weather_qing);
                        break;
                    case "多云":
                        ivWeather.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "阴":
                        ivWeather.setImageResource(R.mipmap.weather_yin);
                        break;
                    case "阵雨":
                        ivWeather.setImageResource(R.mipmap.weather_zhenyu);
                        break;
                    case "雷阵雨":
                        ivWeather.setImageResource(R.mipmap.weather_leizhenyu);
                        break;
                    case "雷阵雨伴有冰雹":
                        ivWeather.setImageResource(R.mipmap.weather_leizhenyubingbao);
                        break;
                    case "雨夹雪":
                        ivWeather.setImageResource(R.mipmap.weather_yujiaxue);
                        break;
                    case "小雨":
                        ivWeather.setImageResource(R.mipmap.weather_xiaoyu);
                        break;
                    case "中雨":
                        ivWeather.setImageResource(R.mipmap.weather_zhongyu);
                        break;
                    case "大雨":
                        ivWeather.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "暴雨":
                        ivWeather.setImageResource(R.mipmap.weather_baoyu);
                        break;
                    case "大暴雨":
                        ivWeather.setImageResource(R.mipmap.weather_dabaoyu);
                        break;
                    case "特大暴雨":
                        ivWeather.setImageResource(R.mipmap.weather_tedabaoyu);
                        break;
                    case "阵雪":
                        ivWeather.setImageResource(R.mipmap.weather_zhenxue);
                        break;
                    case "小雪":
                        ivWeather.setImageResource(R.mipmap.weather_xiaoxue);
                        break;
                    case "中雪":
                        ivWeather.setImageResource(R.mipmap.weather_zhongxue);
                        break;
                    case "大雪":
                        ivWeather.setImageResource(R.mipmap.weather_daxue);
                        break;
                    case "暴雪":
                        ivWeather.setImageResource(R.mipmap.weather_baoxue);
                        break;
                }

                //未来第一天
                WeatherFuture weatherFuture_1 = JSONParser.parserWeatherFuture(obj,1);
                tvWeek_1.setText(weatherFuture_1.getWeek());
                tvTemperature_1.setText(weatherFuture_1 .getTemperature());
                tvClimate_1.setText(weatherFuture_1 .getClimate());
                tvWind_1.setText(weatherFuture_1 .getWind());

                Weather_id id_1 = weatherFuture_1 .getWeather_id();
                String fa_1 = id_1.getFa();//00晴
                String weatherType_1 = WeatherUtil.getWeatherType(fa_1);
                switch (weatherType_1){
                    case "晴":
                        ivWeather_1.setImageResource(R.mipmap.weather_qing);
                        break;
                    case "多云":
                        ivWeather_1.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "阴":
                        ivWeather_1.setImageResource(R.mipmap.weather_yin);
                        break;
                    case "阵雨":
                        ivWeather_1.setImageResource(R.mipmap.weather_zhenyu);
                        break;
                    case "雷阵雨":
                        ivWeather_1.setImageResource(R.mipmap.weather_leizhenyu);
                        break;
                    case "雷阵雨伴有冰雹":
                        ivWeather_1.setImageResource(R.mipmap.weather_leizhenyubingbao);
                        break;
                    case "雨夹雪":
                        ivWeather_1.setImageResource(R.mipmap.weather_yujiaxue);
                        break;
                    case "小雨":
                        ivWeather_1.setImageResource(R.mipmap.weather_xiaoyu);
                        break;
                    case "中雨":
                        ivWeather_1.setImageResource(R.mipmap.weather_zhongyu);
                        break;
                    case "大雨":
                        ivWeather_1.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "暴雨":
                        ivWeather_1.setImageResource(R.mipmap.weather_baoyu);
                        break;
                    case "大暴雨":
                        ivWeather_1.setImageResource(R.mipmap.weather_dabaoyu);
                        break;
                    case "特大暴雨":
                        ivWeather_1.setImageResource(R.mipmap.weather_tedabaoyu);
                        break;
                    case "阵雪":
                        ivWeather_1.setImageResource(R.mipmap.weather_zhenxue);
                        break;
                    case "小雪":
                        ivWeather_1.setImageResource(R.mipmap.weather_xiaoxue);
                        break;
                    case "中雪":
                        ivWeather_1.setImageResource(R.mipmap.weather_zhongxue);
                        break;
                    case "大雪":
                        ivWeather_1.setImageResource(R.mipmap.weather_daxue);
                        break;
                    case "暴雪":
                        ivWeather_1.setImageResource(R.mipmap.weather_baoxue);
                        break;
                }

                //未来第二天
                WeatherFuture weatherFuture_2 = JSONParser.parserWeatherFuture(obj,2);
                tvWeek_2.setText(weatherFuture_2.getWeek());
                tvTemperature_2.setText(weatherFuture_2 .getTemperature());
                tvClimate_2.setText(weatherFuture_2 .getClimate());
                tvWind_2.setText(weatherFuture_2 .getWind());

                Weather_id id_2 = weatherFuture_2 .getWeather_id();
                String fa_2 = id_2.getFa();//00晴

                String weatherType_2 = WeatherUtil.getWeatherType(fa_2);
                switch (weatherType_2){
                    case "晴":
                        ivWeather_2.setImageResource(R.mipmap.weather_qing);
                        break;
                    case "多云":
                        ivWeather_2.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "阴":
                        ivWeather_2.setImageResource(R.mipmap.weather_yin);
                        break;
                    case "阵雨":
                        ivWeather_2.setImageResource(R.mipmap.weather_zhenyu);
                        break;
                    case "雷阵雨":
                        ivWeather_2.setImageResource(R.mipmap.weather_leizhenyu);
                        break;
                    case "雷阵雨伴有冰雹":
                        ivWeather_2.setImageResource(R.mipmap.weather_leizhenyubingbao);
                        break;
                    case "雨夹雪":
                        ivWeather_2.setImageResource(R.mipmap.weather_yujiaxue);
                        break;
                    case "小雨":
                        ivWeather_2.setImageResource(R.mipmap.weather_xiaoyu);
                        break;
                    case "中雨":
                        ivWeather_2.setImageResource(R.mipmap.weather_zhongyu);
                        break;
                    case "大雨":
                        ivWeather_2.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "暴雨":
                        ivWeather_2.setImageResource(R.mipmap.weather_baoyu);
                        break;
                    case "大暴雨":
                        ivWeather_2.setImageResource(R.mipmap.weather_dabaoyu);
                        break;
                    case "特大暴雨":
                        ivWeather_2.setImageResource(R.mipmap.weather_tedabaoyu);
                        break;
                    case "阵雪":
                        ivWeather_2.setImageResource(R.mipmap.weather_zhenxue);
                        break;
                    case "小雪":
                        ivWeather_2.setImageResource(R.mipmap.weather_xiaoxue);
                        break;
                    case "中雪":
                        ivWeather_2.setImageResource(R.mipmap.weather_zhongxue);
                        break;
                    case "大雪":
                        ivWeather_2.setImageResource(R.mipmap.weather_daxue);
                        break;
                    case "暴雪":
                        ivWeather_2.setImageResource(R.mipmap.weather_baoxue);
                        break;
                }
                //未来第三天
                WeatherFuture weatherFuture_3 = JSONParser.parserWeatherFuture(obj,3);
                tvWeek_3.setText(weatherFuture_3.getWeek());
                tvTemperature_3.setText(weatherFuture_3 .getTemperature());
                tvClimate_3.setText(weatherFuture_3 .getClimate());
                tvWind_3.setText(weatherFuture_3 .getWind());

                Weather_id id_3 = weatherFuture_3 .getWeather_id();
                String fa_3 = id_3.getFa();//00晴
                String weatherType_3 = WeatherUtil.getWeatherType(fa_3);
                switch (weatherType_3){
                    case "晴":
                        ivWeather_3.setImageResource(R.mipmap.weather_qing);
                        break;
                    case "多云":
                        ivWeather_3.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "阴":
                        ivWeather_3.setImageResource(R.mipmap.weather_yin);
                        break;
                    case "阵雨":
                        ivWeather_3.setImageResource(R.mipmap.weather_zhenyu);
                        break;
                    case "雷阵雨":
                        ivWeather_3.setImageResource(R.mipmap.weather_leizhenyu);
                        break;
                    case "雷阵雨伴有冰雹":
                        ivWeather_3.setImageResource(R.mipmap.weather_leizhenyubingbao);
                        break;
                    case "雨夹雪":
                        ivWeather_3.setImageResource(R.mipmap.weather_yujiaxue);
                        break;
                    case "小雨":
                        ivWeather_3.setImageResource(R.mipmap.weather_xiaoyu);
                        break;
                    case "中雨":
                        ivWeather_3.setImageResource(R.mipmap.weather_zhongyu);
                        break;
                    case "大雨":
                        ivWeather_3.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "暴雨":
                        ivWeather_3.setImageResource(R.mipmap.weather_baoyu);
                        break;
                    case "大暴雨":
                        ivWeather_3.setImageResource(R.mipmap.weather_dabaoyu);
                        break;
                    case "特大暴雨":
                        ivWeather_3.setImageResource(R.mipmap.weather_tedabaoyu);
                        break;
                    case "阵雪":
                        ivWeather_3.setImageResource(R.mipmap.weather_zhenxue);
                        break;
                    case "小雪":
                        ivWeather_3.setImageResource(R.mipmap.weather_xiaoxue);
                        break;
                    case "中雪":
                        ivWeather_3.setImageResource(R.mipmap.weather_zhongxue);
                        break;
                    case "大雪":
                        ivWeather_3.setImageResource(R.mipmap.weather_daxue);
                        break;
                    case "暴雪":
                        ivWeather_3.setImageResource(R.mipmap.weather_baoxue);
                        break;
                }

                //未来第四天
                WeatherFuture weatherFuture_4 = JSONParser.parserWeatherFuture(obj,4);
                tvWeek_4.setText(weatherFuture_4.getWeek());
                tvTemperature_4.setText(weatherFuture_4 .getTemperature());
                tvClimate_4.setText(weatherFuture_4 .getClimate());
                tvWind_4.setText(weatherFuture_4 .getWind());

                Weather_id id_4 = weatherFuture_4 .getWeather_id();
                String fa_4 = id_4.getFa();//00晴
                String weatherType_4 = WeatherUtil.getWeatherType(fa_4);
                switch (weatherType_4){
                    case "晴":
                        ivWeather_4.setImageResource(R.mipmap.weather_qing);
                        break;
                    case "多云":
                        ivWeather_4.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "阴":
                        ivWeather_4.setImageResource(R.mipmap.weather_yin);
                        break;
                    case "阵雨":
                        ivWeather_4.setImageResource(R.mipmap.weather_zhenyu);
                        break;
                    case "雷阵雨":
                        ivWeather_4.setImageResource(R.mipmap.weather_leizhenyu);
                        break;
                    case "雷阵雨伴有冰雹":
                        ivWeather_4.setImageResource(R.mipmap.weather_leizhenyubingbao);
                        break;
                    case "雨夹雪":
                        ivWeather_4.setImageResource(R.mipmap.weather_yujiaxue);
                        break;
                    case "小雨":
                        ivWeather_4.setImageResource(R.mipmap.weather_xiaoyu);
                        break;
                    case "中雨":
                        ivWeather_4.setImageResource(R.mipmap.weather_zhongyu);
                        break;
                    case "大雨":
                        ivWeather_4.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "暴雨":
                        ivWeather_4.setImageResource(R.mipmap.weather_baoyu);
                        break;
                    case "大暴雨":
                        ivWeather_4.setImageResource(R.mipmap.weather_dabaoyu);
                        break;
                    case "特大暴雨":
                        ivWeather_4.setImageResource(R.mipmap.weather_tedabaoyu);
                        break;
                    case "阵雪":
                        ivWeather_4.setImageResource(R.mipmap.weather_zhenxue);
                        break;
                    case "小雪":
                        ivWeather_4.setImageResource(R.mipmap.weather_xiaoxue);
                        break;
                    case "中雪":
                        ivWeather_4.setImageResource(R.mipmap.weather_zhongxue);
                        break;
                    case "大雪":
                        ivWeather_4.setImageResource(R.mipmap.weather_daxue);
                        break;
                    case "暴雪":
                        ivWeather_4.setImageResource(R.mipmap.weather_baoxue);
                        break;
                }

                //未来第五天
                WeatherFuture weatherFuture_5 = JSONParser.parserWeatherFuture(obj,5);
                tvWeek_5.setText(weatherFuture_5.getWeek());
                tvTemperature_5.setText(weatherFuture_5 .getTemperature());
                tvClimate_5.setText(weatherFuture_5 .getClimate());
                tvWind_5.setText(weatherFuture_5 .getWind());

                Weather_id id_5 = weatherFuture_5 .getWeather_id();
                String fa_5 = id_5.getFa();//00晴
                String weatherType_5 = WeatherUtil.getWeatherType(fa_5);
                switch (weatherType_5){
                    case "晴":
                        ivWeather_5.setImageResource(R.mipmap.weather_qing);
                        break;
                    case "多云":
                        ivWeather_5.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "阴":
                        ivWeather_5.setImageResource(R.mipmap.weather_yin);
                        break;
                    case "阵雨":
                        ivWeather_5.setImageResource(R.mipmap.weather_zhenyu);
                        break;
                    case "雷阵雨":
                        ivWeather_5.setImageResource(R.mipmap.weather_leizhenyu);
                        break;
                    case "雷阵雨伴有冰雹":
                        ivWeather_5.setImageResource(R.mipmap.weather_leizhenyubingbao);
                        break;
                    case "雨夹雪":
                        ivWeather_5.setImageResource(R.mipmap.weather_yujiaxue);
                        break;
                    case "小雨":
                        ivWeather_5.setImageResource(R.mipmap.weather_xiaoyu);
                        break;
                    case "中雨":
                        ivWeather_5.setImageResource(R.mipmap.weather_zhongyu);
                        break;
                    case "大雨":
                        ivWeather_5.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "暴雨":
                        ivWeather_5.setImageResource(R.mipmap.weather_baoyu);
                        break;
                    case "大暴雨":
                        ivWeather_5.setImageResource(R.mipmap.weather_dabaoyu);
                        break;
                    case "特大暴雨":
                        ivWeather_5.setImageResource(R.mipmap.weather_tedabaoyu);
                        break;
                    case "阵雪":
                        ivWeather_5.setImageResource(R.mipmap.weather_zhenxue);
                        break;
                    case "小雪":
                        ivWeather_5.setImageResource(R.mipmap.weather_xiaoxue);
                        break;
                    case "中雪":
                        ivWeather_5.setImageResource(R.mipmap.weather_zhongxue);
                        break;
                    case "大雪":
                        ivWeather_5.setImageResource(R.mipmap.weather_daxue);
                        break;
                    case "暴雪":
                        ivWeather_5.setImageResource(R.mipmap.weather_baoxue);
                        break;
                }

                //未来第六天
                WeatherFuture weatherFuture_6 = JSONParser.parserWeatherFuture(obj,6);
                tvWeek_6.setText(weatherFuture_6.getWeek());
                tvTemperature_6.setText(weatherFuture_6 .getTemperature());
                tvClimate_6.setText(weatherFuture_6 .getClimate());
                tvWind_6.setText(weatherFuture_6 .getWind());

                Weather_id id_6 = weatherFuture_6 .getWeather_id();
                String fa_6 = id_6.getFa();//00晴
                String weatherType_6 = WeatherUtil.getWeatherType(fa_6);
                switch (weatherType_6){
                    case "晴":
                        ivWeather_6.setImageResource(R.mipmap.weather_qing);
                        break;
                    case "多云":
                        ivWeather_6.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "阴":
                        ivWeather_6.setImageResource(R.mipmap.weather_yin);
                        break;
                    case "阵雨":
                        ivWeather_6.setImageResource(R.mipmap.weather_zhenyu);
                        break;
                    case "雷阵雨":
                        ivWeather_6.setImageResource(R.mipmap.weather_leizhenyu);
                        break;
                    case "雷阵雨伴有冰雹":
                        ivWeather_6.setImageResource(R.mipmap.weather_leizhenyubingbao);
                        break;
                    case "雨夹雪":
                        ivWeather_6.setImageResource(R.mipmap.weather_yujiaxue);
                        break;
                    case "小雨":
                        ivWeather_6.setImageResource(R.mipmap.weather_xiaoyu);
                        break;
                    case "中雨":
                        ivWeather_6.setImageResource(R.mipmap.weather_zhongyu);
                        break;
                    case "大雨":
                        ivWeather_6.setImageResource(R.mipmap.weather_dayu);
                        break;
                    case "暴雨":
                        ivWeather_6.setImageResource(R.mipmap.weather_baoyu);
                        break;
                    case "大暴雨":
                        ivWeather_6.setImageResource(R.mipmap.weather_dabaoyu);
                        break;
                    case "特大暴雨":
                        ivWeather_6.setImageResource(R.mipmap.weather_tedabaoyu);
                        break;
                    case "阵雪":
                        ivWeather_6.setImageResource(R.mipmap.weather_zhenxue);
                        break;
                    case "小雪":
                        ivWeather_6.setImageResource(R.mipmap.weather_xiaoxue);
                        break;
                    case "中雪":
                        ivWeather_6.setImageResource(R.mipmap.weather_zhongxue);
                        break;
                    case "大雪":
                        ivWeather_6.setImageResource(R.mipmap.weather_daxue);
                        break;
                    case "暴雪":
                        ivWeather_6.setImageResource(R.mipmap.weather_baoxue);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }



    @Override
    public void onClick(View view) {
        //判断网络状态
        int netWorkState=NetUtil.getNetWorkState(this);
        switch (netWorkState){
            case NetUtil.NETWORK_NONE:
                ToastUtil.showToast(this,"没有网络");
                break;
            case NetUtil.NETWORK_MOBILE:
            case NetUtil.NETWORK_WIFI:
                //启动Service，在Serivce中加载数据
                 service=
                        new Intent(this, WeatherService.class);
                service.putExtra(Constant.CITY_NAME,tvCity.getText().toString());
                startService(service);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        if(service!=null){
            stopService(service);
        }
        if(receiver!=null){
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }
//点击back按钮,执行该方法
    @Override
    public void onBackPressed() {
        //回退建该home键盘
       moveTaskToBack(true);
    }
}
