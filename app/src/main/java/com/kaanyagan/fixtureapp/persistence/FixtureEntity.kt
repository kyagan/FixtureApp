package com.kaanyagan.fixtureapp.persistence;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fixture")
data class FixtureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val homeTeam: String,
    val awayTeam: String,
    val week: Int
){
    override fun toString(): String {
        return "homeTeam: $homeTeam, awayTeam: $awayTeam, week: $week"
    }
}
