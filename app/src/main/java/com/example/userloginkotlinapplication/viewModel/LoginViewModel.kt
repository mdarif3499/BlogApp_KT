package com.example.userloginkotlinapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userloginkotlinapplication.service.model.LoginModeldata
import com.example.userloginkotlinapplication.service.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    var loginRepository: LoginRepository? = null


    fun getLogin(lwoginRepository: LoginRepository) {

        Log.e("appi11111", "view model")
        this.loginRepository = lwoginRepository

        viewModelScope.launch(Dispatchers.IO) {
            lwoginRepository?.userLogin()

        }
    }

    val livedata: LiveData<String>?
        get() = loginRepository?.login

}