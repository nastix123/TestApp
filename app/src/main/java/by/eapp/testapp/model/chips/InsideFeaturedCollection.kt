package by.eapp.testapp.model.chips

import com.google.gson.annotations.SerializedName

data class InsideFeaturedCollection(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,

)
