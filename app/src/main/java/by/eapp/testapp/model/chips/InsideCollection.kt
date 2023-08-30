package by.eapp.testapp.model.chips

import com.google.gson.annotations.SerializedName

data class InsideCollection(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,

)
