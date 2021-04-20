package com.sf.krw.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/03/21
 */
fun ImageView.loadUrl(
    imageUrl: String?,
    diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.RESOURCE
) {
    val glideUrl = object : GlideUrl(imageUrl) {
        override fun getCacheKey(): String {
            val url = toStringUrl()
            return if (url.contains("?")) {
                url.substring(0, url.lastIndexOf("?"))
            } else {
                url
            }
        }
    }

    //Then we load with this url
    Glide.with(this)
        .load(glideUrl)
        .diskCacheStrategy(diskCacheStrategy)
        .into(this)
}