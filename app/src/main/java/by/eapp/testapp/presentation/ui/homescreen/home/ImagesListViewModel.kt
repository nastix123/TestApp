package by.eapp.testapp.presentation.ui.homescreen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import by.eapp.testapp.data.db.database.apiservice.ImageAPIService
import by.eapp.testapp.data.db.database.database.local.Database
import by.eapp.testapp.data.db.database.imageList.Image
import by.eapp.testapp.data.db.database.paging.ImagesRemoteMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class ImagesListViewModel @Inject constructor(
    private val dbImages: Database,
    private val apiService: ImageAPIService,

    ) : ViewModel() {
    @OptIn(ExperimentalPagingApi::class)
    fun curatedImages(): Flow<PagingData<Image>> = Pager(
        config = PagingConfig(
            pageSize = 30,
            prefetchDistance = 10,
            initialLoadSize = 30
        ),
        remoteMediator = ImagesRemoteMediator(
            dbImages = dbImages,
            apiService = apiService,

            ),
                pagingSourceFactory =
        {
            dbImages.imageDao().getAll()
        },
    ).flow.cachedIn(viewModelScope)


}

