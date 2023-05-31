package com.lmkhant.diary.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lmkhant.diary.R
import com.lmkhant.diary.data.model.Diary
import com.lmkhant.diary.util.Converters
import java.util.Calendar
import java.util.Date

class DiaryAdapter(
    private val onClickListener: (Diary) -> Unit
    ) : RecyclerView.Adapter<DiaryViewHolder>() {
    private val diaryList = ArrayList<Diary>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.diary_item, parent, false)
        return DiaryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(diaryList[position], onClickListener)
    }

    fun updateList(newList: List<Diary>) {
        diaryList.clear()
        diaryList.addAll(newList)
        notifyDataSetChanged()
    }
}

class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(diary: Diary, onClickListener: (Diary)->Unit){
        val date = itemView.findViewById<TextView>(R.id.tv_date)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val text = itemView.findViewById<TextView>(R.id.tv_text)

        val calendar: Calendar = Calendar.getInstance()
        calendar.time = diary.updatedOn

        date.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
        title.text = diary.title
        text.text = diary.description

        itemView.setOnClickListener {
            onClickListener(diary)
        }
    }
}
