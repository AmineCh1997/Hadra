package com.dev.hadra.view.activities

import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.R
import com.dev.hadra.adapter.TopicAdapter
import com.dev.hadra.model.Category
import com.dev.hadra.model.Topic
import kotlinx.android.synthetic.main.activity_topics.*
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import com.dev.hadra.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.topic_item.*
import java.sql.Timestamp


class TopicsActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)


        var cat = getIntent().getSerializableExtra("Category") as? Category
        activity_topics_tv_id.setText(cat?.id)
        activity_topics_tv_name.setText(cat?.name)
        activity_topics_tv_color.setText(cat?.color)

        //item_topic_linear.setBackgroundColor(Color.parseColor(cat?.color))


        recyclerView = findViewById(R.id.activity_topics_recycler_view) as RecyclerView


        generateData(cat!!.id)


        activity_topics_fb.setOnClickListener{
            AlertAddTopic()
        }
    }

    private fun generateData(categorie_id : String ) {

        var topicsResult = ArrayList<Topic>()

        db.collection("Topic").get().addOnSuccessListener { result ->
            for (document in result) {

                var topic = Topic()
                document.getDocumentReference("Category")!!.get().addOnSuccessListener { categoryDoc ->
                    var category = categoryDoc.data as Category
                    topic.cat=category
                    print("this is category "+category)
                }
                document.getDocumentReference("User")!!.get().addOnSuccessListener { categoryDoc ->
                    var user = categoryDoc.data as User
                    topic.user=user
                    print("this is user "+user)
                }
               /* var user = User("ddd","dddfd","aaaaaa")
                var category = Category("3BwO5J92YYduUZfIhmZQ","sdsdssd","xdsdsdsd")*/


                Log.e("success", "${document.id} => ${document.data.get("subject")}")

                topic.id= document.id as String
                topic.subject= document.data.get("subject") as String
                topic.content= document.data.get("content") as String
                topic.created_at= document.data.get("created_at") as com.google.firebase.Timestamp

                print("this is topic "+topic)

                if(categorie_id == topic.cat.id){
                    topicsResult.add(topic)
                }

                }


            var adapter = TopicAdapter(topicsResult)
            val layoutManager = LinearLayoutManager(applicationContext)
            recyclerView?.layoutManager = layoutManager
            recyclerView?.itemAnimator = DefaultItemAnimator()
            recyclerView?.adapter = adapter
            adapter.notifyDataSetChanged()

            }.addOnFailureListener { exception ->
                Log.e("failure", "Error getting documents: ", exception)
            }

    }

    fun AlertAddTopic() {


        val dialog = Dialog(this,R.style.Theme_AppCompat_Light_Dialog_Alert)
        dialog .setTitle("Add a topic")
        dialog .setCancelable(false)
        dialog .setContentView(R.layout.add_topic_alertdialog)

        val name = dialog.findViewById(R.id.et_topic_name) as EditText
        val content = dialog.findViewById(R.id.et_topic_content) as EditText
        val add_bt = dialog.findViewById(R.id.bt_add_topic) as Button
        val cancel_bt = dialog.findViewById(R.id.bt_cancel_topic) as Button


        dialog.show()


        cancel_bt.setOnClickListener {
            dialog.dismiss()
        }

        add_bt.setOnClickListener {

            if (name.text.toString().isEmpty()){

                name.error = "Topic is missing !"
                name.requestFocus()
                return@setOnClickListener
            }
            if (content.text.toString().isEmpty()){

                content.error = "Content is missing !"
                content.requestFocus()
                return@setOnClickListener
            }
            else{
                dialog.dismiss()

                Toast.makeText(
                    applicationContext,
                    "Topic Added : " + name.text.toString()  ,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }


    }

}
