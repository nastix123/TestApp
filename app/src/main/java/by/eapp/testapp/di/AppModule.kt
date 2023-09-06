package by.eapp.testapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import by.eapp.testapp.data.remote.ImageAPIService
import by.eapp.testapp.data.local.Database
import by.eapp.testapp.model.imageList.Image
import by.eapp.testapp.data.paging.ImagesRemoteMediator
import by.eapp.testapp.func.Base
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(Base.BaseURL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

   @Singleton
    @Provides
    fun createApiService(retrofit: Retrofit): ImageAPIService {
        return retrofit.create(ImageAPIService::class.java)
    }

   @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context = context,
            Database::class.java,
            "photos_db"
        ).build()
    }
    @OptIn(ExperimentalPagingApi::class)
    @Singleton
    @Provides
    fun provideImagesPager(
        database: Database,
        apiService: ImageAPIService
    ): Pager<Int, Image> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = ImagesRemoteMediator( database, apiService),
            pagingSourceFactory = { database.imageDao().getAll() }
        )
    }
    @Singleton
    @Provides
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}