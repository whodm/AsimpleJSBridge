package com.example.whodm.myjsbridge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private BridgeWebView webView;
    private TextView tv;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (BridgeWebView)findViewById(R.id.webview);
        tv = (TextView)findViewById(R.id.tv);
        webView.registerHandler("kissme", new BridgeCallback() {
            @Override
            public void callBack(String data) {
                Log.d(TAG+"first",data);
                tv.setText(data);
            }
        });
        webView.loadUrl("file:///android_asset/demo.html");
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.send("kissyou","come on");
            }
        });
    }
}
