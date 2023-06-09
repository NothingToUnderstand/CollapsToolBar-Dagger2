package com.example.molfartask.presentation.subliminal.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molfartask.R
import com.example.molfartask.base.BaseFragment
import com.example.molfartask.data.entity.Record
import com.example.molfartask.databinding.FragmentSubliminalsBinding
import com.example.molfartask.domain.Result
import com.example.molfartask.utils.scrollToPosition
import com.google.android.material.chip.Chip
import java.util.*


class SubliminalFragment : BaseFragment<SubliminalViewModel, FragmentSubliminalsBinding>(
    FragmentSubliminalsBinding::inflate
) {
    private lateinit var rvAdapter: SubliminalAdapter
    private lateinit var rvAdapterImage: BackgroundImageAdapter

    override val viewModel: SubliminalViewModel by inject()

    private var category: String? = null
    private var firstCategory = true
    private val categories = mutableSetOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapter = SubliminalAdapter()
        rvAdapterImage = BackgroundImageAdapter()

        viewModel.getRecords()

        binding.rvBackgroundImage.apply {
            adapter = rvAdapterImage
            layoutManager = LinearLayoutManager(requireContext())

        }

        binding.rvRecyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    binding.rvBackgroundImage.scrollBy(dx, dy)
                }
            })
        }


        setListenersToViews()
        observeViewModel()
    }

    private fun setListenersToViews() = with(binding) {

        srlSwipeRefresh.setOnRefreshListener {
            viewModel.getRecords(category)
            srlSwipeRefresh.isRefreshing = false
        }

        cpChips.setOnCheckedStateChangeListener { group, _ ->
            val chip = group.getChildAt(group.checkedChipId - 1) as Chip?
            binding.svScrollView.scrollToPosition<Chip>(chip?.id)
            category = chip?.text.toString().lowercase(Locale.ROOT)
            viewModel.filterRecordsByCategory(category)
        }


        var isShow = true
        var scrollRange = -1
        ablAppbar.addOnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                toolbarCollapse(true)
                isShow = true
            } else if (isShow) {
                toolbarCollapse(false)
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
                    svScrollView.isVisible = true
                    rvRecyclerView.isVisible = true
                    result.data?.let {
                        rvAdapter.setData(it)
                        addChips(it)
                    }
                }
                is Result.NoData -> {
                    svScrollView.isVisible = false
                    rvRecyclerView.isVisible = false
                    pbLoading.isVisible = false
                    result.message?.let { makeToast(it) }
                }
            }
        }
    }

    private fun addChips(records: List<Record>) {
        val categoriesSize = categories.size
        categories.add(getString(R.string.all_together))
        records.forEach { categories.addAll(it.field.category) }
        if (categoriesSize == categories.size) return
        binding.cpChips.removeAllViews()
        categories.forEach {
            binding.cpChips.addView(
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

    private fun toolbarCollapse(collapsed: Boolean) {
        val backgroundColor = if (collapsed) {
            ContextCompat.getColor(requireContext(), R.color.white)
        } else {
            ContextCompat.getColor(requireContext(), R.color.transparent)
        }
        binding.ctlCollaps.setBackgroundColor(backgroundColor)
        binding.textContainer.isInvisible = collapsed
        requireActivity().apply {
            window.statusBarColor = backgroundColor
            findViewById<TextView>(R.id.tv_title).isVisible = collapsed
            findViewById<View>(R.id.top_toolbar).setBackgroundColor(backgroundColor)
        }
    }

    companion object {
        fun getInstance() = SubliminalFragment()
        const val SUBLIMINAL_FRAGMENT_TAG = "SUBLIMINAL_FRAGMENT_TAG"
    }

}

