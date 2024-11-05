package com.example.tvshowapp3.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowapp3.adapter.TVShowAdapter
import com.example.tvshowapp3.databinding.FragmentHomeBinding
import com.example.tvshowapp3.models.TvShowItem
import com.example.tvshowapp3.viewmodel.TVShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), TVShowAdapter.IOnClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: TVShowViewModel by viewModels()
    private lateinit var tVShowAdapter : TVShowAdapter
    private lateinit var tVPopularAdapter : TVShowAdapter
    private lateinit var tVUpComingAdapter : TVShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
//        binding.button.setOnClickListener {
//            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
//        }

    }

    private fun setUpRecyclerView() {
        tVShowAdapter = TVShowAdapter(this)
        tVPopularAdapter = TVShowAdapter(this)
        tVUpComingAdapter = TVShowAdapter(this)

        binding.recyclerView.apply {
            adapter = tVShowAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.recyclerView2.apply {
            adapter = tVPopularAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }


        binding.recyclerView3.apply {
            adapter = tVUpComingAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        viewModel.responseTVShow.observe(viewLifecycleOwner) { listTVShow ->
            tVShowAdapter.tVShows = listTVShow
            Log.w("TAG", "${listTVShow.first()}")
            tVPopularAdapter.tVShows = listTVShow.sortedByDescending { it.rating.average }
            Log.w("TAG", "${tVPopularAdapter.tVShows.first()}")
            tVUpComingAdapter.tVShows = listTVShow.sortedByDescending { it.premiered }
            Log.w("TAG", "${tVUpComingAdapter.tVShows.first()}")

        }
    }

    override fun onClickListenerTVShowItem(tvShowItem: TvShowItem) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
    }


}