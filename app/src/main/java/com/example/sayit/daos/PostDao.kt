package com.example.sayit.daos

import com.example.sayit.models.Post
import com.example.sayit.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PostDao {

    val db = FirebaseFirestore.getInstance()
    val postCollection = db.collection("posts")         //posts collection created
    val auth = Firebase.auth                               //to find current user

    fun addPost(text: String) {

        GlobalScope.launch {
            val currentUserId = auth.currentUser!!.uid           //!! it ensures that auth.currentUser is 100% not null
            val userDao = UserDao()
            val user = userDao.getUserByID(currentUserId).await().toObject(User::class.java)!!    //becuase we're getting task i.e document snapshots but not actual document, so we need to parse object to User class

            val currentTime = System.currentTimeMillis()    //returns long
            val post = Post(text,user, currentTime)
            postCollection.document().set(post)
        }
    }
}