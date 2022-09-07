package ru.tulsu.timetable

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class TSUWebViewClient: WebViewClient() {
    private var stateChangeHandler: IWebStateChangeHandler? = null

    fun setWebStateChangeHandler(handler: IWebStateChangeHandler) {
        stateChangeHandler = handler
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val redirect = request?.url.toString()
        return redirect.contains(view?.context?.getString(R.string.tsu_timetable) ?: "")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        stateChangeHandler?.onLoadFinished()
    }
}