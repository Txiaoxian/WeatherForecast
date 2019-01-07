package cn.tedu.weather.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lienz on 2018/12/10.
 * 发送http请求的工具类
 */

public class HttpUtil {
    //Get请求获取服务端返回的输入流
    public static InputStream
    getInputStream(String path)throws Exception{
        //1.创建Url对象
        URL url=new URL(path);
        //2.通过URL对象获取HttpURLConnection
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        //3.设置请求方式(get/post)
        conn.setRequestMethod("GET");
        //4.发送请求,使用输入流获取服务端返回的数据
        InputStream is =conn.getInputStream();
        return is;
    }
    //读取流中的数据,将输入流转换成字符串
    public static String isToString(InputStream is) throws Exception{
        BufferedReader br=
                new BufferedReader(new InputStreamReader(is));//缓冲字符输入流,一次可以读取一行
        String line=null;
        StringBuilder sb=new StringBuilder();
        while ((line=br.readLine())!=null){
            sb.append(line);
        }
        String data=sb.toString();//json数据
        return data;
    }
}
