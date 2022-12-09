package com.akings.composeekim.AmiralBatti.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SHIP_TABLE")
data class Ship(

    @PrimaryKey(autoGenerate = true)
    val id: Int =0,

    @ColumnInfo(name = "satir")
    var namesatir: Int=0,

    @ColumnInfo(name = "sutun")
    var namesutun: Int=0

    )
