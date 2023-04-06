package com.example.molfartask.presentation.subliminal.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.molfartask.R
import com.example.molfartask.base.BaseFragment
import com.example.molfartask.base.scrollToPosition
import com.example.molfartask.databinding.FragmentSubliminalsBinding
import com.example.molfartask.entity.Record
import com.example.molfartask.presentation.Result
import com.google.android.material.chip.Chip
import java.util.*


class SubliminalFragment : BaseFragment<SubliminalViewModel, FragmentSubliminalsBinding>(
    FragmentSubliminalsBinding::inflate
) {

    companion object {
        fun getInstance() = SubliminalFragment()
        const val SUBLIMINAL_FRAGMENT_TAG = "SUBLIMINAL_FRAGMENT_TAG"
    }

    private lateinit var rvAdapter: SubliminalAdapter

    override val viewModel: SubliminalViewModel by inject()

    private var category = ""
    private var firstCategory = true
    private val categories = mutableSetOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapter = SubliminalAdapter()

        viewModel.getRecords(requireContext())

        binding.recyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        setListenersToViews()
        observeViewModel()
    }

    private fun setListenersToViews() = with(binding) {
        btnSearch.setOnClickListener {
            makeToast(getString(R.string.coming_soon))
        }
        btnSearch2.setOnClickListener {
            makeToast(getString(R.string.coming_soon))
        }
        btnInfo2.setOnClickListener {
            makeToast(getString(R.string.coming_soon))
        }
        btnInfo.setOnClickListener {
            makeToast(getString(R.string.coming_soon))
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.getRecords(requireContext(), category)
            swipeRefresh.isRefreshing = false
        }

        chipGroup.setOnCheckedStateChangeListener { group, _ ->
            val chip = group.getChildAt(group.checkedChipId - 1) as Chip?
            binding.scrollView.scrollToPosition<Chip>(chip?.id)
            category = chip?.text.toString().lowercase(Locale.ROOT)
            viewModel.filterRecordsByCategory(category)
        }

        var isShow = true
        var scrollRange = -1
        mainAppbar.addOnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.upperToolbar.isVisible = true
                isShow = true
            } else if (isShow) {
                binding.upperToolbar.isVisible = false
                isShow = false
            }
        }
    }

    private fun observeViewModel() = with(binding) {
        viewModel.filteredLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> pbLoading.isVisible = true
                is Result.Success -> {
                    pbLoading.isVisible = false
                    tvNoData.isVisible = false
                    scrollView.isVisible = true
                    recyclerView.isVisible = true
                    result.data?.let {
                        rvAdapter.setData(it)
                        addChips(it)
                    }
                }
                is Result.Error -> {
                    scrollView.isVisible = false
                    recyclerView.isVisible = false
                    tvNoData.apply {
                        isVisible = true
                        text = getString(R.string.error, result.message)
                    }
                }
            }
        }
    }

    private fun addChips(records: List<Record>) {
        val categoriesSize = categories.size
        categories.add(getString(R.string.all_together))
        records.forEach { categories.addAll(it.fields.categories) }
        if (categoriesSize == categories.size) return
        binding.chipGroup.removeAllViews()
        categories.forEach {
            binding.chipGroup.addView(
                Chip(
                    requireContext(),
                    null,
                    R.attr.Chips_ScrollView
                ).apply {
                    text = it.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ROOT)
                        else it.toString()
                    }
                    isChecked = firstCategory
                })
            firstCategory = false
        }

    }


}

