package com.sf.krw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.krw.databinding.ItemCategoryBinding
import com.sf.krw.network.response.Category
import com.sf.krw.utils.loadUrl

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/03/21
 */

class CategoryAdapter(
    private val listItem: ArrayList<Category>,
    private var listener: OnItemClicked? = null
) :
    RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {
    private val imageList = arrayListOf(
        "https://wallpapercave.com/wp/wp1977392.jpg",
        "https://ik.imagekit.io/tvlk/blog/2021/01/Monumen-Palagan-Ambarawa-Wikipedia-1024x768.jpg?tr=dpr-1,w-675",
        "https://www.wowkeren.com/display/images/photo/2021/02/01/00350573e1.jpg",
        "https://i.ytimg.com/vi/a1d_-82un5A/hqdefault.jpg"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //put code here if you want to make some event with view
        with(holder.binding) {
            listItem[position].apply {
                textCategoryName.text = catName
                imageCategory.loadUrl(imageList[position])
                cardViewContainer.setOnClickListener {
                    listener?.changeActivity(listItem[position].catId)
                }
            }
        }
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCategoryBinding.bind(view)
    }

    interface OnItemClicked {
        fun changeActivity(catId: Int)
    }
}