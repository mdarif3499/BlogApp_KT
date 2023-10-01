package com.example.userloginkotlinapplication.view.ui

import CustomLoadingDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.userloginkotlinapplication.databinding.ActivityLoginBinding
import com.example.userloginkotlinapplication.service.network.ApiHelper
import com.example.userloginkotlinapplication.service.network.Apiservice
import com.example.userloginkotlinapplication.service.repository.LoginRepository
import com.example.userloginkotlinapplication.utils.MySharedpreference
import com.example.userloginkotlinapplication.utils.MySharedpreferenceKey
import com.example.userloginkotlinapplication.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val mainViewModel: LoginViewModel by viewModels()

    private lateinit var binding: ActivityLoginBinding
    private var sharedPreferenc: MySharedpreference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initi()
        binding.btnLogin.setOnClickListener {

            userLogin()
        }

        binding.btnCreateAc.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            intent.putExtra("update", "create")
            startActivity(intent)
        })

    }

    private fun userLogin() {

        val email: String = binding.inputEmail.text.toString().trim()
        val password: String = binding.inputPassword.getText().toString().trim()
        if (email.isEmpty()) {
            binding.inputEmail.setError("Enter is email address")
            binding.inputEmail.requestFocus()
            return

        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.inputEmail.setError("Enter a valid  email address")
            binding.inputEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            binding.inputPassword.setError("Enter is password address")
            binding.inputPassword.requestFocus()
            return
        }
        if (password.length < 6) {
            binding.inputPassword.setError("Enter a valid  password address")
            binding.inputPassword.requestFocus()
            return
        }
        login()

    }

    private fun login() {
        val userName: String = binding.inputEmail.getText().toString().trim()
        val password: String = binding.inputPassword.getText().toString().trim()


        sharedPreferenc?.saveString(MySharedpreferenceKey.LOGIN_EMAIL, userName)
        sharedPreferenc?.saveString(MySharedpreferenceKey.LOGIN_PASSWORD, password)

        var customD = CustomLoadingDialog().createLoadingDialog(this@LoginActivity)


        val apiserviceService = ApiHelper.getInstance().create(Apiservice::class.java)
        val loginRepository = LoginRepository(apiserviceService, this@LoginActivity)
        mainViewModel.getLogin(loginRepository)
        mainViewModel.livedata?.observe(this, Observer {

            if (it.equals("Success")) {
//                binding.progressbarL.setVisibility(View.GONE)
                customD?.dismiss()
                val intent = Intent(this@LoginActivity, UserPostActivity::class.java)
                sharedPreferenc?.setboolean(MySharedpreferenceKey.ISCHECK, true)
                startActivity(intent)
            }
            if (it.equals("Wrong Credentials")) {
                customD?.dismiss()
                binding.inputEmail.setError(it)
            }
            if (it.equals("Wrong password!")) {
                customD?.dismiss()
                binding.progressbarL.setVisibility(View.GONE)
                binding.inputPassword.setError(it)
            }

        })


    }

    private fun initi() {
        sharedPreferenc = MySharedpreference(this@LoginActivity)


    }
}