package com.lmkhant.diary.ui.newdiary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lmkhant.diary.R
import com.lmkhant.diary.data.model.Diary
import com.lmkhant.diary.databinding.FragmentNewDiaryBinding
import com.lmkhant.diary.viewmodel.DiaryViewModel
import java.time.Instant
import java.util.*

class NewDiaryFragment : Fragment() {
    private var _binding: FragmentNewDiaryBinding? = null
    private val binding get() = _binding!!
    private var id :Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val viewModel = ViewModelProvider(requireActivity())[DiaryViewModel::class.java]
        _binding = FragmentNewDiaryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.ivClose.setOnClickListener {
            findNavController().navigateUp()
        }

        arguments?.let { it ->
            id = it.getInt("id")

            binding.etTitle.setText(it.getString("title"))
            binding.etText.setText(it.getString("description"))

        }
        binding.ivDone.setOnClickListener {

            if(id != -1){
                val diary = Diary(Date.from(Instant.now()),
                    binding.etTitle.text.toString(),
                    binding.etText.text.toString())
                diary.id = id
                viewModel.update(diary)
            }else viewModel.insert(
                Diary(
                    Date.from(Instant.now()),
                    binding.etTitle.text.toString(),
                    binding.etText.text.toString()
                )
            )

            findNavController().navigateUp()
        }

        changeBottomNavVisibility(View.GONE)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        changeBottomNavVisibility(View.VISIBLE)
        _binding = null
    }

    private fun changeBottomNavVisibility(int: Int){
        val navView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        if (navView != null) {
            navView.visibility = int
        }
    }

}