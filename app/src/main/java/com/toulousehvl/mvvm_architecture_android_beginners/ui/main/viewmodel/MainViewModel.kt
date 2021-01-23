package com.toulousehvl.mvvm_architecture_android_beginners.ui.main.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.toulousehvl.mvvm_architecture_android_beginners.data.model.Insult
import com.toulousehvl.mvvm_architecture_android_beginners.data.model.User
import com.toulousehvl.mvvm_architecture_android_beginners.data.repository.MainRepository
import com.toulousehvl.mvvm_architecture_android_beginners.utils.Resource


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

   // private val users = MutableLiveData<Resource<List<User>>>()
    private val compositeDisposable = CompositeDisposable()
    private val insult = MutableLiveData<Resource<Insult>>()
    val txtInsult : ObservableField<String> = ObservableField()

    init {
        //fetchUsers()
        txtInsult.get()
        fetchInsult()
        Log.e("mainviewmodel", "fetchInsult: ${fetchInsult().toString()}")
    }

   fun fetchInsult(){
        insult.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getInsults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ insults ->
                    insult.postValue(Resource.success(insults))
                    txtInsult.set(insults.insult)
                    Log.e("mainviewmodel", "${insult.postValue(Resource.success(insults))}")
                }, {error -> Log.e("MainViewModel", "${insult.postValue(Resource.error("Something Went Wrong", null))}")}
                )
        )
    }
/*    private fun fetchUsers(){
        Log.e("MainViewModel", "fetchUsers")
        users.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    users.postValue(Resource.success(userList))
                },{ throwable ->
                    users.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }*/

    override fun onCleared() {
        super.onCleared()
        Log.e("MainViewModel","onCleared" )
        compositeDisposable.dispose()
    }

    fun getInsults() : MutableLiveData<Resource<Insult>> {
        return insult
    }


/*    fun getUsers(): LiveData<Resource<List<User>>> {
        return users
    }*/
}