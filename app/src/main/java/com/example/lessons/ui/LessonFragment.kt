package com.example.lessons.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lessons.R
import com.example.lessons.databinding.FragmentLessonBinding
import com.example.lessons.data.local.LocalStorage
import com.example.lessons.utils.gone
import com.example.lessons.utils.set
import com.example.lessons.utils.visible

class LessonFragment : Fragment(R.layout.fragment_lesson) {
    private lateinit var binding: FragmentLessonBinding
    private lateinit var videoUrl: String
    private lateinit var name: String
    private lateinit var description: String
    private lateinit var thumbnail: String
    private var positionInAdapter = -1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLessonBinding.bind(view)

        initVariables()

        initObservers()

        initListeners()
    }

    private fun initListeners() {
        binding.icYoutube.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("video_url", videoUrl)
            findNavController().navigate(
                R.id.action_lessonFragment_to_videoPlayerFragment,
                bundle
            )
        }

        binding.ivLocked.setOnClickListener {
            showPurchase()
        }
    }

    private fun showPurchase() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvBuy: AppCompatTextView = dialog.findViewById(R.id.tv_buy)
        val tvOk: AppCompatTextView = dialog.findViewById(R.id.tv_ok)

        tvBuy.setOnClickListener {
            LocalStorage.pref.edit().putBoolean("isActive", true).apply()
            dialog.dismiss()
        }

        tvOk.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun initObservers() {
        if (LocalStorage.pref.getBoolean("isActive", false)) {
            binding.icYoutube.visible()
            binding.ivLocked.gone()
            binding.bgView.gone()

        } else {
            if (positionInAdapter in 0..2) {
                binding.icYoutube.visible()
                binding.ivLocked.gone()
                binding.bgView.gone()
            } else {
                binding.icYoutube.gone()
                binding.ivLocked.visible()
                binding.bgView.visible()
            }
        }
    }

    private fun initVariables() {
        videoUrl = arguments!!.getString("video_url", "")
        name = arguments!!.getString("name", "")
        description = arguments!!.getString("description", "")
        thumbnail = arguments!!.getString("thumbnail", "")
        positionInAdapter = arguments!!.getInt("position", -1)

        binding.apply {
            tvName.text = name
            tvDescription.text = description
            ivThumbnail.set(thumbnail)
        }
    }
}