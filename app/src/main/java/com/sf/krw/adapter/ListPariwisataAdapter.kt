package com.sf.krw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.krw.databinding.ItemListPariwisataBinding
import com.sf.krw.network.response.Destination
import com.sf.krw.utils.loadUrl

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/04/21
 */

class ListPariwisataAdapter(
    private val listItem: List<Destination>,
    private val listener: OnItemClick? = null
) :
    RecyclerView.Adapter<ListPariwisataAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemListPariwisataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //put code here if you want to make some event with view
        with(holder.binding) {
            listItem[position].apply {
                imagePariwisata.loadUrl(parImage)
                textPariwisataName.text = parName
                textPariwisataLocation.text = parLocation
                cardViewContainer.setOnClickListener {
                    listener?.changeToDetailActivity(this)
                }
            }
        }
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListPariwisataBinding.bind(view)
    }

    interface OnItemClick {
        fun changeToDetailActivity(item: Destination)
    }
}