package dev.panwar.a7minutesworkout

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Create an entity with @param [tableName]
 * Use @param [date] as primary key
 * */
@Entity(tableName = "weight-table")
data class BMIModel(
    @PrimaryKey
    val weight: String, 
    val height: String,
    val bmi: String
)