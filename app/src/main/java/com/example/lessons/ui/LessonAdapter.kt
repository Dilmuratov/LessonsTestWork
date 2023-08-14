package com.example.lessons.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lessons.R
import com.example.lessons.data.models.Lesson
import com.example.lessons.databinding.ItemLessonBinding
import com.example.lessons.utils.set

class LessonAdapter : ListAdapter<Lesson, LessonAdapter.LessonViewHolder>(
    object : DiffUtil.ItemCallback<Lesson>() {
        override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson) = oldItem.id == newItem.id
    }
) {

    inner class LessonViewHolder(private val binding: ItemLessonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val lesson = getItem(position)

            binding.ivThumbnail.set(lesson.thumbnail)
            binding.tvName.text = lesson.name
            binding.tvDescription.text = lesson.description

            if (position <= 2) {
                binding.bgView.setBackgroundResource(R.color.active)
            } else binding.bgView.setBackgroundResource(R.color.passive)

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(lesson, position)
            }
        }
    }

    private var onItemClickListener: ((Lesson, Int) -> Unit)? = null
    fun setOnItemClickListener(block: ((Lesson, Int) -> Unit)) {
        onItemClickListener = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LessonViewHolder(
        ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(position)
    }
}