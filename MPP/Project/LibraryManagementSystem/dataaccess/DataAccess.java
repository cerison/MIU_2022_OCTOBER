package dataaccess;

import java.time.LocalDate;
import java.util.HashMap;

import business.Book;
import business.BookCopy;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

/*
 * This interface implemented by a class that provides a specific implementation for storing and retrieving data, 
 * such as a DataAccessObject (DAO) class that uses a database to store data. This allows the rest of the program to interact with the data through the methods defined in this interface, 
 * while the actual implementation of how the data is stored and retrieved is abstracted away and can be changed as needed.
 * 
 */
public interface DataAccess { 
	//Returns a HashMap containing book objects, where the key is the ISBN of the book.
	public HashMap<String,Book> readBooksMap(); 
	//Returns a HashMap containing user objects, where the key is the user's unique identifier.
	public HashMap<String,User> readUserMap();
	//Returns a HashMap containing library member objects, where the key is the member's unique identifier.
	public HashMap<String, LibraryMember> readMemberMap(); 
	//Saves a new library member to storage.
	public void saveNewMember(LibraryMember member); 
	//Saves a new book to storage.
	public void saveNewBook(Book book); 
	//Updates an existing book in storage.
	public void updateBook(Book book); 
	//Updates an existing library member in storage.
	public void updateMember(LibraryMember member);
	//Returns a library member with the given member ID.
	public LibraryMember getLibraryMemberById(String memberId);
	//Returns a book with the given ISBN.
	public Book getBookByIsbn(String isbn);
	//Delete a book with the given ISBN.
	public void deleteBookByIsbn(String isbn);
}
