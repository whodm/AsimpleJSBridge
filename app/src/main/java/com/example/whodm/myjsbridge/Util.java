package com.example.whodm.myjsbridge;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by whodm on 2016/12/5.
 */

public class Util {
    public static String JSCONTROL = "control://";
    public static String OFFLINE = "/";
    public static String JSLOCAL = "webBridge.js";
    public static int getBuildVersion(){
        return Build.VERSION.SDK_INT;
    }

    public static void webViewLoadLocalJs(WebView view, String path){
        String jsContent = assetFile2Str(view.getContext(), path);
        Log.d("webViewLoadLocalJs",view.getContext()+path);
        view.loadUrl("javascript:" + jsContent);
    }

    private static String assetFile2Str(Context c, String urlStr){
        InputStream in = null;
        try{
            in = c.getAssets().open(urlStr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuilder sb = new StringBuilder();
            do {
                line = bufferedReader.readLine();
                if (line != null && !line.matches("^\\s*\\/\\/.*")) {
                    sb.append(line);
                }
            } while (line != null);

            bufferedReader.close();
            in.close();

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }
}
