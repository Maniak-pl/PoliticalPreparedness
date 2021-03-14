package com.example.android.politicalpreparedness.database

import com.example.android.politicalpreparedness.network.models.Election
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ElectionRepository(private val electionDao: ElectionDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ElectionDataSource {
    override suspend fun getElections(): List<Election> {
        TODO("Not yet implemented")
    }

    override suspend fun saveElection(election: Election) {
        TODO("Not yet implemented")
    }

    override suspend fun getElection(id: Int): Election? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }
}