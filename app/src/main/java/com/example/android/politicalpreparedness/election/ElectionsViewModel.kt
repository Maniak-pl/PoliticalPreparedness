package com.example.android.politicalpreparedness.election

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.database.ElectionRepository
import com.example.android.politicalpreparedness.network.CivicsApiService
import com.example.android.politicalpreparedness.network.models.Election
import kotlinx.coroutines.launch

//Done: Construct ViewModel and provide election datasource
class ElectionsViewModel(private val api: CivicsApiService, private val repository: ElectionRepository) : ViewModel() {

    //Done: Create live data val for upcoming elections
    private val _electionList = MutableLiveData<List<Election>>()
    val electionList: LiveData<List<Election>>
        get() = _electionList

    //Done: Create live data val for saved elections
    private val _savedElectionList = MutableLiveData<List<Election>>()
    val savedElectionList: LiveData<List<Election>>
        get() = _savedElectionList


    //Done: Create val and functions to populate live data for upcoming elections from the API and saved elections from local database
    fun getUpcomingElections() {
        viewModelScope.launch {
            _electionList.value = api.getElections()
        }
    }

    fun getSavedElections() {
        viewModelScope.launch {
            _savedElectionList.value = repository.getElections()
        }
    }
}