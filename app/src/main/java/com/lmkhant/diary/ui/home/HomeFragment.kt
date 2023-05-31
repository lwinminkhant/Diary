package com.lmkhant.diary.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lmkhant.diary.R
import com.lmkhant.diary.databinding.FragmentHomeBinding
import com.lmkhant.diary.viewmodel.DiaryViewModel

class HomeFragment : Fragment(){


    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    lateinit var viewModel: DiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvDiary: RecyclerView = binding.rvDiary
        viewModel = ViewModelProvider(requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[DiaryViewModel::class.java]

        val rvAdapter = DiaryAdapter{
            val bundle = bundleOf("id" to it.id,"title" to it.title, "description" to it.description)
            findNavController().navigate(R.id.navigation_new_diary, bundle)
        }

        rvDiary.adapter = rvAdapter
        viewModel.diaryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                rvAdapter.updateList(it)
            }
        } )

        rvDiary.layoutManager = LinearLayoutManager(this.context)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}