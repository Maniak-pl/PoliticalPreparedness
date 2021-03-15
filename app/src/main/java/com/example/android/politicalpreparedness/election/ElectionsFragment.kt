package com.example.android.politicalpreparedness.election

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter
import com.example.android.politicalpreparedness.election.adapter.OnElectionListener
import com.example.android.politicalpreparedness.network.models.Election

class ElectionsFragment : Fragment() {

    //Done: Declare ViewModel
    val viewModel: ElectionsViewModel by viewModels { ElectionsViewModelFactory(activity as Context) }
    private lateinit var binding: FragmentElectionBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        //Done: Add ViewModel values and create ViewModel
        //Done: Add binding values
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_election, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        //Done: Link elections to voter info
        val upcomingElectionAdapter = ElectionListAdapter(OnElectionListener { electionClick(it) })
        val savedElectionAdapter = ElectionListAdapter(OnElectionListener { electionClick(it) })

        //Done: Initiate recycler adapters
        binding.upcomingElectionsList.adapter = upcomingElectionAdapter
        binding.savedElectionsList.adapter = savedElectionAdapter

        //Done: Populate recycler adapters
        viewModel.electionList.observe(viewLifecycleOwner) {
            upcomingElectionAdapter.submitList(it)
        }

        viewModel.savedElectionList.observe(viewLifecycleOwner) {
            savedElectionAdapter.submitList(it)
        }
    }

    //Done: Create functions to navigate to saved or upcoming election voter info
    private fun electionClick(election: Election) {
        findNavController().navigate(ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(election))
    }

    //Done: Refresh adapters when fragment loads
    override fun onResume() {
        super.onResume()
        viewModel.getUpcomingElections()
        viewModel.getSavedElections()
    }
}