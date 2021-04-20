package com.sf.krw.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sf.krw.R
import com.sf.krw.adapter.OtherImagePariwisataAdapter
import com.sf.krw.databinding.ActivityDetailPariwisataBinding
import com.sf.krw.network.response.Destination
import com.sf.krw.utils.*
import com.sf.krw.viewModel.DetailPariwisataViewModel

class DetailActivity : BaseActivityBinding<ActivityDetailPariwisataBinding>(),
    OtherImagePariwisataAdapter.OnImageClicked {

    private val viewModel: DetailPariwisataViewModel by viewModels()
    private var idPariwisata = -1

    override val bindingInflater: (LayoutInflater) -> ActivityDetailPariwisataBinding
        get() = ActivityDetailPariwisataBinding::inflate

    override fun setUpView(binding: ActivityDetailPariwisataBinding) {
        setUpToolbar()
        getExtra()
        checkNetworkState()
    }

    private fun checkNetworkState() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            getDataDetail()
        } else {
            NoConnectionDialog.show(
                this,
                getString(R.string.app_try_connect_again),
                null,
                { dialogInterface, _ ->
                    checkNetworkState()
                    dialogInterface.dismiss()
                })
        }
    }

    private fun getDataDetail() = with(binding) {
        viewModel.getDetail(idPariwisata)
        viewModel.isProcess.observe(this@DetailActivity, {
            if (it) {
                progressLoading.visibility = View.VISIBLE
                recyclerviewOtherImages.visibility = View.GONE
            } else {
                progressLoading.visibility = View.GONE
                recyclerviewOtherImages.visibility = View.VISIBLE
            }
        })

        viewModel.isSuccess.observe(this@DetailActivity, {
            if (!it) {
                Snackbar.make(
                    containerDetail,
                    getString(R.string.app_cannot_retreive_data_from_server),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.detailPariwisataResponse.observe(this@DetailActivity, {
            with(binding) {
                it.data.let { data ->
                    textCost.text = data.parCost
                    textOpenTime.text = data.parOpenTime
                    textOpenDay.text = data.parOpenDays
                    textDescription.text = data.parDescription
                    textOtherImage.text = String.format(
                        getString(R.string.detail_app_other_images),
                        it.data.parName
                    )
                }
                recyclerviewOtherImages.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerviewOtherImages.adapter =
                    OtherImagePariwisataAdapter(this@DetailActivity, it.images, this@DetailActivity)
            }
        })
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getExtra() = with(binding) {
        val data = if (intent.hasExtra(Extra.DATA)) intent.extras?.getParcelable<Destination>(
            Extra.DATA
        ) else null
        try {
            idPariwisata = data?.parId ?: -1
        } catch (e: Exception) {
            Toast.makeText(
                this@DetailActivity,
                getString(R.string.app_cannot_retreive_data_from_server),
                Toast.LENGTH_SHORT
            ).show()
        }
        data?.let {
            imagePariwisata.loadUrl(data.parImage)
            toolbar.title = data.parName
            textCost.text = data.parCost
            textOpenTime.text = data.parOpenTime
            textOpenDay.text = data.parOpenDays
            textDescription.text = data.parDescription
            textOtherImage.text = String.format(
                getString(R.string.detail_app_other_images),
                data.parName
            )
        }
    }

    override fun changeToDetailImage(view: View, urImage: String) {
        val intent = Intent(this, ImageDetailActivity::class.java).apply {
            putExtra(Extra.DATA, urImage)
        }
        startActivity(intent)
    }
}