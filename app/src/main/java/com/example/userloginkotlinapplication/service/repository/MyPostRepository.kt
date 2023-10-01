package com.example.userloginkotlinapplication.service.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.userloginkotlinapplication.service.model.MyPostModel
import com.example.userloginkotlinapplication.service.network.Apiservice
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey

class MyPostRepository(val apiService: Apiservice, val applicationContext: Context) {
    private val liveMyPostd = MutableLiveData<MyPostModel>()
    private val liveDeletePostd = MutableLiveData<String>()


    val myPostLiveData: LiveData<MyPostModel>
        get() = liveMyPostd

    val liveeletePost1: LiveData<String>
        get() = liveDeletePostd

    suspend fun getSelfPost() {
        val myshare = MySharedpreference(applicationContext)


        val token = myshare.getString(MySharedpreferenceKey.TOKEN)
        val id_ = myshare.getString(MySharedpreferenceKey.ID)
        val ruselt = apiService.getMyPost("Bearer " + token, id_)
        if (ruselt.isSuccessful) {
            liveMyPostd.postValue(ruselt.body())


        } else {


        }


    }

    suspend fun deletepst(str: String) {


        val sr = MySharedpreference(applicationContext).getString(MySharedpreferenceKey.TOKEN)
        Log.e("ggggg","token"+sr)

        Log.e("ggggg","id-======"+str)

        val ruset=apiService.deletePost(str,"Bearer "+sr)
        if (ruset.isSuccessful){
            liveDeletePostd.postValue("success")

        }
        else
        {
            Log.e("ggggg","rrrrrrr===")
        }


    }

}