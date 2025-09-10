package com.example.mytodolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytodolist.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private val activitylistBinding: ActivityListBinding by lazy {
        ActivityListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activitylistBinding.root)
        setSupportActionBar(activitylistBinding.toolbar.toolbar)
        supportActionBar?.apply {
            title = "MyToDoList"
            subtitle = "Tasks"
        }
    }
}