package com.example.sayit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sayit.daos.PostDao
import com.example.sayit.daos.UserDao
import com.example.sayit.databinding.ActivityCreatePostBinding

class CreatePostActivity : AppCompatActivity() {

    lateinit var post: ActivityCreatePostBinding
    private  lateinit var  postDao: PostDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        post = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(post.root)

        post.postButton.setOnClickListener{

            val input = post.postInput.text.toString().trim()
            if(input.isNotEmpty()){
                postDao.addPost(input)
            }

        }
    }
}