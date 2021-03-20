package com.sf.krw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.krw.MainActivity
import com.sf.krw.databinding.ItemPariwisataBinding
import com.sf.krw.utils.loadUrl

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/03/21
 */
class PariwisataAdapter(private val listItem: ArrayList<MainActivity.DummyData>) :
    RecyclerView.Adapter<PariwisataAdapter.ItemViewHolder>() {

    var onItemClick: (item: MainActivity.DummyData) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemPariwisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder) {
            binding.textPariwisataName.text = listItem[position].name
            binding.imagePariwisata.loadUrl(listItem[position].imageUrl)
            binding.cardViewContainer.setOnClickListener {
                onItemClick(listItem[position])
            }
        }
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPariwisataBinding.bind(view)
    }
}