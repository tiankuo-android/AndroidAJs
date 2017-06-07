package com.atguigu.androidandh5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.atguigu.androidandh5.R.id.webview;

/**
 * 作者：尚硅谷-杨光福 on 2016/7/28 11:19
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：java和js互调
 */
public class JsCallJavaCallPhoneActivity extends Activity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_java_video);
        webView = (WebView)findViewById(webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("file:///android_asset/JsCallJavaCallPhone.html");
        webView.addJavascriptInterface(new MyInterface(),"Android");
    }

    class MyInterface {
        public void showcontacts(){
            // 下面的代码建议在子线程中调用
            String json = "[{\"name\":\"阿福\", \"phone\":\"18600012345\"},{\"name\":\"田阔\", \"phone\":\"18601234567\"}]";
            // 调用JS中的方法
            webView.loadUrl("javascript:show('" + json + "')");
        }
        public void call(String number){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
            startActivity(intent);
        }
    }
}
