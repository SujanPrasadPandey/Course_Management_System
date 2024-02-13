package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane_welcomeScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreen frame = new WelcomeScreen();
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
	public WelcomeScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1333, 777);
		contentPane_welcomeScreen = new JPanel();
		contentPane_welcomeScreen.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane_welcomeScreen);
		contentPane_welcomeScreen.setLayout(null);
		
		JLabel lbl_AppName = new JLabel("Knowledge Tree: Course Management System (by SPP)");
		lbl_AppName.setBounds(10, 10, 1299, 83);
		lbl_AppName.setForeground(new Color(255, 255, 255));
		lbl_AppName.setFont(new Font("Segoe UI Black", Font.BOLD, 45));
		contentPane_welcomeScreen.add(lbl_AppName);
		
		JLabel lbl_createdBy = new JLabel("Created By: Sujan Prasad Pandey (SPP)");
		lbl_createdBy.setBounds(10, 692, 438, 38);
		lbl_createdBy.setForeground(new Color(255, 255, 255));
		lbl_createdBy.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));
		contentPane_welcomeScreen.add(lbl_createdBy);
		
		JButton btn_logIn = new JButton("   Log In");
		btn_logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn login_frame = new LogIn();
				login_frame.setVisible(true);
				WelcomeScreen.this.dispose();
			}
		});
		btn_logIn.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\logInLogo.png"));
		btn_logIn.setBounds(235, 354, 349, 83);
		btn_logIn.setBackground(new Color(253, 245, 230));
		btn_logIn.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		contentPane_welcomeScreen.add(btn_logIn);
		
		JButton btn_signUp = new JButton("   Sign Up");
		btn_signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp signup_frame = new SignUp();
				signup_frame.setVisible(true);
				WelcomeScreen.this.dispose();
				
			}
		});
		btn_signUp.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\signUpLogo.png"));
		btn_signUp.setBounds(753, 354, 349, 83);
		btn_signUp.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		btn_signUp.setBackground(new Color(253, 245, 230));
		contentPane_welcomeScreen.add(btn_signUp);
		
		JButton btn_info = new JButton("");
		btn_info.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\about.png"));
		btn_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        AboutMe aboutMeDialog = new AboutMe();
		        aboutMeDialog.setVisible(true);
			}
		});
		btn_info.setBackground(Color.LIGHT_GRAY);
		btn_info.setBounds(1265, 686, 44, 44);
		contentPane_welcomeScreen.add(btn_info);
		
		JLabel lbl_backgroundImage = new JLabel("");
		lbl_backgroundImage.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\bookcase_welcomeScreen.png"));
		lbl_backgroundImage.setBounds(-16, -40, 1356, 805);
		contentPane_welcomeScreen.add(lbl_backgroundImage);
	}
}
