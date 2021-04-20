package com.sf.krw.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sf.krw.network.ApiService
import com.sf.krw.network.datasource.PariwisataDataSource
import com.sf.krw.network.response.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/04/21
 */
class HomeViewModel : ViewModel() {
    var homeResponse = MutableLiveData<HomeResponse>()
    var isProcess = MutableLiveData<Boolean>()
    var isSuccess = MutableLiveData<Boolean>()

    fun getHome() {
        isProcess.value = true
        val retrofit = ApiService.newInstance()
        val apiEndpoint = retrofit.create(PariwisataDataSource::class.java)
        val call = apiEndpoint.getHome()
        call.enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                if (response.isSuccessful) {
                    homeResponse.value = response.body()
                    isProcess.value = false
                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                isProcess.value = false
                isSuccess.value = false
            }
        })
    }
}