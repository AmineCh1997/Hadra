package com.dev.hadra.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.R
import com.dev.hadra.adapter.CategoryAdapter
import com.dev.hadra.di.InjectorUtils
import com.dev.hadra.model.Category
import com.dev.hadra.viewmodel.HomeViewModel
import com.dev.hadra.viewmodel.UserViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class HomeFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var userViewModel: UserViewModel
    private val TAG = "Home Fragment"
    private lateinit var homeViewModel: HomeViewModel

    // [START get_firestore_instance]
    val db = FirebaseFirestore.getInstance()
    // [END get_firestore_instance]

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)*/
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = root.findViewById(R.id.fragment_home_recycler_view)
        generateData()
       /* val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userFactory = InjectorUtils.provideUserViewModelFactory()
        userViewModel = ViewModelProviders.of(activity!!,userFactory).get(UserViewModel::class.java)
        val homeFactory = InjectorUtils.provideUHomeViewModelFactory()
        homeViewModel = ViewModelProviders.of(activity!!,homeFactory).get(HomeViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           /* homeViewModel.categoryAll().observe(this, Observer {
                var id:String=""
                it.forEach {
                    Log.e(TAG,it.name)
                    id=it.id!!
                }
                homeViewModel.categoryGetById(id).observe(this, Observer {
                    Log.e(TAG,it.id)

                    homeViewModel.topicAdd("topic subject","topic content",it.id!!).observe(this, Observer {
                        Log.e(TAG,it.category!!.name)
                        val gson = Gson()
                        Log.e(TAG,gson.toJson(it).toString())
                    })
                })

            })
            homeViewModel.topicGetById("5dbe2d8ee906a3738c3a833c").observe(this, Observer {
                val gson = Gson()
                Log.e(TAG,gson.toJson(it).toString())
            })*/
       /* homeViewModel.commentAdd("comment from android",null
            ,"5dbee02b803e3e7f845a9e61","5db5d8d4f8c61d42e4ca57af").observe(this, Observer {
            Log.e(TAG,it.content)
        })*/
    }

    private fun UserRequests(){
        var id:String = ""
        userViewModel.getAllUsers().observe(this, Observer {
            it.forEach {
                Log.e("HomeFragment",it.toString())
            }

        })
        userViewModel.login("selim","123456").observe(this, Observer {
            Log.e(TAG,it.token)
            Log.e(TAG,it.id)
            id=it.id!!
            /* userViewModel.update("selim","123456",id).observe(this, Observer {
                 Log.e(TAG,it.username)
             })*/
            userViewModel.getUserById(id).observe(this, Observer {
                Log.e(TAG,it.toString())
            })
        })

        userViewModel.register("testandroid","hadra@hadra.tn","123456").observe(this, Observer {
            Log.e(TAG,it.username)
        })
    }
    private fun generateData(){
        var categoriesResult = ArrayList<Category>()
        homeViewModel.categoryAll().observe(this, Observer {
            it.forEach {
                categoriesResult.add(it)
                var adapter = CategoryAdapter(categoriesResult)
                val layoutManager = LinearLayoutManager(activity?.applicationContext)
                recyclerView?.layoutManager = layoutManager
                recyclerView?.itemAnimator = DefaultItemAnimator()
                recyclerView?.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })
    }
   /* private fun generateData() {

        var categoriesResult = ArrayList<Category>()

        db.collection("Category")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.e("success", "${document.id} => ${document.data.get("name")}")
                    var cat: Category = Category(document.id as String,
                        document.data.get("name") as String,document.data.get("color") as String )
                        categoriesResult.add(cat)
                }
                var adapter = CategoryAdapter(categoriesResult)
                val layoutManager = LinearLayoutManager(activity?.applicationContext)
                recyclerView?.layoutManager = layoutManager
                recyclerView?.itemAnimator = DefaultItemAnimator()
                recyclerView?.adapter = adapter
                adapter.notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.e("failure", "Error getting documents: ", exception)
            }
        // [END get_all_users]


      /*  for (i in 0..9) {
            var cat: Category = Category("Poilitics")
            categoriesResult.add(cat)
        }*/
    }*/

}