package com.example.molfartask.presentation.subliminal.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.molfartask.R
import com.example.molfartask.data.entity.Record

class SubliminalAdapter : RecyclerView.Adapter<SubliminalViewHolder>() {

    private var dataList: MutableList<Record> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SubliminalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.subliminal_item, parent, false)
        )

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: SubliminalViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun setData(list: List<Record>) {
        val diffUtilCallBack = SubliminalDiffUtilCallBack(dataList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        this.dataList = list as MutableList<Record>
        diffResult.dispatchUpdatesTo(this)
    }
}

class SubliminalViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.subliminal_title)
    private val subtitle: TextView = view.findViewById(R.id.subliminal_subtitle)
    private val image: ImageView = view.findViewById(R.id.image)

    fun bind(record: Record) {
        title.text = record.field.title
        subtitle.text = record.field.subTitle
        record.field.image.firstOrNull()?.let {
            Glide.with(view).load(it.url).into(image)
        }
    }
}

class SubliminalDiffUtilCallBack(
    private val oldList: List<Record>,
    private val newList: List<Record>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].field == newList[newItemPosition].field

}