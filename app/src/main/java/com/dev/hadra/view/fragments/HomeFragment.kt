package com.dev.hadra.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.R
import com.dev.hadra.adapter.CategoryAdapter
import com.dev.hadra.model.Category
import com.dev.hadra.viewmodel.HomeViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private var recyclerView: RecyclerView? = null


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

    private fun generateData() {

        var categoriesResult = ArrayList<Category>()

        db.collection("Category")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.e("success", "${document.id} => ${document.data.get("name")}")
                    var cat: Category = Category(document.data.get("name") as String,document.data.get("color") as String )
                    categoriesResult.add(cat)

                }
                var adapter = CategoryAdapter(categoriesResult)
                val layoutManager = LinearLayoutManager(activity?.applicationContext)
                recyclerView?.layoutManager = layoutManager
                recyclerView?.itemAnimator = DefaultItemAnimator()
                recyclerView?.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("failure", "Error getting documents: ", exception)
            }
        // [END get_all_users]


      /*  for (i in 0..9) {
            var cat: Category = Category("Poilitics")
            categoriesResult.add(cat)
        }*/
    }

}