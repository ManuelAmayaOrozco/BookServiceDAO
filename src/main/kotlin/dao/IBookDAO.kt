package org.ejemploscompose.dao

import org.ejemploscompose.entity.BookEntity

interface IBookDAO {
    fun insert(book: BookEntity): BookEntity?
    fun selectAll(): List<BookEntity>?
    fun selectById(isbn: String): BookEntity?
    fun update(book: BookEntity): BookEntity?
    fun deleteById(isbn: String): Boolean
}