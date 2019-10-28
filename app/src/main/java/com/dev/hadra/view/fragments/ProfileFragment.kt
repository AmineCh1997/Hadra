package com.dev.hadra.view.fragments

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dev.hadra.R
import com.dev.hadra.view.activities.LoginActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.FileDescriptor
import java.io.IOException

class ProfileFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    val READ_REQUEST_CODE: Int = 42
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        val root = inflater.inflate(R.layout.fragment_profile,container,false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        super.onViewCreated(view, savedInstanceState)
    }

    fun setupView(){
        profile_IV.setOnClickListener {
            Log.e("profile frag","image clicked")
            performFileSearch()
        }
        logout_btn.setOnClickListener {
            Logout()
        }
        update_btn.setOnClickListener {
            Update()
        }
    }

    fun performFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            // Filter to only show results that can be "opened", such as a
            // file (as opposed to a list of contacts or timezones)
            addCategory(Intent.CATEGORY_OPENABLE)

            // Filter to show only images, using the image MIME data type.
            // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
            // To search for all documents available via installed storage providers,
            // it would be "*/*".
            type = "image/*"
        }

        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    private fun Logout(){
        firebaseAuth.signOut()
        startActivity(Intent(activity,LoginActivity::class.java))
        activity!!.finish()
    }

    private fun Update(){
        val username = username_et.text.toString()
        val password = password_et.text.toString()
        val user = hashMapOf(
            "username" to username
        )
        firebaseAuth.currentUser!!.updatePassword(password).addOnSuccessListener {
            db.collection("User")
                .document(firebaseAuth.currentUser!!.email!!)
                .set(user, SetOptions.merge()).addOnSuccessListener { Log.d("Profile frag", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w("Profile frag", "Error writing document", e) }
        }.addOnFailureListener {
            Log.w("Profile frag", "Error writing document", it)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == READ_REQUEST_CODE && resultCode==Activity.RESULT_OK){
            data?.data.also {
                Log.e("profile frag",it.toString())
                Glide.with(this).load(it).into(profile_IV)
            }
        }
    }


}