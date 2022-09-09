package com.example.task7.ui.main.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task7.databinding.LayoutActiveCoursesBinding
import com.example.task7.extensions.setImage
import com.example.task7.extensions.toColor
import com.example.task7.model.CoursesResponse

class ActiveCoursesAdapter :
    ListAdapter<CoursesResponse.ActiveCourse, ActiveCoursesAdapter.CoursesViewHolder>(
        ActiveCoursesCallback()
    ) {
    inner class CoursesViewHolder(private val binding: LayoutActiveCoursesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            binding.apply {
                ivIcon.apply {
                    currentItem.image?.let { setImage(it) }
                    background.setTint(currentItem.mainColor.toColor())
                }
                tvTitle.apply {
                    text = currentItem.title
                    setTextColor(currentItem.mainColor.toColor())
                }
                tvBookingTime.apply {
                    text = currentItem.bookingTime
                    setTextColor(currentItem.mainColor.toColor())
                }
                pbProgress.apply {
                    progress = currentItem.progress?.toInt() ?: 0
                    setIndicatorColor(currentItem.mainColor.toColor())
                }
                root.apply {
                    background.setTint(currentItem.mainColor.toColor())
                    background.alpha = currentItem.backgroundColorPercent?.toInt() ?: 50
                }
                ivStartButton.apply {
                    background.alpha = (currentItem.playButtonColorPercent?.toInt() ?: 50)
                    background.setTint(currentItem.mainColor.toColor())
                    imageTintList = ColorStateList.valueOf(currentItem.mainColor.toColor())

                }
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

    class ActiveCoursesCallback : DiffUtil.ItemCallback<CoursesResponse.ActiveCourse>() {
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