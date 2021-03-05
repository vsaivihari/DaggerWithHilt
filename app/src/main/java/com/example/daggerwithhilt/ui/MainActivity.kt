package com.example.daggerwithhilt.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.daggerwithhilt.R
import com.example.daggerwithhilt.model.Blog
import com.example.daggerwithhilt.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"
    private lateinit var text: TextView
    private lateinit var progress: ProgressBar
    private  val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.text)
        progress = findViewById(R.id.progress)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { datastate ->
            when (datastate) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(datastate.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(datastate.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if(!message.isNullOrEmpty()) {
            text.text = message
        } else {
            text.text = "unknown Error"
        }
    }

    private fun displayProgressBar(shouldDisplay: Boolean) {
        progress.visibility = if(shouldDisplay) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n")
        }
        text.text = sb.toString()
    }
}