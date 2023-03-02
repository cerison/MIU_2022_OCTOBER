package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.BookCopy;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;


/*
This  class implements a DataAccess interface. It defines an enum StorageType with three options: BOOKS, MEMBERS, and USERS.

The class then defines a constant OUTPUT_DIR that is the user's current working directory plus the path to a directory called "storage" 
within the package "dataaccess". It also defines a constant DATE_PATTERN with the value "MM/dd/yyyy".

The class has various public methods that allow for the retrieval and modification of books, library members, and users. 
The methods getLibraryMemberById, getBookByIsbn, and readBooksMap, readMemberMap, readUserMap are all used to retrieve data. 
The methods deleteBookByIsbn, saveNewMember, updateMember, saveNewBook, and updateBook are all used to modify data.

The class also has several static methods, loadBookMap, loadUserMap, and loadMemberMap, that are used to place test data into storage at startup. 
The saveToStorage method is used to save data to storage after it has been modified.

The class uses the "SuppressWarnings" annotation to suppress the unchecked warning for the return type of the readFromStorage method, 
which is used by several of the other methods in this class to read data from storage
 */
public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS;
	}
	// Windows user can use
	
	/*public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "\\src\\dataaccess\\storage";*/
	
	// For Mac Users path can use / 
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "/src/dataaccess/storage";
	
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	public LibraryMember getLibraryMemberById(String memberId) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		return mems.get(memberId);
	}
	
	public Book getBookByIsbn(String isbn) {
		HashMap<String, Book> books = readBooksMap();
		return books.get(isbn);
	}
	
	public void deleteBookByIsbn(String isbn) {
		HashMap<String, Book> books = readBooksMap();
		books.remove(isbn);
		saveToStorage(StorageType.BOOKS, books);
	}
	
	//implement: other save operations
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);	
	}
	
	public void updateMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);	
	}
	
	public void saveNewBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		String bookId = book.getIsbn();
		books.put(bookId, book);
		saveToStorage(StorageType.BOOKS, books);
	}
	
	public void updateBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		String bookId = book.getIsbn();
		books.put(bookId, book);
		saveToStorage(StorageType.BOOKS, books);	
	}
	
	/*This code is reading a HashMap of Books from storage, where the key is the ISBN number and the value is the Book object. 
	 * The method is using the readFromStorage method to read the data, which takes a StorageType argument (in this case, StorageType.BOOKS) to specify which data to read. 
	 * The method is then returning the HashMap of books as a generic object, which needs to be cast to the correct type (HashMap<String,Book>). 
	 * The @SuppressWarnings("unchecked") annotation is used to suppress the compiler warning that occurs when the type of the object is not specified during casting.
	 */
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(
				StorageType.MEMBERS);
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		//Returns a Map with name/value pairs being
		//   userId -> User
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	
	
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
 
	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}
	
	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	
	
	final static class Pair<S,T> implements Serializable{
		
		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
		private static final long serialVersionUID = 5399827794066637059L;
	}
	
}
