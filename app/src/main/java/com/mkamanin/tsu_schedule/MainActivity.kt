package com.mkamanin.tsu_schedule

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val adapter = ScheduleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prompt = findViewById<TextView>(R.id.prompt)
        val search = findViewById<EditText>(R.id.search)
        val mainButton = findViewById<TextView>(R.id.mainButton)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        search.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                prompt.setTextColor(getColorFromAttr(
                    if (hasFocus) R.attr.color_main_light
                    else R.attr.color_light_text
                ))
            }

        mainButton.setOnClickListener {
            sendRequest(search.text.toString())
        }

        recyclerView.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun sendRequest(query: String) {
        MainScope().launch {
            val result = Network.get("https://tsu-schedule-api.xyz/schedule/${query}")
            val prompt = findViewById<TextView>(R.id.resultPrompt)
            val dto = Gson().fromJson(result, ScheduleDTO::class.java)

            prompt.text = "Расписание для группы ${dto.group}"

            adapter.addItems(dto.schedule[0].dailySchedule)
        }
    }


    private fun getColorFromAttr(attr: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute(attr, typedValue, true)
        return typedValue.data
    }
}