package by.eapp.testapp.presentation.ui.homescreen.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.eapp.testapp.func.Resource
import by.eapp.testapp.repo.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchBarViewModel @Inject constructor(
    private val repository: ImagesRepository
): ViewModel() {
    private val _imageList: MutableState<SearchBarState> = mutableStateOf(SearchBarState())
    val imageList = _imageList

    fun searchImages(query: String) = viewModelScope.launch {
        imageList.value = SearchBarState(isLoad = true)
        when (val result = repository.searchPhoto(query)) {
            is Resource.Success -> {
                _imageList.value = SearchBarState(data = result.data.photos)
            }

            is Resource.Loading -> {
                _imageList.value = SearchBarState(isLoad = true)
            }

            is Resource.Error -> {
                imageList.value = SearchBarState(err = result.errorMessage)
            }
        }
    }
}