package com.sf.krw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.krw.databinding.ItemPariwisataBinding
import com.sf.krw.network.response.Destination
import com.sf.krw.utils.loadUrl

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/04/21
 */

class PariwisataAdapter(
    private val listItem: ArrayList<Destination>,
    private var listener: OnItemClicked? = null
) :
    RecyclerView.Adapter<PariwisataAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemPariwisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //put code here if you want to make some event with view
        with(holder.binding) {
            listItem[position].apply {
                textPariwisataName.text = parName
                textPariwisataLocation.text = parLocation
                imagePariwisata.loadUrl(parImage)
                root.setOnClickListener {
                    listener?.changeActivity(this)
                }
            }
        }
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPariwisataBinding.bind(view)
    }

    interface OnItemClicked {
        fun changeActivity(item: Destination)
    }
}