package com.example.android.politicalpreparedness.database

import com.example.android.politicalpreparedness.network.models.Election
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ElectionRepository(private val electionDao: ElectionDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ElectionDataSource {

    //Done: Add get elections
    override suspend fun getElections(): List<Election> = with(ioDispatcher) {
        return electionDao.getElections()
    }

    //Done: Add save election
    override suspend fun saveElection(election: Election) = with(ioDispatcher) {
        electionDao.insertElection(election)
    }

    //Done: Add get election by id
    override suspend fun getElection(id: Int): Election? = with(ioDispatcher) {
        return electionDao.getElectionById(id)
    }

    //Done: Add delete election by id
    override suspend fun deleteById(id: Int) = with(ioDispatcher) {
        electionDao.deleteById(id)
    }
}