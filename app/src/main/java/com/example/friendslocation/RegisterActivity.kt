package com.example.friendslocation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.register_view.*

class RegisterActivity: AppCompatActivity() {
    var username: String?=null
    var password: String?=null
    var password2: String?=null
    var email: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_view)


        registerButton.setOnClickListener { view ->

            username = (findViewById(R.id.usernameRegister)as EditText).text.toString()
            password = (findViewById(R.id.passwordOneRegister)as EditText).text.toString()
            password2 = (findViewById(R.id.passwordTwoRegister)as EditText).text.toString()
            email = (findViewById(R.id.emailRegister)as EditText).text.toString()

            if (!password.equals(password2)){
                Toast.makeText(this,"Passwords don't match!",
                    Toast.LENGTH_LONG).show();
            }
            else{
                var i:Intent = Intent(this, HomeActivity::class.java)
                startActivity(i)
            }



        }


    }
}