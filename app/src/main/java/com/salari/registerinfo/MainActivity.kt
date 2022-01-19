package com.salari.registerinfo

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isGone
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val femaleBtt = findViewById<RadioButton>(R.id.radio1)
        val maleBtt = findViewById<RadioButton>(R.id.radio2)
        val fullName=findViewById<EditText>(R.id.fullName)
        val userName=findViewById<EditText>(R.id.userName)
        val email=findViewById<EditText>(R.id.Email)
        val password=findViewById<EditText>(R.id.pass1)
        val rePassword=findViewById<EditText>(R.id.pass2)
        val regBtt=findViewById<Button>(R.id.registerButt)
        val showBtt=findViewById<Button>(R.id.showButt)
        val hideBtt=findViewById<Button>(R.id.hideButt)
        val showName=findViewById<TextView>(R.id.ShowName)
        val showUserName=findViewById<TextView>(R.id.ShowUserName)
        val showEmail=findViewById<TextView>(R.id.ShowEmail)
        val showPassword=findViewById<TextView>(R.id.ShowPassword)
        val showGender=findViewById<TextView>(R.id.ShowGender)
        val sharedPref: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        femaleBtt.setOnClickListener {
            maleBtt.isChecked=false
        }
        maleBtt.setOnClickListener {
            femaleBtt.isChecked=false
        }

            regBtt.setOnClickListener {
                if (rePassword.text.toString() == password.text.toString()) {
                    val name = fullName.text.toString()
                    val user = userName.text.toString()
                    val emailSt = email.text.toString()
                    val pass = password.text.toString()
                    val gender: String
                    if (femaleBtt.isChecked) {
                        gender = "Female"
                    } else {
                        gender = "Male"
                    }

                    val editor = sharedPref.edit()
                    editor.putString("fullName", name)
                    editor.putString("userName", user)
                    editor.putString("email", emailSt)
                    editor.putString("password", pass)
                    editor.putString("Gender", gender)
                    editor.apply()
                    editor.commit()
                }
                else{
                    Toast.makeText(this,"password doesn't mach with retype",Toast.LENGTH_LONG).show()
                }
            }
            showBtt.setOnClickListener {
                val sharedName = sharedPref.getString("fullName","")
                val sharedUserName = sharedPref.getString("userName", "")
                val sharedEmail = sharedPref.getString("email", "")
                val sharedPass = sharedPref.getString("password", "")
                val sharedGender = sharedPref.getString("Gender", "")
                showName.setText(" Name: $sharedName").toString()
                showName.isVisible=true
                showUserName.setText(" UserName: $sharedUserName").toString()
                showUserName.isVisible=true
                showEmail.setText(" Email: $sharedEmail").toString()
                showEmail.isVisible=true
                showPassword.setText(" Password: $sharedPass").toString()
                showPassword.isVisible=true
                showGender.setText(" Gender: $sharedGender").toString()
                showGender.isVisible=true
                hideBtt.isVisible=true

            }
            hideBtt.setOnClickListener {
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()
                showName.isGone=true
                showUserName.isGone=true
                showGender.isGone=true
                showPassword.isGone=true
                showEmail.isGone=true
                hideBtt.isGone=true
            }
    }

}