package com.example.userloginkotlinapplication.service.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.userloginkotlinapplication.service.model.Aata
import com.example.userloginkotlinapplication.service.model.Erroess
import com.example.userloginkotlinapplication.service.network.Apiservice
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class LoginRepository(val apiService: Apiservice, val applicationContext: Context) {
private var mySharedPreferences : MySharedpreference?=null

    private val liveData = MutableLiveData<String>()


    val login: LiveData<String>
        get() = liveData


    @SuppressLint("SuspiciousIndentation")
    suspend fun userLogin() {
       initi()
        var email = mySharedPreferences?.getString(MySharedpreferenceKey.LOGIN_EMAIL)
        var pass = mySharedPreferences?.getString(MySharedpreferenceKey.LOGIN_PASSWORD)


        val result = apiService.loginUser(email!!, pass!!)
        if (result.isSuccessful) {


            val login = result.body()
            val token = login?.token
            val id11 = login?.data?._id
            val name = login?.data?.name

            val gson = Gson()
            val data:String=gson.toJson(login?.data)
            mySharedPreferences?.saveString(MySharedpreferenceKey.JSON_STRING, data)
            mySharedPreferences?.saveString(MySharedpreferenceKey.ID, "" + id11)
            mySharedPreferences?.saveString(MySharedpreferenceKey.NAME, "" + name)
            mySharedPreferences?.saveString(MySharedpreferenceKey.TOKEN, "" + token)





            liveData.postValue("Success")


        } else {
            val gson = Gson()
            val type = object : TypeToken<Erroess>() {}.type
            var errorResponse: Erroess? = gson.fromJson(result.errorBody()!!.charStream(), type)
          val s=  errorResponse?.message

          liveData.postValue(""+s)


        }

    }

    private fun initi() {
        mySharedPreferences = MySharedpreference(applicationContext)
    }


}