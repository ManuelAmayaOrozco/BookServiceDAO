package org.ejemploscompose.service

import org.ejemploscompose.entity.BookEntity

interface IBookService {
    fun insert(book: BookEntity): BookEntity?
    fun selectById(isbn: String): BookEntity?
    fun update(book: BookEntity): BookEntity?
    fun deleteById(isbn: String): Boolean
    fun selectAll(): List<BookEntity>?
}