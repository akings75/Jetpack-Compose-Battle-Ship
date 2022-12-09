package com.akings.battleship.database
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akings.composeekim.AmiralBatti.database.Ship
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ShipViewModel @Inject constructor(
    private val repo: ShipRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var ships by mutableStateOf(emptyList<Ship>())
    var ship by mutableStateOf(Ship())

    init {
        Log.d("ShipViewModel", "SavedStateHandle...")
        savedStateHandle.get<String>("shipId")?.let { shipId ->
            Log.d("ShipViewModel", "ShipId: $shipId")
            getShip(shipId.toInt()) }
    }
    fun getShips() = viewModelScope.launch {
        repo.getShipsFromRoom().collect { response ->
            ships = response

        }
    }
    fun getShip(id: Int) = viewModelScope.launch {
        repo.getShipFromRoom(id).collect { response ->
            ship = response
        }
    }

    fun addShip(ship: Ship) = viewModelScope.launch(Dispatchers.IO) {
        repo.addShipToRoom(ship)
    }

    fun updateShip(ship: Ship) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateShipInRoom(ship)
    }

    fun deleteShip(ship: Ship) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteShipFromRoom(ship = ship)
    }
    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteAll()
    }
}