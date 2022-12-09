package com.akings.battleship.database

import com.akings.composeekim.AmiralBatti.database.Ship

class ShipRepositoryImpl(
    private val shipDao: ShipDao
) : ShipRepository {
    override suspend fun getShipsFromRoom() = shipDao.getShips()

    override suspend fun getShipFromRoom(id: Int) = shipDao.getShip(id)

    override suspend fun addShipToRoom(ship: Ship) = shipDao.addShip(ship)

    override suspend fun updateShipInRoom(ship: Ship) = shipDao.updateShip(ship)

    override suspend fun deleteShipFromRoom(ship: Ship) = shipDao.deleteShip(ship)

    override suspend fun deleteAll() = shipDao.deleteAll()


}