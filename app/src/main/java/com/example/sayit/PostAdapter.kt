package com.example.sayit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sayit.databinding.ItemPostBinding
import com.example.sayit.models.Post
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PostAdapter(options: FirestoreRecyclerOptions<Post>, val listner: IPostAdaptor) : FirestoreRecyclerAdapter<Post, PostAdapter.PostViewHolder>(
    options
) {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val PostAdap = ItemPostBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {


        val viewHolder = PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))
        viewHolder.PostAdap.likeButton.setOnClickListener{
            listner.onLikeClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {

        holder.PostAdap.postTitle.text = model.text
        holder.PostAdap.userName.text = model.creator.displayName
        Glide.with(holder.PostAdap.userName.context).load(model.creator.imageUrl).circleCrop().into(holder.PostAdap.userImage)
        holder.PostAdap.likeCount.text = model.likedBy.size.toString()
        holder.PostAdap.createdAt.text = Utils.getTimeAgo(model.createdAt)

        val auth = Firebase.auth
        val currentUserID = auth.currentUser!!.uid
        val isLiked = model.likedBy.contains(currentUserID)
        if(isLiked){
            holder.PostAdap.likeButton.setImageDrawable(ContextCompat.getDrawable(holder.PostAdap.likeButton.context,R.drawable.ic_liked))
        }
        else{
            holder.PostAdap.likeButton.setImageDrawable(ContextCompat.getDrawable(holder.PostAdap.likeButton.context,R.drawable.ic_unliked))
        }
    }
}

interface IPostAdaptor{
    fun onLikeClicked(postId: String)
}