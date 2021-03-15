package com.example.android.politicalpreparedness.election

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
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

        //Done: Handle save button UI state
        val args = VoterInfoFragmentArgs.fromBundle(requireArguments())
        viewModel.checkElection(args.argElection)

        //Done: Populate voter info -- hide views without provided data.
        viewModel.getVoterInfo(args.argElection)

        //Done: Handle loading of URLs
        viewModel.url.observe(viewLifecycleOwner) {
            startWeb(it)
        }

        //Done: cont'd Handle save button clicks
        binding.buttonFollow.setOnClickListener { viewModel.followElection() }

        return binding.root
    }

    //Done: Create method to load URL intents
    private fun startWeb(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}