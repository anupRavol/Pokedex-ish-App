package com.example.pokedex_ishapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex_ishapp.di.DaggerViewModelComponent
import com.example.pokedex_ishapp.model.ApiResponse
import com.example.pokedex_ishapp.model.CharactersApiService
import com.example.pokedex_ishapp.model.Result
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel(){

    val characters by lazy { MutableLiveData<List<Result>>()}
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var api: CharactersApiService

    init {
        DaggerViewModelComponent.create().inject(this)
    }


    fun refresh(){
        loading.value = true
        getCharacters()
    }

    private fun getCharacters(){
        disposable.add(
            api.getCharacters()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ApiResponse>(){
                    override fun onSuccess(response: ApiResponse) {
                        if(response.results.isNullOrEmpty()){
                            loadError.value = true
                            loading.value = false
                        }else{
                            response.results?.let {
                                characters.value = response.results
                                loadError.value = false
                                loading.value = false
                            }

                        }

                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}