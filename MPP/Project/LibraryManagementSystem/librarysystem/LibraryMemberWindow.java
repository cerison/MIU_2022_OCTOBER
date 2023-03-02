package librarysystem;

import utility.FrameDragListenerUtil;
import business.Address;
import business.ControllerInterface;
import java.awt.Color;
import javax.swing.JOptionPane;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import business.LibraryMember;
import business.SystemController;
import dataaccess.Auth;
import static dataaccess.Auth.BOTH;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import utility.MouseListenerUtil;

/*
 * This code is defining a class called "LibraryMemberWindow" which is a JFrame class in the Java Swing library. 
 * The class has a constructor method that initializes the frame and its components, and also has a private method called "init" 
 * which sets up some variables and data for the frame. The constructor method also checks the current authentication status and 
 * removes certain mouse listeners from certain elements of the frame based on the authentication status. The "init" method sets 
 * the controller interface and data access variables, and then creates a 2D array of strings called "data" which is populated with 
 * information from the library member objects from the data access facade. It then creates a new table model using this data and sets 
 * it as the table model for the frame.
 */
public class LibraryMemberWindow extends javax.swing.JFrame {

    private ControllerInterface controllerInterface;
    private DataAccess dataAccess;
    private DefaultTableModel tableModel;

    public LibraryMemberWindow() {
        init();
        initComponents();
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

    private void init() {
        controllerInterface = new SystemController();
        dataAccess = new DataAccessFacade();

        String[][] data = new String[dataAccess.readMemberMap().values().size()][8];
        int index = 0;
        for (LibraryMember member : dataAccess.readMemberMap().values()) {
            int columnNumber = 0;
            data[index][columnNumber++] = member.getMemberId();
            data[index][columnNumber++] = member.getTelephone();
            data[index][columnNumber++] = member.getAddress().getStreet();
            data[index][columnNumber++] = member.getAddress().getCity();
            data[index][columnNumber++] = member.getAddress().getState();
            data[index][columnNumber++] = member.getAddress().getZip();

            index++;
        }
        tableModel = new DefaultTableModel(data,
                new String[]{
                    "Member ID", "Telephone", "Street Address", "State", "City", "Zip Code"
                }
        );
    }
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String memberID = memberIdTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String telephone = telephoneTextField.getText();
        String street = streetAddressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String zipCode = zipCodeTextField.getText();

        if (memberID.equals("") || firstName.equals("") || lastName.equals("") || telephone.equals("")
                || street.equals("") || city.equals("") || state.equals("") || zipCode.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return;
        }
        
        if(ValidationUtil.memberExists(memberID)){
            JOptionPane.showMessageDialog(null, "There is existing library member with that ID.");
            return;
        }

        if (!ValidationUtil.isValidAlpha(firstName) || !ValidationUtil.isValidAlpha(lastName)) {
            JOptionPane.showMessageDialog(null, "Invalid first name or last name");
            return;
        }
        
        if (!ValidationUtil.isValidPhone(telephone)) {
            JOptionPane.showMessageDialog(null, "Invalid phone number");
            return;
        }
        
        if (!ValidationUtil.isValidZipCode(zipCode)) {
            JOptionPane.showMessageDialog(null, "Invalid zip code");
            return;
        }

        Address address = new Address(street, city, state, zipCode);
        LibraryMember libraryMember = new LibraryMember(memberID, firstName, lastName, telephone, address);
        controllerInterface.addLibraryMember(libraryMember);
        tableModel.addRow(new String[]{memberID, firstName, lastName, telephone, street, state, city, zipCode});
        CustomConfirmationSuccessDialog.getCustomConfirmationSuccessDialog("Added Successfully");

        memberIdTextField.setText("");
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        telephoneTextField.setText("");
        streetAddressTextField.setText("");
        cityTextField.setText("");
        stateTextField.setText("");
        zipCodeTextField.setText("");
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    	/*
    	 * This code is triggered when a button labeled "Add" is pressed. 
    	 * It retrieves text input from several text fields and uses it to create a new LibraryMember object, which is then passed to a controller 
    	 * for adding to a database or data structure. The method also includes several checks for valid input, such as checking if all fields are filled,
    	 *  if the member ID is unique, and if the name, phone number and zipcode are in valid format using a validation utility class. If all the input is valid, 
    	 *  the method adds the new LibraryMember object to a table and display a success message. If there is any invalid input, 
    	 *  it will display an error message and return without adding the Library Member.
    	 */
        int row = libraryMembersTable.getSelectedRow();
        if (row >= 0) {
            tableModel.removeRow(row);
            memberIdTextField.setText("");
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            streetAddressTextField.setText("");
            cityTextField.setText("");
            stateTextField.setText("");
            zipCodeTextField.setText("");
            telephoneTextField.setText("");
            JOptionPane.showMessageDialog(null, "Deleted Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    	
    	/*
    	 * This code is a Java method that is triggered when a button labeled "Update" is clicked. 
    	 * It appears to be updating the selected row in a table called "libraryMembersTable" with values entered into several text fields (memberIdTextField, 
    	 * firstNameTextField, lastNameTextField, telephoneTextField, streetAddressTextField, cityTextField, stateTextField, zipCodeTextField). 
    	 * Before updating the selected row, it performs several validation checks to ensure that the data entered into the text fields is valid. 
    	 * If any of the text fields are empty or if the data entered is invalid, it displays an error message using the JOptionPane class.
    	 * If the validation checks pass and a row is selected, the method updates the selected row with the data entered into the text fields, 
    	 * and shows a message that the update was successful.
    	 */
        int row = libraryMembersTable.getSelectedRow();
        
        String memberID = memberIdTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String telephone = telephoneTextField.getText();
        String street = streetAddressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String zipCode = zipCodeTextField.getText();

        if (memberID.equals("") || firstName.equals("") || lastName.equals("") || telephone.equals("")
                || street.equals("") || city.equals("") || state.equals("") || zipCode.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return;
        }
        
//        if(ValidationUtil.memberExists(memberID)){
//            JOptionPane.showMessageDialog(null, "There is existing library member with that ID.");
//            return;
//        }

        if (!ValidationUtil.isValidAlpha(firstName) || !ValidationUtil.isValidAlpha(lastName)) {
            JOptionPane.showMessageDialog(null, "Invalid first name or last name");
            return;
        }
        
        if (!ValidationUtil.isValidPhone(telephone)) {
            JOptionPane.showMessageDialog(null, "Invalid phone number");
            return;
        }
        
        if (!ValidationUtil.isValidZipCode(zipCode)) {
            JOptionPane.showMessageDialog(null, "Invalid zip code");
            return;
        }
        
        if (row >= 0) {
            libraryMembersTable.setValueAt(memberID, row, 0);
            libraryMembersTable.setValueAt(telephone, row, 1);
            libraryMembersTable.setValueAt(street, row, 2);
            libraryMembersTable.setValueAt(telephone, row, 3);
            libraryMembersTable.setValueAt(street, row, 4);
            libraryMembersTable.setValueAt(city, row, 5);
            libraryMembersTable.setValueAt(state, row, 6);
            libraryMembersTable.setValueAt(zipCode, row, 7);
            
            JOptionPane.showMessageDialog(null, "Updated Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
    	/*
    	 * This code is a Java method that is executed when the "Clear" button is pressed. It appears to clear the text fields for a library member's ID, 
    	 * first name, last name, street address, city, state, zip code, and telephone number 
    	 * by setting the text of each text field to an empty string.
    	 */
        memberIdTextField.setText("");
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        streetAddressTextField.setText("");
        cityTextField.setText("");
        stateTextField.setText("");
        zipCodeTextField.setText("");
        telephoneTextField.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void libraryMembersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libraryMembersTableMouseClicked
    	/*
    	 * This code is for the event handler of a mouse click on a table (libraryMembersTable). When a user clicks on a row of the table, 
    	 * the code is retrieving the values of certain columns from the selected row and setting them as the text in certain text fields 
    	 * (memberIdTextField, telephoneTextField, etc.). The text fields' text will be set to the values of the columns retrieved from the selected row of the table. 
    	 * Also, it sets the text color of all these text fields to black.
    	 */
        int r = libraryMembersTable.getSelectedRow();
        memberIdTextField.setText(tableModel.getValueAt(r, 0).toString());
        telephoneTextField.setText(tableModel.getValueAt(r, 3).toString());
        streetAddressTextField.setText(tableModel.getValueAt(r, 4).toString());
        cityTextField.setText(tableModel.getValueAt(r, 5).toString());
        stateTextField.setText(tableModel.getValueAt(r, 6).toString());
        zipCodeTextField.setText(tableModel.getValueAt(r, 7).toString());

        memberIdTextField.setForeground(Color.black);
        telephoneTextField.setForeground(Color.black);
        streetAddressTextField.setForeground(Color.black);
        cityTextField.setForeground(Color.black);
        stateTextField.setForeground(Color.black);
        zipCodeTextField.setForeground(Color.black);
    }//GEN-LAST:event_libraryMembersTableMouseClicked

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        jPanel2 = new javax.swing.JPanel();
        btnCloseWindow = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        basicInfoLabel = new javax.swing.JLabel();
        memberIdLabel = new javax.swing.JLabel();
        memberIdTextField = new javax.swing.JTextField();
        firstNameLabel = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        lastNameTextField = new javax.swing.JTextField();
        telephoneLabel = new javax.swing.JLabel();
        telephoneTextField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        streetAddressLabel = new javax.swing.JLabel();
        streetAddressTextField = new javax.swing.JTextField();
        stateLabel = new javax.swing.JLabel();
        stateTextField = new javax.swing.JTextField();
        cityLabel = new javax.swing.JLabel();
        cityTextField = new javax.swing.JTextField();
        zipCodeLabel = new javax.swing.JLabel();
        zipCodeTextField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        libraryMembersTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        jPanel1.setBackground(new java.awt.Color(107, 117, 113));
        //Library system header
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("  Library system");
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        panelLinkManageMembers.setBackground(new java.awt.Color(60, 170, 230));

        imgManageMembers.setBackground(new java.awt.Color(60, 170, 230));
        imgManageMembers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //imgManageMembers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_user_account_20px.png"))); // NOI18N
        imgManageMembers.setOpaque(true);

        labelManageMembers.setBackground(new java.awt.Color(60, 170, 230));
        labelManageMembers.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        labelManageMembers.setForeground(new java.awt.Color(255, 255, 255));
        labelManageMembers.setText(" Manage Members");
        labelManageMembers.setOpaque(true);

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
        panelLinkCheckoutRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelLinkCheckoutRecordsMouseClicked(evt);
            }
        });

        imgCheckoutRecords.setBackground(new java.awt.Color(60, 170, 230));
        imgCheckoutRecords.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       // imgCheckoutRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_to_do_20px.png"))); // NOI18N
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

        labelCheckoutRecords.setBackground(new java.awt.Color(60, 170, 230));
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
           // .addComponent(panelLinkLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        headingLabel.setText("Manage Members of Library");

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

        basicInfoLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        basicInfoLabel.setText("Basic Info");
        basicInfoLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(102, 102, 102)));

        memberIdLabel.setText("Member ID");

        memberIdTextField.setForeground(new java.awt.Color(0, 51, 51));
        memberIdTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        firstNameLabel.setText("First Name");

        firstNameTextField.setForeground(new java.awt.Color(0, 51, 51));
        firstNameTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        lastNameLabel.setText("Last Name");

        lastNameTextField.setForeground(new java.awt.Color(0, 51, 51));
        lastNameTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        telephoneLabel.setText("Telephone");

        telephoneTextField.setForeground(new java.awt.Color(0, 51, 51));
        telephoneTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        addressLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        addressLabel.setText("Address");
        addressLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(102, 102, 102)));

        streetAddressLabel.setText("Street Address");

        streetAddressTextField.setForeground(new java.awt.Color(0, 51, 51));
        streetAddressTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        stateLabel.setText("State");

        stateTextField.setForeground(new java.awt.Color(0, 51, 51));
        stateTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        cityLabel.setText("City");

        cityTextField.setForeground(new java.awt.Color(0, 51, 51));
        cityTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        zipCodeLabel.setText("Zip Code");

        zipCodeTextField.setForeground(new java.awt.Color(0, 51, 51));
        zipCodeTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout); //
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnAdd)
                .addGap(28, 28, 28)
                .addComponent(btnUpdate)
                .addGap(28, 28, 28)
                .addComponent(btnDelete)
                .addGap(28, 28, 28)
                .addComponent(btnClear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(telephoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(telephoneTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lastNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(firstNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(firstNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(38, 38, 38)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stateTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cityLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cityTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(zipCodeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(zipCodeTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(basicInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(streetAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(streetAddressTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(addressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(memberIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memberIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(basicInfoLabel)
                    .addComponent(addressLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(streetAddressLabel)
                        .addGap(5, 5, 5)
                        .addComponent(streetAddressTextField))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(memberIdLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(memberIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(firstNameLabel)
                        .addGap(5, 5, 5)
                        .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(stateLabel)
                        .addGap(5, 5, 5)
                        .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lastNameLabel)
                        .addGap(5, 5, 5)
                        .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(telephoneLabel)
                        .addGap(5, 5, 5)
                        .addComponent(telephoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cityLabel)
                        .addGap(5, 5, 5)
                        .addComponent(cityTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(zipCodeLabel)
                        .addGap(5, 5, 5)
                        .addComponent(zipCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        libraryMembersTable.setModel(tableModel);
        libraryMembersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                libraryMembersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(libraryMembersTable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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


    private void btnCloseWindowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseWindowMouseExited
        btnCloseWindow.setBackground(Color.white);
        btnCloseWindow.setForeground(Color.black);
    }//GEN-LAST:event_btnCloseWindowMouseExited

    private void panelLinkManageBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLinkManageBooksMouseClicked
        navigateToBookWindow();
    }//GEN-LAST:event_panelLinkManageBooksMouseClicked

    private void imgManageBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgManageBooksMouseClicked
        navigateToBookWindow();
    }//GEN-LAST:event_imgManageBooksMouseClicked

    private void labelManageBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelManageBooksMouseClicked
        navigateToBookWindow();
    }//GEN-LAST:event_labelManageBooksMouseClicked

    private void panelLinkCheckoutRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLinkCheckoutRecordsMouseClicked
        navigateToCheckoutRecordWindow();
    }//GEN-LAST:event_panelLinkCheckoutRecordsMouseClicked

    private void imgCheckoutRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgCheckoutRecordsMouseClicked
        navigateToCheckoutRecordWindow();
    }//GEN-LAST:event_imgCheckoutRecordsMouseClicked

    private void labelCheckoutRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCheckoutRecordsMouseClicked
        navigateToCheckoutRecordWindow();
    }//GEN-LAST:event_labelCheckoutRecordsMouseClicked


    

 // This method is used to navigate from the current window to the BookWindow.
    private void navigateToBookWindow() {
	    // Hides the current window
	    this.setVisible(false);
	    // Instantiates a new BookWindow object
	    BookWindow bookWindow = new BookWindow();
	 // Instantiates a new FrameDragListenerUtil object, passing in the bookWindow as a parameter
	    FrameDragListenerUtil frameDragListener = new FrameDragListenerUtil(bookWindow);
	    // Adds the frameDragListener as a mouse listener and motion listener to the bookWindow
	    bookWindow.addMouseListener(frameDragListener);
	    bookWindow.addMouseMotionListener(frameDragListener);
	    // Sets the bookWindow location to the center of the screen
	    bookWindow.setLocationRelativeTo(null);
	    // Makes the bookWindow visible
	    bookWindow.setVisible(true);
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


    private javax.swing.JLabel addressLabel;
    private javax.swing.JLabel basicInfoLabel;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JLabel btnCloseWindow;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JLabel imgCheckoutRecords;
    //private javax.swing.JLabel imgLinkLogout;
    private javax.swing.JLabel imgManageBooks;
    private javax.swing.JLabel imgManageMembers;
    //private javax.swing.JLabel imgMoreInfo;
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
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JTable libraryMembersTable;
    private javax.swing.JLabel memberIdLabel;
    private javax.swing.JTextField memberIdTextField;
    private javax.swing.JPanel panelLinkCheckoutRecords;
    private javax.swing.JPanel panelLinkManageBooks;
    private javax.swing.JPanel panelLinkManageMembers;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JTextField stateTextField;
    private javax.swing.JLabel streetAddressLabel;
    private javax.swing.JTextField streetAddressTextField;
    private javax.swing.JLabel telephoneLabel;
    private javax.swing.JTextField telephoneTextField;
    private javax.swing.JLabel zipCodeLabel;
    private javax.swing.JTextField zipCodeTextField;
    // End of variables declaration//GEN-END:variables
}
