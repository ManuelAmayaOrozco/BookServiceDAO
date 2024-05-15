package org.ejemploscompose

import db_connection.DataSourceFactory
import org.ejemploscompose.dao.BookDAO
import org.ejemploscompose.entity.BookEntity
import org.ejemploscompose.service.BookService
import output.Console

fun main() {

    val console = Console()

    // Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.JDBC)

    // Creamos la instancia de BookDAO
    val bookDao = BookDAO(dataSource, console)

    // Creamos la instancia de BookService
    val bookService = BookService(bookDao)

    // Insertamos un nuevo libro
    val newBook = BookEntity(isbn = "123-456-789", name = "Kotlin para principiantes", author = "Juan Pérez")
    var insertedBook = bookService.insert(newBook)
    console.showMessage("Inserted book: $insertedBook")

    // Obtenemos un libro por su ISBN
    val foundBook =
        if (insertedBook != null) {
            bookService.selectById(insertedBook.isbn)
        } else {
            null
        }
    console.showMessage("Found book: ${foundBook ?: "none"}")

    // Actualizamos el libro
    val savedBook =
        if (foundBook != null) {
            val updatedBook = foundBook.copy(author = "Ana López")
            bookService.update(updatedBook)
        } else {
            null
        }
    console.showMessage("Updated book: ${savedBook ?: "none"}")

    val otherBook = BookEntity(isbn = "987-654-321", name = "Fundamentos de Programación en Kotlin", author = "Carlos García")
    insertedBook = bookService.insert(otherBook)
    console.showMessage("Inserted book: $insertedBook")


    // Obtenemos todos los libros
    var allBooks = bookService.selectAll()
    console.show(allBooks)


    // Eliminamos el libro
    if (savedBook != null) {
        bookService.deleteById(savedBook.isbn)
        console.showMessage("Book deleted")
    }

    // Obtenemos todos los libros
    allBooks = bookService.selectAll()
    console.show(allBooks)

    // Eliminamos el usuario
    bookService.deleteById(otherBook.isbn)
    console.showMessage("Book deleted")

    // Obtenemos todos los libros
    allBooks = bookService.selectAll()
    console.show(allBooks)
}