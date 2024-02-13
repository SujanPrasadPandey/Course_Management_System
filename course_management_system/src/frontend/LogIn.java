package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.DbConn;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_email;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		Connection conn = DbConn.dbConn();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1333, 777);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_appName = new JLabel("Knowledge Tree: CMS");
		lbl_appName.setForeground(Color.WHITE);
		lbl_appName.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
		lbl_appName.setBounds(416, 10, 458, 71);
		contentPane.add(lbl_appName);
		
		JLabel lbl_logIn = new JLabel("LOG IN");
		lbl_logIn.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_logIn.setForeground(Color.WHITE);
		lbl_logIn.setFont(new Font("Segoe UI", Font.BOLD, 33));
		lbl_logIn.setBounds(496, 91, 307, 51);
		contentPane.add(lbl_logIn);
		
		JLabel lbl_email = new JLabel("Email");
		lbl_email.setForeground(Color.WHITE);
		lbl_email.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_email.setBounds(416, 210, 164, 24);
		contentPane.add(lbl_email);
		
		textField_email = new JTextField();
		textField_email.setForeground(Color.WHITE);
		textField_email.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField_email.setColumns(10);
		textField_email.setBackground(Color.GRAY);
		textField_email.setBounds(416, 235, 458, 51);
		contentPane.add(textField_email);
		
		JLabel lbl_password = new JLabel("Password");
		lbl_password.setForeground(Color.WHITE);
		lbl_password.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_password.setBounds(416, 336, 164, 24);
		contentPane.add(lbl_password);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		passwordField.setBackground(Color.GRAY);
		passwordField.setBounds(417, 359, 457, 51);
		contentPane.add(passwordField);
		
		JButton btn_logIn = new JButton("    Log In");
		btn_logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userEmail = textField_email.getText().toString();
				String userPassword = String.valueOf(passwordField.getPassword());
				
				try {
					String query = "SELECT * FROM user WHERE email = ? AND password = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, userEmail);
					pst.setString(2, userPassword);
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while (rs.next()) {
						count++;
					}
					if(count == 1) {
			            DashBoard dashboard_frame = new DashBoard(userEmail);
			            dashboard_frame.setVisible(true);
				 		LogIn.this.dispose();
					} else if (count > 1) {
						JOptionPane.showMessageDialog(null, "Email and Password Duplicate");
					} else {
						JOptionPane.showMessageDialog(null, "Email and Password is NOT Correct");
					}
					rs.close();
					pst.close();
				 } catch (Exception exc_login) {
					 JOptionPane.showMessageDialog(null, exc_login);
				 }
			}
		});
		btn_logIn.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\logInLogo.png"));
		btn_logIn.setForeground(Color.WHITE);
		btn_logIn.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		btn_logIn.setBackground(Color.GRAY);
		btn_logIn.setBounds(503, 547, 286, 65);
		contentPane.add(btn_logIn);
		
		JButton btn_goBack = new JButton("");
		btn_goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeScreen welcome_frame = new WelcomeScreen();
				welcome_frame.setVisible(true);
				
				LogIn.this.dispose();
			}
		});
		btn_goBack.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\backArrow.png"));
		btn_goBack.setBounds(10, 10, 65, 65);
		contentPane.add(btn_goBack);
		
		JButton btn_info = new JButton("");
		btn_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutMe aboutMeDialog = new AboutMe();
		        aboutMeDialog.setVisible(true);
			}
		});
		btn_info.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\about.png"));
		btn_info.setBackground(Color.LIGHT_GRAY);
		btn_info.setBounds(1265, 686, 44, 44);
		contentPane.add(btn_info);
	}

}
