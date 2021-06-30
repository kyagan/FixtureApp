package com.kaanyagan.fixtureapp.network;

import com.kaanyagan.fixtureapp.model.TeamModel;
import retrofit2.Call

import retrofit2.Response;
import retrofit2.http.GET;

public interface APIService {

    @GET("team.json")
    fun getTeamList(): Call<List<TeamModel>>;
}
