package com.dev.hadra.view.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.R
import com.dev.hadra.adapter.CategoryAdapter
import com.dev.hadra.adapter.TopicAdapter
import com.dev.hadra.model.Category
import com.dev.hadra.model.Topic
import kotlinx.android.synthetic.main.activity_topics.*
import kotlinx.android.synthetic.main.topic_item.*

class TopicsActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)


        var cat = getIntent().getSerializableExtra("Category") as? Category
        activity_topics_tv_id.setText(cat?.id)
        activity_topics_tv_name.setText(cat?.name)
        activity_topics_tv_color.setText(cat?.color)
        activity_topics_relative.setBackgroundColor(Color.parseColor(cat?.color))

        recyclerView = findViewById(R.id.activity_topics_recycler_view) as RecyclerView

        generateData()

    }

    private fun generateData() {

        var topicsResult = ArrayList<Topic>()



        // [END get_all_users]


         for (i in 0..9) {
              var topic: Topic = Topic()
              topicsResult.add(topic)
          }
        var adapter = TopicAdapter(topicsResult)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
