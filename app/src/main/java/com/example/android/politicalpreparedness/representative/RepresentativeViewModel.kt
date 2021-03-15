package com.example.android.politicalpreparedness.representative

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.network.CivicsApiService
import com.example.android.politicalpreparedness.network.models.Address
import com.example.android.politicalpreparedness.representative.model.Representative
import kotlinx.coroutines.launch

class RepresentativeViewModel(val api: CivicsApiService) : ViewModel() {

    //Done: Establish live data for representatives and address
    private var _representatives = MutableLiveData<List<Representative>>()
    val representativeList: LiveData<List<Representative>>
        get() = _representatives
    private var _address = MutableLiveData<Address>()
    val address: LiveData<Address>
        get() = _address

    //Done: Create function to fetch representatives from API from a provided address
    fun findRepresentatives() {
        _address.value?.let { address ->
            viewModelScope.launch {
                try {
                    val (offices, officials) = api.getRepresentativesAsync(address.toFormattedString()).await()
                    _representatives.value = offices.flatMap { office -> office.getRepresentatives(officials) }
                } catch (e: Exception) {
                    Log.e("Representative", e.localizedMessage)
                }
            }
        }
    }

    //Done: Create function get address from geo location
    fun setAddress(address: Address) {
        _address.value = address
    }

    //TODO: Create function to get address from individual fields

}
