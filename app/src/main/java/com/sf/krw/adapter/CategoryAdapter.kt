package com.sf.krw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.krw.MainActivity
import com.sf.krw.databinding.ItemCategoryBinding
import com.sf.krw.utils.loadUrl

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/03/21
 */
class CategoryAdapter(private val listItem: ArrayList<MainActivity.DummyData>) :
    RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.textCategoryName.text = listItem[position].name
        holder.binding.imageCategory.loadUrl(listItem[position].imageUrl)
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCategoryBinding.bind(view)
    }
}