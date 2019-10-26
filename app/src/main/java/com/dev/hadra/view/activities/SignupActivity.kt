package com.dev.hadra.view.activities

import android.util.Log
import android.content.Intent
import com.dev.hadra.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        activity_signup_btn_signup.setOnClickListener{
            signupUser()
        }
    }

    fun signupUser(){
        if (activity_signup_et_username.text.toString().isEmpty()) {
            activity_signup_et_username.error = "Username is missing !"
            activity_signup_et_username.requestFocus()
            return
        }
        if (activity_signup_et_email.text.toString().isEmpty()) {
            activity_signup_et_email.error = "Email is missing !"
            activity_signup_et_email.requestFocus()
            return
        }
        if (activity_signup_et_password.text.toString().isEmpty()) {
            activity_signup_et_password.error = "Password is missing !"
            activity_signup_et_password.requestFocus()
            return
        }
        if (activity_signup_et_password.text.toString().length < 6) {
            activity_signup_et_password.error = "Password should be at least 6 characters"
            activity_signup_et_password.requestFocus()
            return
        }
        if (activity_signup_et_password.text.toString() != activity_signup_et_password2.text.toString() ) {
            activity_signup_et_password2.error = "Passwords do not match ! "
            activity_signup_et_password2.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(activity_signup_et_email.text.toString(), activity_signup_et_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }
}
