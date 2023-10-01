package com.example.userloginkotlinapplication.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.userloginkotlinapplication.R
import com.example.userloginkotlinapplication.databinding.ActivityUserDetailsBinding
import com.example.userloginkotlinapplication.service.model.Aata
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

class UserDetailsActivity : AppCompatActivity() {
   private var bindingU: ActivityUserDetailsBinding? = null
  private  var mySharedPreferences: MySharedpreference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingU = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(bindingU!!.root)
        init()
        showUserDetails()


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.update -> {
                val intent = Intent(this@UserDetailsActivity, SignUpActivity::class.java)
                intent.putExtra("update", "update")
                startActivity(intent)
            }
            R.id.delete -> {}
        }
        return super.onOptionsItemSelected(item)
    }


    private fun init() {
        mySharedPreferences = MySharedpreference(this@UserDetailsActivity)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.Search_viewId)
        val searchView = menuItem.actionView as SearchView?
        searchView!!.maxWidth = Int.MIN_VALUE
        searchView.queryHint = "Search Hear"
        return super.onCreateOptionsMenu(menu)
    }


    private fun showUserDetails() {
        val gson = Gson()
        val str = mySharedPreferences?.getString(MySharedpreferenceKey.JSON_STRING)
        var data1 = gson.fromJson(str, Aata::class.java)

        bindingU!!.tvName.setText(data1.name)
        bindingU!!.emailDtls.setText(data1.email)
        bindingU!!.nameDtls.setText(data1.userName)
        bindingU!!.imgDetail.setImageURI(Uri.parse(data1.profile))
    }






}