package com.example.testapplication.ui.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkivanov.mvikotlin.core.view.MviView
import com.arkivanov.mvikotlin.rx.Observer
import com.arkivanov.mvikotlin.rx.internal.PublishSubject
import com.example.testapplication.R
import com.example.testapplication.data.model.CatItem
import com.example.testapplication.ui.CatItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatFactsMviFragment : Fragment(), MviView<CatsStateStore.ScreenState, CatsStateStore.UserIntent> {

    private val viewModel by viewModels<CatFactsMviViewModel>()

    private lateinit var recyclerVIEW: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerVIEW = view.findViewById(R.id.recycler_view)
        recyclerVIEW.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated(lifecycle, this)
    }

    override fun render(model: CatsStateStore.ScreenState) {
        if (model.isLoading) {
            Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
            return
        }

        if (model.items.isNotEmpty()) {
            recyclerVIEW.adapter = CatItemAdapter(itemList = model.items, onItemClick = {
                openCatItem(it)
            })
        }

        if (model.hasError) {
            Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
            return
        }
    }

    private val subject = PublishSubject<CatsStateStore.UserIntent>()
    override fun events(observer: Observer<CatsStateStore.UserIntent>) = subject.subscribe(observer)

    private fun openCatItem(item: CatItem) {
        subject.onNext(CatsStateStore.UserIntent.CatItemClicked(item))
    }

    fun oneOffEvent(event: CatsStateStore.OneOffEvent) {
        when (event) {
            is CatsStateStore.OneOffEvent.OpenImageFragment -> {

                // for some unknown reasons the event comes twice
                val destination = CatFactsMviFragmentDirections
                    .actionGotoImageFragment(imageUrl = event.item.image.url)

                findNavController()
                    .currentDestination
                    ?.getAction(destination.actionId)
                    ?.let { findNavController().navigate(destination) }

            }
        }
    }

}
