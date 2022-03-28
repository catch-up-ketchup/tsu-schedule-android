package com.mkamanin.tsu_schedule

import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ScheduleAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    public val items = arrayListOf<DailySchedule>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ScheduleViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_lesson, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ScheduleViewHolder).bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public fun addItems(list: List<DailySchedule>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }
}

class ScheduleViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.title)
    val time = view.findViewById<TextView>(R.id.time)
    val location = view.findViewById<TextView>(R.id.location)
    val teacher = view.findViewById<TextView>(R.id.teacher)
    val type = view.findViewById<TextView>(R.id.type)
    val container = view.findViewById<ConstraintLayout>(R.id.container)

    public fun bind(item: DailySchedule) {
        title.text = item.subject;
        time.text = "${item.time.start} - ${item.time.end}"
        location.text = item.auditorium
        teacher.text = item.teacher
        type.text = item.type.ru

        if (item.type.subgroup != "")
            type.text = "${item.type.ru} (${item.type.subgroup} подгруппа)"

        when (item.type.en) {
            "lecture" -> {
                container.setBackgroundResource(R.drawable.dr_item_lecture)
                type.setTextColor(getColorFromAttr(R.attr.color_lecture_accent))
            }
            "practice" ->{
                container.setBackgroundResource(R.drawable.dr_item_practice)
                type.setTextColor(getColorFromAttr(R.attr.color_practice_accent))
            }
            "laboratory-work" ->{
                container.setBackgroundResource(R.drawable.dr_item_laboratory)
                type.setTextColor(getColorFromAttr(R.attr.color_laboratory_work_accent))
            }
            else -> {
                container.setBackgroundResource(R.drawable.dr_item_another)
                type.setTextColor(getColorFromAttr(R.attr.color_another_accent))
            }
        }
    }

    private fun getColorFromAttr(attr: Int): Int {
        val typedValue = TypedValue()
        view.context.theme.resolveAttribute(attr, typedValue, true)
        return typedValue.data
    }
}