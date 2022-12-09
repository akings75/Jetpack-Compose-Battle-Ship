package com.akings.battleship.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.akings.composeekim.AmiralBatti.database.Ship
import kotlinx.coroutines.flow.Flow

@Dao
interface ShipDao {
    @Query("SELECT * from SHIP_TABLE")
    fun getShips(): Flow<List<Ship>>

    @Query("SELECT * from SHIP_TABLE where id = :id")
    fun getShip(id: Int): Flow<Ship>

    @Insert(onConflict = REPLACE)
    fun addShip(ship: Ship)

    @Update
    fun updateShip(ship: Ship)

    @Delete
    fun deleteShip(ship: Ship)

    @Query("DELETE FROM SHIP_TABLE")
    fun deleteAll()
}