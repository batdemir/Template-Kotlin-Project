package com.batdemir.template.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Example")
data class Example(
    @PrimaryKey
    val id: Long = 0
)