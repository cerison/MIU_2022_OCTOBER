/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librarysystem;

import utility.FrameDragListenerUtil;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import business.Book;
import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;
import static dataaccess.Auth.BOTH;
import java.awt.Color;

import java.awt.event.ActionEvent;
import utility.MouseListenerUtil;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;



public class BookWindow extends javax.swing.JFrame {
	/*
	 * This defines a class called "BookWindow" that extends the JFrame class from the javax.swing library. 
	 * It creates a new instance of the "SystemController" class and assigns it to the "ci" variable, which is an instance of the "ControllerInterface" interface. 
	 * It also creates a new instance of the "DefaultTableModel" class and assigns it to the "tableModel" variable. 
	 * The tableModel has 5 columns: "Title", "ISBN Number", "Total Number of Copies", "Copies Available", and "Maximum Checkout Length".
	 */
    private ControllerInterface ci;
    private DefaultTableModel tableModel;

    /**
     * Creates new form LibraryMemberWindow
     */
    public BookWindow() {
        ci = new SystemController();

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Title");
        tableModel.addColumn("ISBN Number");
        tableModel.addColumn("Total Number of Copies");
        tableModel.addColumn("Copies Available");
        tableModel.addColumn("Maximum Checkout Length");

        initComponents();

        loadListOfBooks();
        
        /*The code checks the current authentication status of the user and removes the mouse listener for certain panels, 
         * images, and labels depending on the authentication status. 
         * This is done using the "Auth" enumeration and the "MouseListenerUtil" class.
         */

        Auth auth = SystemController.currentAuth;
        if (auth != null) {
            switch (auth) {
                case BOTH:
                    break;
                case ADMIN:
                    MouseListenerUtil.removeMouseListener(panelLinkCheckoutRecords, imgCheckoutRecords, labelCheckoutRecords);
                    break;
                case LIBRARIAN:
                    MouseListenerUtil.removeMouseListener(panelLinkManageMembers, imgManageMembers, labelManageMembers);
                    MouseListenerUtil.removeMouseListener(panelLinkManageBooks, imgManageBooks, labelManageBooks);
                    break;
                default:
                    MouseListenerUtil.removeMouseListener(panelLinkCheckoutRecords, imgCheckoutRecords, labelCheckoutRecords);
                    MouseListenerUtil.removeMouseListener(panelLinkManageMembers, imgManageMembers, labelManageMembers);
                    MouseListenerUtil.removeMouseListener(panelLinkManageBooks, imgManageBooks, labelManageBooks);
                    break;
            }
        }
    }

    public void loadListOfBooks() {
    	/*
    	 * This code retrieves a list of books from an object (presumably the controller object, "ci") and populates a table with the book data. 
    	 * The method first sets the row count of the table model to zero, so that any previous data in the table is cleared. 
    	 * It then retrieves the list of books from the controller object using the "allBooks()" method. For each book in the list, 
    	 * the method inserts a new row into the table model and populates the row with data from the book object. Specifically, the title, 
    	 * ISBN, number of copies, number of available copies, and maximum checkout length of the book are inserted into the table. 
    	 * The data is inserted in the order of the array: title, ISBN, number of copies, number of available copies, 
    	 * and maximum checkout length of the book.
    	 */
        tableModel.setRowCount(0);
        List<Book> books = ci.allBooks();
        for (Book book : books) {
            this.tableModel.insertRow(
                    tableModel.getRowCount(),
                    new Object[]{
                        book.getTitle(),
                        book.getIsbn(),
                        book.getCopyNums().size(),
                        book.countAvailable(),
                        book.getMaxCheckoutLength()
                    }
            );
        }
    }
    

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
    	/*
    	 * This code is a method that is called when a button labeled "Add" is pressed in a Java program. It performs several actions:
    	 * It retrieves the text entered in several text fields (ISBN number, book title, number of copies, and number of checkout days) and assigns them to variables.
    	 * It checks if any of the fields are empty and displays an error message if any are empty.
    	 * It uses a method from a ValidationUtil class to check if the ISBN number, number of copies, and number of checkout days are numeric and displays an error message if any are not.
    	 * It uses a method from a ci (library catalog) object to check if a book with the same ISBN already exists in the catalog and displays an error message if it does.
    	 * It uses a try-catch block to parse the text in the number of copies and number of checkout days fields as integers. If it fails, it displays an error message and exits the method.
    	 * It creates a new Book object with the ISBN, title, number of copies, and max checkout length and adds it to the catalog using the ci object's addBook method.
    	 * It calls a loadListOfBooks method and displays a message to confirm that the book was added successfully.
    	 */
        String isbn = iSBNNumberTextField.getText();
        String title = bookTitleTextField.getText();
        String numberOfCopiesText = numberOfCopiesTextField.getText();
        String checkoutDays = numberOfCheckoutDaysTextField.getText();
        if(isbn.isEmpty() || title.isEmpty() || numberOfCopiesText.isEmpty() || checkoutDays.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Pleas fill out all fields.");
            return;
        }
        
        if(!ValidationUtil.isValidNumber(isbn) || !ValidationUtil.isValidNumber(numberOfCopiesText) || !ValidationUtil.isValidNumber(checkoutDays)){
            JOptionPane.showMessageDialog(null, "ISBN/Number of copies/Max checkout must be numeric.");
            return;
        }
        
        if (ci.bookAlreadyExists(isbn)) {
            JOptionPane.showMessageDialog(null, "A book with this ISBN already exists.");
            return;
        }
        
        int maxCheckoutLength = 0, numberOfCopies = 0;
        
        /*parse the text in the "numberOfCopiesTextField" text field as an integer. If it is successful, the parsed integer is assigned to the variable "numberOfCopies". 
         * However, if the parse fails (e.g. the text field contains a non-numeric character), it will catch the resulting exception and display 
         * a message dialog saying "Number of copies must be a number." before returning and exiting the method. This is a way to validate user 
         * input to ensure that it is a valid number before proceeding with any further operations.
         */
        try {
            maxCheckoutLength = Integer.parseInt(numberOfCheckoutDaysTextField.getText());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Max number of checkout days must be a number.");
            return;
        }
        try {
            numberOfCopies = Integer.parseInt(numberOfCopiesTextField.getText());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Number of copies must be a number.");
            return;
        }

        Book book = new Book(isbn, title, numberOfCopies, maxCheckoutLength);
        ci.addBook(book);

        BookWindow.this.loadListOfBooks();
        JOptionPane.showMessageDialog(null, "Added book successfully.");
    }//GEN-LAST:event_btnAddActionPerformed
    
    private void btnAddCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCopyActionPerformed
    	/*
    	 * This code is a method that is called when a button labeled "Add Copy" is pressed in a Java program. It performs several actions:
    	 * It retrieves the selected row in a table displaying a list of books and assigns it to a variable.
    	 * It checks if a row is selected and if not it displays an error message and exits the method.
    	 * It gets the ISBN of the selected book from the table model and assigns it to a variable.
    	 * It uses the ci object's addBookCopyByIsbn method to add a copy of the book with the specified ISBN to the catalog.
    	 * It calls the loadListOfBooks method to update the table displaying the list of books.
    	 * It displays a message to confirm that the copy was added successfully.
    	 */
        int selectedRow = booksListTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row from the list of books");
            return;
        }

        String isbn = tableModel.getValueAt(selectedRow, 1).toString();
        ci.addBookCopyByIsbn(isbn);
        loadListOfBooks();

        JOptionPane.showMessageDialog(null, "Copy added successully.");
    }//GEN-LAST:event_btnAddCopyActionPerformed

    
    /*The btnClearActionPerformed method is called when the 'Clear' button is clicked.
	This method sets all the text fields in the add book form to empty strings.
    It does this by calling the setText() method on each text field, passing in an empty string as the argument.
    This is useful for the user when they want to clear the form and start adding a new book.
    */
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        iSBNNumberTextField.setText("");
        bookTitleTextField.setText("");
        numberOfCopiesTextField.setText("");
        numberOfCheckoutDaysTextField.setText("");
    }//GEN-LAST:event_btnClearActionPerformed
    
   


    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    	/*
    	 * This function handles the event when a user clicks on a "delete" button. 
    	 * It first checks if a row in a table (booksListTable) is selected. If not, a message dialog box appears to remind the user to select a row. 
    	 * If a row is selected, it gets the selected book from a list of books using the selected row index and the ISBN of the book. 
    	 * Then it calls a method ci.deleteBookById(selectedBook.getIsbn()) that deletes the book from the list. 
    	 * Finally, it reloads the list of books and shows a message dialog box to confirm the deletion.
    	 */
        int selectedRow = booksListTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row from the list of books");
            return;
        }

        Book selectedBook = ci.allBooks().get(selectedRow);
        ci.deleteBookById(selectedBook.getIsbn());
        loadListOfBooks();

        JOptionPane.showMessageDialog(null, "Book deleted successfully.");
    }//GEN-LAST:event_btnDeleteActionPerformed
    
    
    private void btnManageAuthors1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageAuthors1ActionPerformed
    	/*
    	 * This code is a function in Java that handles the event when a user clicks on a "manage authors" button. 
    	 * It creates an instance of the class OverdueWindow which is likely a window or dialog box for managing overdue books. 
    	 * Then it creates an instance of the class FrameDragListenerUtil which is likely a class that provides functionality 
    	 * for dragging the overdueWindow around the screen. The next line of the code attach a mouse listener and mouse motion listener of FrameDragListenerUtil 
    	 * class on overdueWindow. After that it centers the overdueWindow on the screen using the setLocationRelativeTo(null) method, 
    	 * which sets the location of the window to the center of the screen. Finally, it makes the overdueWindow visible using the setVisible(true) method, 
    	 * so the user can interact with it.
    	 */
    	// Create an instance of the OverdueWindow class
    	OverdueWindow overdueWindow = new OverdueWindow();
        // Create an instance of the FrameDragListenerUtil class and attach it to the overdueWindow
        FrameDragListenerUtil frameDragListener = new FrameDragListenerUtil(overdueWindow);
        overdueWindow.addMouseListener(frameDragListener);
        overdueWindow.addMouseMotionListener(frameDragListener);
        
        // Center the overdueWindow on the screen
        overdueWindow.setLocationRelativeTo(null);
        
        // Make the overdueWindow visible
        overdueWindow.setVisible(true);
    }
    
    //This method is called when the "Manage Authors" button is pressed
    private void btnManageAuthorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageAuthorsActionPerformed
	    //Check if a row is selected from the list of books
	    int selectedRow = booksListTable.getSelectedRow();
	    if (selectedRow == -1) {
	    //If no row is selected, display an error message
	    JOptionPane.showMessageDialog(this, "Please select a row from the list of books");
	    return;
	    }
	    //Hide the current window
	    this.setVisible(false);
	    //Retrieve the selected book from the list of books
	    Book selectedBook = ci.allBooks().get(selectedRow);

	    //Create a new window to manage the authors of the selected book
	    BookAuthorWindow bookAuthorWindow = new BookAuthorWindow(selectedBook);

	    //Add a drag listener to the new window
	    FrameDragListenerUtil frameDragListener = new FrameDragListenerUtil(bookAuthorWindow);
	    bookAuthorWindow.addMouseListener(frameDragListener);
	    bookAuthorWindow.addMouseMotionListener(frameDragListener);

	    //Center the new window on the screen
	    bookAuthorWindow.setLocationRelativeTo(null);
	    //Show the new window
	    bookAuthorWindow.setVisible(true);
	}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    
    /*
     * The "initComponents()" method is called to initialize the graphical components of the window. The "loadListOfBooks()" 
     * method is called to load a list of books into the tableModel. This method calls the "allBooks()" method on the "ci" variable, 
     * which returns a List of "Book" objects. For each "Book" object in the list, a new row is added to the tableModel with the book's title, ISBN, number of copies, 
     * number of available copies, and maximum checkout length.
     */
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        panelLinkManageMembers = new javax.swing.JPanel();
        imgManageMembers = new javax.swing.JLabel();
        labelManageMembers = new javax.swing.JLabel();
        panelLinkManageBooks = new javax.swing.JPanel();
        imgManageBooks = new javax.swing.JLabel();
        labelManageBooks = new javax.swing.JLabel();
        panelLinkCheckoutRecords = new javax.swing.JPanel();
        imgCheckoutRecords = new javax.swing.JLabel();
        labelCheckoutRecords = new javax.swing.JLabel();
//        panelLinkMoreInfo = new javax.swing.JPanel();
//        imgMoreInfo = new javax.swing.JLabel();
//        labelMoreInfo = new javax.swing.JLabel();
//        panelLinkLogout = new javax.swing.JPanel();
//        imgLinkLogout = new javax.swing.JLabel();
//        labelLinkLogout = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCloseWindow = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        memberIdLabel = new javax.swing.JLabel();
        bookTitleTextField = new javax.swing.JTextField();
        firstNameLabel = new javax.swing.JLabel();
        iSBNNumberTextField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        numberOfCopiesTextField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnAddCopy = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnManageAuthors = new javax.swing.JButton();
        btnManageAuthors1 = new javax.swing.JButton();
        lastNameLabel1 = new javax.swing.JLabel();
        numberOfCheckoutDaysTextField = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        booksListTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        jPanel1.setBackground(new java.awt.Color(107, 117, 113));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(" Library System");
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(25, 25, 255)));

        panelLinkManageMembers.setBackground(new java.awt.Color(53, 137, 224));
        panelLinkManageMembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelLinkManageMembersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelLinkManageMembersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelLinkManageMembersMouseExited(evt);
            }
        });

        imgManageMembers.setBackground(new java.awt.Color(53, 137, 224));
        imgManageMembers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //imgManageMembers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_user_account_20px.png"))); // NOI18N
        imgManageMembers.setOpaque(true);
        imgManageMembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgManageMembersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imgManageMembersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgManageMembersMouseExited(evt);
            }
        });

        labelManageMembers.setBackground(new java.awt.Color(53, 137, 224));
        labelManageMembers.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        labelManageMembers.setForeground(new java.awt.Color(255, 255, 255));
        labelManageMembers.setText(" Manage Library Members");
        labelManageMembers.setOpaque(true);
        labelManageMembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelManageMembersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelManageMembersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelManageMembersMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelLinkManageMembersLayout = new javax.swing.GroupLayout(panelLinkManageMembers);
        panelLinkManageMembers.setLayout(panelLinkManageMembersLayout);
        panelLinkManageMembersLayout.setHorizontalGroup(
            panelLinkManageMembersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLinkManageMembersLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(imgManageMembers, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelManageMembers, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
        );
        panelLinkManageMembersLayout.setVerticalGroup(
            panelLinkManageMembersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgManageMembers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelManageMembers, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelLinkManageBooks.setBackground(new java.awt.Color(60, 170, 230));

        imgManageBooks.setBackground(new java.awt.Color(60, 170, 230));
        imgManageBooks.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //imgManageBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_book_20px.png"))); // NOI18N
        imgManageBooks.setOpaque(true);

        labelManageBooks.setBackground(new java.awt.Color(60, 170, 230));
        labelManageBooks.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        labelManageBooks.setForeground(new java.awt.Color(255, 255, 255));
        labelManageBooks.setText(" Manage Books");
        labelManageBooks.setOpaque(true);

        javax.swing.GroupLayout panelLinkManageBooksLayout = new javax.swing.GroupLayout(panelLinkManageBooks);
        panelLinkManageBooks.setLayout(panelLinkManageBooksLayout);
        panelLinkManageBooksLayout.setHorizontalGroup(
            panelLinkManageBooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLinkManageBooksLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(imgManageBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelManageBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLinkManageBooksLayout.setVerticalGroup(
            panelLinkManageBooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgManageBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelManageBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelLinkCheckoutRecords.setBackground(new java.awt.Color(53, 137, 224));
        panelLinkCheckoutRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelLinkCheckoutRecordsMouseClicked(evt);
            }
        });

        imgCheckoutRecords.setBackground(new java.awt.Color(53, 137, 224));
        imgCheckoutRecords.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //imgCheckoutRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_to_do_20px.png"))); // NOI18N
        imgCheckoutRecords.setOpaque(true);
        imgCheckoutRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgCheckoutRecordsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imgCheckoutRecordsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgCheckoutRecordsMouseExited(evt);
            }
        });

        labelCheckoutRecords.setBackground(new java.awt.Color(53, 137, 224));
        labelCheckoutRecords.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        labelCheckoutRecords.setForeground(new java.awt.Color(255, 255, 255));
        labelCheckoutRecords.setText(" Checkout Records");
        labelCheckoutRecords.setOpaque(true);
        labelCheckoutRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCheckoutRecordsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCheckoutRecordsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCheckoutRecordsMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelLinkCheckoutRecordsLayout = new javax.swing.GroupLayout(panelLinkCheckoutRecords);
        panelLinkCheckoutRecords.setLayout(panelLinkCheckoutRecordsLayout);
        panelLinkCheckoutRecordsLayout.setHorizontalGroup(
            panelLinkCheckoutRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLinkCheckoutRecordsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(imgCheckoutRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelCheckoutRecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLinkCheckoutRecordsLayout.setVerticalGroup(
            panelLinkCheckoutRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgCheckoutRecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelCheckoutRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
        );


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLinkManageMembers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelLinkManageBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelLinkCheckoutRecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            //.addComponent(panelLinkMoreInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          //  .addComponent(panelLinkLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelLinkManageMembers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLinkManageBooks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLinkCheckoutRecords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                //.addComponent(panelLinkMoreInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               // .addComponent(panelLinkLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               // .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        btnCloseWindow.setBackground(new java.awt.Color(255, 255, 255));
        btnCloseWindow.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCloseWindow.setForeground(new java.awt.Color(51, 51, 51));
        btnCloseWindow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCloseWindow.setText("X");
        btnCloseWindow.setOpaque(true);
        btnCloseWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseWindowMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseWindowMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseWindowMouseExited(evt);
            }
        });

        headingLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 15)); // NOI18N
        headingLabel.setText("Manage Books");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCloseWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCloseWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        memberIdLabel.setText("Book Title");

        bookTitleTextField.setForeground(new java.awt.Color(0, 51, 51));
        bookTitleTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        firstNameLabel.setText("ISBN Number");

        iSBNNumberTextField.setForeground(new java.awt.Color(0, 51, 51));
        iSBNNumberTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        lastNameLabel.setText("Number of Copies");

        numberOfCopiesTextField.setForeground(new java.awt.Color(0, 51, 51));
        numberOfCopiesTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        numberOfCopiesTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberOfCopiesTextFieldActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnAddCopy.setText("Add Copy");
        btnAddCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCopyActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnManageAuthors.setText("Manage Authors");
        btnManageAuthors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageAuthorsActionPerformed(evt);
            }
        });

        btnManageAuthors1.setText("Overdue");
        btnManageAuthors1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageAuthors1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddCopy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManageAuthors, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(btnManageAuthors1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddCopy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnManageAuthors)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnManageAuthors1))
        );

        lastNameLabel1.setText("Max checkout(in days)");

        numberOfCheckoutDaysTextField.setForeground(new java.awt.Color(0, 51, 51));
        numberOfCheckoutDaysTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bookTitleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(memberIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iSBNNumberTextField)
                    .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numberOfCopiesTextField)
                    .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numberOfCheckoutDaysTextField)
                    .addComponent(lastNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lastNameLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numberOfCheckoutDaysTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(memberIdLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bookTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iSBNNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(191, 191, 191))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstNameLabel)
                            .addComponent(lastNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numberOfCopiesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        booksListTable.setModel(tableModel);
        jScrollPane1.setViewportView(booksListTable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    private void btnCloseWindowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseWindowMouseEntered
        btnCloseWindow.setBackground(Color.red);
        btnCloseWindow.setForeground(Color.white);
    }//GEN-LAST:event_btnCloseWindowMouseEntered

    private void btnCloseWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseWindowMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCloseWindowMouseClicked

    private void panelLinkManageMembersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLinkManageMembersMouseEntered
        linkManageMembersMouseEntered();
    }//GEN-LAST:event_panelLinkManageMembersMouseEntered

    private void panelLinkManageMembersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLinkManageMembersMouseExited
        linkManageMembersMouseExited();
    }//GEN-LAST:event_panelLinkManageMembersMouseExited

    private void imgCheckoutRecordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCheckoutRecordsMouseEntered
        linkCheckoutRecordsMouseEntered();
    }//GEN-LAST:event_imgCheckoutRecordsMouseEntered

    private void imgCheckoutRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCheckoutRecordsMouseExited
        linkCheckoutRecordsMouseExited();
    }//GEN-LAST:event_imgCheckoutRecordsMouseExited

    private void labelCheckoutRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCheckoutRecordsMouseExited
        linkCheckoutRecordsMouseExited();
    }//GEN-LAST:event_labelCheckoutRecordsMouseExited

    private void labelCheckoutRecordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCheckoutRecordsMouseEntered
        linkCheckoutRecordsMouseEntered();
    }//GEN-LAST:event_labelCheckoutRecordsMouseEntered

    private void imgManageMembersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgManageMembersMouseEntered
        linkManageMembersMouseEntered();
    }//GEN-LAST:event_imgManageMembersMouseEntered

    private void labelManageMembersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelManageMembersMouseEntered
        linkManageMembersMouseEntered();
    }//GEN-LAST:event_labelManageMembersMouseEntered

    private void imgManageMembersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgManageMembersMouseExited
        linkManageMembersMouseExited();
    }//GEN-LAST:event_imgManageMembersMouseExited

    private void labelManageMembersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelManageMembersMouseExited
        linkManageMembersMouseExited();
    }//GEN-LAST:event_labelManageMembersMouseExited

    private void btnCloseWindowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseWindowMouseExited
        btnCloseWindow.setBackground(Color.white);
        btnCloseWindow.setForeground(Color.black);
    }//GEN-LAST:event_btnCloseWindowMouseExited

    private void panelLinkManageMembersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLinkManageMembersMouseClicked
        navigateToLibraryMemberWindow();
    }//GEN-LAST:event_panelLinkManageMembersMouseClicked

    private void imgManageMembersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgManageMembersMouseClicked
        navigateToLibraryMemberWindow();
    }//GEN-LAST:event_imgManageMembersMouseClicked

    private void labelManageMembersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelManageMembersMouseClicked
        navigateToLibraryMemberWindow();
    }//GEN-LAST:event_labelManageMembersMouseClicked

    private void numberOfCopiesTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberOfCopiesTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberOfCopiesTextFieldActionPerformed

    private void panelLinkCheckoutRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLinkCheckoutRecordsMouseClicked
        navigateToCheckoutRecordWindow();
    }//GEN-LAST:event_panelLinkCheckoutRecordsMouseClicked

    private void imgCheckoutRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCheckoutRecordsMouseClicked
        navigateToCheckoutRecordWindow();
    }//GEN-LAST:event_imgCheckoutRecordsMouseClicked

    private void labelCheckoutRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCheckoutRecordsMouseClicked
        navigateToCheckoutRecordWindow();
    }//GEN-LAST:event_labelCheckoutRecordsMouseClicked

    
    private void navigateToLibraryMemberWindow() {
        this.setVisible(false);
        LibraryMemberWindow libraryMember = new LibraryMemberWindow();

        FrameDragListenerUtil frameDragListener = new FrameDragListenerUtil(libraryMember);
        libraryMember.addMouseListener(frameDragListener);
        libraryMember.addMouseMotionListener(frameDragListener);
        libraryMember.setLocationRelativeTo(null);
        libraryMember.setVisible(true);
    }

    private void navigateToCheckoutRecordWindow() {
        this.setVisible(false);
        CheckoutRecordWindow checkoutRecordWindow = new CheckoutRecordWindow();

        FrameDragListenerUtil frameDragListener = new FrameDragListenerUtil(checkoutRecordWindow);
        checkoutRecordWindow.addMouseListener(frameDragListener);
        checkoutRecordWindow.addMouseMotionListener(frameDragListener);
        checkoutRecordWindow.setLocationRelativeTo(null);
        checkoutRecordWindow.setVisible(true);
    }


    private void linkManageMembersMouseEntered() {
        panelLinkManageMembers.setBackground(new java.awt.Color(60, 170, 230));
        imgManageMembers.setBackground(new java.awt.Color(60, 170, 230));
        labelManageMembers.setBackground(new java.awt.Color(60, 170, 230));
    }

    private void linkManageMembersMouseExited() {
        panelLinkManageMembers.setBackground(new java.awt.Color(53, 137, 224));
        imgManageMembers.setBackground(new java.awt.Color(53, 137, 224));
        labelManageMembers.setBackground(new java.awt.Color(53, 137, 224));
    }

    private void linkCheckoutRecordsMouseEntered() {
        panelLinkCheckoutRecords.setBackground(new java.awt.Color(60, 170, 230));
        imgCheckoutRecords.setBackground(new java.awt.Color(60, 170, 230));
        labelCheckoutRecords.setBackground(new java.awt.Color(60, 170, 230));
    }

    private void linkCheckoutRecordsMouseExited() {
        panelLinkCheckoutRecords.setBackground(new java.awt.Color(53, 137, 224));
        imgCheckoutRecords.setBackground(new java.awt.Color(53, 137, 224));
        labelCheckoutRecords.setBackground(new java.awt.Color(53, 137, 224));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bookTitleTextField;
    private javax.swing.JTable booksListTable;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCopy;
    private javax.swing.JButton btnClear;
    private javax.swing.JLabel btnCloseWindow;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnManageAuthors;
    private javax.swing.JButton btnManageAuthors1;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JTextField iSBNNumberTextField;
    private javax.swing.JLabel imgCheckoutRecords;
    private javax.swing.JLabel imgManageBooks;
    private javax.swing.JLabel imgManageMembers;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCheckoutRecords;
    private javax.swing.JLabel labelManageBooks;
    private javax.swing.JLabel labelManageMembers;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel lastNameLabel1;
    private javax.swing.JLabel memberIdLabel;
    private javax.swing.JTextField numberOfCheckoutDaysTextField;
    private javax.swing.JTextField numberOfCopiesTextField;
    private javax.swing.JPanel panelLinkCheckoutRecords;
    private javax.swing.JPanel panelLinkManageBooks;
    private javax.swing.JPanel panelLinkManageMembers;
    // End of variables declaration//GEN-END:variables
}
