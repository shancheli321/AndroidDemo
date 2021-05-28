package com.lf.nativejs;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lf.main.R;

public class MyNativeJSActivity extends AppCompatActivity {

    private final static String SCHEME = "NativeAPP";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_native_j_s);

        mWebView = findViewById(R.id.native_webview);



    }


    //  1、拦截shouldOverrideUrlLoading方法
    //     借助WebView在加载一个url的时候都会调用WebViewClient类的shouldOverrideUrlLoading（WebView view, String url）方法这一特性，
    //    我们可以针对参数的url定义好特殊的schema协议，来调用native提供的不同的逻辑；
    //    首先我们需要为WebView设置WebViewClient对象；接着重写shouldOverrideUrlLoading（WebView view, String url）方法；

    private void jsOpenNativeShouldOverrideUrlLoading() {

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if (handlerUrl(String.valueOf(request.getUrl()))) {
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    //  2、拦截alert、prompt、confirm处理方法
    // H5端在调用window.alert("nativeApp://login?name=wang")时，会调用WebChromeClient类的onJsAlert方法，
    // 我们在该方法中处理schema，调用native方法；

    private void jsOpenNativeAlert(String url) {

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                handlerUrl(url);
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
    }


    //  3、addJavascriptInterface注册Android对象供H5调用
    //  通过调用webView.addJavascriptInterface(对象实例, "对象名称")方法，注册一个Android对象到H5端；
    //  注册到H5的对象的方法需要添加@JavascriptInterface 注解，这样H5端才能正常调用；

    // H5端使用方式：window.NativeJSObject.jsToNative(“我是H5来的提示”)就可以调用到native的方法

    private void jsOpenNative() {
        mWebView.addJavascriptInterface(new MyJSToNativeInterface(MyNativeJSActivity.this), "NativeJSObject");
    }


    /**
     * 如果是自定义scheme 返回true 表示可以拦截
     * @param url
     * @return
     */
    private boolean handlerUrl(String url) {

        if (url.startsWith(SCHEME)) {


            return true;
        }

        return false;

    }



    // Android native目前通过WebView.loadUrl("javascript:方法名('参数')")来调用H5的方法；如H5有如下方法：
    //
    //  window.showCity = function(city) {
    //
    //      console.log("我是Native传递来的城市："+city)
    //
    //  }
    private void nativeOpenJSbyLoad() {

        mWebView.loadUrl("javascript:window.showCity('北京')");
    }


    /**
     * 当安卓版本大于4.4时可用
     */
    private void nativeOpenJSbyEvalue() {

        String exeUrl = "javascript:window.showCity('北京')";

        mWebView.evaluateJavascript(exeUrl, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.d("js的返回值---", value);
            }
        });

    }
}