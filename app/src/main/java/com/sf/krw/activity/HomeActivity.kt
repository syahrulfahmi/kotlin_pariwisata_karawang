package com.sf.krw.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sf.krw.R
import com.sf.krw.adapter.CategoryAdapter
import com.sf.krw.adapter.PariwisataAdapter
import com.sf.krw.databinding.ActivityMainBinding
import com.sf.krw.network.response.Category
import com.sf.krw.network.response.Destination
import com.sf.krw.utils.BaseActivityBinding
import com.sf.krw.utils.Extra
import com.sf.krw.utils.NetworkUtils
import com.sf.krw.utils.NoConnectionDialog
import com.sf.krw.viewModel.HomeViewModel

class HomeActivity : BaseActivityBinding<ActivityMainBinding>(), PariwisataAdapter.OnItemClicked,
    CategoryAdapter.OnItemClicked {

    private lateinit var pariwisataAdapter: PariwisataAdapter
    private val viewModel: HomeViewModel by viewModels()
    private val itemListPariwisata = ArrayList<Destination>()
    private val itemListCategory = ArrayList<Category>()

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setUpView(binding: ActivityMainBinding) {
        checkNetworkState()
        setUpListener()
    }

    private fun checkNetworkState() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            getData()
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

    private fun setUpListener() = with(binding) {
        textSeeAll.setOnClickListener {
            val intent = Intent(this@HomeActivity, ListPariwisataActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getData() = with(binding) {
        viewModel.getHome()
        viewModel.isProcess.observe(this@HomeActivity, {
            if (it) {
                progressLoading.visibility = View.VISIBLE
                containerListHome.visibility = View.GONE
            } else {
                progressLoading.visibility = View.GONE
                containerListHome.visibility = View.VISIBLE
            }
        })

        viewModel.isSuccess.observe(this@HomeActivity, {
            if (!it) {
                Snackbar.make(
                    containerHome,
                    getString(R.string.app_cannot_retreive_data_from_server),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.homeResponse.observe(this@HomeActivity, {
            itemListPariwisata.addAll(it.data.destinations)
            itemListCategory.addAll(it.data.category)
            pariwisataAdapter = PariwisataAdapter(itemListPariwisata, this@HomeActivity)
            recyclerView.adapter = pariwisataAdapter
            indicator.attachToRecyclerView(recyclerView)
            val layoutManager = GridLayoutManager(this@HomeActivity, 2)
            binding.recyclerViewCategory.layoutManager = layoutManager
            binding.recyclerViewCategory.adapter =
                CategoryAdapter(itemListCategory, this@HomeActivity)
        })
    }

    override fun changeActivity(item: Destination) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(Extra.DATA, item)
        }
        startActivity(intent)
    }

    override fun changeActivity(catId: Int) {
        val intent = Intent(this, ListPariwisataActivity::class.java).apply {
            putExtra(Extra.DATA, catId)
        }
        startActivity(intent)
    }
}