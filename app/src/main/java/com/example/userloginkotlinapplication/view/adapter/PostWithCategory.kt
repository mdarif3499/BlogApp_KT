package com.example.userloginkotlinapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userloginkotlinapplication.R
import com.example.userloginkotlinapplication.service.model.categoryname

class PostWithCategory (var contex: Context,var  category:List<String>):
    RecyclerView.Adapter<PostWithCategory.PostWithCategoryViewHolder>()
{




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostWithCategoryViewHolder {

        return PostWithCategoryViewHolder(
            LayoutInflater.from(contex).inflate(R.layout.postwithcategory, parent, false)
        )
    }

    override fun getItemCount(): Int {
    return category.size
    }

    override fun onBindViewHolder(holder: PostWithCategoryViewHolder, position: Int) {

        holder.tv_name.setText(category.get(position))



    }






    inner  class PostWithCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tv_name: TextView

        init {
            tv_name = itemView.findViewById<TextView>(R.id.category_name)
        }

    }






}