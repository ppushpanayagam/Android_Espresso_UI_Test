package com.example.testapplication.ui.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.R
import com.example.testapplication.data.model.CatItem
import com.example.testapplication.databinding.FragmentCatFactsBinding
import com.example.testapplication.ui.CatItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatFactsMvvmFragment : Fragment(R.layout.fragment_cat_facts) {

    private val viewModel by viewModels<CatFactsViewModel>()

    private var binding: FragmentCatFactsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCatFactsBinding.inflate(
        inflater,
        container,
        false
    ).apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.swipeToRefreshLayout?.setOnRefreshListener {
            viewModel.refresh()
        }

        binding?.recyclerView?.layoutManager = LinearLayoutManager(activity)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.catFacts.collect(::updateUi)
            }
        }
    }

    private fun updateUi(uiState: UiState) {
        with (binding ?: return) {

            // update loading state
            swipeToRefreshLayout.isRefreshing = uiState.isLoadingCatFacts

            when {
                uiState.hasFailedLoading -> {
                    recyclerView.hide()
                    messageLayout.show()
                    messageTv.text = "Ups something went wrong:\n\n[${uiState.loadException}]"
                }
                else -> {
                    recyclerView.adapter = CatItemAdapter(
                        itemList = uiState.catFacts,
                        onItemClick = {
                            openCatItem(it)
                        }
                    )
                    recyclerView.show()
                    messageLayout.hide()
                }
            }
        }
    }

    private fun openCatItem(item: CatItem) {
        findNavController()
            .navigate(CatFactsMvvmFragmentDirections.actionGotoImageFragment(
                imageUrl = item.image.url
            ))
    }
}

fun View.show() { isVisible = true }
fun View.hide() { isVisible = false }