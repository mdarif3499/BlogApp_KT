package com.example.userloginkotlinapplication.view.ui

import CustomLoadingDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.userloginkotlinapplication.databinding.ActivitySignUpBinding
import com.example.userloginkotlinapplication.service.model.Usere
import com.example.userloginkotlinapplication.service.network.ApiHelper
import com.example.userloginkotlinapplication.service.network.Apiservice
import com.example.userloginkotlinapplication.service.repository.SignUp_UodateRepository
import com.example.userloginkotlinapplication.viewModel.SignUp_UpDate_ViewModel

class SignUpActivity : AppCompatActivity() {

    private val mainViewModel: SignUp_UpDate_ViewModel by viewModels()

    private var bindingS: ActivitySignUpBinding? = null
    private var signUp_upDate_viewModel: SignUpActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingS = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(bindingS?.getRoot())
        initi()


        val str = intent.extras!!.getString("update")
        if (str == "update") {
            bindingS!!.btnSinUP.text = "Update"
            bindingS!!.pasword1.isFocusable = false
        }
        bindingS!!.btnSinUP.setOnClickListener {
            try {
                userRegistrat()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }


    }

    private fun initi() {


    }


    private fun userRegistrat() {
        val email = bindingS!!.email1.text.toString().trim()
        val password = bindingS!!.pasword1.text.toString().trim()
        if (email.isEmpty()) {
            bindingS!!.email1.error = "Enter is email address"
            bindingS!!.email1.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            bindingS!!.email1.error = "Enter a valid  email address"
            bindingS!!.email1.requestFocus()
            return
        }

//        if (password.isEmpty()) {
//
//            bindingS.pasword1.setError("Enter is password address");
//            bindingS.pasword1.requestFocus();
//            return;
//        }
//        if (password.length() < 6) {
//            bindingS.pasword1.setError("Enter a valid  password address");
//
//            bindingS.pasword1.requestFocus();
//            return;
//        }


//        if (password.isEmpty()) {
//
//            bindingS.pasword1.setError("Enter is password address");
//            bindingS.pasword1.requestFocus();
//            return;
//        }
//        if (password.length() < 6) {
//            bindingS.pasword1.setError("Enter a valid  password address");
//
//            bindingS.pasword1.requestFocus();
//            return;
//        }
        val str = intent.extras!!.getString("update")
        if (str != null && str.equals("update")) {
            updatee()
        } else {
            signup()
        }


    }

    private fun signup() {


        val name = bindingS!!.etName.text.toString().trim()
        val userName = bindingS!!.etUserName.text.toString().trim()
        val email = bindingS!!.email1.text.toString().trim()
        val password = bindingS!!.pasword1.text.toString().trim()
        val user = Usere("" + name, "" + userName, "" + email, "" + password)
        var customD = CustomLoadingDialog().createLoadingDialog(this@SignUpActivity)
        val apiserviceService = ApiHelper.getInstance().create(Apiservice::class.java)
        val signRepository = SignUp_UodateRepository(apiserviceService, this@SignUpActivity)
        mainViewModel.signUpUser(signRepository, user)


        mainViewModel.livedata?.observe(this, Observer {

            if (it.equals("success")) {
                customD?.dismiss()
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                val i = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(i)

            } else {
                customD?.dismiss()
                bindingS!!.email1.error = "" + it

            }

        })


    }

    private fun updatee() {

        val name = bindingS?.etName?.text.toString().trim()
        val userName = bindingS!!.etUserName.text.toString().trim()
        val email = bindingS!!.email1.text.toString().trim()
        val user = Usere("" + name, "" + userName, "arif12", "1111111")
        var customD = CustomLoadingDialog().createLoadingDialog(this@SignUpActivity)
        val apiserviceService = ApiHelper.getInstance().create(Apiservice::class.java)
        val signRepository = SignUp_UodateRepository(apiserviceService, this@SignUpActivity)
        mainViewModel.signUpUser(signRepository, user)
        mainViewModel.livedata?.observe(this, Observer {

            if (it.equals("success")) {
                customD?.dismiss()
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()

                customD?.dismiss()
                bindingS!!.email1.error = "" + it
            }

        })


    }
}