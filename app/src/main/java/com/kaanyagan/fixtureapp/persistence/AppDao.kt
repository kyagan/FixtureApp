package com.kaanyagan.fixtureapp.persistence;


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
public interface AppDao {

    @Insert
    suspend fun insertTeams(teams: kotlin.collections.List<TeamEntity>)

    @Query("Select * from teams")
    suspend fun getAllTeams(): kotlin.collections.List<TeamEntity>

    @Query("select count(*) from teams")
    suspend fun getTeamCount(): Int

    @Insert
    suspend fun insertFixtureItem(fixtureItem: FixtureEntity)

    @Query("Select * from fixture")
    suspend fun getAllFixture(): kotlin.collections.List<FixtureEntity>

    @Query("Select * from fixture where week =:week")
    suspend fun getMatchesByWeek(week: Int): List<FixtureEntity>
}
