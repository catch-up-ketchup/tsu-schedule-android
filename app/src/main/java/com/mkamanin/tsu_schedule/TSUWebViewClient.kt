package com.mkamanin.tsu_schedule

import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class TSUWebViewClient: WebViewClient() {
    // Redirect around workspace
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (request?.url.toString().contains("tsu-schedule.space"))
            view?.loadUrl(request?.url.toString());

        return false
    }

    // Hide WebView on loading
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        view?.visibility = View.VISIBLE
    }
}