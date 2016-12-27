package com.example.whodm.myjsbridge;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by whodm on 2016/12/5.
 */

public class BridgeWebChromeClient extends WebChromeClient {
    private BridgeWebView webView;

    public BridgeWebChromeClient(BridgeWebView webView) {
        this.webView = webView;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }
    
}
