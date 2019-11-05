package com.dev.hadra.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.R
import com.dev.hadra.model.Comment
import com.dev.hadra.model.Topic
import com.dev.hadra.view.activities.TopicsActivity

class CommentAdapter (private var items: ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.comment_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var comment = items[position]
        holder.comment_username!!.text=comment.user!!.username
        holder.comment_content!!.text = comment.content
        holder.comment_createdat!!.text = comment.createdAt
    }


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var comment_username: TextView? = null
        var comment_userimage: ImageView? = null
        var comment_createdat : TextView? = null
        var comment_content : TextView? = null


        lateinit var cns_layout: ConstraintLayout
        init {
            this.comment_username = row?.findViewById<TextView>(R.id.item_comment_tv_username)
            this.comment_userimage = row?.findViewById<ImageView>(R.id.item_comment_iv_user)
            this.comment_createdat = row?.findViewById<TextView>(R.id.item_comment_tv_created_at)
            this.comment_content = row?.findViewById<TextView>(R.id.item_comment_tv_content)
        }
    }
}