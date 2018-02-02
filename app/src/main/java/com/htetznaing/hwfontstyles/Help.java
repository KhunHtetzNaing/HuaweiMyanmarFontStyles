package com.htetznaing.hwfontstyles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Help extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/help.html");
    }
}
