package com.mkamanin.tsu_schedule

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object Network {
    public suspend fun get(request: String?): String {
        val result = withContext(Dispatchers.IO) {
            val url = URL(request)
            val connection = url.openConnection() as HttpURLConnection

            connection.connect()

            val inputStream = connection.inputStream

            if (inputStream != null)
                convertInputStreamToString(inputStream)
            else
                "{}"
        }
        return result
    }

    private fun convertInputStreamToString(inputStream: InputStream): String {
        val bufferedReader: BufferedReader = BufferedReader(InputStreamReader(inputStream))

        var line = bufferedReader.readLine()
        var result = ""

        while (line != null) {
            result += line
            line = bufferedReader.readLine()
        }

        inputStream.close()
        return result
    }
}