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
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dev.hadra.di.InjectorUtils
import com.dev.hadra.model.Category
import com.dev.hadra.viewmodel.HomeViewModel


class CommentsActivity : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null
    private lateinit var homeViewModel: HomeViewModel
    private val TAG = "Comment Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        val homeFactory = InjectorUtils.provideUHomeViewModelFactory()
        homeViewModel = ViewModelProviders.of(this,homeFactory).get(HomeViewModel::class.java)
        var topic = getIntent().getSerializableExtra("topic") as String
        getSupportActionBar()?.hide()
        recyclerView = findViewById(R.id.activity_comments_recycler_view) as RecyclerView

        activity_comments_et_comment_content.addTextChangedListener {
            if(activity_comments_et_comment_content.text.toString().isEmpty()){

                activity_comments_tv_post.setTypeface(activity_comments_tv_post.typeface, Typeface.NORMAL)
            }else{
                activity_comments_tv_post.setTypeface(activity_comments_tv_post.typeface, Typeface.BOLD)
            }
        }

        activity_comments_tv_post.setOnClickListener {
            homeViewModel.commentAdd(activity_comments_et_comment_content.text.toString(),null,topic,"5dbc084d3549036e541671ce")
        }

        generateData(topic)
    }

    private fun generateData(topic:String) {

        var commentsResult = ArrayList<Comment>()



        // [END get_all_users]


      homeViewModel.commentGetByTopic(topic).observe(this, Observer {
          Log.e(TAG,"after observe")
          Log.e(TAG,"list size "+it.size)
          it.forEach {
              Log.e(TAG,it.content)
              commentsResult.add(it)
          }
          var adapter = CommentAdapter(commentsResult)
          val layoutManager = LinearLayoutManager(applicationContext)
          recyclerView?.layoutManager = layoutManager
          recyclerView?.itemAnimator = DefaultItemAnimator()
          recyclerView?.adapter = adapter
          adapter.notifyDataSetChanged()
      })

    }
}
