package com.example.userloginkotlinapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userloginkotlinapplication.R
import com.example.userloginkotlinapplication.service.model.UserPostModel1
import com.example.userloginkotlinapplication.utils.CustomOnClickListener

class MyPostAdapter : RecyclerView.Adapter<MyPostAdapter.MyPostViewHolder> {
    var item: List<UserPostModel1>
    private var context: Context
   var listener: CustomOnClickListener?=null
    private var postwithCategory: PostWithCategory? = null

    constructor(item: List<UserPostModel1>, context: Context) {
        this.item = item
        this.context = context
    }

    constructor(item: List<UserPostModel1>, context: Context, listener: CustomOnClickListener?) {
        this.item = item
        this.context = context
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {
        return MyPostViewHolder(
            LayoutInflater.from(context).inflate(R.layout.mypostm, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        val post = item[position]
        holder.body.setText(post.body)
        holder.titlem.setText(post.title)
        holder.name.setText(post.user.name)

        holder.recyclerView.setLayoutManager(
            LinearLayoutManager(
                context,RecyclerView.HORIZONTAL,false
            )
        )
        postwithCategory= PostWithCategory(context,post.category)
        holder.recyclerView.setAdapter(postwithCategory)



        holder.itemView.findViewById<View>(R.id.dots).setOnClickListener {
            val popupMenu =
                PopupMenu(context, holder.itemView.findViewById<View>(R.id.dots))
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.updatep -> {
                        post._id
                        listener?.customOnClickListener(position, 1, post)
                    }
                    R.id.deletepo -> listener?.customOnClickListener(position, 2, post)
                    R.id.cancel -> popupMenu.dismiss()
                }
                true
            }
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class MyPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var body: TextView
        var titlem: TextView
        var imageViewm: ImageView
        var imgProfile: ImageView
        var imgDots: ImageView
        var recyclerView: RecyclerView
        init {
            titlem = itemView.findViewById<TextView>(R.id.tv_titleM)
            body = itemView.findViewById<TextView>(R.id.tv_bodyM)
            imageViewm = itemView.findViewById<ImageView>(R.id.img_PostM)
            imgProfile = itemView.findViewById<ImageView>(R.id.profileM)
            imgDots = itemView.findViewById<ImageView>(R.id.dots)
            name = itemView.findViewById<TextView>(R.id.tv_nameM)
            recyclerView = itemView.findViewById(R.id.myrecyclerviewCatagory)

        }
    }
}
