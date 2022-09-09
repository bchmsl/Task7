package com.example.task7.ui.main.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task7.databinding.LayoutNewCoursesBinding
import com.example.task7.extensions.toMinutes
import com.example.task7.model.CoursesResponse

class NewCoursesAdapter :
    ListAdapter<CoursesResponse.NewCourse, NewCoursesAdapter.CoursesViewHolder>(ActiveCoursesCallback()) {
    inner class CoursesViewHolder(private val binding: LayoutNewCoursesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            binding.apply {
                tvTitle.text = currentItem.title
                tvQuestion.text = currentItem.question
                tvDuration.text = currentItem.duration.toMinutes()
                root.background.setTint(Color.parseColor("#${currentItem.mainColor}"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        return CoursesViewHolder(
            LayoutNewCoursesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind()
    }

    class ActiveCoursesCallback: DiffUtil.ItemCallback<CoursesResponse.NewCourse>(){
        override fun areItemsTheSame(
            oldItem: CoursesResponse.NewCourse,
            newItem: CoursesResponse.NewCourse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CoursesResponse.NewCourse,
            newItem: CoursesResponse.NewCourse
        ): Boolean {
            return oldItem == newItem
        }


    }
}