package com.moneytap.praveen.wiki.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.annotation.TargetApi
import android.widget.Toast
import android.webkit.WebViewClient


class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mWebview = WebView(this)

        mWebview.getSettings().setJavaScriptEnabled(true) // enable javascript

        val activity = this

        mWebview.setWebViewClient(object : WebViewClient() {
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show()
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            override fun onReceivedError(view: WebView, req: WebResourceRequest, rerr: WebResourceError) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.errorCode, rerr.description.toString(), req.url.toString())
            }
        })
        var query = intent.extras.getString("title")
        query.replace(" ","_")
        mWebview.loadUrl("https://en.wikipedia.org/wiki/"+query)
        setContentView(mWebview)
    }
}
