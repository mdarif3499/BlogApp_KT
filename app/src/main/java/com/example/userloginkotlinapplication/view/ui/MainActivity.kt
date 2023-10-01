package com.example.userloginkotlinapplication.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userloginkotlinapplication.R
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey

class MainActivity : AppCompatActivity() {

    private var mySharedpreference: MySharedpreference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initi()
        if (mySharedpreference?.getIsCheck(MySharedpreferenceKey.ISCHECK) == true) {
            val ii = Intent(this, UserPostActivity::class.java)
            startActivity(ii)
        } else {


            val i = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(i)
        }
    }

    private fun initi() {
        mySharedpreference = MySharedpreference(this)
    }


    init {


    }
}