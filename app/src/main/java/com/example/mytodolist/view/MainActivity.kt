package com.example.mytodolist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
        setSupportActionBar(activityMainBinding.toolbar.toolbar)
        supportActionBar?.apply {
            title = "My To Do List"
            subtitle = "My Tasks"
        }
    }
}