package com.example.whodm.myjsbridge;

import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URLDecoder;

/**
 * Created by whodm on 2016/12/5.
 */

public class BridgeWebClient extends WebViewClient {
    private String TAG = "BridgeWebClient";
    private BridgeWebView webView;

    public BridgeWebClient(BridgeWebView webView) {
        this.webView = webView;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        try{
            url = URLDecoder.decode(url,"utf-8");
            Log.d(TAG, "shouldOverrideUrlLoading: "+url);
        }catch (Exception e){
            e.printStackTrace();
        }
        //判断逻辑
        if (url.startsWith(Util.JSCONTROL)){
            webView.handleMessage(url);
            return true;
        }else {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (Util.JSLOCAL != null){
            Util.webViewLoadLocalJs(view,Util.JSLOCAL);
        }
    }

}
