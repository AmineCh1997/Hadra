package com.dev.hadra.view.activities

import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.text.style.StyleSpan
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.R
import com.dev.hadra.adapter.CommentAdapter
import com.dev.hadra.adapter.TopicAdapter
import com.dev.hadra.model.Comment
import com.dev.hadra.model.Topic
import kotlinx.android.synthetic.main.activity_comments.*
import java.time.format.TextStyle
import android.R.attr.font
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dev.hadra.model.Category
import com.dev.hadra.model.User
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_topics.*
import java.util.*
import kotlin.collections.ArrayList


class CommentsActivity : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null
    val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    var commentsResult = ArrayList<Comment>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        auth = FirebaseAuth.getInstance()

        var topic = getIntent().getSerializableExtra("Topic") as? Topic
        /*activity_topics_tv_id.setText(cat?.id)
        activity_topics_tv_name.setText(cat?.name)
        activity_topics_tv_color.setText(cat?.color)*/

        activity_comments_tv_post.setOnClickListener{
            if (activity_comments_et_comment_content.text.toString().isEmpty()) {

                activity_comments_et_comment_content.error = "Content is missing !"
                activity_comments_et_comment_content.requestFocus()
                return@setOnClickListener

            } else {
                //Adding a topic

                val newComment = db.collection("Comment").document()
                val current_topic = db.collection("Topic").document(topic!!.id)
                val current_user = db.collection("User").document(auth!!.currentUser!!.uid)
                val items = HashMap<String, Any>()
                items.put("content", activity_comments_et_comment_content.text.toString())
                items.put("created_at", Timestamp(Date()))
                items.put("Topic", current_topic)
                items.put("User", current_user)

                newComment.set(items).addOnSuccessListener {
                    Toast.makeText(
                        applicationContext,
                        "Comment Added ",
                        Toast.LENGTH_SHORT
                    ).show()
                    activity_comments_et_comment_content.text.clear()
                    commentsResult.clear()
                    generateData(topic!!.id)




                }.addOnFailureListener {
                    Toast.makeText(
                        applicationContext,
                        "Sorry an error has occuered , please try again later",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
        }

        getSupportActionBar()?.hide()
        recyclerView = findViewById(R.id.activity_comments_recycler_view) as RecyclerView

        activity_comments_et_comment_content.addTextChangedListener {
            if(activity_comments_et_comment_content.text.toString().isEmpty()){

                activity_comments_tv_post.setTypeface(activity_comments_tv_post.typeface, Typeface.NORMAL)
            }else{
                activity_comments_tv_post.setTypeface(activity_comments_tv_post.typeface, Typeface.BOLD)
            }
        }


        generateData(topic!!.id)

        activity_comments_refresh.setOnRefreshListener {
            commentsResult.clear()
            generateData(topic!!.id)
            activity_comments_refresh.isRefreshing = false

        }


    }

    private fun generateData(topic_id : String) {


        db.collection("Comment").get().addOnSuccessListener { result ->
            for (document in result) {
                var comment = Comment()
                comment.id=document.id
                comment.content=document.get("content") as String
                comment.created_at = document.getTimestamp("created_at")?.toDate()
                //Get Topic
                document.getDocumentReference("Topic")!!.get()
                    .addOnSuccessListener { topicDoc ->
                        if(topic_id == topicDoc.id){
                            //Get User
                            document.getDocumentReference("User")!!.get()
                                .addOnSuccessListener { userDoc ->

                                    val user = User(userDoc.id,userDoc.data?.get("email").toString(),
                                        userDoc.data?.get("username").toString())
                                    //Updating comment
                                    comment.user = user

                                    commentsResult.add(comment)
                                    var adapter = CommentAdapter(commentsResult)
                                    val layoutManager = LinearLayoutManager(applicationContext)
                                    recyclerView?.layoutManager = layoutManager
                                    recyclerView?.itemAnimator = DefaultItemAnimator()
                                    recyclerView?.adapter = adapter
                                    adapter.notifyDataSetChanged()


                                }.addOnFailureListener { exception ->
                                    Log.e("failure", "Error getting documents: ", exception)
                                }
                        }
                    }.addOnFailureListener { exception ->
                        Log.e("failure", "Error getting documents: ", exception)
                    }
            }
        }.addOnFailureListener { exception ->
            Log.e("failure", "Error getting documents: ", exception)
        }

    }
}
