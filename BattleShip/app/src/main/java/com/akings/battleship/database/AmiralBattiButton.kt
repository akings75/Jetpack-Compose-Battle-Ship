package com.akings.battleship.database

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akings.composeekim.AmiralBatti.database.Ship

@SuppressLint("SuspiciousIndentation", "UnrememberedMutableState")
@Composable
fun AmiralBattiButton(viewModel: ShipViewModel = hiltViewModel()) {

    viewModel.getShips()
    Column(modifier = Modifier.padding(8.dp)) {

        var satir by rememberSaveable(viewModel.ship.namesatir) { mutableStateOf(viewModel.ship.namesatir) }
        var sutun by rememberSaveable(viewModel.ship.namesutun) { mutableStateOf(viewModel.ship.namesutun) }
        var id by remember(viewModel.ship.id) { mutableStateOf(viewModel.ship.id) }


        val context = LocalContext.current
        val ship = Ship(0, satir, sutun)

        println("viewModel.book:${viewModel.ships}")
        println("viewModelBookSutun:${viewModel.ship.namesutun}")
        println("viewModelBookSatir:${viewModel.ship.namesatir}")
        println("viewModelBookId:$id")

        Row() {
            TextField(value = satir.toString(), onValueChange = { satir = it.toInt() })
            Spacer(modifier = Modifier.padding(2.dp))
            TextField(value = sutun.toString(), onValueChange = { sutun = it.toInt() })
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val ship2 = Ship(id = id, satir, sutun)
                viewModel.deleteAll()
            }
        ) {
            Text(text = "DeleteAll")
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                viewModel.addShip(ship)
            }
        ) {
            Text(text = "Save")
        }

        OyunAlani()
    }
}

@Composable
fun OyunAlani(viewModel: ShipViewModel = hiltViewModel()) {

    Column(modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(2.dp)) {
        for (i in 1..6) {
            Row(modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                for (k in 1..6) {

                    var puan by rememberSaveable() { mutableStateOf(0) }
                    var isSelected by rememberSaveable { mutableStateOf(false) }
                    val targetColor by animateColorAsState(targetValue = if (isSelected) Color.Red else Color.Green,
                        animationSpec = tween(4000))
                    Button(onClick = {
                        if (viewModel.ships.isEmpty()) {
                            isSelected = !isSelected
                            if (isSelected) {
                                val ship = Ship(0, namesatir = i, namesutun = k)
                                viewModel.addShip(ship)
                            }
                        } else {
                            viewModel.ships.forEach {
                                println("forEach.ships:$it")
                                println("forEach.ships:${viewModel.ships}")
                                if (it.namesatir == i && it.namesutun == k) {
                                    puan += 10
                                    println("puan:$puan")
                                    println("it.namesatir:${it.namesatir}")

                                } else {
                                    isSelected = !isSelected
                                    if (isSelected) {
                                        val ship = Ship(0, namesatir = i, namesutun = k)
                                        viewModel.addShip(ship)
                                    }
                                }
                            }
                        }


                    },
                        colors = ButtonDefaults.buttonColors(targetColor),
                        modifier = Modifier.size(60.dp))
                    { Text(text = "$i $k") }


                }
            }

        }
    }

}



