package output

import org.ejemploscompose.entity.BookEntity

class Console: IOutputInfo {

    override fun showMessage(message: String, lineBreak: Boolean) {
        if (lineBreak) println(message) else print(message)
    }

    override fun show(bookList: List<BookEntity>?, message: String) {
        if (bookList != null) {
            if (bookList.isEmpty()) {
                showMessage("No books found!")
            } else {
                showMessage(message)
                bookList.forEachIndexed { index, book ->
                    showMessage("\t${index + 1}. $book")
                }
            }
        }
    }

}