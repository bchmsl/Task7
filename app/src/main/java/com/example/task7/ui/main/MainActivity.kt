package com.example.task7.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.task7.databinding.ActivityMainBinding
import com.example.task7.model.CoursesResponse
import com.example.task7.network.ResponseHandler
import com.example.task7.ui.main.adapters.ActiveCoursesAdapter
import com.example.task7.ui.main.adapters.NewCoursesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    private val activeCoursesAdapter by lazy{ActiveCoursesAdapter()}
    private val newCoursesAdapter by lazy{ NewCoursesAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        start()
    }

    private fun start() {
        observers()
    }

    private fun observers() {
        lifecycleScope.launch {
            viewModel.getCourses()
            viewModel.responseState.collect {
                when(it){
                    is ResponseHandler.Success -> handleSuccess(it.data)
                    is ResponseHandler.Error -> handleError(it.errorMessage)
                    else -> {}
                }
            }
        }
    }

    private fun handleError(errorMessage: Throwable) {
        Toast.makeText(this, "${errorMessage.message}", Toast.LENGTH_SHORT).show()
    }

    private fun handleSuccess(data: CoursesResponse) {
        setupRecyclers(data)
    }

    private fun setupRecyclers(data: CoursesResponse) {
        with(activeCoursesAdapter){
            binding.rvActiveCourses.adapter = this
            this.submitList(data.activeCourses)
        }
        with(newCoursesAdapter){
            binding.rvNewCourses.adapter = this
            this.submitList(data.newCourse)
        }

    }
}