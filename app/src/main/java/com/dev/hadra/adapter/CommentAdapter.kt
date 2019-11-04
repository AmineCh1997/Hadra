package com.dev.hadra.adapter

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.R
import com.dev.hadra.model.Comment
import com.dev.hadra.model.Topic
import com.dev.hadra.view.activities.TopicsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CommentAdapter (private var items: ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private lateinit var auth: FirebaseAuth
    val db = FirebaseFirestore.getInstance()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        auth = FirebaseAuth.getInstance()
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.comment_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var comment = items[position]

        holder.comment_username?.setText(comment.user?.username)
        holder.comment_content?.setText(comment.content)
        holder.comment_createdat?.setText(comment.created_at.toString())

        if(auth.currentUser?.uid == comment.user?.id){
            holder?.comment_delete?.visibility = View.VISIBLE
        }else{
            holder?.comment_delete?.visibility = View.GONE
        }

        holder?.comment_delete?.setOnClickListener{


            var context = holder.itemView.context
            val builder = AlertDialog.Builder(context)

            // Set the alert dialog title
            builder.setTitle("Delete your comment")

            // Display a message on alert dialog
            builder.setMessage("Are you sure you want to delete your comment ?")

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("YES"){dialog, which ->
                // Do something when user press the positive button
                db.collection("Comment").document(comment.id)
                    .delete()
                    .addOnSuccessListener {
                        Log.d("SUCCESS", "DocumentSnapshot successfully deleted!")
                        items.remove(comment)
                        this.notifyItemRemoved(position)
                    }
                    .addOnFailureListener {
                            e -> Log.w("ERROR", "Error deleting document", e)
                    }

            }

            // Display a negative button on alert dialog
            builder.setNegativeButton("No"){dialog,which ->
                dialog.dismiss()
            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()

        }



    }


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var comment_username: TextView? = null
        var comment_userimage: ImageView? = null
        var comment_createdat : TextView? = null
        var comment_content : TextView? = null
        var comment_delete : ImageView? = null


        lateinit var cns_layout: ConstraintLayout
        init {
            this.comment_username = row?.findViewById<TextView>(R.id.item_comment_tv_username)
            this.comment_userimage = row?.findViewById<ImageView>(R.id.item_comment_iv_user)
            this.comment_createdat = row?.findViewById<TextView>(R.id.item_comment_tv_created_at)
            this.comment_content = row?.findViewById<TextView>(R.id.item_comment_tv_content)
            this.comment_delete = row?.findViewById<ImageView>(R.id.item_comment_iv_delete)
        }
    }
}