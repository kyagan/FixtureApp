package com.kaanyagan.fixtureapp.viewmodel;

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.kaanyagan.fixtureapp.datasource.local.LocalDataSource
import com.kaanyagan.fixtureapp.datasource.remote.RemoteDataSource
import com.kaanyagan.fixtureapp.model.Mapper.teamModelMapper
import com.kaanyagan.fixtureapp.model.TeamModel
import com.kaanyagan.fixtureapp.persistence.FixtureEntity
import com.kaanyagan.fixtureapp.persistence.TeamEntity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class TeamListViewModel constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {

    val teamList = MutableLiveData<List<TeamModel>>()
    var size: Int = 0

    fun getAllTeams() {
        remoteDataSource.getAllTeams(object : Callback<List<TeamModel>> {
            override fun onResponse(
                call: Call<List<TeamModel>>,
                response: Response<List<TeamModel>>
            ) {
                if (response.isSuccessful) {
                    val list = response.body()
                    teamList.value = list!!
                    size = list.size
                    viewModelScope.launch { checkCache(list) }
                }
            }

            override fun onFailure(call: Call<List<TeamModel>>, t: Throwable) {

            }
        })
    }

    suspend fun checkCache(list: List<TeamModel>) {
        val cacheListSize: Int = localDataSource.getTeamsCount()
        val mappedList: List<TeamEntity> = list.map { it.teamModelMapper() }
        if (cacheListSize < list.size) {
            localDataSource.insertTeams(mappedList)
        }
        createFixture(mappedList)
    }

    suspend fun createFixture(list: List<TeamEntity>) {

        val randomIndex = rand(1, list.size)
        val constantTeam = list.get(randomIndex)
        list.drop(randomIndex)
        for (i in 1..size - 1) {
            val entity = FixtureEntity(null, constantTeam.name, list.get(0).name, i)
            saveFixtureItem(entity, size)
            for (j in 1..(size - 2) / 2) {
                val entity2 = FixtureEntity(
                    null,
                    list.get(j + 1).name,
                    list.get(size - j).name,
                    i
                )
                saveFixtureItem(
                    entity2, size
                )
            }
        }

    }

    suspend fun saveFixtureItem(fixtureItem: FixtureEntity, listSize: Int) {
        localDataSource.insertFixtureItem(fixtureItem)
        Log.i("FIXTURE_LOG", fixtureItem.toString())
        if (fixtureItem.week < listSize) {
            val nextHalfFixture = FixtureEntity(
                null,
                homeTeam = fixtureItem.awayTeam,
                awayTeam = fixtureItem.homeTeam,
                week = fixtureItem.week + listSize - 1
            )
            localDataSource.insertFixtureItem(nextHalfFixture)
            Log.i("FIXTURE_LOG", nextHalfFixture.toString())
        }


    }


    fun rand(from: Int, to: Int): Int {
        return Random().nextInt(to - from) + from
    }


}
