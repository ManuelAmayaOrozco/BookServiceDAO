package output

import org.ejemploscompose.entity.BookEntity
interface IOutputInfo {

    fun showMessage(message: String, lineBreak: Boolean = true)
    fun show(bookList: List<BookEntity>?, message: String = "All books:")
}