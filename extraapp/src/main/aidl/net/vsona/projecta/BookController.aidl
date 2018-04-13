// BookController.aidl
package net.vsona.projecta;

// Declare any non-default types here with import statements
import net.vsona.projecta.Book;
interface BookController {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Book> getBookList();

        void addBookInOut(inout Book book);
}
