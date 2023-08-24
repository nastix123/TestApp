package by.eapp.testapp.func

sealed class Resource<T> {
    class Loading<T>: Resource<T>()
    data class Error<T>(val errorMessage: String) : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
}
