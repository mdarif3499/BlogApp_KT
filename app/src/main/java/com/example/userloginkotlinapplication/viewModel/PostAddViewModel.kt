package com.example.userloginkotlinapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userloginkotlinapplication.service.model.CategoryModel
import com.example.userloginkotlinapplication.service.model.MyPost
import com.example.userloginkotlinapplication.service.repository.PostAddRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostAddViewModel: ViewModel() {
    var postAddRepository: PostAddRepository? = null


    fun addPost (lwoginRepository:PostAddRepository,myPost:MyPost ) {

        Log.e("appi11111", "view model")
        this.postAddRepository = lwoginRepository

        viewModelScope.launch(Dispatchers.IO) {
            lwoginRepository?.AddPost(myPost)

        }

    }



    fun category (lwoginRepository:PostAddRepository) {

        Log.e("appi11111", "view model")
        this.postAddRepository = lwoginRepository

        viewModelScope.launch(Dispatchers.IO) {
            lwoginRepository?.getCategory()

        }

    }

    fun postUpdate (lwoginRepository:PostAddRepository,title:String , body:String, id_1:String,arr:MutableList<String>) {

        Log.e("appi11111", "view model")
        this.postAddRepository = lwoginRepository

        viewModelScope.launch(Dispatchers.IO) {
            lwoginRepository?.updatePost(title,body,id_1,arr)

        }

    }


    val liveadd: LiveData<String>?
        get() = postAddRepository?.liveadd

    val livecatagory: LiveData<CategoryModel>?
        get() = postAddRepository?.categor

    val liveupdate: LiveData<String>?
        get() = postAddRepository?.postUPdate

}







