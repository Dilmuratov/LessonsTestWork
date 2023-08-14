package com.example.lessons.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lessons.R
import com.example.lessons.databinding.FragmentMainBinding
import com.example.lessons.presentation.LessonsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val adapter = LessonAdapter()
    private val viewModel: LessonsViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        initObservers()

        getLessons()

        binding.recyclerView.adapter = adapter


        initListeners()

    }

    private fun initListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            getLessons()
        }

        adapter.setOnItemClickListener { lesson, position ->
            val bundle = Bundle()
            bundle.putInt("position", position)
            bundle.putString("name", lesson.name)
            bundle.putString("description", lesson.description)
            bundle.putString("thumbnail", lesson.thumbnail)
            bundle.putString("video_url", lesson.video_url)

            findNavController().navigate(
                R.id.action_mainFragment_to_lessonFragment,
                bundle
            )
        }
    }

    private fun getLessons() {
        binding.swipeRefreshLayout.isRefreshing = true
        MainScope().launch {
            viewModel.getLessons()
        }
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun initObservers() {
        viewModel.lessonsLiveData.observe(requireActivity()) {
            adapter.submitList(it)
        }
    }
}