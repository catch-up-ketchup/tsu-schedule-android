package com.mkamanin.tsu_schedule

import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

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

    override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
        view?.loadUrl("blank.htm")
        view?.alpha = 0f
        Toast.makeText(view?.context, "No internet connection :(", Toast.LENGTH_LONG).show()

        //super.onReceivedError(view, errorCode, description, failingUrl)
    }
}