package com.sf.krw.activity

import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.sf.krw.R
import com.sf.krw.adapter.ListPariwisataAdapter
import com.sf.krw.databinding.ActivityListPariwisataBinding
import com.sf.krw.network.response.Destination
import com.sf.krw.utils.BaseActivityBinding
import com.sf.krw.utils.Extra
import com.sf.krw.utils.NetworkUtils
import com.sf.krw.utils.NoConnectionDialog
import com.sf.krw.viewModel.ListPariwisataViewModel

class ListPariwisataActivity : BaseActivityBinding<ActivityListPariwisataBinding>(),
    ListPariwisataAdapter.OnItemClick {

    private val viewModel: ListPariwisataViewModel by viewModels()
    private var idCategory = ""

    override val bindingInflater: (LayoutInflater) -> ActivityListPariwisataBinding
        get() = ActivityListPariwisataBinding::inflate

    override fun setUpView(binding: ActivityListPariwisataBinding) {
        getExtra()
        checkNetworkState()
    }

    private fun checkNetworkState() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            getData()
        } else {
            NoConnectionDialog.show(
                this,
                getString(R.string.app_try_connect_again),
                null,
                DialogInterface.OnClickListener { dialogInterface, which ->
                    checkNetworkState()
                    dialogInterface.dismiss()
                }
            )
        }
    }

    private fun getData() = with(binding) {
        viewModel.getListPariwisataByCategory(idCategory)
        viewModel.isProcess.observe(this@ListPariwisataActivity, Observer{
            if (it) {
                progressLoading.visibility = View.VISIBLE
                recyclerViewPariwisata.visibility = View.GONE
            } else {
                progressLoading.visibility = View.GONE
                recyclerViewPariwisata.visibility = View.VISIBLE
            }
        })

        viewModel.isSuccess.observe(this@ListPariwisataActivity, Observer{
            if (!it) {
                Snackbar.make(
                    containerListPariwisata,
                    getString(R.string.app_cannot_retreive_data_from_server),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.listPariwisataResponse.observe(this@ListPariwisataActivity, Observer{
            recyclerViewPariwisata.adapter = ListPariwisataAdapter(it.data, this@ListPariwisataActivity)
        })
    }

    private fun getExtra() {
        val data = if (intent.hasExtra(Extra.DATA)) intent.extras?.getString(
            Extra.DATA
        ) else ""
        try {
            idCategory = data.toString()
        } catch (e: Exception) {
            Toast.makeText(
                this@ListPariwisataActivity,
                getString(R.string.app_cannot_retreive_data_from_server),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun changeToDetailActivity(item: Destination) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(Extra.DATA, item)
        }
        startActivity(intent)
    }
}