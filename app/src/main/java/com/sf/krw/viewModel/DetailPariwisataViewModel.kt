package com.sf.krw.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sf.krw.network.ApiService
import com.sf.krw.network.datasource.PariwisataDataSource
import com.sf.krw.network.response.DetailPariwisataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/04/21
 */
class DetailPariwisataViewModel: ViewModel() {
    var detailPariwisataResponse = MutableLiveData<DetailPariwisataResponse>()
    var isProcess = MutableLiveData<Boolean>()
    var isSuccess = MutableLiveData<Boolean>()

    fun getDetail(idPariwisata: Int) {
        isProcess.value = true
        val retrofit = ApiService.newInstance()
        val apiEndpoint = retrofit.create(PariwisataDataSource::class.java)
        val call = apiEndpoint.getDetail(idPariwisata)
        call.enqueue(object : Callback<DetailPariwisataResponse> {
            override fun onResponse(call: Call<DetailPariwisataResponse>, response: Response<DetailPariwisataResponse>) {
                if (response.isSuccessful) {
                    detailPariwisataResponse.value = response.body()
                    isProcess.value = false
                }
            }

            override fun onFailure(call: Call<DetailPariwisataResponse>, t: Throwable) {
                isProcess.value = false
                isSuccess.value = false
            }
        })
    }
}