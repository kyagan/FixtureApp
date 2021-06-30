package com.kaanyagan.fixtureapp.model

import com.kaanyagan.fixtureapp.persistence.TeamEntity

object Mapper {

    fun TeamModel.teamModelMapper(): TeamEntity {
        return TeamEntity(this.id.toInt(), this.name)
    }
}