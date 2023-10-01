package com.example.userloginkotlinapplication.service.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.userloginkotlinapplication.service.model.*
import com.example.userloginkotlinapplication.service.network.Apiservice
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.HashMap

class PostAddRepository(val apiService: Apiservice, val applicationContext: Context) {

    val liveadd = MutableLiveData<String>()
    val categoryd = MutableLiveData<CategoryModel>()
    val postUpdate = MutableLiveData<String>()


    val categor: LiveData<CategoryModel>
        get() = categoryd


    val postUPdate: LiveData<String>
        get() = postUpdate


    val postadd: LiveData<String>
        get() = liveadd

    suspend fun AddPost(myPost1: MyPost) {
        val sharedPreferences = MySharedpreference(applicationContext)
        val token = sharedPreferences.getString(MySharedpreferenceKey.TOKEN)
        val result = apiService.addMyPost("Bearer " + token, myPost1)

        if (result.isSuccessful) {


            liveadd.postValue("success")

        } else {
            val gson = Gson()
            val type = object : TypeToken<Asssssss>() {}.type
            var errorResponse: Asssssss? = gson.fromJson(result.errorBody()!!.charStream(), type)
            val s = errorResponse?.dafefaef?.wwewewe?.title?.message
            liveadd.postValue("" + s)


        }


    }


    suspend fun getCategory() {
        val token = MySharedpreference(applicationContext).getString(MySharedpreferenceKey.TOKEN)

        val result = apiService.getCategory("Bearer " + token)

        if (result.isSuccessful) {
            categoryd.postValue(result.body())

        } else {

            val gson = Gson()
            val type = object : TypeToken<Erroess>() {}.type
            var errorResponse: Erroess? = gson.fromJson(result.errorBody()!!.charStream(), type)
            val s = errorResponse?.message

        }


    }


    suspend fun updatePost(title: String, body: String, id_1: String,arr:MutableList<String>) {
        Log.e("eeeee", "ffff")
        if (!title.isEmpty() && title != "" && !body.isEmpty() && body != "") {
            val token =
                MySharedpreference(applicationContext).getString(MySharedpreferenceKey.TOKEN)

            val map: MutableMap<String, Any> = HashMap<String, Any>()
            map["title"] = title
            map["body"] = body
            map["category"] = arr
            map["photo"] = ""

            val result = apiService.updateUserPost(id_1, "Bearer " + token, map)
            if (result.isSuccessful) {
                postUpdate.postValue("success")

            } else {

                Log.e("eeeee", "eeeeeee")


            }


        }

    }


}

