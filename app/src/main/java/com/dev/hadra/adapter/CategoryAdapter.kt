package com.dev.hadra.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import com.dev.hadra.R
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.model.Category
import com.dev.hadra.view.activities.SignupActivity
import com.dev.hadra.view.activities.TopicsActivity
import androidx.core.content.ContextCompat.startActivity



class CategoryAdapter(private var items: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var category = items[position]
        var context = holder?.itemView?.context
        holder?.txtName?.text = category.name
        holder?.cns_layout?.setBackgroundColor(Color.parseColor(category.color))

        holder?.itemView.setOnClickListener {
            Toast.makeText(context,"Welcome to "+category.name,Toast.LENGTH_LONG ).show()
            val myIntent = Intent(context, TopicsActivity::class.java)
            myIntent.putExtra("Category", category)
            context.startActivity(myIntent)


        }

    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var txtName: TextView? = null
        lateinit var cns_layout: ConstraintLayout
        init {
            this.txtName = row?.findViewById<TextView>(R.id.item_category_tv_category_name)
            this.cns_layout = row?.findViewById<ConstraintLayout>(R.id.item_category_constraint)
        }
    }


}