package com.sf.krw

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.sf.krw.databinding.ActivityDetailBinding
import com.sf.krw.utils.Extra
import com.sf.krw.utils.loadUrl

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolbar()
        getExtra()
    }

    private fun setUpToolbar() {
        binding.btnBack.setOnClickListener {
            finish()
        }


        binding.appBar.addOnOffsetChangedListener(
            object : AppBarLayout.OnOffsetChangedListener {
                private var prevOffset = 0
                override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                    val offsetAlpha = appBarLayout !!.y / (binding.appBar.totalScrollRange - binding.containerToolbar.height)
                    binding.viewDetailPariwisata.alpha = 0 + offsetAlpha * - 1
                    prevOffset = verticalOffset
                }
            })
    }

    private fun getExtra() {
        val data =
            if (intent.hasExtra(Extra.DATA)) intent.extras?.getParcelable<MainActivity.DummyData>(
                Extra.DATA
            ) else null
        Log.i("ZXC", data.toString())
        binding.imagePariwisata.loadUrl(data?.imageUrl)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            onBackPressed()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}