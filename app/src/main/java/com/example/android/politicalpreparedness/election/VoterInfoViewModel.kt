package com.example.android.politicalpreparedness.election

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.database.ElectionDataSource
import com.example.android.politicalpreparedness.network.CivicsApiService
import com.example.android.politicalpreparedness.network.models.Election
import kotlinx.coroutines.launch

class VoterInfoViewModel(private val api: CivicsApiService, private val repository: ElectionDataSource) : ViewModel() {

    //Done: Add live data to hold voter info
    private val _election = MutableLiveData<Election>()
    val election: LiveData<Election>
        get() = _election
    private val _isFollowed = MutableLiveData<Boolean>(false)
    val isFollowed: LiveData<Boolean>
        get() = _isFollowed

    fun checkElection(election: Election) {
        viewModelScope.launch {
            _election.value = election
            val saveElection = repository.getElection(election.id)
            _isFollowed.value = saveElection != null
        }
    }

    //TODO: Add var and methods to populate voter info

    //TODO: Add var and methods to support loading URLs

    //Done: Add var and methods to save and remove elections to local database
    fun followElection() {
        viewModelScope.launch {
            _isFollowed.value?.let { isFollow ->
                val election = election.value
                election?.let {
                    if (isFollow) {
                        repository.deleteById(election.id)
                    } else {
                        repository.saveElection(election)
                    }
                    //Done: cont'd -- Populate initial state of save button to reflect proper action based on election saved status
                    _isFollowed.value = !isFollow
                }
            }
        }
    }
}