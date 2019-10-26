package com.dev.hadra.view.activities

import android.widget.Toast
import android.util.Log
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.hadra.MainActivity
import com.dev.hadra.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        //Going to signup Activity and killing the login Activity
        activity_login_tv_toSignup.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        activity_login_btn_login.setOnClickListener{
            login()
        }



    }

    public fun login(){

        if (activity_login_et_email.text.toString().isEmpty()) {
            activity_login_et_email.error = "Email is missing !"
            activity_login_et_email.requestFocus()
            return
        }
        if (activity_login_et_password.text.toString().isEmpty()) {
            activity_login_et_password.error = "Email is missing !"
            activity_login_et_password.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(activity_login_et_email.text.toString(),activity_login_et_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser : FirebaseUser?){
        if (currentUser != null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }else{
            Toast.makeText(baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT).show()

        }
    }
}
