package org.ejemploscompose.dao

import org.ejemploscompose.entity.BookEntity
import output.IOutputInfo
import java.sql.SQLException
import javax.sql.DataSource

class BookDAO(private val dataSource: DataSource, private val console: IOutputInfo) :IBookDAO {

    override fun insert(book: BookEntity): BookEntity? {
        val sql = "INSERT INTO library (isbn, name, author) VALUES (?, ?, ?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.isbn)
                    stmt.setString(2, book.name)
                    stmt.setString(3, book.author)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        book
                    } else {
                        console.showMessage("*Error* Insert query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* Insert query failed! (${e.message})")
            null
        }
    }

    override fun selectById(isbn: String): BookEntity? {
        val sql = "SELECT * FROM library WHERE isbn = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, isbn)
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        BookEntity(
                            isbn = rs.getString("isbn"),
                            name = rs.getString("name"),
                            author = rs.getString("author")
                        )
                    } else {
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* Select query failed! (${e.message})")
            null
        }
    }

    override fun selectAll(): List<BookEntity>? {
        val sql = "SELECT * FROM library"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val users = mutableListOf<BookEntity>()
                    while (rs.next()) {
                        users.add(
                            BookEntity(
                                isbn = rs.getString("isbn"),
                                name = rs.getString("name"),
                                author = rs.getString("author")
                            )
                        )
                    }
                    users
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* Select query failed! (${e.message})")
            null
        }
    }

    override fun update(book: BookEntity): BookEntity? {
        val sql = "UPDATE library SET name = ?, author = ? WHERE isbn = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.name)
                    stmt.setString(2, book.author)
                    stmt.setString(3, book.isbn)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        book
                    } else {
                        console.showMessage("*Error* Update query failed! ($rs records updated)")
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* Update query failed! (${e.message})")
            null
        }
    }

    override fun deleteById(isbn: String): Boolean {
        val sql = "DELETE FROM library WHERE isbn = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, isbn)
                    (stmt.executeUpdate() == 1)
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* Delete query failed! (${e.message})")
            false
        }
    }
}