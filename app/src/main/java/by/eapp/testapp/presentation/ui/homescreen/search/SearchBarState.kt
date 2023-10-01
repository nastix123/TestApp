package by.eapp.testapp.presentation.ui.homescreen.search

import by.eapp.testapp.feature_images.domain.model.image_Search.Photo


data class SearchBarState(
    val isLoad: Boolean = false,
    val data: List<Photo> = emptyList(),
    val err: String = ""
)
