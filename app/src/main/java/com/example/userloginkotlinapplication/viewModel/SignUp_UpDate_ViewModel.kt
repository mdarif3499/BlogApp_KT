package com.example.userloginkotlinapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userloginkotlinapplication.service.model.Usere
import com.example.userloginkotlinapplication.service.repository.SignUp_UodateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUp_UpDate_ViewModel: ViewModel() {

    var signRepository: SignUp_UodateRepository? = null


    fun signUpUser (lwoginRepository: SignUp_UodateRepository,user:Usere) {

        Log.e("appi11111", "view model")
        this.signRepository = lwoginRepository

        viewModelScope.launch(Dispatchers.IO) {
            lwoginRepository?.userSignIn(user)

        }
    }

    fun updateUser (lwoginRepository: SignUp_UodateRepository,user:Usere) {
        this.signRepository = lwoginRepository

        viewModelScope.launch(Dispatchers.IO) {
            lwoginRepository?.userSignIn(user)

        }
    }
    val livedata: LiveData<String>?
        get() = signRepository?.userList

}