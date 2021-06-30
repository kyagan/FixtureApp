package com.kaanyagan.fixtureapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "teams")
data class TeamEntity(@PrimaryKey(autoGenerate = true)
                      @ColumnInfo(name = "id")
                      val id: Int,
                      @ColumnInfo(name = "teamName")
                      val name: String
)
