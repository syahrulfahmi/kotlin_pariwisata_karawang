package com.sf.krw.activity

import android.view.LayoutInflater
import android.view.WindowManager
import com.sf.krw.databinding.ActivityImageDetailBinding
import com.sf.krw.utils.BaseActivityBinding
import com.sf.krw.utils.Extra
import com.sf.krw.utils.loadUrl

class ImageDetailActivity : BaseActivityBinding<ActivityImageDetailBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityImageDetailBinding
        get() = ActivityImageDetailBinding::inflate

    override fun setUpView(binding: ActivityImageDetailBinding) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        getExtra()
        setUpListener()
    }

    private fun getExtra() {
        val data = if (intent.hasExtra(Extra.DATA)) intent.extras?.getString(Extra.DATA) else ""
        binding.imagePariwisata.loadUrl(data)
    }

    private fun setUpListener() = with(binding) {
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

}