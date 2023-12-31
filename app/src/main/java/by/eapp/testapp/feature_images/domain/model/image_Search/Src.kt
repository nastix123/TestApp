package by.eapp.testapp.feature_images.domain.model.image_Search

import com.google.gson.annotations.SerializedName

data class Src(
    @SerializedName("original")
    val landscape: String,
    @SerializedName("large2x")
    val large: String,
    @SerializedName("large")
    val large2x: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val original: String,
    @SerializedName("portrait")
    val portrait: String,
    @SerializedName("landscape")
    val small: String,
    @SerializedName("tiny")
    val tiny: String
)