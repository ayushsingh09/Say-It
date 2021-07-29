package com.example.sayit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Util
import com.example.sayit.databinding.ItemPostBinding
import com.example.sayit.models.Post
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class PostAdapter(options: FirestoreRecyclerOptions<Post>) : FirestoreRecyclerAdapter<Post, PostAdapter.PostViewHolder>(
    options
) {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val PostAdap = ItemPostBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {


        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {

        holder.PostAdap.postTitle.text = model.text
        holder.PostAdap.userName.text = model.creator.displayName
        Glide.with(holder.PostAdap.userName.context).load(model.creator.imageUrl).circleCrop().into(holder.PostAdap.userImage)
        holder.PostAdap.likeCount.text = model.likedBy.size.toString()
        holder.PostAdap.createdAt.text = Utils.getTimeAgo(model.createdAt)
    }
}