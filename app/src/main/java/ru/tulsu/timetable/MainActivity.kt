package ru.tulsu.timetable

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.min


class MainActivity : AppCompatActivity(), IThemeChangeHandler {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val frame = FrameLayout(this)
        val progress = ProgressBar(this)
        val webView = StyleableWebView(this)

        frame.addView(webView)
        frame.addView(progress)
        setContentView(frame)

        webView.apply {
            setThemeChangeHandler(this@MainActivity)
            webViewClient = TSUWebViewClient().apply {
                setWebStateChangeHandler(object: IWebStateChangeHandler {
                    override fun onLoadFinished() { progress.visibility = View.GONE }
                })
            }
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            loadUrl(getString(R.string.tsu_timetable_url))
        }

        progress.apply {
            isIndeterminate = true
            indeterminateTintList = ColorStateList.valueOf(Color.parseColor("#4c6fff"))
            setPadding(200, 32, 200, 32)
        }
    }

    override fun onThemeChanged(color: Int) {
        window.apply {
            statusBarColor = color
            navigationBarColor = color
            decorView.systemUiVisibility =
                if (color < -1000000) decorView.systemUiVisibility xor decorView.systemUiVisibility
                else View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }
}