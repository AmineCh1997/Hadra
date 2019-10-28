package com.dev.hadra.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.R
import com.dev.hadra.adapter.CommentAdapter
import com.dev.hadra.adapter.TopicAdapter
import com.dev.hadra.model.Comment
import com.dev.hadra.model.Topic

class CommentsActivity : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)


        recyclerView = findViewById(R.id.activity_comments_recycler_view) as RecyclerView

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
