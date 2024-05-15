package org.ejemploscompose.service

import org.ejemploscompose.dao.IBookDAO
import org.ejemploscompose.entity.BookEntity

class BookService(private val bookDao: IBookDAO) : IBookService {
    override fun insert(book: BookEntity): BookEntity? {
        return bookDao.insert(book)
    }

    override fun selectById(isbn: String): BookEntity? {
        return bookDao.selectById(isbn)
    }

    override fun update(book: BookEntity): BookEntity? {
        return bookDao.update(book)
    }

    override fun deleteById(isbn: String): Boolean {
        return bookDao.deleteById(isbn)
    }

    override fun selectAll(): List<BookEntity>? {
        return bookDao.selectAll()
    }
}