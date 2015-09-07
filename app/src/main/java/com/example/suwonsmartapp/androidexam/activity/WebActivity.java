
package com.example.suwonsmartapp.androidexam.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.suwonsmartapp.androidexam.R;

public class WebActivity extends AppCompatActivity implements View.OnKeyListener {

    private WebView mWebView;
    private EditText mAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWebView = (WebView) findViewById(R.id.web_view);
        mAddress = (EditText) findViewById(R.id.et_web_address);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);

        mAddress.setOnKeyListener(this);
    }

    private void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            loadUrl("http://" + mAddress.getText().toString());
            return true;
        }

        return false;
    }
}
