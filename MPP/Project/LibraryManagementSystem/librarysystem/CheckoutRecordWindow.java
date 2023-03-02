/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librarysystem;

import utility.FrameDragListenerUtil;
import java.awt.Color;
import java.awt.TrayIcon;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import business.Book;
import business.CheckoutEntry;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import dataaccess.Auth;
import static dataaccess.Auth.ADMIN;
import static dataaccess.Auth.BOTH;
import static dataaccess.Auth.LIBRARIAN;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import utility.MouseListenerUtil;


public class CheckoutRecordWindow extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private ControllerInterface ci;
    private DataAccess dataAccess;
    private DefaultTableModel tableModel;

    /**
     * Creates new form LibraryMemberWindow
     */
    public CheckoutRecordWindow() {
    	/*
    	 * This code creates a new window for a library's checkout records. It initializes a new instance of the SystemController class and a new DefaultTableModel 
    	 * for a table. It then adds columns to the table for Member ID, Book Title, ISBN Number, and Copy Number. 
    	 * It then calls a method to initialize the window's components and load a list of checkout entries. 
    	 * It then checks the current authentication level (stored in the SystemController.currentAuth variable) and removes mouse listener functionality 
    	 * from certain components based on the authentication level (admin, librarian, or both) 
    	 * using a utility method called MouseListenerUtil.
    	 */
        ci = new SystemController();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Member ID");
        tableModel.addColumn("Book Title");
        tableModel.addColumn("ISBN Number");
        tableModel.addColumn("Copy Number");
        initComponents();
        loadListOfCheckoutEntries();

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

    public void loadListOfCheckoutEntries() {
    	/*
    	 * This code is a method that loads a list of checkout entries into the table in the CheckoutRecordWindow. 
    	 * The method first clears any existing rows in the table by setting the row count to 0 using the setRowCount() method. 
    	 * Then it retrieves a list of CheckoutEntry objects from the SystemController (ci) by calling the allCheckoutEntries() method. 
    	 * It then iterates through the list of entries, adding a new row to the table for each entry. 
    	 * The new row contains the following data for each entry: the Member ID of the library member who checked out the book, 
    	 * the title of the book, the ISBN number of the book, and the copy number of the book. 
    	 * This information is obtained by calling various methods on the CheckoutEntry, CheckoutRecord, LibraryMember and BookCopy objects, such as getCheckoutRecord(), 
    	 * getLibraryMember(),getMemberId(),getBook(),getTitle(),getIsbn(),getBookCopy(),getCopyNum().
    	 */
        tableModel.setRowCount(0);
        List<CheckoutEntry> entries = ci.allCheckoutEntries();
        for (CheckoutEntry entry : entries) {
            this.tableModel.insertRow(
                    tableModel.getRowCount(),
                    new Object[]{
                        entry.getCheckoutRecord().getLibraryMember().getMemberId(),                     
                        entry.getBookCopy().getBook().getTitle(),
                        entry.getBookCopy().getBook().getIsbn(),
                        entry.getBookCopy().getCopyNum(),

                    }
            );
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
 
    private void initComponents() {
    	/*
    	 * This code initializes the components of a Java Swing application. It creates multiple JPanels, JLabels, JButtons, JTextFields, and a JTable, 
    	 * and sets properties for them such as backgrounds, borders, and text. It also sets up mouse listeners for certain components, 
    	 * such as when the mouse enters or exits them, or when they are clicked. The purpose of this code is likely to create the layout 
    	 * and functionality of a library system application, with links to manage members, books, and checkout records.
    	 */
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
        jPanel2 = new javax.swing.JPanel();
        btnCloseWindow = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        memberIdLabel = new javax.swing.JLabel();
        memberIdTextField = new javax.swing.JTextField();
        firstNameLabel = new javax.swing.JLabel();
        iSBNNumberTextField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnClear1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        checkoutRecordsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        jPanel1.setBackground(new java.awt.Color(107, 117, 113));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 30)); 
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("  Library system");
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        panelLinkManageMembers.setBackground(new java.awt.Color(60, 170, 230));
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

        imgManageMembers.setBackground(new java.awt.Color(60, 170, 230));
        imgManageMembers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       
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

        labelManageMembers.setBackground(new java.awt.Color(60, 170, 230));
        labelManageMembers.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        labelManageMembers.setForeground(new java.awt.Color(255, 255, 255));
        labelManageMembers.setText(" Manage Members");
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
        panelLinkManageBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelLinkManageBooksMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelLinkManageBooksMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelLinkManageBooksMouseExited(evt);
            }
        });

        imgManageBooks.setBackground(new java.awt.Color(60, 170, 230));
        imgManageBooks.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //imgManageBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_book_20px.png"))); // NOI18N
        imgManageBooks.setOpaque(true);
        imgManageBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgManageBooksMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imgManageBooksMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgManageBooksMouseExited(evt);
            }
        });

        labelManageBooks.setBackground(new java.awt.Color(60, 170, 230));
        labelManageBooks.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        labelManageBooks.setForeground(new java.awt.Color(255, 255, 255));
        labelManageBooks.setText(" Manage Books");
        labelManageBooks.setOpaque(true);
        labelManageBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelManageBooksMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelManageBooksMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelManageBooksMouseExited(evt);
            }
        });

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

        panelLinkCheckoutRecords.setBackground(new java.awt.Color(60, 170, 230));

        imgCheckoutRecords.setBackground(new java.awt.Color(60, 170, 230));
        imgCheckoutRecords.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      //  imgCheckoutRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_to_do_20px.png"))); // NOI18N
        imgCheckoutRecords.setOpaque(true);

        labelCheckoutRecords.setBackground(new java.awt.Color(60, 170, 230));
        labelCheckoutRecords.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        labelCheckoutRecords.setForeground(new java.awt.Color(255, 255, 255));
        labelCheckoutRecords.setText(" Checkout Records");
        labelCheckoutRecords.setOpaque(true);

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
         //   .addComponent(panelLinkLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                )
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(24, 04, 04)));

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
        headingLabel.setText("Checkout Records");

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

        memberIdLabel.setText("Member ID");

        memberIdTextField.setForeground(new java.awt.Color(0, 51, 51));
        memberIdTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        firstNameLabel.setText("ISBN Number");

        iSBNNumberTextField.setForeground(new java.awt.Color(0, 51, 51));
        iSBNNumberTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnUpdate.setText("Checkout");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnClear1.setBackground(new java.awt.Color(242, 242, 242));
        btnClear1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear1.setForeground(new java.awt.Color(51, 51, 51));
        btnClear1.setText("Print Checkout Record");
        btnClear1.setBorder(null);
        btnClear1.setOpaque(true);
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnClear1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnClear)
                .addGap(18, 18, 18)
                .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(memberIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memberIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(iSBNNumberTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    //.addComponent(btnClear2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memberIdLabel)
                            .addComponent(firstNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(memberIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iSBNNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        checkoutRecordsTable.setModel(tableModel);
        checkoutRecordsTable.setEditingColumn(0);
        checkoutRecordsTable.setEditingRow(0);
        jScrollPane1.setViewportView(checkoutRecordsTable);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void panelLinkManageBooksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLinkManageBooksMouseEntered
        linkManageBooksMouseEntered();
    }//GEN-LAST:event_panelLinkManageBooksMouseEntered

    private void panelLinkManageBooksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLinkManageBooksMouseExited
        linkManageBooksMouseExited();
    }//GEN-LAST:event_panelLinkManageBooksMouseExited

    private void imgManageBooksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgManageBooksMouseEntered
        linkManageBooksMouseEntered();
    }//GEN-LAST:event_imgManageBooksMouseEntered

    private void labelManageBooksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelManageBooksMouseEntered
        linkManageBooksMouseEntered();
    }//GEN-LAST:event_labelManageBooksMouseEntered

    private void imgManageBooksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgManageBooksMouseExited
        linkManageBooksMouseExited();
    }//GEN-LAST:event_imgManageBooksMouseExited

    private void labelManageBooksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelManageBooksMouseExited
        linkManageBooksMouseExited();
    }//GEN-LAST:event_labelManageBooksMouseExited

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

    private void panelLinkManageBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLinkManageBooksMouseClicked
        navigateToBookWindow();
    }//GEN-LAST:event_panelLinkManageBooksMouseClicked

    private void imgManageBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgManageBooksMouseClicked
        navigateToBookWindow();
    }//GEN-LAST:event_imgManageBooksMouseClicked

    private void labelManageBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelManageBooksMouseClicked
        navigateToBookWindow();
    }//GEN-LAST:event_labelManageBooksMouseClicked



    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        memberIdTextField.setText("");
        iSBNNumberTextField.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String memberId = memberIdTextField.getText();
        String isbn = iSBNNumberTextField.getText();

        if (memberId.isEmpty() || isbn.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all the fields.");
            return;
        }

        if (!ValidationUtil.isValidNumber(memberId) || !ValidationUtil.isValidNumber(isbn)) {
            JOptionPane.showMessageDialog(null, "Member id and ISBN must be numbers.");
            return;
        }

        String errorMessage = ci.validateMemberIDAndISBN(memberId, isbn);
        if (!errorMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, errorMessage);
            return;
        }

        ci.createCheckoutRecordEntry(memberId, isbn);
        CheckoutRecordWindow.this.loadListOfCheckoutEntries();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        String memberId = memberIdTextField.getText();

        if (memberId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter member id first.");
            return;
        }
        List<CheckoutEntry> memberCheckoutEntries = ci.getCheckoutEntriesByID(memberId);

        dataAccess = new DataAccessFacade();
        LibraryMember member = dataAccess.readMemberMap().get(memberId);
        System.out.println("Check out record of: " + member.getFullName());
        for (CheckoutEntry checkoutEntry : memberCheckoutEntries) {
            System.out.println(
                    checkoutEntry.getBookCopy().getBook().getTitle() + "|"
                    + checkoutEntry.getBookCopy().getBook().getIsbn() + "|"
                    + checkoutEntry.getBookCopy().getCopyNum() + "|"
                    + checkoutEntry.getCheckoutDate() + "|"
                    + checkoutEntry.getDueDate()
            );
        }
        MemberCheckoutHistoryWindow memberCheckoutHistoryWindow = new MemberCheckoutHistoryWindow(memberId);
        FrameDragListenerUtil frameDragListener = new FrameDragListenerUtil(memberCheckoutHistoryWindow);
        memberCheckoutHistoryWindow.addMouseListener(frameDragListener);
        memberCheckoutHistoryWindow.addMouseMotionListener(frameDragListener);
        memberCheckoutHistoryWindow.setLocationRelativeTo(null);
        memberCheckoutHistoryWindow.setVisible(true);
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void navigateToLibraryMemberWindow() {
        this.setVisible(false);
        LibraryMemberWindow libraryMember = new LibraryMemberWindow();

        FrameDragListenerUtil frameDragListener = new FrameDragListenerUtil(libraryMember);
        libraryMember.addMouseListener(frameDragListener);
        libraryMember.addMouseMotionListener(frameDragListener);
        libraryMember.setLocationRelativeTo(null);
        libraryMember.setVisible(true);
    }

    private void navigateToBookWindow() {
        this.setVisible(false);
        BookWindow bookWindow = new BookWindow();

        FrameDragListenerUtil frameDragListener = new FrameDragListenerUtil(bookWindow);
        bookWindow.addMouseListener(frameDragListener);
        bookWindow.addMouseMotionListener(frameDragListener);
        bookWindow.setLocationRelativeTo(null);
        bookWindow.setVisible(true);
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

    private void linkManageBooksMouseEntered() {
        panelLinkManageBooks.setBackground(new java.awt.Color(60, 170, 230));
        imgManageBooks.setBackground(new java.awt.Color(60, 170, 230));
        labelManageBooks.setBackground(new java.awt.Color(60, 170, 230));
    }

    private void linkManageBooksMouseExited() {
        panelLinkManageBooks.setBackground(new java.awt.Color(53, 137, 224));
        imgManageBooks.setBackground(new java.awt.Color(53, 137, 224));
        labelManageBooks.setBackground(new java.awt.Color(53, 137, 224));
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
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JLabel btnCloseWindow;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTable checkoutRecordsTable;
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
    private javax.swing.JLabel memberIdLabel;
    private javax.swing.JTextField memberIdTextField;
    private javax.swing.JPanel panelLinkCheckoutRecords;
    private javax.swing.JPanel panelLinkManageBooks;
    private javax.swing.JPanel panelLinkManageMembers;
    // End of variables declaration//GEN-END:variables
}
