package com.akings.battleship.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akings.composeekim.AmiralBatti.database.Ship

@Database(entities = [Ship::class], version = 1, exportSchema = false)
abstract class ShipDb : RoomDatabase() {
    abstract fun shipDao(): ShipDao
}