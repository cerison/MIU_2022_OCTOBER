package dataaccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import business.Address;
import business.Author;
import business.Book;
import business.BookCopy;
import business.LibraryMember;
import business.SystemController;

/**
 * This class loads data into the data repository and also
 * sets up the storage units that are used in the application.
 * The main method in this class must be run once (and only
 * once) before the rest of the application can work properly.
 * It will create three serialized objects in the dataaccess.storage
 * folder.
 * 
 *
 */
public class TestData {
	
	
	public static void main(String[] args) {

                DataAccess da = new DataAccessFacade();
                Book book = da.getBookByIsbn("1006");
                LibraryMember mem = da.getLibraryMemberById("1007"); 
                SystemController ci = new SystemController();
                
                
                

                BookCopy bookCopy = book.getNextAvailableCopy();
                LocalDate checkoutDate = LocalDate.now().minusDays(15);
                LocalDate dueDate = LocalDate.now().minusDays(10);

                mem.getCheckoutRecord().addCheckoutEntry(bookCopy, checkoutDate, dueDate);
                bookCopy.changeAvailability();

                da.updateMember(mem);
                da.updateBook(book);
                
                
		
	}
	///create books
	public void bookData() {
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(1).addCopy();
		allBooks.get(3).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		DataAccessFacade.loadBookMap(allBooks);
	}
	
	public void userData() {
		DataAccessFacade.loadUserMap(allUsers);
	}
	
	//create library members
	public void libraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
		members.add(libraryMember);
		
		DataAccessFacade.loadMemberMap(members);	
	}
	
	///////////// DATA //////////////
	List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")
	
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
			add(new Address("501 Central", "Mountain View", "CA", "94707"));
		}
	};
	@SuppressWarnings("serial")
	public List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he.", true));
			add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she.", true));
			add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts.", true));
			add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books.", true));
			add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style.", true));
		}
	};
	
	@SuppressWarnings("serial")
	List<Book> allBooks = new ArrayList<Book>() {
		
		// create and assoc authors - , Arrays.asList(allAuthors.get(0), allAuthors.get(1))
		{
			add(new Book("23-11451", "The Big Fish", 2, 21));
			add(new Book("28-12331", "Antartica", 3, 7));
			add(new Book("99-22223", "Thinking Java", 4, 21));
			add(new Book("48-56882", "Jimmy's First Day of School", 5, 7));		
		}
	};
	
	@SuppressWarnings("serial")
	List<User> allUsers = new ArrayList<User>() {
		{
			add(new User("101", "xyz", Auth.LIBRARIAN));
			add(new User("102", "abc", Auth.ADMIN));
			add(new User("103", "111", Auth.BOTH));
		}
	};
}
