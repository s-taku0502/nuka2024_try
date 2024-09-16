package com.example.nuka2024_try.ui.stamps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.nuka2024_try.R
import com.example.nuka2024_try.Stamp

class StampAdapter(private val stampList:List<Stamp>) :
    RecyclerView.Adapter<StampAdapter.StampViewHolder>() {

    private var onItemClickListener: ((Stamp) -> Unit)? = null

    class StampViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stampImageView: ImageView = itemView.findViewById(R.id.stamp_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StampViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.stamp_item, parent, false)
        return StampViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StampViewHolder, position: Int) {
        val stamp = stampList[position]
        holder.stampImageView.setImageResource(stamp.imageResource)

        // スタンプがクリックされたときの処理
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(stamp)
        }
    }

    override fun getItemCount(): Int {
        return stampList.size
    }

    fun setOnItemClickListener(listener: (Stamp) -> Unit) {
        onItemClickListener = listener
    }
}