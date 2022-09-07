package ru.tulsu.timetable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.webkit.WebView

class StyleableWebView(context: Context): WebView(context) {
    private var themeChangeHandler: IThemeChangeHandler? = null
    private var oldColor = 0

    fun setThemeChangeHandler(handler: IThemeChangeHandler) {
        themeChangeHandler = handler
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        val _canvas = Canvas(bitmap)
        super.onDraw(_canvas)
        val color = bitmap.getPixel(0, 0)

        if (oldColor != color) {
            oldColor = color
            themeChangeHandler?.onThemeChanged(color)
        }
    }
}