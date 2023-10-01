package com.example.userloginkotlinapplication.service.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.userloginkotlinapplication.service.model.Aata
import com.example.userloginkotlinapplication.service.model.SignUpError
import com.example.userloginkotlinapplication.service.model.Usere
import com.example.userloginkotlinapplication.service.network.Apiservice
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SignUp_UodateRepository(val apiService: Apiservice, val applicationContext: Context) {

    private val liveData = MutableLiveData<String>()

    private val liveUp = MutableLiveData<String>()


    val userList: LiveData<String>
        get() = liveData

    val update: LiveData<String>
        get() = liveUp

    suspend fun userSignIn(user: Usere) {

        val result = apiService.signUp(user)
        if (result.isSuccessful) {
            val r1es = result.body()
            r1es?.message


            liveData.postValue("success")


        } else {
            val gson = Gson()
            val type = object : TypeToken<SignUpError>() {}.type
            var errorResponse: SignUpError? = gson.fromJson(result.errorBody()!!.charStream(), type)
            val s = errorResponse?.error?.errors?.email?.message
            liveData.postValue(s!!)

//            liveData.postValue(""+s)


        }


    }


    suspend fun upDateUser(user: Usere) {
        val gson = Gson()

        val str = MySharedpreference(applicationContext)
        val sta = str.getString(MySharedpreferenceKey.JSON_STRING)
        var data1 = gson.fromJson(sta, Aata::class.java)
        val token = str.getString(MySharedpreferenceKey.TOKEN)
        val id = data1._id
        val resulee = apiService.updateData(id, "Bearer " + token, user)
        if (resulee.isSuccessful) {
            liveUp.postValue("success")


        }


    }


}