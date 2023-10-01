package com.example.userloginkotlinapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userloginkotlinapplication.R
import com.example.userloginkotlinapplication.service.model.UserPostModel1

class UserPostAdapter(item: List<UserPostModel1>, context: Context) :
    RecyclerView.Adapter<UserPostAdapter.PostViewHolder>() {
    var item: List<UserPostModel1>
    private val context: Context
    private var postwithCategory: PostWithCategory? = null

    init {
        this.item = item
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(context).inflate(R.layout.postlayout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post: UserPostModel1 = item[position]
        holder.body.setText(post.body)
        holder.title.setText(post.title)
        holder.nameP.setText(post.user.name)
        holder.recyclerView.setLayoutManager(
            LinearLayoutManager(
                context,RecyclerView.HORIZONTAL,false
            )
        )
        postwithCategory= PostWithCategory(context,post.category)
        holder.recyclerView.setAdapter(postwithCategory)


    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var body: TextView
        var nameP: TextView
        var imageView: ImageView
        var imgProfile: ImageView
        var recyclerView: RecyclerView

        init {
            nameP = itemView.findViewById<TextView>(R.id.tv_nameM)
            body = itemView.findViewById<TextView>(R.id.tv_bodyM)
            title = itemView.findViewById<TextView>(R.id.tv_titleM)
            imgProfile = itemView.findViewById<ImageView>(R.id.profileId)
            imageView = itemView.findViewById<ImageView>(R.id.img_PostM)
            recyclerView = itemView.findViewById(R.id.recyclerviewCatagory)
        }
    }
}
