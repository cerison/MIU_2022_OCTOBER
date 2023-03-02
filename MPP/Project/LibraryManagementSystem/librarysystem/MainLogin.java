/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librarysystem;

import utility.FrameDragListenerUtil;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;
import java.awt.Color;


public class MainLogin extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1234234L;
	private ControllerInterface ci;

    /**
     * Creates new form Login
     */
    public MainLogin() {
        ci = new SystemController();
        initComponents();
    }
    
  
    
    public static void main(String args[]) {
    	 /*
         * This main code is a program that uses the Java Swing library to create a graphical user interface.
         * The first section of the code sets the "Nimbus" look and feel for the program, which is an optional feature that can be used to change the appearance of the program. 
         * If the Nimbus look and feel is not available, the code defaults to the default look and feel.
         * The next section of the code creates an instance of the Main class, which is likely a class that extends the JFrame class and contains the main components 
         * of the program's user interface. The FrameDragListenerUtil is a class that allows to drag the frame by clicking on it.
         * Then, the code sets the title of the Main instance to "Login", sets the size of the frame, sets the frame to be centered on the screen, and makes the frame visible to the user.
         * The last line of code invoke the 'run' method of the Runnable interface, which is used to display the form and run the program.
         */
    	try {
    	setLookAndFeel();
    	} catch (Exception e) {
    	logError(e);
    	}
    	java.awt.EventQueue.invokeLater(new Runnable() {
    	    public void run() {
    	        MainLogin login = new MainLogin();

    	        FrameDragListenerUtil frameDragListener = new FrameDragListenerUtil(login);
    	        login.addMouseListener(frameDragListener);
    	        login.addMouseMotionListener(frameDragListener);

    	        login.setTitle("Login");
    	        login.pack();
    	        login.setLocationRelativeTo(null);
    	        login.setVisible(true);
    	    }
    	});
    }

    private static void setLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	    if ("Nimbus".equals(info.getName())) {
	    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	    break;
    }
    }
    }

    private static void logError(Exception e) {
    java.util.logging.Logger.getLogger(MainLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
    }


    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
    	/*
         * This code is executed when the user clicks on the login button. It gets the username and password entered by the user from the text fields, 
         * and then calls the login method of the "ci" object, passing in the username and password as arguments.
         * If the login is successful, it shows a message dialog box with the message "Welcome (ADMIN/LIBRARIAN)" or "Welcome (auth)". 
         * Based on the auth value , it will open the library member window or checkoutRecord window, and close the current login window.
         * If the login fails, it shows an error message dialog box with the message "Invalid Credentials" and a custom dialog box with the message 
         * "Invalid Credentials!" and also clear the password field.
         */
        String username = txtFieldUsername.getText();
        char[] password = passwordTextField.getPassword();
        try {
            ci.login(username, new String(password));
            Auth auth = SystemController.currentAuth;
            JOptionPane.showMessageDialog(this, "Welcome " + (auth == Auth.BOTH ? "ADMIN/LIBRARIAN" : auth));
            switch (auth) {
                case LIBRARIAN:
                    this.setVisible(false);
                    CheckoutRecordWindow checkoutRecordWindow = new CheckoutRecordWindow();
                    FrameDragListenerUtil frameDragListener2 = new FrameDragListenerUtil(checkoutRecordWindow);
                    checkoutRecordWindow.addMouseListener(frameDragListener2);
                    checkoutRecordWindow.addMouseMotionListener(frameDragListener2);
                    checkoutRecordWindow.setLocationRelativeTo(null);
                    checkoutRecordWindow.setVisible(true);
                    break;
                default:
                    this.setVisible(false);
                    LibraryMemberWindow libraryMember = new LibraryMemberWindow();
                    FrameDragListenerUtil frameDragListener1 = new FrameDragListenerUtil(libraryMember);
                    libraryMember.addMouseListener(frameDragListener1);
                    libraryMember.addMouseMotionListener(frameDragListener1);
                    libraryMember.setLocationRelativeTo(null);
                    libraryMember.setVisible(true);
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Credentials");
            CustomConfirmationFailedDialog dialog = new CustomConfirmationFailedDialog("Invalid Credentials!");
            dialog.setVisible(true);
            passwordTextField.setText("");
        }
    }
//GEN-LAST:event_btnLoginActionPerformed

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
    	/*
    	 * This code is defining a login window for a library system using the Java Swing library. It creates several components such as a login panel, image panel, labels, 
    	 * text fields, and buttons. The image panel is currently commented out and it's not clear what image is being set for the jLabel7. 
    	 * The layout for the image panel is also defined and set to default. 
    	 * The final effect of this code is that it creates the visual elements of the login window but it does not handle any login logic or functionality.
    	 */

        panelLogin = new javax.swing.JPanel();
        panelImage = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        panelLoginForm = new javax.swing.JPanel();
        btnLoginExit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFieldUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        passwordTextField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(1, 1, 1, 1));
        setUndecorated(true);

        panelLogin.setBackground(new java.awt.Color(255, 255, 255));
        panelLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        panelLogin.setForeground(new java.awt.Color(204, 204, 204));
        panelImage.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelImageLayout.setVerticalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelLoginForm.setBackground(new java.awt.Color(128, 135, 133));

        btnLoginExit.setBackground(new java.awt.Color(53, 137, 224));
        btnLoginExit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLoginExit.setForeground(new java.awt.Color(255, 255, 255));
        btnLoginExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLoginExit.setText("X");
        btnLoginExit.setToolTipText("Close to exit the application");
        btnLoginExit.setOpaque(true);
        btnLoginExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginExitMouseExited(evt);
            }
        });


        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Group 7 Library Management System");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password");

        txtFieldUsername.setBackground(new java.awt.Color(200, 200, 255));
        txtFieldUsername.setForeground(new java.awt.Color(0, 0, 0));
   

        btnLogin.setBackground(new java.awt.Color(53, 137, 224));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("LOGIN");
     
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        passwordTextField.setBackground(new java.awt.Color(200, 200, 255));
        passwordTextField.setForeground(new java.awt.Color(0, 0, 0));
       

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
      

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLoginFormLayout = new javax.swing.GroupLayout(panelLoginForm);
        panelLoginForm.setLayout(panelLoginFormLayout);
        panelLoginFormLayout.setHorizontalGroup(
            panelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, panelLoginFormLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLoginExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelLoginFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelLoginFormLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE,177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLoginFormLayout.createSequentialGroup()
                        .addGroup(panelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE,175, Short.MAX_VALUE)
                            .addComponent(txtFieldUsername)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        panelLoginFormLayout.setVerticalGroup(
            panelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(panelLoginFormLayout.createSequentialGroup()
                .addComponent(btnLoginExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGroup(panelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(panelLoginFormLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLoginFormLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(panelLoginFormLayout.createSequentialGroup()
                        .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE,40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(panelLoginFormLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addComponent(panelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelLoginForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                    .addComponent(panelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLoginForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnLoginExitMouseClicked

    private void btnLoginExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginExitMouseEntered
        btnLoginExit.setBackground(Color.red);
        btnLoginExit.setForeground(Color.white);
    }//GEN-LAST:event_btnLoginExitMouseEntered

    private void btnLoginExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginExitMouseExited
        btnLoginExit.setBackground(new Color(53,137,224));
        btnLoginExit.setForeground(Color.white);
    }//GEN-LAST:event_btnLoginExitMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel btnLoginExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel panelImage;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPanel panelLoginForm;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JTextField txtFieldUsername;
    // End of variables declaration//GEN-END:variables
}
