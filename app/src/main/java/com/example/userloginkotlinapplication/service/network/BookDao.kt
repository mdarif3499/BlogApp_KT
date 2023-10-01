package com.example.userloginkotlinapplication.service.network

import androidx.room.*
import com.example.userloginkotlinapplication.service.model.Book

@Dao
interface BookDao {

    @Insert
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM books_table")
    fun getAllBooks(): List<Book>

    @Update
    suspend fun updateBook(book: Book)

    @Query("DELETE FROM books_table")
    suspend fun deleteBook()

}