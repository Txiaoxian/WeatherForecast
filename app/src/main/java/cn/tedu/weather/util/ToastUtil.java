package cn.tedu.weather.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lienz on 2018/12/4.
 */

public class ToastUtil {
    private static Toast toast=null;
    public static void showToast(Context context,String text){
        if(toast==null){
            toast=Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }else{
            toast.setText(text);
        }
        toast.show();
    }
}
// TostUtil.showToast(this,"没有网络");