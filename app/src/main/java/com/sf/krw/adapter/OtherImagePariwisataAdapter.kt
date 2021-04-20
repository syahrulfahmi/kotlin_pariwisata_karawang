package com.sf.krw.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.krw.R
import com.sf.krw.databinding.ItemOtherImagePariwisataBinding
import com.sf.krw.network.response.Image
import com.sf.krw.utils.loadUrl

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/04/21
 */

class OtherImagePariwisataAdapter(
    private val context: Context,
    private val listItem: List<Image>,
    private val listener: OnImageClicked? = null
) :
    RecyclerView.Adapter<OtherImagePariwisataAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemOtherImagePariwisataBinding.inflate(
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
                if (position % 3 == 0) {
                    cardViewContainer.layoutParams.height =
                        context.resources.getDimension(R.dimen.heigthEven).toInt()
                } else {
                    cardViewContainer.layoutParams.height =
                        context.resources.getDimension(R.dimen.heigthOdd).toInt()
                }
                imagePariwisata.loadUrl(listItem[position].parOtherImages)
                imagePariwisata.setOnClickListener {
                    listener?.changeToDetailImage(cardViewContainer, listItem[position].parOtherImages)
                }
            }
        }
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemOtherImagePariwisataBinding.bind(view)
    }

    interface OnImageClicked {
        fun changeToDetailImage(view: View,urImage: String)
    }
}