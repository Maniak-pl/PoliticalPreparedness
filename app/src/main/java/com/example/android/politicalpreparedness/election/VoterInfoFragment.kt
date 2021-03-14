package com.example.android.politicalpreparedness.election

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.databinding.FragmentVoterInfoBinding

class VoterInfoFragment : Fragment() {

    //Done: Declare ViewModel
    val viewModel: VoterInfoViewModel by viewModels { VoterInfoViewModelFactory(activity as Context) }
    private lateinit var binding: FragmentVoterInfoBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //Done: Add ViewModel values and create ViewModel
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_voter_info, container, false)
        binding.lifecycleOwner = this

        //Done: Add binding values
        binding.viewModel = viewModel

        //TODO: Populate voter info -- hide views without provided data.
        /**
        Hint: You will need to ensure proper data is provided from previous fragment.
         */

        //TODO: Handle loading of URLs

        //Done: Handle save button UI state
        viewModel.checkElection(VoterInfoFragmentArgs.fromBundle(requireArguments()).argElection)

        //Done: cont'd Handle save button clicks
        binding.buttonFollow.setOnClickListener { viewModel.followElection() }

        return binding.root
    }

    //TODO: Create method to load URL intents

}