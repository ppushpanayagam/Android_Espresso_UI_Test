package com.example.testapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplication.R
import com.example.testapplication.data.model.CatItem

class CatItemAdapter(private val itemList: List<CatItem>, private val onItemClick: (CatItem) -> Unit) :
    RecyclerView.Adapter<CatItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_item_placeholder, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemTextView: TextView = itemView.findViewById(R.id.item_text_view)
        private val itemPlaceholderView: TextView = itemView.findViewById(R.id.item_placeholder_text_view)
        private val itemImageView: ImageView = itemView.findViewById(R.id.item_image_view)

        fun bind(item: CatItem) {
            itemTextView.text = item.text
            itemPlaceholderView.text = item.image.placeHolderText

            Glide.with(itemImageView.context)
                .load(item.image.url)
                .centerCrop()
                .into(itemImageView)
        }
    }

}
