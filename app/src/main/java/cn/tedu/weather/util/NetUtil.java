package cn.tedu.weather.util;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by lienz on 2018/12/4.
 *判断网络的连接状态的工具类
 */

public class NetUtil {
    public static final int NETWORK_NONE=0;//没有网络
    public static final int NETWORK_MOBILE=1;//手机网络
    public static final int NETWORK_WIFI=2;//WIFI网络

    public static int getNetWorkState(Context context){
        //获取网络管理工具类
        ConnectivityManager connManager= (ConnectivityManager)
           context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取当前的网络状态
        NetworkInfo networkInfo=connManager.getActiveNetworkInfo();
        if(networkInfo==null){
            return NETWORK_NONE;
        }
        //获取当前手机的联网状态(wifi,流量)
        int networkInfoType=networkInfo.getType();
        if(networkInfoType==ConnectivityManager.TYPE_MOBILE){
            return NETWORK_MOBILE;
        }else if(networkInfoType==ConnectivityManager.TYPE_WIFI){
            return NETWORK_WIFI;
        }
        return NETWORK_NONE;
    }
}
