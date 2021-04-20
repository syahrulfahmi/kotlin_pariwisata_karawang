package com.sf.krw.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sf.krw.network.ApiService
import com.sf.krw.network.datasource.PariwisataDataSource
import com.sf.krw.network.response.PariwisataListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/04/21
 */
class ListPariwisataViewModel : ViewModel() {
    var listPariwisataResponse = MutableLiveData<PariwisataListResponse>()
    var isProcess = MutableLiveData<Boolean>()
    var isSuccess = MutableLiveData<Boolean>()

    fun getListPariwisataByCategory(idCategory: String) {
        isProcess.value = true
        val retrofit = ApiService.newInstance()
        val apiEndpoint = retrofit.create(PariwisataDataSource::class.java)
        val call = apiEndpoint.getPariwisataByCategory(idCategory)
        call.enqueue(object : Callback<PariwisataListResponse> {
            override fun onResponse(
                call: Call<PariwisataListResponse>,
                response: Response<PariwisataListResponse>
            ) {
                if (response.isSuccessful) {
                    listPariwisataResponse.value = response.body()
                    isProcess.value = false
                    isSuccess.value = true
                }
            }

            override fun onFailure(call: Call<PariwisataListResponse>, t: Throwable) {
                isProcess.value = false
                isSuccess.value = false
            }
        })
    }
}