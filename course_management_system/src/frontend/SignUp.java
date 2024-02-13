package frontend;

import backend.DbConn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane_SignUp;
	private JTextField textField_name;
	private JTextField textField_phone;
	private JPasswordField passwordField;
	private JTextField textField_email;
	private JComboBox<String> comboBox_course;
	private JPasswordField passwordField_SU;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		Connection conn = DbConn.dbConn();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1333, 777);
		contentPane_SignUp = new JPanel();
		contentPane_SignUp.setBackground(Color.DARK_GRAY);
		contentPane_SignUp.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane_SignUp);
		contentPane_SignUp.setLayout(null);
		
		JButton btn_goBack = new JButton("");
		btn_goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeScreen welcome_frame = new WelcomeScreen();
				welcome_frame.setVisible(true);
				
				SignUp.this.dispose();
			}
		});
		btn_goBack.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\backArrow.png"));
		btn_goBack.setBounds(10, 10, 65, 65);
		contentPane_SignUp.add(btn_goBack);
		
		
		
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
		contentPane_SignUp.add(btn_info);
		
		JLabel lbl_appName = new JLabel("Knowledge Tree: CMS");
		lbl_appName.setForeground(Color.WHITE);
		lbl_appName.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
		lbl_appName.setBounds(416, 10, 458, 71);
		contentPane_SignUp.add(lbl_appName);
		
		JLabel lbl_createAcc = new JLabel("CREATE ACCOUNT");
		lbl_createAcc.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_createAcc.setForeground(Color.WHITE);
		lbl_createAcc.setFont(new Font("Segoe UI", Font.BOLD, 33));
		lbl_createAcc.setBounds(496, 91, 307, 51);
		contentPane_SignUp.add(lbl_createAcc);
		
		JLabel lbl_name = new JLabel("Name");
		lbl_name.setForeground(Color.WHITE);
		lbl_name.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_name.setBounds(416, 152, 164, 24);
		contentPane_SignUp.add(lbl_name);
		
		JLabel lbl_email = new JLabel("Email");
		lbl_email.setForeground(Color.WHITE);
		lbl_email.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_email.setBounds(416, 248, 164, 24);
		contentPane_SignUp.add(lbl_email);
		
		JLabel lbl_phone = new JLabel("Phone");
		lbl_phone.setForeground(Color.WHITE);
		lbl_phone.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_phone.setBounds(416, 339, 164, 24);
		contentPane_SignUp.add(lbl_phone);
		
		JLabel lbl_password = new JLabel("Password");
		lbl_password.setForeground(Color.WHITE);
		lbl_password.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_password.setBounds(416, 433, 164, 24);
		contentPane_SignUp.add(lbl_password);
		
		JLabel lbl_mode = new JLabel("Mode");
		lbl_mode.setForeground(Color.WHITE);
		lbl_mode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_mode.setBounds(416, 527, 164, 28);
		contentPane_SignUp.add(lbl_mode);
		
		textField_name = new JTextField();
		textField_name.setForeground(Color.WHITE);
		textField_name.setBackground(Color.GRAY);
		textField_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField_name.setColumns(10);
		textField_name.setBounds(416, 177, 458, 51);
		contentPane_SignUp.add(textField_name);
		
		textField_email = new JTextField();
		textField_email.setForeground(Color.WHITE);
		textField_email.setBackground(Color.GRAY);
		textField_email.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField_email.setColumns(10);
		textField_email.setBounds(416, 273, 458, 51);
		contentPane_SignUp.add(textField_email);
		
		textField_phone = new JTextField();
		textField_phone.setForeground(Color.WHITE);
		textField_phone.setBackground(Color.GRAY);
		textField_phone.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField_phone.setColumns(10);
		textField_phone.setBounds(416, 362, 458, 51);
		contentPane_SignUp.add(textField_phone);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(Color.GRAY);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		passwordField.setBounds(417, 456, 457, 51);
		contentPane_SignUp.add(passwordField);
		
		JPanel panel_courseChoose = new JPanel();
		panel_courseChoose.setBackground(Color.DARK_GRAY);
		panel_courseChoose.setBounds(932, 381, 288, 111);
		contentPane_SignUp.add(panel_courseChoose);
		panel_courseChoose.setLayout(null);
		

		JLabel lbl_course = new JLabel("Course");
		lbl_course.setBounds(34, 10, 164, 24);
		panel_courseChoose.add(lbl_course);
		lbl_course.setForeground(Color.WHITE);
		lbl_course.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		JPanel panel_adminSU = new JPanel();
		panel_adminSU.setBackground(Color.DARK_GRAY);
		panel_adminSU.setBounds(932, 517, 288, 111);
		contentPane_SignUp.add(panel_adminSU);
		panel_adminSU.setLayout(null);
		
		passwordField_SU = new JPasswordField();
		passwordField_SU.setForeground(Color.WHITE);
		passwordField_SU.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		passwordField_SU.setBackground(Color.GRAY);
		passwordField_SU.setBounds(36, 33, 208, 51);
		panel_adminSU.add(passwordField_SU);
		
		JLabel lbl_password_SU = new JLabel("Super User KeyPass");
		lbl_password_SU.setForeground(Color.WHITE);
		lbl_password_SU.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_password_SU.setBounds(35, 10, 232, 24);
		panel_adminSU.add(lbl_password_SU);
		
		comboBox_course = new JComboBox<String>();
		comboBox_course.setBounds(34, 33, 208, 51);
		panel_courseChoose.add(comboBox_course);
		comboBox_course.setForeground(Color.WHITE);
		comboBox_course.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		comboBox_course.setBackground(Color.GRAY);
		
		JPanel panel_chooseModule = new JPanel();
		panel_chooseModule.setBackground(Color.DARK_GRAY);
		panel_chooseModule.setBounds(932, 252, 288, 111);
		contentPane_SignUp.add(panel_chooseModule);
		panel_chooseModule.setLayout(null);
		
		JComboBox<String> comboBox_module = new JComboBox<String>();
		comboBox_module.setBounds(37, 35, 208, 51);
		panel_chooseModule.add(comboBox_module);
		comboBox_module.setForeground(Color.WHITE);
		comboBox_module.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		comboBox_module.setBackground(Color.GRAY);
		
		JLabel lbl_module = new JLabel("Module");
		lbl_module.setBounds(37, 10, 155, 28);
		panel_chooseModule.add(lbl_module);
		lbl_module.setForeground(Color.WHITE);
		lbl_module.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		//get the courses from database to the combobox
		try {
            String query = "SELECT name FROM course";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String courseName = rs.getString("name");
                comboBox_course.addItem(courseName);
            }
        } catch (Exception exc_comboBox_course) {
        	exc_comboBox_course.printStackTrace();
        }
		
		//get the modules for the teacher from database to combobox
		try {
            String query = "SELECT classCode FROM module;";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String moduleName = rs.getString("classCode");
                comboBox_module.addItem(moduleName);
            }
        } catch (Exception exc_comboBox_module) {
        	exc_comboBox_module.printStackTrace();
        }
		
		JComboBox<String> comboBox_mode = new JComboBox<String>();
		comboBox_mode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedMode = (String) comboBox_mode.getSelectedItem();
				
				// Check the selected mode and update visibility accordingly
                if ("Student".equals(selectedMode)) {
                	panel_courseChoose.setVisible(true);
                	panel_adminSU.setVisible(false);
                	panel_chooseModule.setVisible(false);
                	
                	panel_courseChoose.setBounds(634, 517, 288, 111);
                	
                }else if ("Admin".equals(selectedMode)){
                	panel_courseChoose.setVisible(false);
                	panel_adminSU.setVisible(true);
                	panel_chooseModule.setVisible(false);
                	
                	panel_adminSU.setBounds(634, 517, 288, 111);
                	
                }else if ("Teacher".equals(selectedMode)){
                	panel_courseChoose.setVisible(false);
                	panel_adminSU.setVisible(false);
                	panel_chooseModule.setVisible(true);
                	
                	panel_chooseModule.setBounds(634, 517, 288, 111);
                }
			}
		});
		comboBox_mode.setForeground(Color.WHITE);
		comboBox_mode.setBackground(Color.GRAY);
		comboBox_mode.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		comboBox_mode.setBounds(416, 552, 208, 51);
		contentPane_SignUp.add(comboBox_mode);
		comboBox_mode.addItem("Student");
		comboBox_mode.addItem("Teacher");
		comboBox_mode.addItem("Admin");
		
		
		
		JButton btn_signUp = new JButton("    Sign Up");
		// ActionListener for SignUp button
		btn_signUp.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String password = String.valueOf(passwordField.getPassword());
		        String email = textField_email.getText();
		        String selectedMode = comboBox_mode.getSelectedItem().toString();

		        if (validateEmail(email) && !checkEmailExists(email, conn) && validatePassword(password, conn) && validateAdmin(selectedMode, String.valueOf(passwordField_SU.getPassword()), conn)) {
		            int userId = insertUser(textField_name.getText(), email, textField_phone.getText(), password, selectedMode, conn);

		            if (userId != -1) {
		                if ("Student".equals(selectedMode)) {
		                    insertStudent(userId, (String) comboBox_course.getSelectedItem(), conn);
		                } else if ("Teacher".equals(selectedMode)) {
		                    insertTeacher(userId, (String) comboBox_module.getSelectedItem(), conn);
		                } else if ("Admin".equals(selectedMode)) {
		                    insertAdmin(userId, conn);
		                }


		                // Now go to login page
		                LogIn login_frame = new LogIn();
		                login_frame.setVisible(true);

		                SignUp.this.dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Failed to add user to user table.");
		            }
		        }
		    }
		});
		
		btn_signUp.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\signUpLogo.png"));
		btn_signUp.setForeground(Color.WHITE);
		btn_signUp.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		btn_signUp.setBackground(Color.GRAY);
		btn_signUp.setBounds(503, 638, 286, 65);
		contentPane_SignUp.add(btn_signUp);
		
		
		
		
	}
	// Method to validate email format
	private boolean validateEmail(String email) {
	    String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
	    Pattern patternEmail = Pattern.compile(regexEmail);
	    Matcher matcherEmail = patternEmail.matcher(email);
	    if (!matcherEmail.matches()) {
	        JOptionPane.showMessageDialog(null, "Invalid Email Format");
	        return false;
	    }
	    return true;
	}

	// Method to check if email exists in the database
	private boolean checkEmailExists(String email, Connection conn) {
	    try {
	        String checkUserQuery = "SELECT COUNT(*) FROM user WHERE email = ?";
	        PreparedStatement checkUserPst = conn.prepareStatement(checkUserQuery);
	        checkUserPst.setString(1, email);
	        ResultSet userCountResultSet = checkUserPst.executeQuery();

	        if (userCountResultSet.next() && userCountResultSet.getInt(1) > 0) {
	            JOptionPane.showMessageDialog(null, "User with the provided email already exists");
	            return true;
	        }
	    } catch (SQLException ex_checkEmailDatabase) {
	        ex_checkEmailDatabase.printStackTrace();
	    }
	    return false;
	}

	// Method to validate password strength
	private boolean validatePassword(String password, Connection conn) {
	    String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
	    Pattern patternPassword = Pattern.compile(regexPassword);
	    Matcher matcherPassword = patternPassword.matcher(password);

	    if (!matcherPassword.matches()) {
	        int PasswordConfirm = JOptionPane.showConfirmDialog(null, "Password seems weak. Still continue?", "Weak Password Detected", JOptionPane.YES_NO_OPTION);
	        return PasswordConfirm == JOptionPane.YES_OPTION;
	    }
	    return true;
	}

	// Method to handle admin validation
	private boolean validateAdmin(String selectedMode, String enteredSUPass, Connection conn) {
	    if ("Admin".equals(selectedMode)) {
	        try {
	            String query = "SELECT superUserPassword FROM adminKey";
	            PreparedStatement pst = conn.prepareStatement(query);
	            ResultSet rs = pst.executeQuery();

	            if (rs.next() && !enteredSUPass.equals(rs.getString("superUserPassword"))) {
	                JOptionPane.showMessageDialog(null, "Wrong Super User Password");
	                return false;
	            }
	        } catch (SQLException e_adminKey) {
	            e_adminKey.printStackTrace();
	        }
	    }
	    return true;
	}

	// Method to insert user into the 'user' table
	private int insertUser(String name, String email, String phone, String password, String selectedMode, Connection conn) {
	    try {
	        String insertUserQuery = "INSERT INTO user (name, email, phone, password, mode) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement insertUserPst = conn.prepareStatement(insertUserQuery, PreparedStatement.RETURN_GENERATED_KEYS);
	        insertUserPst.setString(1, name);
	        insertUserPst.setString(2, email);
	        insertUserPst.setString(3, phone);
	        insertUserPst.setString(4, password);
	        insertUserPst.setString(5, selectedMode);

	        int affectedRows = insertUserPst.executeUpdate();

	        if (affectedRows > 0) {
	            ResultSet generatedKeys = insertUserPst.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return -1;
	}

	// Method to insert student into the 'student' table
	private void insertStudent(int userId, String selectedCourse, Connection conn) {
	    try {
	        String getCourseIdQuery = "SELECT id FROM course WHERE name = ?";
	        PreparedStatement getCourseIdPst = conn.prepareStatement(getCourseIdQuery);
	        getCourseIdPst.setString(1, selectedCourse);
	        ResultSet courseResultSet = getCourseIdPst.executeQuery();

	        if (courseResultSet.next()) {
	            int courseId = courseResultSet.getInt("id");

	            String insertStudentQuery = "INSERT INTO student (userId, courseId) VALUES (?, ?)";
	            PreparedStatement insertStudentPst = conn.prepareStatement(insertStudentQuery);
	            insertStudentPst.setInt(1, userId);
	            insertStudentPst.setInt(2, courseId);
	            insertStudentPst.executeUpdate();
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	// Method to insert teacher into the 'teacher' table
	private void insertTeacher(int userId, String moduleName, Connection conn) {
	    try {
	        String insertTeacherQuery = "INSERT INTO teacher (teacherId, moduleId) VALUES (?, (SELECT id FROM module WHERE classCode = ?))";
	        PreparedStatement insertTeacherPst = conn.prepareStatement(insertTeacherQuery);
	        insertTeacherPst.setInt(1, userId);
	        insertTeacherPst.setString(2, moduleName);

	        int affectedRowsTeacher = insertTeacherPst.executeUpdate();

	        if (affectedRowsTeacher > 0) {
	            JOptionPane.showMessageDialog(null, "Teacher added successfully.");
	        } else {
	            JOptionPane.showMessageDialog(null, "Failed to add teacher to teacher table.");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	private void insertAdmin(int userId, Connection conn) {
	    try {
	        // Update the mode to 'Admin' for the user
	        String updateModeQuery = "UPDATE user SET mode = 'Admin' WHERE id = ?";
	        try (PreparedStatement updateModePst = conn.prepareStatement(updateModeQuery)) {
	            updateModePst.setInt(1, userId);
	            int affectedRowsUpdateMode = updateModePst.executeUpdate();

	            if (affectedRowsUpdateMode > 0) {
	                // Admin added successfully (updated mode to 'Admin')
	                JOptionPane.showMessageDialog(null, "Admin added successfully.");
	            } else {
	                // No rows affected, mode update failed
	                JOptionPane.showMessageDialog(null, "Failed to update user mode to 'Admin'.");
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	
}
