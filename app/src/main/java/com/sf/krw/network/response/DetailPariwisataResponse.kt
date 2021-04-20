package com.sf.krw.network.response
import com.google.gson.annotations.SerializedName


/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/04/21
 */

data class DetailPariwisataResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: DetailPariwisataData,
    @SerializedName("images")
    val images: List<Image>
)

data class DetailPariwisataData(
    @SerializedName("par_id")
    val parId: Int,
    @SerializedName("par_name")
    val parName: String,
    @SerializedName("par_location")
    val parLocation: String,
    @SerializedName("par_image")
    val parImage: String,
    @SerializedName("par_category")
    val parCategory: Int,
    @SerializedName("par_description")
    val parDescription: String,
    @SerializedName("par_open_time")
    val parOpenTime: String,
    @SerializedName("par_cost")
    val parCost: String,
    @SerializedName("par_open_days")
    val parOpenDays: String
)

data class Image(
    @SerializedName("id")
    val id: Int,
    @SerializedName("par_other_images")
    val parOtherImages: String
)