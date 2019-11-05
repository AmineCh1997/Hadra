package com.dev.hadra.view.activities

import android.app.Dialog
import android.app.ProgressDialog
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
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import com.dev.hadra.di.InjectorUtils
import com.dev.hadra.viewmodel.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.ArrayList
import androidx.lifecycle.Observer;

class TopicsActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var mProgressBar: ProgressDialog? = null
    val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    private val TAG = "Topic Activity"
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)
        val homeFactory = InjectorUtils.provideUHomeViewModelFactory()
        homeViewModel = ViewModelProviders.of(this,homeFactory).get(HomeViewModel::class.java)

        var cat = getIntent().getSerializableExtra("Category") as? Category
        /*activity_topics_tv_id.setText(cat?.id)
        activity_topics_tv_name.setText(cat?.name)
        activity_topics_tv_color.setText(cat?.color)*/
        getSupportActionBar()?.setTitle(cat?.name)

        //item_topic_linear.setBackgroundColor(Color.parseColor(cat?.color))


        recyclerView = findViewById(R.id.activity_topics_recycler_view) as RecyclerView
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // Initialize Progress Bar
        mProgressBar = ProgressDialog(this)


        generateData(cat!!.id!!)


        activity_topics_fb.setOnClickListener {
            AlertAddTopic(cat!!.id!!)
        }
    }
    private fun generateData(categorie_id: String) {
        homeViewModel.topicGetByCategory(categorie_id).observe(this,androidx.lifecycle.Observer {
            var topicsResult = ArrayList<Topic>()
            it.forEach {
                topicsResult.add(it)
            }
            var adapter = TopicAdapter(topicsResult)
            val layoutManager = LinearLayoutManager(applicationContext)
            recyclerView?.layoutManager = layoutManager
            recyclerView?.itemAnimator = DefaultItemAnimator()
            recyclerView?.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }
    /*private fun generateData(categorie_id: String) {

        var topicsResult = ArrayList<Topic>()

        //Getting the Topic
        db.collection("Topic").get().addOnSuccessListener { result ->
            for (document in result) {
                var topic = Topic()
                topic.id = document.id
                topic.subject = document.data.get("subject") as String
                topic.content = document.data.get("content") as String
                topic.created_at = document.getTimestamp("created_at")?.toDate()
                Log.e("TOPIC", "this is topic without " + topic)

                //Getting Category of the Topic
                document.getDocumentReference("Category")!!.get()
                    .addOnSuccessListener { categoryDoc ->

                        var category = Category(
                            categoryDoc.id, categoryDoc.data?.get("name").toString(),
                            categoryDoc.data?.get("color").toString()
                        )
                        topic.cat = category


                        Log.e(
                            "CATEGORY",
                            "This is category " + categoryDoc.data + " and It's id : " + categoryDoc.id + category
                        )

                        if(categorie_id == topic.cat!!.id) {
                            document.getDocumentReference("User")!!.get()
                                .addOnSuccessListener { userDoc ->
                                    var user = User(
                                        userDoc.id, userDoc.data?.get("email").toString(),
                                        userDoc.data?.get("username").toString()
                                    )
                                    Log.e(
                                        "USER",
                                        "This is user" + userDoc.data + "and It's id : " + userDoc.id + user
                                    )
                                    topic.user = user
                                    topicsResult.add(topic)
                                    Log.e("FFFFFF", "HHHHHH" + topicsResult)
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

                    }.addOnFailureListener { exception ->
                        Log.e("failure", "Error getting documents: ", exception)
                    }


            }


        }.addOnFailureListener { exception ->
            Log.e("failure", "Error getting documents: ", exception)
        }

    }*/

    fun AlertAddTopic(categorie_id: String) {


        val dialog = Dialog(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
        dialog.setTitle("Add a topic")
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.add_topic_alertdialog)

        val subject = dialog.findViewById(R.id.et_topic_name) as EditText
        val content = dialog.findViewById(R.id.et_topic_content) as EditText
        val add_bt = dialog.findViewById(R.id.bt_add_topic) as Button
        val cancel_bt = dialog.findViewById(R.id.bt_cancel_topic) as Button


        dialog.show()


        cancel_bt.setOnClickListener {
            dialog.dismiss()
        }

        add_bt.setOnClickListener {

            if (subject.text.toString().isEmpty()) {

                subject.error = "Topic is missing !"
                subject.requestFocus()
                return@setOnClickListener
            }
            if (content.text.toString().isEmpty()) {

                content.error = "Content is missing !"
                content.requestFocus()
                return@setOnClickListener
            } else {
                homeViewModel.topicAdd(subject.text.toString(),content.text.toString(),categorie_id,"5dbc084d3549036e541671ce")
                    .observe(this,Observer {

                       if(it != null) {
                           Log.e(TAG,it.subject)
                           dialog.dismiss()
                       }
                    })
               /* //Adding a topic
                mProgressBar!!.setMessage("Registering Topic...")
                mProgressBar!!.show()
                val newTopic = db.collection("Topic").document()
                val user = db.collection("User").document(auth!!.currentUser!!.uid)
                val category = db.collection("Category").document(categorie_id)
                val userId = auth!!.currentUser!!.uid
                val items = HashMap<String, Any>()
                items.put("content", content.text.toString())
                items.put("created_at", Timestamp(Date()))
                items.put("subject", subject.text.toString())
                items.put("Category", category)
                items.put("User", user)

                newTopic.set(items).addOnSuccessListener {
                    mProgressBar!!.hide()
                    Toast.makeText(
                        applicationContext,
                        "Topic Added : " + subject.text.toString(),
                        Toast.LENGTH_SHORT
                    ).show()


                }.addOnFailureListener {
                    Toast.makeText(
                        applicationContext,
                        "Sorry an error has occuered , please try again later",
                        Toast.LENGTH_SHORT
                    ).show()
                }


                dialog.dismiss()

*/
            }

        }


    }

}
