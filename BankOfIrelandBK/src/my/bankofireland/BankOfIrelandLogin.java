/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.bankofireland;

import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Class BankOfIrelandLogin allows the user to Login and Access the main GUI BankOfIrelandUI
 * @author A00037189
 */
public class BankOfIrelandLogin extends javax.swing.JFrame {
   
  /**
   * *Creates new form BankOfIrelandLogin
   */
    /** Connection to hold the con*/
    Connection con;
    /** Statement to hold the stmt*/
    Statement stmt;
    /**ResultSet to hold the rs*/
    ResultSet rs;
    /**Integer to hold the count*/
    int count;
    /**Integer to hold the current*/
    int current;
    /**String to hold the username*/
    private String username;
    /**String to hold the password*/
    private String password;

    /**
     * Constructor to initialize the data
     */
    public BankOfIrelandLogin() {
        username="";
        password="";
        
        initComponents();        //method to initialize GUI components
        dbConn();		// method to connect to database using odbc-jdbc
        initDB();               //method to display Database info to the GUI
              
    }
    /**
     * Connects to the Bank database
     */
    public void dbConn()
    {
	try	
	{		// load the jdbc-odbc bridge driver manager
		//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
			// driver to use with named database
		//String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=c:\\Bank\\Bank.mdb";
            String url = "jdbc:ucanaccess://c:/Bank/Bank.mdb";
  	
			// connection represents a session with a specific database
		con = DriverManager.getConnection(url);

			// stmt used for executing sql statements and obtaining results
		stmt = con.createStatement();

		rs = stmt.executeQuery("SELECT * FROM Employee");

		while(rs.next())	// count number of rows in table
			count++;
		rs.close();
	}
	catch(Exception e) { System.out.println("Error in start up......");}
    }
    /**
     * Initializes information from the database to the GUI
     */
private void initDB()
    {
	try		// display database info in gui
	{
		rs = stmt.executeQuery("SELECT * FROM Employee");
		rs.next();   

		username = rs.getString("UserName");
   		password = rs.getString("Password");
                
               
		current = 1;
		rs.close();
                        
	}
	catch(Exception e) {System.out.println("Error in initialising DB info to GUI");}
    }
/**
 * Clears the Username and Password fields
 * @param evt 
 */
private void ClearActionPerformed(java.awt.event.ActionEvent evt) {                                      
                UserNme.setText("");
		Passwrd.setText("");
                
                // TODO add your handling code here:
    }  
/**
 * Finds the specified username from the database
 * @param nameToFind String value to hold the username
 * @param passwordToFind String value to hold the password
 */
private void login(String nameToFind, String  passwordToFind)
     {
        int foundUser = 0;
        boolean found = false;
        
        try
        {
            rs = stmt.executeQuery("Select * FROM Employee");
            
            while(rs.next() && found == false)
            {
                foundUser++; //will hold the number of the row where the name was
                String curName = rs.getString("Username");
                String curPass = rs.getString("Password");
                curName.trim(); //trims white spaces
                curPass.trim();//trims white spaces
                if (curName.equals(nameToFind)&& curPass.equals(passwordToFind))
                    found = true;
            }
            rs.close();
        }
        
        catch(Exception e) {System.out.println("Error finding User in databse"); e.printStackTrace();}
        
        if (found == true)
        {
            this.dispose();
            BankOfIrelandUI b= new BankOfIrelandUI();
            b.setVisible(true);            
        }
        else
        {UserNme.setText("");
         Passwrd.setText("");
         Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(BankOfIrelandLogin.this,
    "Incorrect Username or Password, please try again",
    "Invalid Login", JOptionPane.PLAIN_MESSAGE);}
     }
/** 
 * Moves to a selected row in the database and displays it in the GUI
 * @param index String value to hold the specified database row
 */
public void moveToRow(int index)
    {
	try
	{
		rs = stmt.executeQuery("SELECT * FROM Employee");
		
		for(int i = 0; i < index; i++)
			rs.next();  	// move to specific row in table (at index)

		username = rs.getString("UserName");
   		password = rs.getString("Password");		// get info by type
		

		
		
		current = index;
                
		rs.close();
	}
	catch(Exception e) {System.out.println("Error in moving to row at index specified");}
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BankLogo = new javax.swing.JLabel();
        Passwrd = new javax.swing.JPasswordField();
        UserNme = new javax.swing.JTextField();
        Uname = new javax.swing.JLabel();
        Pword = new javax.swing.JLabel();
        Submit = new javax.swing.JButton();
        Exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(98, 149, 199));

        jPanel1.setBackground(new java.awt.Color(98, 149, 199));

        BankLogo.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        BankLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bankofirelandbk/boigm2.png"))); // NOI18N
        BankLogo.setText("Bank Of Ireland Accounts Admin");
        BankLogo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BankLogo.setPreferredSize(new java.awt.Dimension(468, 115));

        Passwrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswrdActionPerformed(evt);
            }
        });

        UserNme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserNmeActionPerformed(evt);
            }
        });

        Uname.setBackground(new java.awt.Color(204, 204, 204));
        Uname.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        Uname.setText("Username");
        Uname.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Pword.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        Pword.setText("Password");
        Pword.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Submit.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        Submit.setText("Submit");
        Submit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        Exit.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        Exit.setText("Exit");
        Exit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(BankLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Pword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Uname, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Passwrd, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                    .addComponent(UserNme)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(BankLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(UserNme)
                    .addComponent(Uname, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Passwrd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * Closes the GUI and exits the system
 * @param evt listens for the Exit button click
 */
    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_ExitActionPerformed
/**
 * Verifies the username and password from the database
 * @param evt listens for the Submit button click 
 * uses the login method to verify user input
 */
    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        
    username = UserNme.getText();
    password = Passwrd.getText();
    login(username, password);	
        
        
       
//WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
//Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
// TODO add your handling code here:
    }//GEN-LAST:event_SubmitActionPerformed

    
        
    
    private void UserNmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserNmeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserNmeActionPerformed

    private void PasswrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswrdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswrdActionPerformed

    /** 
     * Main Method is the starting point for the application
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BankOfIrelandLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BankOfIrelandLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BankOfIrelandLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BankOfIrelandLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BankOfIrelandLogin().setVisible(true);
                
                
                
            }
        });
    }
    /** 
     * Initializes the components onto the GUI.
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BankLogo;
    private javax.swing.JButton Exit;
    private javax.swing.JPasswordField Passwrd;
    private javax.swing.JLabel Pword;
    private javax.swing.JButton Submit;
    private javax.swing.JLabel Uname;
    private javax.swing.JTextField UserNme;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
