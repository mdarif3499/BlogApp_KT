package com.example.userloginkotlinapplication.view.ui

import CustomLoadingDialog
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.userloginkotlinapplication.R
import com.example.userloginkotlinapplication.databinding.ActivityUserPostBinding
import com.example.userloginkotlinapplication.service.model.*
import com.example.userloginkotlinapplication.service.network.*
import com.example.userloginkotlinapplication.service.repository.UserPostRepository
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey
import com.example.userloginkotlinapplication.utils.NetworkUtils
import com.example.userloginkotlinapplication.view.adapter.UserPostAdapter
import com.example.userloginkotlinapplication.viewModel.UserPostViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserPostActivity : AppCompatActivity() {
    private lateinit var bookDao: BookDao
    private var category:MutableList <String> = arrayListOf()
    private var mySharedPreferences: MySharedpreference? = null
    private var userPostAdapter: UserPostAdapter? = null
    private val list1: List<User>? = null
    private lateinit var binding: ActivityUserPostBinding
    private val mainViewModel: UserPostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inini()
//        {
//            "to": "/topics/Update",
//            "notification":{
//            "body":"Body of Your Notification",
//            "title":"Title of Ypur Notification"
//
//
//        }








        drawer()
        FirebaseMessaging.getInstance().subscribeToTopic("Update")
        binding.btnFloating.setOnClickListener {
            val i = Intent(this@UserPostActivity, PostAddActivity::class.java)
            i.putExtra("code", "post")
            startActivity(i)
        }


    }

    private fun inini() {

        mySharedPreferences = MySharedpreference(this@UserPostActivity)

    }


    @SuppressLint("SuspiciousIndentation")
    private fun showUserPost() {
        val custm = CustomLoadingDialog().createLoadingDialog(this@UserPostActivity)

        val apiserviceService = ApiHelper.getInstance().create(Apiservice::class.java)
        val loginRepository = UserPostRepository(apiserviceService, this@UserPostActivity)
        mainViewModel.getUser(loginRepository)
        mainViewModel.livedata?.observe(this, Observer {
            custm?.dismiss()
            if (it.size <= 0) {

                val i = Intent(this@UserPostActivity, LoginActivity::class.java)

                startActivity(i)
            } else {





                binding.recycleryViewId.setLayoutManager(LinearLayoutManager(this@UserPostActivity))
                userPostAdapter = UserPostAdapter(it, this@UserPostActivity)
                binding.recycleryViewId.setAdapter(userPostAdapter)

            }


        })


    }


    @SuppressLint("SuspiciousIndentation")
    private fun drawer() {
        var toggle = ActionBarDrawerToggle(
            this@UserPostActivity, binding.drawerLayout, R.string.open, R.string.close

        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val gson = Gson()
        val userDeteles = mySharedPreferences?.getString(MySharedpreferenceKey.JSON_STRING)
        var data1 = gson.fromJson(userDeteles, Aata::class.java)

        val menu: Menu = binding.navView.getMenu()
        val home = menu.findItem(R.id.mHome)
        val header: View = binding.navView.getHeaderView(0)
        val tv_name: TextView
        val tv_email = header.findViewById<View>(R.id.arif1) as TextView
        tv_name = header.findViewById<TextView>(R.id.tv_name_h)
        tv_name.text = "" + data1.name
        tv_email.text = "" + data1.email
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mHome -> {
                    val intent = Intent(this@UserPostActivity, UserDetailsActivity::class.java)
                    startActivity(intent)
                    binding.drawerLayout.closeDrawers()
                }
                R.id.myPost -> {
                    val i = Intent(this@UserPostActivity, MyPostActivity::class.java)
                    startActivity(i)
                    binding.drawerLayout.closeDrawers()
                }
                R.id.logout -> {
                    mySharedPreferences?.setboolean(MySharedpreferenceKey.ISCHECK, false)
                    val ii = Intent(this@UserPostActivity, LoginActivity::class.java)
                    startActivity(ii)
                    finish()
                    binding.drawerLayout.closeDrawers()
                }
            }
            false
        })
        binding.imageMenu.setOnClickListener(View.OnClickListener { // Code Here
            binding.drawerLayout.openDrawer(GravityCompat.START)
        })
    }

    override fun onStart() {
        super.onStart()


            showUserPost()






    }



}

