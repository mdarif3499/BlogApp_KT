package com.example.userloginkotlinapplication.service.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userloginkotlinapplication.service.model.Book


@Database(entities = [Book::class], version = 10)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
