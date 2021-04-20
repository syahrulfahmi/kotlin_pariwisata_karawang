package com.sf.krw.network.response
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class HomeResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: HomeData
)

data class HomeData(
    @SerializedName("destinations")
    val destinations: List<Destination>,
    @SerializedName("category")
    val category: List<Category>
)

@Parcelize
data class Destination(
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
): Parcelable

data class Category(
    @SerializedName("cat_id")
    val catId: Int,
    @SerializedName("cat_name")
    val catName: String
)