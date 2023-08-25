package by.eapp.testapp.presentation.ui.cardInformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.eapp.testapp.data.db.database.imageDetail.ImageDetailResponse
import by.eapp.testapp.func.Resource
import by.eapp.testapp.presentation.ui.homescreen.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardScreenViewModel @Inject constructor(
    private val repository: ImagesRepository
): ViewModel() {
    private val _photoDetailsStateFlow = MutableStateFlow<Resource<ImageDetailResponse>> (
        Resource.Loading()
    )
    val photoDetailsStateFlow: StateFlow<Resource<ImageDetailResponse>> = _photoDetailsStateFlow
    fun getImageDetails(photoId: Int) = viewModelScope.launch {
        val response = repository.getPhotoDetails(photoId)
        _photoDetailsStateFlow.value = response
    }
}