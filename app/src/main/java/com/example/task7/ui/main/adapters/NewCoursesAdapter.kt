package com.example.task7.ui.main.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Icon
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task7.R
import com.example.task7.databinding.LayoutNewCoursesBinding
import com.example.task7.extensions.setImage
import com.example.task7.extensions.toColor
import com.example.task7.extensions.toMinutes
import com.example.task7.model.CoursesResponse
import com.example.task7.model.IconType

class NewCoursesAdapter :
    ListAdapter<CoursesResponse.NewCourse, NewCoursesAdapter.CoursesViewHolder>(ActiveCoursesCallback()) {
    inner class CoursesViewHolder(private val binding: LayoutNewCoursesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            binding.apply {
                tvTitle.text = currentItem.title
                tvQuestion.text = currentItem.question
                tvDuration.text = currentItem.duration?.toMinutes()
                root.background.setTint(currentItem.mainColor.toColor())
                ivButtonStart.imageTintList = ColorStateList.valueOf(currentItem.mainColor.toColor())
                val icon = when(currentItem.iconType){
                    IconType.CARD.value -> IconType.CARD.icon
                    IconType.SETTINGS.value -> IconType.SETTINGS.icon
                    else -> R.drawable.ic_launcher_foreground
                }
                ivIcon.setImageResource(icon)
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