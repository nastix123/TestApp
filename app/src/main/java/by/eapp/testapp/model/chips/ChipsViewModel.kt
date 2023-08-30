package by.eapp.testapp.model.chips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.eapp.testapp.func.Resource
import by.eapp.testapp.model.imageList.ImageResponse
import by.eapp.testapp.repo.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias Collections = Resource<List<CollectionResponse>>
@HiltViewModel
class ChipsViewModel @Inject constructor(
private val repository: ImagesRepository
): ViewModel() {

    private val _collectionsStateFlow = MutableStateFlow<Collections> (
         Resource.Loading())
    val collectionsStateFlow:MutableStateFlow<Resource<List<CollectionResponse>>> = _collectionsStateFlow

    fun getCollections(collection:  List<*>) = viewModelScope.launch {
        val response = repository.getCollections(collection = collection)
        _collectionsStateFlow.value = response
    }
}