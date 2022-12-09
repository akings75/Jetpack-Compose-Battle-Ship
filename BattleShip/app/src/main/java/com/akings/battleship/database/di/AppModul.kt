package com.akings.battleship.database.di

import android.content.Context
import androidx.room.Room
import com.akings.battleship.database.ShipDao
import com.akings.battleship.database.ShipDb
import com.akings.battleship.database.ShipRepository
import com.akings.battleship.database.ShipRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideShipDb(
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        ShipDb::class.java,
        "SHIP_TABLE"
    ).build()

    @Provides
    fun provideShipDao(
        shipDb: ShipDb
    ) = shipDb.shipDao()

    @Provides
    fun provideShipRepository(
        shipDao: ShipDao
    ): ShipRepository = ShipRepositoryImpl(shipDao)
}