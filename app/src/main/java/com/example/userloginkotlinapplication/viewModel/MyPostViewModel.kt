package com.example.userloginkotlinapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userloginkotlinapplication.service.model.MyPostModel
import com.example.userloginkotlinapplication.service.repository.MyPostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPostViewModel() : ViewModel() {
    var selfRepository: MyPostRepository? = null
    fun getselfPost(lwoginRepository: MyPostRepository) {


        Log.e("appi11111", "view model")
        this.selfRepository = lwoginRepository

        viewModelScope.launch(Dispatchers.IO) {
            lwoginRepository?.getSelfPost()

        }
    }


    fun deleteselfPost(lwoginRepository: MyPostRepository,id:String) {


        Log.e("appi11111", "view model")
        this.selfRepository = lwoginRepository

        viewModelScope.launch(Dispatchers.IO) {
            lwoginRepository?.deletepst(id)

        }
    }

    val selflivePost: LiveData<MyPostModel>?
        get() = selfRepository?.myPostLiveData

    val deletpostmy: LiveData<String>?
        get() = selfRepository?.liveeletePost1




}