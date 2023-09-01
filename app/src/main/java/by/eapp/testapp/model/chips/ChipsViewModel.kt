package by.eapp.testapp.model.chips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.eapp.testapp.func.Resource
import by.eapp.testapp.repo.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//typealias Collections = Resource<List<CollectionResponse>>
@HiltViewModel
class ChipsViewModel @Inject constructor(
    private val repository: ImagesRepository
) : ViewModel() {

    private val _collectionsStateFlow = MutableStateFlow<Resource<CollectionResponse>>(
        Resource.Loading()
    )
    val collectionsStateFlow: StateFlow<Resource<CollectionResponse>> = _collectionsStateFlow

    private val _collectionTitles = MutableStateFlow<List<String>>(emptyList())
    val collectionTitles: StateFlow<List<String>> = _collectionTitles

    suspend fun getCollections(): List<String> {
        val response = repository.getCollections(7)

        if (response is Resource.Success) {
            val data = response.data
            if (data is CollectionResponse) {
                val insideCollection = data.collections
                val collectionTitles = insideCollection.map { it.title }
                _collectionTitles.value = collectionTitles
                return collectionTitles
            }
        }

        return emptyList()
    }

}

