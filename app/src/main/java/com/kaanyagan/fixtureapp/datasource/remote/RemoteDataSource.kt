package com.kaanyagan.fixtureapp.datasource.remote

import com.kaanyagan.fixtureapp.model.TeamModel
import com.kaanyagan.fixtureapp.network.APIService
import com.kaanyagan.fixtureapp.network.networkhelper.ApiResult
import com.kaanyagan.fixtureapp.network.networkhelper.DataSourceException
import com.kaanyagan.fixtureapp.network.networkhelper.RequestErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource constructor(private val apiService: APIService) {

    fun getAllTeams(callback: Callback<List<TeamModel>>) {
        apiService.getTeamList().enqueue(callback)
    }

}