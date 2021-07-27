package com.example.sayit.daos

import com.example.sayit.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    fun addUser(user: User?){
        user?.let {
            //to use it on the background thread
            GlobalScope.launch { Dispatchers.IO
                usersCollection.document(user.uid).set(it)
            }

        }
    }
}