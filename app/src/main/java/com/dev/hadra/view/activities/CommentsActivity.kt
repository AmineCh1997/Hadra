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



class CommentsActivity : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)


        recyclerView = findViewById(R.id.activity_comments_recycler_view) as RecyclerView

        activity_comments_et_comment_content.addTextChangedListener {
            if(activity_comments_et_comment_content.text.toString().isEmpty()){

                activity_comments_tv_post.setTypeface(activity_comments_tv_post.typeface, Typeface.NORMAL)
            }else{
                activity_comments_tv_post.setTypeface(activity_comments_tv_post.typeface, Typeface.BOLD)
            }
        }



        generateData()
    }

    private fun generateData() {

        var commentsResult = ArrayList<Comment>()



        // [END get_all_users]


        for (i in 0..9) {
            var comment: Comment = Comment()
            commentsResult.add(comment)
        }
        var adapter = CommentAdapter(commentsResult)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
