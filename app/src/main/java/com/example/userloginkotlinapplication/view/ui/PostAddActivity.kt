package com.example.userloginkotlinapplication.view.ui

import CustomLoadingDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userloginkotlinapplication.R
import com.example.userloginkotlinapplication.databinding.ActivityPostAddBinding
import com.example.userloginkotlinapplication.service.model.*
import com.example.userloginkotlinapplication.service.network.ApiHelper
import com.example.userloginkotlinapplication.service.network.Apiservice
import com.example.userloginkotlinapplication.service.network.NotificationAPI
import com.example.userloginkotlinapplication.service.network.RetrofitInastans
import com.example.userloginkotlinapplication.service.repository.NotificationRepository
import com.example.userloginkotlinapplication.service.repository.PostAddRepository
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey
import com.example.userloginkotlinapplication.view.adapter.CategoryAdapter
import com.example.userloginkotlinapplication.viewModel.LoginViewModel
import com.example.userloginkotlinapplication.viewModel.PostAddViewModel
import com.example.userloginkotlinapplication.viewModel.SendNotificationViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import retrofit2.create

class PostAddActivity : AppCompatActivity() {
    private var apiserviceService: Apiservice? = null
    private val mainnotificationViewModel: SendNotificationViewModel by viewModels()
    private var upDatePost1: UserPostModel1? = null
    private var category: CategoryModel? = null

    private val mainViewModel: PostAddViewModel by viewModels()
    private var newCategoryList: MutableList<String> = arrayListOf()
    private var listC: MutableList<categoryname> = arrayListOf()
    private var code: String? = null
    private var postaddRepository: PostAddRepository? = null
    private lateinit var binding: ActivityPostAddBinding
    private var recylerView: RecyclerView? = null
    private var sharedPreferences: MySharedpreference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initi()
        binding = ActivityPostAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (code == "update") {
            setUpdateData()
        }
        binding.categoryAdd.setOnClickListener { selectCategory() }


        binding.btnPost.setOnClickListener {
            if (code == "update") {
                updatePost()
            } else {
                Post()
            }
        }

    }

    private fun setUpdateData() {
        binding.btnPost.text = "update"
        val str = sharedPreferences?.getString(MySharedpreferenceKey.UPDATE_USER)
        val gson = Gson()
        upDatePost1 = gson.fromJson(str, UserPostModel1::class.java)

        binding.etBody.setText(upDatePost1?.body)
        binding.etTitle.setText(upDatePost1?.title)


    }

    private fun updatePost() {
        val id = upDatePost1?._id
        val title = binding.etTitle.text.toString().trim()
        val body = binding.etBody.text.toString().trim()
        val userId = sharedPreferences!!.getString(MySharedpreferenceKey.ID)


        var customD = CustomLoadingDialog().createLoadingDialog(this@PostAddActivity)
        Log.e("list11", "===" + newCategoryList.size)
        mainViewModel.postUpdate(postaddRepository!!, title, body, id!!, newCategoryList!!)
        mainViewModel.liveupdate?.observe(this, Observer {

            if (it.equals("success")) {
                customD?.dismiss()
                val intent = Intent(this@PostAddActivity, MyPostActivity::class.java)
                startActivity(intent)


            }

        })


    }

    private fun initi() {

        code = intent.getStringExtra("code")
        sharedPreferences = MySharedpreference(this@PostAddActivity)
        apiserviceService = ApiHelper.getInstance().create(Apiservice::class.java)
        postaddRepository = PostAddRepository(apiserviceService!!, this@PostAddActivity)


    }


    private fun Post() {
        val title = binding.etTitle.text.toString().trim()
        val body = binding.etBody.text.toString().trim()

        val userId = sharedPreferences?.getString(MySharedpreferenceKey.ID)
        if (!title.isEmpty() && title != "" && !body.isEmpty() && body != "") {
            val post = MyPost(
                title,
                body,
                "https://pixabay.com/photos/tree-sunset" + "-clouds-sky-silhouette-736885/",
                userId!!, newCategoryList
            )
            var customD = CustomLoadingDialog().createLoadingDialog(this@PostAddActivity)

            val signRepository = PostAddRepository(apiserviceService!!, this@PostAddActivity)
            mainViewModel.addPost(signRepository, post)
            mainViewModel.liveadd?.observe(this@PostAddActivity, Observer {
                customD?.dismiss()
                if (it.equals("success")) {


                    val  notificationata = NotificationData("/topics/Update",PushNotification("New post added",""+title))

                    sendnotification(notificationata)
                    val intent = Intent(this@PostAddActivity, UserPostActivity::class.java)
                    startActivity(intent)
                } else {

                    binding.etTitle.setError(it)
                }

            })


        }
    }


    private fun selectCategory() {

        var customD = CustomLoadingDialog().createLoadingDialog(this@PostAddActivity)

        val dialog = Dialog(this@PostAddActivity, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.frame_help)
        recylerView = dialog.findViewById<RecyclerView>(R.id.recycleIdcc)

        dialog.findViewById<View>(R.id.progressbar11).setVisibility(View.VISIBLE)

        dialog.findViewById<View>(R.id.tv_cencel).setOnClickListener {
            customD?.dismiss()
            listC.clear()


        }


        val apiserviceService = ApiHelper.getInstance().create(Apiservice::class.java)
        val signRepository = PostAddRepository(apiserviceService, this@PostAddActivity)
        mainViewModel.category(signRepository)
        mainViewModel.livecatagory?.observe(this, Observer {
            dialog.findViewById<View>(R.id.progressbar11).setVisibility(View.GONE)



            category = null
            category = it

            customD?.dismiss()

            if (code != null && code.equals("update") && upDatePost1?.category != null) {

                for (i in upDatePost1!!.category.indices) {

                    for (j in category!!.data.indices) {
                        if (upDatePost1!!.category.get(i).equals(category!!.data.get(j).name)) {
                            category!!.data.get(j).isCheckee = true

                        }


                    }

                }

                recylerView?.setLayoutManager(LinearLayoutManager(this@PostAddActivity))
                val categoryAdapter = CategoryAdapter(this@PostAddActivity, it)
                recylerView?.setAdapter(categoryAdapter)


            } else {

                recylerView?.setLayoutManager(LinearLayoutManager(this@PostAddActivity))
                val categoryAdapter = CategoryAdapter(this@PostAddActivity, it)
                recylerView?.setAdapter(categoryAdapter)
            }


        })
        dialog.show()


        dialog.findViewById<View>(R.id.ok).setOnClickListener {
            Log.e("list11", "size==" + category?.data?.size)

            newCategoryList.clear()
            var c = 0
            for (i in category?.data!!.indices) {
                if (category?.data?.get(i)!!.isCheckee) {
                    newCategoryList.add(category!!.data.get(i).name)
                    c++
                    Log.e("list11", "size==" + category?.data?.size)


                }


            }
            dialog.dismiss()
        }


    }


//    private fun getToken() {
//
//
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Toast.makeText(this@PostAddActivity, "hdygcbuhcgd", Toast.LENGTH_SHORT).show()
//                return@OnCompleteListener
//            }
//
//            val token = task.result
//            val arrr = arrayOf(token)
//            Log.e("message1", arrr.get(0))
//
//
//            val notif = PushNotification("Body of Your Notification", "Title of Ypur Notification")
//            val notifi = NotificationData("/topics/Update", notif)
//            sendnotification(notifi)
//
//
//        })
//    }


    private fun sendnotification(notifi: NotificationData) {
        val notificationApi = RetrofitInastans.getInstance().create(NotificationAPI::class.java)

        val notiRepo = NotificationRepository(notificationApi)
        mainnotificationViewModel.sendNotification(notiRepo, notifi)
        mainnotificationViewModel.livedata?.observe(this@PostAddActivity, Observer {


        })
    }


}