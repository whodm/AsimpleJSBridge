package com.example.whodm.myjsbridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.HashMap;

/**
 * Created by whodm on 2016/12/5.
 */

@SuppressLint("SetJavaScriptEnabled")
public class BridgeWebView extends WebView {

    private String TAG = "BridgeWebView";
    private HashMap<String,BridgeCallback> bridgeMap = new HashMap<>();

    public BridgeWebView(Context context) {
        super(context);
        init();
    }

    public BridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BridgeWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.setWebViewClient(getBridgeWebClient());
    }

    private BridgeWebClient getBridgeWebClient(){
        return new BridgeWebClient(this);
    }


    protected void handleMessage(String url){
        String temp = url.replace(Util.JSCONTROL,"");
        Log.d(TAG, "handleMessage: "+temp);
        String function[] = temp.split(Util.OFFLINE);
        for (int i = 0;i< function.length;i++){
            Log.d("handleMessage" + i, "handleMessage: "+function[i]);
        }
        BridgeCallback f = bridgeMap.get(function[0]);
        Log.d(TAG, "handleMessage: "+Boolean.toString(f==null));
        if (f!=null&&function.length > 1){
            f.callBack(function[1]);
        }
    }

    public void registerHandler(String name,BridgeCallback bridgeCallback){
        bridgeMap.put(name,bridgeCallback);
    }

    public void clearAllData(){
        bridgeMap.clear();
    }

    public void unregisterHandler(String name){
        bridgeMap.remove(name);
    }

    public void send(String handler,String data){
        this.loadUrl(String.format("javascript:webBridge.handleMessage('%s','%s')",handler,data));
    }


}
