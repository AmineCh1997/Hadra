package com.dev.hadra.adapter

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.R
import com.dev.hadra.model.Topic
import com.dev.hadra.view.activities.CommentsActivity
import com.dev.hadra.view.activities.TopicsActivity

class TopicAdapter (private var items: ArrayList<Topic>) : RecyclerView.Adapter<TopicAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.topic_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var topic = items[position]
        var context = holder?.itemView?.context

        //

        //Changing Item Bakcground color
        holder?.lnr_layout?.setBackgroundColor(Color.parseColor("#FF4DB6AC"))




        holder?.itemView.setOnClickListener {
            Toast.makeText(context,"Comments section", Toast.LENGTH_LONG ).show()
            val myIntent = Intent(context, CommentsActivity::class.java)
            //Passing Topic ID
            //myIntent.putExtra("", con)
            context.startActivity(myIntent)



        }


    }


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var topic_username: TextView? = null
        var topic_userimage: ImageView? = null
        var topic_createdat : TextView? = null
        var topic_subject : TextView? = null
        var topic_content : TextView? = null
        var topic_watchs : TextView? = null
        lateinit var lnr_layout: LinearLayout


        init {
            this.lnr_layout = row?.findViewById<LinearLayout>(R.id.item_topic_linear)
            this.topic_username = row?.findViewById<TextView>(R.id.item_topic_tv_topic_user_name)
            this.topic_userimage = row?.findViewById<ImageView>(R.id.item_topic_tv_topic_user_image)
            this.topic_createdat = row?.findViewById<TextView>(R.id.item_topic_tv_topic_date)
            this.topic_subject = row?.findViewById<TextView>(R.id.item_topic_tv_topic_subject)
            this.topic_content = row?.findViewById<TextView>(R.id.item_topic_tv_topic_content)
            this.topic_watchs = row?.findViewById<TextView>(R.id.item_topic_tv_topic_watchs)
        }
    }
}