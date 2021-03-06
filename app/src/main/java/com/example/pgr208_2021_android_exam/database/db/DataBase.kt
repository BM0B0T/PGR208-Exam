package com.example.pgr208_2021_android_exam.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pgr208_2021_android_exam.database.entities.Transaction
import com.example.pgr208_2021_android_exam.database.entities.Wallet

@Database(entities = [Transaction::class, Wallet::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun transactionDao(): DAO

    companion object {
        private var db: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            val newDb =
                db ?: Room.databaseBuilder(context, DataBase::class.java, "stonks_database")
                    .build()
            return newDb.also {
                db = it

            }
        }
    }
}
