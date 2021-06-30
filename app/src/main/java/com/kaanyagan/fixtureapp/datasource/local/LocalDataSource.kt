package com.kaanyagan.fixtureapp.datasource.local;

import com.kaanyagan.fixtureapp.persistence.AppDao
import com.kaanyagan.fixtureapp.persistence.FixtureEntity
import com.kaanyagan.fixtureapp.persistence.TeamEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class LocalDataSource constructor(private val appDao: AppDao) {

    suspend fun insertTeams(list: List<TeamEntity>) = withContext(Dispatchers.Default) {
        appDao.insertTeams(list)
    }

    suspend fun getAllTeams(): List<TeamEntity> = withContext(Dispatchers.Default) {
        return@withContext appDao.getAllTeams()
    }

    suspend fun getTeamsCount(): Int = withContext(Dispatchers.Default) {
        return@withContext appDao.getTeamCount()
    }

    suspend fun insertFixtureItem(fixtureItem: FixtureEntity) = withContext(Dispatchers.Default) {
        appDao.insertFixtureItem(fixtureItem)
    }

    suspend fun getAllFixture(): List<FixtureEntity> = withContext(Dispatchers.Default) {
        return@withContext appDao.getAllFixture()
    }


}
