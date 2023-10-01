package com.example.userloginkotlinapplication.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userloginkotlinapplication.R
import com.example.userloginkotlinapplication.service.model.CategoryModel
import com.example.userloginkotlinapplication.service.model.categoryname

class CategoryAdapter(var context: Context,val listItem: CategoryModel) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
   var listItem1: List<categoryname>?=null

    init {
        listItem1=listItem.data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(context).inflate(R.layout.category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = listItem1?.get(position)
       holder.tv_name.setText(item?.name)
        if (item!!.isCheckee) {
            holder.checkBox.isChecked = true
        } else {
            holder.checkBox.isChecked = false
        }
        holder.checkBox.setOnClickListener {

            if (item!!.isCheckee) {
                holder.checkBox.isChecked = false
                item!!.isCheckee=false
            } else {

                holder.checkBox.isChecked = true
                item!!.isCheckee=true

            }
        }
    }

    override fun getItemCount(): Int {
        return listItem1!!.size
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_name: TextView
        var checkBox: CheckBox

        init {
            tv_name = itemView.findViewById<TextView>(R.id.tv_NameC)
            checkBox = itemView.findViewById<CheckBox>(R.id.checkboxC)
        }
    }
}