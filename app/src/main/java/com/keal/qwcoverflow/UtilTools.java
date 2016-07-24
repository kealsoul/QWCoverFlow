package com.keal.qwcoverflow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Created by ${Keal} on 2015/11/11.
 */
public class UtilTools {
    public static void log(String string){
        Log.v("Xiang",string);
    }

    public static void toast(Context context,String string){
        Toast.makeText(context, string,Toast.LENGTH_SHORT).show();
    }

    public static Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
    }
}
