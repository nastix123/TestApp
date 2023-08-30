package by.eapp.testapp.model.chips

import com.google.gson.annotations.SerializedName

data class CollectionResponse(
    @SerializedName("collections")
    val collections: List<InsideCollection>,

)
