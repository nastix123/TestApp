package by.eapp.testapp.presentation.ui.homescreen

import by.eapp.testapp.data.db.database.imageList.Image

data class SearchBarState(
    val isLoad: Boolean = false,
    val data:List<Image> = emptyList(),


    val err: String = ""
)
