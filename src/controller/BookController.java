package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;

public class BookController {
	private ObservableList<Book> bookList = FXCollections.observableArrayList();
	public ObservableList<Book> getBooks() {
	    return bookList;
	}

	public void addBook(Book book) {
		bookList.add(book);
	}
	
	public void removeBook(Book book) {
		bookList.remove(book);
	}

}
