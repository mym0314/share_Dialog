package com.sxt.testmywebview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener {


    private static final String TAG = "MainActivity";
    private WebView webView;
    private TextView textViewContent;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView= (WebView) findViewById(R.id.webView);

        findViewById(R.id.buttonBack).setOnClickListener(this);
        findViewById(R.id.buttonRefresh).setOnClickListener(this);
        findViewById(R.id.buttonInvokeJavascript).setOnClickListener(this);
        textViewContent= (TextView) findViewById(R.id.textViewContent);

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");


        webView.addJavascriptInterface(this,"Android");


        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });



        webView.setWebChromeClient(new WebChromeClient(){


            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                textViewContent.setText(title);

            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }


        });
        webView.loadUrl("file:///android_asset/demo.html");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonBack:
                webView.goBack();
                break;
            case R.id.buttonRefresh:
                webView.reload();
                break;
            case R.id.buttonInvokeJavascript://调用Javascript的方法
                webView.loadUrl("javascript:invokeAndroid()");
                break;
        }
    }


    @JavascriptInterface
    public int  numberAdd(int x, int y) {

        Log.d(TAG, "两个数之和" + (x + y));

        return x*y;
    }
}
