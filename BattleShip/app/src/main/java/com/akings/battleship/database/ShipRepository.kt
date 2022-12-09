package com.akings.battleship.database

import com.akings.composeekim.AmiralBatti.database.Ship
import kotlinx.coroutines.flow.Flow

interface ShipRepository {
    suspend fun getShipsFromRoom(): Flow<List<Ship>>

    suspend fun getShipFromRoom(id: Int): Flow<Ship>

    suspend fun addShipToRoom(ship: Ship)

    suspend fun updateShipInRoom(ship: Ship)

    suspend fun deleteShipFromRoom(ship: Ship)

    suspend fun deleteAll()


}