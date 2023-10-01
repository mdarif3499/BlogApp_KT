package com.example.userloginkotlinapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userloginkotlinapplication.service.model.UserPostModel1
import com.example.userloginkotlinapplication.service.repository.UserPostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserPostViewModel() : ViewModel() {
    var userRepository: UserPostRepository? = null


    fun getUser (lwoginRepository: UserPostRepository) {

        Log.e("appi11111", "view model")
        this.userRepository = lwoginRepository

        viewModelScope.launch(Dispatchers.IO) {
            lwoginRepository?.getUserPost()

        }
    }

    val livedata: LiveData<List<UserPostModel1>>?
        get() = userRepository?.userList

}