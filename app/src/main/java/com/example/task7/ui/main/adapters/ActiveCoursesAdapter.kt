package com.example.task7.ui.main.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task7.databinding.LayoutActiveCoursesBinding
import com.example.task7.extensions.setImage
import com.example.task7.model.CoursesResponse

class ActiveCoursesAdapter :
    ListAdapter<CoursesResponse.ActiveCourse, ActiveCoursesAdapter.CoursesViewHolder>(ActiveCoursesCallback()) {
    inner class CoursesViewHolder(private val binding: LayoutActiveCoursesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            binding.apply {
                ivIcon.setImage(currentItem.image)
                tvTitle.text = currentItem.title
                tvBookingTime.text = currentItem.bookingTime
                pbProgress.progress = currentItem.progress.toInt()
                root.background.setTint(Color.parseColor("#${currentItem.mainColor}"))
                root.alpha = (currentItem.backgroundColorPercent.toInt()/100).toFloat()
                pbProgress.setIndicatorColor(Color.parseColor("#${currentItem.mainColor}"))

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        return CoursesViewHolder(
            LayoutActiveCoursesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind()
    }

    class ActiveCoursesCallback: DiffUtil.ItemCallback<CoursesResponse.ActiveCourse>(){
        override fun areItemsTheSame(
            oldItem: CoursesResponse.ActiveCourse,
            newItem: CoursesResponse.ActiveCourse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CoursesResponse.ActiveCourse,
            newItem: CoursesResponse.ActiveCourse
        ): Boolean {
            return oldItem == newItem
        }

    }
}