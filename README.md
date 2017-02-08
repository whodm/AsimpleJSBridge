# AsimpleJSBridge
a simple safe and easy JsBridge

use it on your layout

    <com.example.whodm.myjsbridge.BridgeWebView
        android:id="@+id/webview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

find your webview as usual

webView = (BridgeWebView)findViewById(R.id.webview);

load as usual

webView.loadUrl("your site");

register your Bridge

        webView.registerHandler("you name it", new BridgeCallback() {
            @Override
            public void callBack(String data) {
                
            }
        });
        
use this in your H5 page to send msg

                window.webBridge.bridgeSend("you name it","your data");
        
        
register you handler in you h5;

        window.webBridge.registerHandler('you name it',function(data,handlerName){

    });

send msg to your page

                webView.send("kissyou","come on");

more detail can see mainactivity and /assets/demo.html
