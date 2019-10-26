package com.dev.hadra.adapter

import android.view.LayoutInflater
import android.view.View
import com.dev.hadra.R
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.hadra.model.Category

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
        holder?.txtName?.text = category.name
    }


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var txtName: TextView? = null
        init {
            this.txtName = row?.findViewById<TextView>(R.id.item_category_tv_category_name)
        }
    }

}