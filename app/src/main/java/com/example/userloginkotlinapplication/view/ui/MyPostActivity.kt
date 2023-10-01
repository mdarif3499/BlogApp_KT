package com.example.userloginkotlinapplication.view.ui

import CustomLoadingDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userloginkotlinapplication.databinding.ActivityMyPostBinding
import com.example.userloginkotlinapplication.service.model.UserPostModel1
import com.example.userloginkotlinapplication.service.network.ApiHelper
import com.example.userloginkotlinapplication.service.network.Apiservice
import com.example.userloginkotlinapplication.service.repository.MyPostRepository
import com.example.userloginkotlinapplication.utils.CustomOnClickListener
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey
import com.example.userloginkotlinapplication.view.adapter.MyPostAdapter
import com.example.userloginkotlinapplication.viewModel.MyPostViewModel
import com.google.gson.Gson

class MyPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPostBinding
    private val mainViewModel: MyPostViewModel by viewModels()
    private var mySharedpreference: MySharedpreference? = null


    private var list1: List<UserPostModel1>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initi()


    }

    private fun initi() {
        mySharedpreference = MySharedpreference(this@MyPostActivity)
    }

    private fun getSelfPost() {
        var customD = CustomLoadingDialog().createLoadingDialog(this@MyPostActivity)

        val apiservice = ApiHelper.getInstance().create(Apiservice::class.java)


        val myPostrepository = MyPostRepository(apiservice, this@MyPostActivity)
        mainViewModel.getselfPost(myPostrepository)
        mainViewModel.selflivePost?.observe(this, Observer {
            Log.e("success", "sssssssssssssssssssss")
            customD?.dismiss()
            showMypost(it.data)


        })


    }


    private fun showMypost(myPostList: List<UserPostModel1>?) {

        list1 = myPostList

        val listener: CustomOnClickListener = object : CustomOnClickListener {
            override fun customOnClickListener(p: Int, code: Int, item: UserPostModel1?) {
                if (code == 1) {
                    updateUsere(item!!)
                } else {
                    deleteUserPost(p, item!!)
                }
            }


        }
        if (myPostList != null) {
            binding.recycleryViewId.setLayoutManager(LinearLayoutManager(this@MyPostActivity))
            val userPostAdapter = MyPostAdapter(myPostList, this@MyPostActivity, listener)
            binding.recycleryViewId.setAdapter(userPostAdapter)

        }


    }


    private fun deleteUserPost(p: Int, item: UserPostModel1) {
        val builder1 = AlertDialog.Builder(this@MyPostActivity)
        builder1.setMessage("Write your message here.")
        builder1.setCancelable(true)
        builder1.setPositiveButton(
            "Yes"
        ) { dialog, id ->
            var customD = CustomLoadingDialog().createLoadingDialog(this@MyPostActivity)

            val apiservice = ApiHelper.getInstance().create(Apiservice::class.java)


            val myPostrepository = MyPostRepository(apiservice, this@MyPostActivity)
            mainViewModel.deleteselfPost(myPostrepository, item._id)
            mainViewModel.deletpostmy?.observe(this, Observer {
                if (it.equals("success")) {
                    customD?.dismiss()
                    Toast.makeText(this@MyPostActivity, "t=success", Toast.LENGTH_SHORT).show()
                    getSelfPost()

                } else {
                    customD?.dismiss()
                }

            })
            dialog.cancel()
        }
        builder1.setNegativeButton(
            "No"
        ) { dialog, id -> dialog.cancel() }
        val alert11 = builder1.create()
        alert11.show()
    }


    private fun updateUsere(p: UserPostModel1) {
        val gson = Gson()
        val data: String = gson.toJson(p)
        val i = Intent(this@MyPostActivity, PostAddActivity::class.java)
        i.putExtra("code", "update")
        mySharedpreference?.saveString(MySharedpreferenceKey.UPDATE_USER, data)

        startActivity(i)

    }

    override fun onStart() {
        super.onStart()
        getSelfPost()

    }


}