package frontend;

import backend.DbConn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URI;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_notificationSearch;
	private JTable table_notification;
	private JTable table_teachers;
	private JTable table_modules;
	private JTable table_Courses;
	private JTable table_Students;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoard frame = new DashBoard(null);
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
	public DashBoard(String userEmail) {		
		Connection conn = DbConn.dbConn();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1333, 777);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String userName = null;
		String userMode = null;
		try  {
		    // Fetch userName and userMode based on userEmail
		    String userInfoQuery = "SELECT name AS userName, mode AS userMode FROM user WHERE email = ?";
		    PreparedStatement userInfoStmt = conn.prepareStatement(userInfoQuery);
		    userInfoStmt.setString(1, userEmail);
		    ResultSet userInfoResult = userInfoStmt.executeQuery();

		    if (userInfoResult.next()) {
		        userName = userInfoResult.getString("userName");
		        userMode = userInfoResult.getString("userMode");
		    } else {
		        JOptionPane.showMessageDialog(null, "User not found for email");
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Error fetching user information: " + ex.getMessage());
		}
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\BLOCK_Scrollpane.png"));
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setBounds(227, 70, 373, 35);
		contentPane.add(lblNewLabel);
		
		
		
		JLabel lbl_appName = new JLabel("Knowledge Tree");
		lbl_appName.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\tree.png"));
		lbl_appName.setForeground(Color.WHITE);
		lbl_appName.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		lbl_appName.setBounds(10, 10, 329, 62);
		contentPane.add(lbl_appName);
		
		JButton btn_openMail = new JButton("");
		btn_openMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    Desktop.getDesktop().browse(new URI("https://mail.google.com/mail/"));
                } catch (Exception e_linkedInOpen) {
                	e_linkedInOpen.printStackTrace();
                }
			}
		});
		btn_openMail.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\mail.png"));
		btn_openMail.setForeground(Color.BLACK);
		btn_openMail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_openMail.setBackground(Color.DARK_GRAY);
		btn_openMail.setBounds(1268, 10, 41, 41);
		contentPane.add(btn_openMail);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.DARK_GRAY);
		tabbedPane.setBounds(227, 82, 1082, 648);
		contentPane.add(tabbedPane);
		
		JButton btn_home = new JButton("Home");
		btn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btn_home.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\home.png"));
		btn_home.setForeground(Color.BLACK);
		btn_home.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_home.setBackground(Color.LIGHT_GRAY);
		btn_home.setBounds(20, 82, 197, 62);
		contentPane.add(btn_home);
		
		JButton btn_Grades = new JButton("Grades");
		btn_Grades.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = DbConn.dbConn()) {
		            // Fetch userId based on userEmail
		            String userIdQuery = "SELECT id, mode FROM user WHERE email = ?";
		            PreparedStatement userIdStmt = conn.prepareStatement(userIdQuery);
		            userIdStmt.setString(1, userEmail);
		            ResultSet userIdResult = userIdStmt.executeQuery();

		            if (userIdResult.next()) {
		                int userId = userIdResult.getInt("id");
		                String userMode = userIdResult.getString("mode");

		                if ("Student".equals(userMode)) {
		                    // If the user is a student, fetch studentId from the student table
		                    String studentIdQuery = "SELECT id FROM student WHERE userId = ?";
		                    PreparedStatement studentIdStmt = conn.prepareStatement(studentIdQuery);
		                    studentIdStmt.setInt(1, userId);
		                    ResultSet studentIdResult = studentIdStmt.executeQuery();

		                    if (studentIdResult.next()) {
		                        int studentId = studentIdResult.getInt("id");

		                        // Open the Grades frame with the studentId
		                        Grades grades_frame = new Grades(studentId);
		                        grades_frame.setVisible(true);
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Student information not found.");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Only students can access grades.");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "User not found for email: " + userEmail);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error checking user information: " + ex.getMessage());
		        }
		    }
		});
		btn_Grades.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\grades.png"));
		btn_Grades.setForeground(Color.BLACK);
		btn_Grades.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_Grades.setBackground(Color.LIGHT_GRAY);
		btn_Grades.setBounds(20, 596, 197, 62);
		contentPane.add(btn_Grades);
		
		
		JButton btn_LogOut = new JButton("Log Out");
		btn_LogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeScreen welcome_frame = new WelcomeScreen();
				welcome_frame.setVisible(true);
				
				DashBoard.this.dispose();
			}
		});
		btn_LogOut.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\logOut.png"));
		btn_LogOut.setForeground(Color.BLACK);
		btn_LogOut.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_LogOut.setBackground(Color.LIGHT_GRAY);
		btn_LogOut.setBounds(20, 668, 197, 62);
		contentPane.add(btn_LogOut);
		
		
		
		JPanel panel_home = new JPanel();
		panel_home.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Home", null, panel_home, null);
		panel_home.setLayout(null);
		
		JLabel lbl_noti = new JLabel("Notifications");
		lbl_noti.setForeground(Color.WHITE);
		lbl_noti.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lbl_noti.setBounds(20, 10, 218, 55);
		panel_home.add(lbl_noti);
		
		JPanel panel_modifyNoti = new JPanel();
		panel_modifyNoti.setBackground(Color.DARK_GRAY);
		panel_modifyNoti.setBounds(582, 10, 267, 55);
		panel_home.add(panel_modifyNoti);
		panel_modifyNoti.setLayout(null);
		
		JButton btn_deleteNoti = new JButton("Delete");
		btn_deleteNoti.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = DbConn.dbConn()) {
		            int selectedRow = table_notification.getSelectedRow();

		            if (selectedRow == -1) {
		                // No row selected
		                JOptionPane.showMessageDialog(null, "Please select a notification to delete.");
		                return;
		            }

		            int id = (int) table_notification.getValueAt(selectedRow, 0);
		            String deleteQuery = "DELETE FROM notification WHERE id = ?";

		            try (PreparedStatement deletePst = conn.prepareStatement(deleteQuery)) {
		                deletePst.setInt(1, id);
		                int affectedRows = deletePst.executeUpdate();

		                if (affectedRows > 0) {
		                    // Notification deleted successfully
		                    JOptionPane.showMessageDialog(null, "Notification deleted successfully.");
		                    // Update the table after deletion
		                    updateTable(conn);
		                } else {
		                    // No rows affected, deletion failed
		                    JOptionPane.showMessageDialog(null, "Failed to delete notification.");
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btn_deleteNoti.setBounds(138, 10, 118, 32);
		panel_modifyNoti.add(btn_deleteNoti);
		btn_deleteNoti.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_deleteNoti.setBackground(Color.LIGHT_GRAY);
		
		
		
		JButton btn_addNoti = new JButton("Add");
		btn_addNoti.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = DbConn.dbConn()) {
		            String newNotification = textField_notificationSearch.getText().trim();

		            if (!newNotification.isEmpty()) {
		                // Insert the new notification into the database
		                String insertQuery = "INSERT INTO notification (notification) VALUES (?)";
		                try (PreparedStatement insertPst = conn.prepareStatement(insertQuery)) {
		                    insertPst.setString(1, newNotification);
		                    insertPst.executeUpdate();
		                }

		                // Update the table to reflect the changes
		                updateTable(conn);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btn_addNoti.setBounds(10, 10, 118, 32);
		panel_modifyNoti.add(btn_addNoti);
		btn_addNoti.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_addNoti.setBackground(Color.LIGHT_GRAY);


		
		JScrollPane scrollPane_notification = new JScrollPane();
		scrollPane_notification.setBounds(20, 75, 1047, 536);
		panel_home.add(scrollPane_notification);
		
		table_notification = new JTable();
		scrollPane_notification.setViewportView(table_notification);
		table_notification.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Notification", "Time"
			}
		));
		table_notification.setForeground(Color.WHITE);
		table_notification.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		table_notification.setBackground(Color.DARK_GRAY);
		
		textField_notificationSearch = new JTextField();
		textField_notificationSearch.setBounds(859, 10, 208, 45);
		panel_home.add(textField_notificationSearch);
		textField_notificationSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_notificationSearch.setColumns(10);
		textField_notificationSearch.setBackground(Color.GRAY);
		
		textField_notificationSearch.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        updateTable();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        updateTable();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        // Plain text components do not fire these events
		    }

		    private void updateTable() {
		        try (Connection conn = DbConn.dbConn()) {
		            String searchText = textField_notificationSearch.getText().trim();
		            String searchQuery = "SELECT id, notification, timeStamp FROM notification WHERE notification LIKE ?";
		            try (PreparedStatement searchPst = conn.prepareStatement(searchQuery)) {
		                searchPst.setString(1, "%" + searchText + "%");

		                try (ResultSet searchRs = searchPst.executeQuery()) {
		                    DefaultTableModel model = (DefaultTableModel) table_notification.getModel();
		                    model.setRowCount(0); // Clear existing rows

		                    while (searchRs.next()) {
		                        int id = searchRs.getInt("id");
		                        String notificationText = searchRs.getString("notification");
		                        java.sql.Timestamp timeStamp = searchRs.getTimestamp("timeStamp");

		                        // Format the timestamp if needed
		                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		                        String formattedTimeStamp = dateFormat.format(timeStamp);

		                        model.addRow(new Object[]{id, notificationText, formattedTimeStamp});
		                    }
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		try {
		    String notificationQuery = "SELECT id, notification, timeStamp FROM notification";
		    PreparedStatement notificationPst = conn.prepareStatement(notificationQuery);
		    ResultSet notificationRs = notificationPst.executeQuery();

		    DefaultTableModel model = (DefaultTableModel) table_notification.getModel();

		    while (notificationRs.next()) {
		        int id = notificationRs.getInt("id");
		        String notificationText = notificationRs.getString("notification");
		        java.sql.Timestamp timeStamp = notificationRs.getTimestamp("timeStamp");

		        // Format the timestamp if needed
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String formattedTimeStamp = dateFormat.format(timeStamp);

		        model.addRow(new Object[]{id, notificationText, formattedTimeStamp});
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

		
		int teacherCount = 0;
		int studentCount = 0;
		int courseCount = 0;
		int moduleCount = 0;
		try {
		    // Count teachers
			String countTeachersQuery = "SELECT COUNT(*) AS teacherCount FROM Teacher";
			PreparedStatement countTeachersStmt = conn.prepareStatement(countTeachersQuery);
			ResultSet countTeachersResult = countTeachersStmt.executeQuery();
			teacherCount = countTeachersResult.next() ? countTeachersResult.getInt("teacherCount") : 0;

			// Count students
			String countStudentsQuery = "SELECT COUNT(*) AS studentCount FROM student";
			PreparedStatement countStudentsStmt = conn.prepareStatement(countStudentsQuery);
			ResultSet countStudentsResult = countStudentsStmt.executeQuery();
			studentCount = countStudentsResult.next() ? countStudentsResult.getInt("studentCount") : 0;


		    // Count courses
		    String countCoursesQuery = "SELECT COUNT(*) AS courseCount FROM course";
		    PreparedStatement countCoursesStmt = conn.prepareStatement(countCoursesQuery);
		    ResultSet countCoursesResult = countCoursesStmt.executeQuery();
		    courseCount = countCoursesResult.next() ? countCoursesResult.getInt("courseCount") : 0;

		    // Count modules
		    String countModulesQuery;
		    PreparedStatement countModulesStmt;
		    countModulesQuery = "SELECT COUNT(*) AS moduleCount FROM module;";
        	countModulesStmt = conn.prepareStatement(countModulesQuery);
		    ResultSet countModulesResult = countModulesStmt.executeQuery();
		    moduleCount = countModulesResult.next() ? countModulesResult.getInt("moduleCount") : 0;
		    
		    conn.close();
		} catch (Exception e_gettingCount) {
			e_gettingCount.printStackTrace();
		}
		
		JPanel panel_teachers = new JPanel();
		panel_teachers.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Teachers", null, panel_teachers, null);
		panel_teachers.setLayout(null);
		
		JLabel lbl_Teachers = new JLabel("Teachers");
		lbl_Teachers.setForeground(Color.WHITE);
		lbl_Teachers.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lbl_Teachers.setBounds(10, 10, 218, 55);
		panel_teachers.add(lbl_Teachers);
		
		JPanel panel_modifyTeachers = new JPanel();
		panel_modifyTeachers.setBackground(Color.DARK_GRAY);
		panel_modifyTeachers.setBounds(657, 10, 273, 55);
		panel_teachers.add(panel_modifyTeachers);
		panel_modifyTeachers.setLayout(null);
		
		
		
		JButton btn_addTeachers = new JButton("Add");
		btn_addTeachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTeacher add_teacher_frame = new AddTeacher();
				add_teacher_frame.setVisible(true);
			}
		});
		btn_addTeachers.setBounds(10, 10, 118, 32);
		panel_modifyTeachers.add(btn_addTeachers);
		btn_addTeachers.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_addTeachers.setBackground(Color.LIGHT_GRAY);
		
		JButton btn_deleteTeachers = new JButton("Delete");
		btn_deleteTeachers.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = DbConn.dbConn()) {
		            int selectedRow = table_teachers.getSelectedRow();

		            if (selectedRow == -1) {
		                // No row selected
		                JOptionPane.showMessageDialog(null, "Please select a teacher to delete.");
		                return;
		            }

		            int teacherId = (int) table_teachers.getValueAt(selectedRow, 0);

		            // Delete from teacher table
		            String deleteTeacherQuery = "DELETE FROM teacher WHERE teacherId = ?";
		            try (PreparedStatement deleteTeacherPst = conn.prepareStatement(deleteTeacherQuery)) {
		                deleteTeacherPst.setInt(1, teacherId);
		                int affectedRowsTeacher = deleteTeacherPst.executeUpdate();

		                if (affectedRowsTeacher > 0) {
		                    // Deleted from teacher table successfully
		                } else {
		                    // No rows affected, deletion from teacher table failed
		                    JOptionPane.showMessageDialog(null, "Failed to delete teacher from teacher table.");
		                    return;
		                }
		            }

		            // Delete from user table
		            String deleteUserQuery = "DELETE FROM user WHERE id = ?";
		            try (PreparedStatement deleteUserPst = conn.prepareStatement(deleteUserQuery)) {
		                deleteUserPst.setInt(1, teacherId);
		                int affectedRowsUser = deleteUserPst.executeUpdate();

		                if (affectedRowsUser > 0) {
		                    // Deleted from user table successfully
		                    JOptionPane.showMessageDialog(null, "Teacher deleted successfully.");
		                    // Update the table after deletion
		                    populateTeachersTable(conn);
		                } else {
		                    // No rows affected, deletion from user table failed
		                    JOptionPane.showMessageDialog(null, "Failed to delete teacher from user table.");
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btn_deleteTeachers.setBounds(138, 10, 118, 32);
		panel_modifyTeachers.add(btn_deleteTeachers);
		btn_deleteTeachers.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_deleteTeachers.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane_teachers = new JScrollPane();
		scrollPane_teachers.setBounds(20, 75, 1047, 536);
		panel_teachers.add(scrollPane_teachers);
		
		table_teachers = new JTable();
		scrollPane_teachers.setViewportView(table_teachers);
		table_teachers.setBackground(Color.DARK_GRAY);
		table_teachers.setForeground(Color.WHITE);
		table_teachers.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		table_teachers.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Email", "Phone", "Module"
			}
		));
		
		JButton button_refreshTeachers = new JButton("Refresh");
		button_refreshTeachers.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		button_refreshTeachers.setBackground(Color.LIGHT_GRAY);
		button_refreshTeachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateTeachersTable();
			}
		});
		button_refreshTeachers.setBounds(940, 11, 127, 55);
		panel_teachers.add(button_refreshTeachers);
		
		populateTeachersTable();
		
		
		
		JPanel panel_modules = new JPanel();
		panel_modules.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Modules", null, panel_modules, null);
		panel_modules.setLayout(null);
		
		JLabel lbl_Modules = new JLabel("Modules");
		lbl_Modules.setForeground(Color.WHITE);
		lbl_Modules.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lbl_Modules.setBounds(10, 10, 218, 55);
		panel_modules.add(lbl_Modules);
		
		JButton button_refreshModules = new JButton("Refresh");
		button_refreshModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateModulesTable();
			}
		});
		button_refreshModules.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		button_refreshModules.setBackground(Color.LIGHT_GRAY);
		button_refreshModules.setBounds(940, 11, 127, 55);
		panel_modules.add(button_refreshModules);
		
		JPanel panel_modifyModules = new JPanel();
		panel_modifyModules.setLayout(null);
		panel_modifyModules.setBackground(Color.DARK_GRAY);
		panel_modifyModules.setBounds(657, 10, 273, 55);
		panel_modules.add(panel_modifyModules);
		
		
		
		JButton btn_addModules = new JButton("Add");
		btn_addModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddModule add_module_frame = new AddModule();
				add_module_frame.setVisible(true);
			}
		});
		btn_addModules.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_addModules.setBackground(Color.LIGHT_GRAY);
		btn_addModules.setBounds(10, 10, 118, 32);
		panel_modifyModules.add(btn_addModules);
		
		JButton btn_deleteModules = new JButton("Delete");
		btn_deleteModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_modules.getSelectedRow();
		        if (selectedRow != -1) {
		            int retrievedId = (int) table_modules.getValueAt(selectedRow, 0);
		            try (Connection conn = DbConn.dbConn()) {
		                conn.setAutoCommit(false); // Start a transaction

		                // Update teacher table first
		                PreparedStatement updateTeachers = conn.prepareStatement("UPDATE Teacher SET moduleId = NULL WHERE moduleId = ?");
		                updateTeachers.setInt(1, retrievedId);
		                updateTeachers.executeUpdate();

		                // Delete module after updating teachers
		                PreparedStatement deleteModule = conn.prepareStatement("DELETE FROM module WHERE id = ?");
		                deleteModule.setInt(1, retrievedId);
		                int deletedRows = deleteModule.executeUpdate();

		                if (deletedRows == 1) {
		                    conn.commit(); // Commit the transaction if successful
		                    JOptionPane.showMessageDialog(null, "Module deleted successfully!");
		                    populateModulesTable();
		                } else {
		                    conn.rollback(); // Rollback if deletion fails
		                    JOptionPane.showMessageDialog(null, "Error deleting module.");
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Please select a module to delete.");
		        }
			}
		});
		btn_deleteModules.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_deleteModules.setBackground(Color.LIGHT_GRAY);
		btn_deleteModules.setBounds(138, 10, 118, 32);
		panel_modifyModules.add(btn_deleteModules);
		
		JScrollPane scrollPane_modules = new JScrollPane();
		scrollPane_modules.setBounds(20, 75, 1047, 536);
		panel_modules.add(scrollPane_modules);
		
		table_modules = new JTable();
		scrollPane_modules.setViewportView(table_modules);
		table_modules.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Course", "Module"
			}
		));
		table_modules.setForeground(Color.WHITE);
		table_modules.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		table_modules.setBackground(Color.DARK_GRAY);
		
		populateModulesTable();
		
		JPanel panel_courses = new JPanel();
		panel_courses.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Courses", null, panel_courses, null);
		panel_courses.setLayout(null);
		
		JLabel lbl_Courses = new JLabel("Courses");
		lbl_Courses.setForeground(Color.WHITE);
		lbl_Courses.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lbl_Courses.setBounds(10, 10, 218, 55);
		panel_courses.add(lbl_Courses);
		
		JPanel panel_modifyCourses = new JPanel();
		panel_modifyCourses.setLayout(null);
		panel_modifyCourses.setBackground(Color.DARK_GRAY);
		panel_modifyCourses.setBounds(657, 10, 273, 55);
		panel_courses.add(panel_modifyCourses);
		
		
		
		JButton btn_addCourses = new JButton("Add");
		btn_addCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCourse add_course_frame = new AddCourse();
				add_course_frame.setVisible(true);
			}
		});
		btn_addCourses.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_addCourses.setBackground(Color.LIGHT_GRAY);
		btn_addCourses.setBounds(10, 10, 118, 32);
		panel_modifyCourses.add(btn_addCourses);
		
		JButton btn_deleteCourses = new JButton("Delete");
		btn_deleteCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DbConn.dbConn()) {
		            int selectedRow = table_Courses.getSelectedRow();
		            if (selectedRow != -1) {
		                int courseId = (int) table_Courses.getValueAt(selectedRow, 0); // Get courseId from selected row

		                // Set courseId to NULL in referencing tables
		                PreparedStatement updateStudent = conn.prepareStatement("UPDATE student SET courseId = NULL WHERE courseId = ?");
		                PreparedStatement updateModule = conn.prepareStatement("UPDATE module SET courseId = NULL WHERE courseId = ?");
		                updateStudent.setInt(1, courseId);
		                updateModule.setInt(1, courseId);
		                updateStudent.executeUpdate();
		                updateModule.executeUpdate();

		                // Delete the course
		                PreparedStatement deleteCourse = conn.prepareStatement("DELETE FROM course WHERE id = ?");
		                deleteCourse.setInt(1, courseId);
		                int affectedRows = deleteCourse.executeUpdate();

		                if (affectedRows == 1) {
		                    JOptionPane.showMessageDialog(null, "Course deleted successfully!");
		                    populateCoursesTable(); // Refresh the table
		                } else {
		                    JOptionPane.showMessageDialog(null, "Error deleting course.");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Please select a course to delete.");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
		        }
			}
		});
		btn_deleteCourses.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_deleteCourses.setBackground(Color.LIGHT_GRAY);
		btn_deleteCourses.setBounds(138, 10, 118, 32);
		panel_modifyCourses.add(btn_deleteCourses);
		
		JButton button_refreshCourses = new JButton("Refresh");
		button_refreshCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateCoursesTable();
			}
		});
		button_refreshCourses.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		button_refreshCourses.setBackground(Color.LIGHT_GRAY);
		button_refreshCourses.setBounds(940, 10, 127, 55);
		panel_courses.add(button_refreshCourses);
		
		JScrollPane scrollPane_Courses = new JScrollPane();
		scrollPane_Courses.setBounds(20, 75, 1047, 536);
		panel_courses.add(scrollPane_Courses);
		
		table_Courses = new JTable();
		scrollPane_Courses.setViewportView(table_Courses);
		table_Courses.setForeground(Color.WHITE);
		table_Courses.setBackground(Color.DARK_GRAY);
		table_Courses.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		table_Courses.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Seats", "Batch", "Years"
			}
		));
		
		populateCoursesTable();
		
		JPanel panel_students = new JPanel();
		panel_students.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Students", null, panel_students, null);
		panel_students.setLayout(null);
		
		
		
		JLabel lbl_Students = new JLabel("Students");
		lbl_Students.setForeground(Color.WHITE);
		lbl_Students.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lbl_Students.setBounds(10, 10, 218, 55);
		panel_students.add(lbl_Students);
		
		JButton button_gradesModify = new JButton("Grade");
		button_gradesModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_Students.getSelectedRow();
		        if (selectedRow != -1) {
		        	int studentId = (int) table_Students.getValueAt(selectedRow, 0);
		        	ModifyGrade modifyGrade = new ModifyGrade(studentId);

		            modifyGrade.setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(null, "Please select a student.");
		        }
			}
		});
		button_gradesModify.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		button_gradesModify.setBackground(Color.LIGHT_GRAY);
		button_gradesModify.setBounds(812, 22, 127, 32);
		panel_students.add(button_gradesModify);
		
		JButton btn_deleteStudents = new JButton("Delete");
		btn_deleteStudents.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = DbConn.dbConn()) {
		            int selectedRow = table_Students.getSelectedRow();
		            if (selectedRow != -1) {
		                int studentId = (int) table_Students.getValueAt(selectedRow, 0); // Get studentId from selected row

		                // Check for foreign key constraints and handle gracefully
		                String[] foreignKeyTables = {"grade"}; // Adjust this list based on your database structure
		                for (String table : foreignKeyTables) {
		                    String checkFKQuery = "SELECT COUNT(*) FROM " + table + " WHERE studentId = ?";
		                    PreparedStatement checkFKStmt = conn.prepareStatement(checkFKQuery);
		                    checkFKStmt.setInt(1, studentId);
		                    ResultSet checkFKResult = checkFKStmt.executeQuery();

		                    if (checkFKResult.next() && checkFKResult.getInt(1) > 0) {
		                        JOptionPane.showMessageDialog(null, "Cannot delete student: Linked entries exist in " + table + ".");
		                        return; // Prevent further deletion attempts
		                    }
		                }

		                // Delete entries from related tables, considering cascading or soft deletes
		                String[] relatedTables = {"grade"}; // Adjust this list based on your requirements
		                for (String table : relatedTables) {
		                    String deleteRelatedQuery = "DELETE FROM " + table + " WHERE studentId = ?";
		                    PreparedStatement deleteRelatedStmt = conn.prepareStatement(deleteRelatedQuery);
		                    deleteRelatedStmt.setInt(1, studentId);
		                    deleteRelatedStmt.executeUpdate();
		                }

		                // Delete the student from the user table
		                PreparedStatement deleteUser = conn.prepareStatement("DELETE FROM user WHERE id = ?");
		                deleteUser.setInt(1, studentId);
		                int affectedRowsUser = deleteUser.executeUpdate();

		                // Delete the student from the student table
		                PreparedStatement deleteStudent = conn.prepareStatement("DELETE FROM student WHERE id = ?");
		                deleteStudent.setInt(1, studentId);
		                int affectedRowsStudent = deleteStudent.executeUpdate();

		                if (affectedRowsUser + affectedRowsStudent >= 1) {
		                    JOptionPane.showMessageDialog(null, "Student deleted successfully!");
		                    populateStudentsTable(); // Refresh the table
		                } else {
		                    JOptionPane.showMessageDialog(null, "Error deleting student.");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Please select a student to delete.");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
		        }
		    }
		});

		btn_deleteStudents.setBounds(949, 23, 118, 32);
		panel_students.add(btn_deleteStudents);
		btn_deleteStudents.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_deleteStudents.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane_Students = new JScrollPane();
		scrollPane_Students.setBounds(20, 75, 1047, 536);
		panel_students.add(scrollPane_Students);
		
		table_Students = new JTable();
		scrollPane_Students.setViewportView(table_Students);
		table_Students.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Course", "Email", "Phone"
			}
		));
		table_Students.setForeground(Color.WHITE);
		table_Students.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		table_Students.setBackground(Color.DARK_GRAY);
		
		populateStudentsTable();
		

		
		JButton btn_courses = new JButton(courseCount + " Courses");
		btn_courses.setBounds(20, 286, 197, 56);
		contentPane.add(btn_courses);
		btn_courses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btn_courses.setForeground(Color.BLACK);
		btn_courses.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_courses.setBackground(Color.LIGHT_GRAY);
		
		
		
		JButton btn_students = new JButton(studentCount + " Students");
		btn_students.setBounds(20, 352, 197, 56);
		contentPane.add(btn_students);
		btn_students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btn_students.setForeground(Color.BLACK);
		btn_students.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_students.setBackground(Color.LIGHT_GRAY);
		
		JButton btn_modules = new JButton(moduleCount + " Modules");
		btn_modules.setBounds(20, 220, 197, 56);
		contentPane.add(btn_modules);
		btn_modules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btn_modules.setForeground(Color.BLACK);
		btn_modules.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_modules.setBackground(Color.LIGHT_GRAY);
		
		JButton btn_teachers = new JButton(teacherCount + " Teachers");
		btn_teachers.setBounds(20, 154, 197, 56);
		contentPane.add(btn_teachers);
		btn_teachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btn_teachers.setForeground(Color.BLACK);
		btn_teachers.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_teachers.setBackground(Color.LIGHT_GRAY);
		
		JLabel lbl_userName = new JLabel("Hello, " + userName);
		lbl_userName.setForeground(Color.WHITE);
		lbl_userName.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lbl_userName.setBounds(587, 10, 534, 56);
		contentPane.add(lbl_userName);
		
		JButton btn_info = new JButton("");
		btn_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutMe aboutMeDialog = new AboutMe();
		        aboutMeDialog.setVisible(true);
			}
		});
		btn_info.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\about.png"));
		btn_info.setBackground(Color.LIGHT_GRAY);
		btn_info.setBounds(1214, 10, 44, 44);
		contentPane.add(btn_info);
		
		
		if (!"Admin".equals(userMode)) {
        	panel_modifyNoti.setVisible(false);
        	panel_modifyTeachers.setVisible(false);
        	panel_modifyModules.setVisible(false);
        	panel_modifyCourses.setVisible(false);
        }
		if("Student".equals(userMode)) {
        	panel_students.setVisible(false);
        	btn_students.setVisible(false);
        }
		if(!"Student".equals(userMode)) {
        	btn_Grades.setVisible(false);
        }
	
		
	}
	private void updateTable(Connection conn) {
	    String searchText = textField_notificationSearch.getText().trim();

	    try (PreparedStatement searchPst = conn.prepareStatement("SELECT id, notification, timeStamp FROM notification WHERE notification LIKE ?")) {
	        searchPst.setString(1, "%" + searchText + "%");

	        try (ResultSet searchRs = searchPst.executeQuery()) {
	            DefaultTableModel model = (DefaultTableModel) table_notification.getModel();
	            model.setRowCount(0); // Clear existing rows

	            while (searchRs.next()) {
	                int id = searchRs.getInt("id");
	                String notificationText = searchRs.getString("notification");
	                java.sql.Timestamp timeStamp = searchRs.getTimestamp("timeStamp");

	                // Format the timestamp if needed
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                String formattedTimeStamp = dateFormat.format(timeStamp);

	                model.addRow(new Object[]{id, notificationText, formattedTimeStamp});
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	private void populateTeachersTable() {
		try (Connection conn = DbConn.dbConn()){
	        String query = "SELECT t.teacherId, u.name, u.email, u.phone, m.classCode " +
	                        "FROM teacher t JOIN user u ON t.teacherId = u.id " +
	                        "LEFT JOIN module m ON t.moduleId = m.id;"; // Use LEFT JOIN to include teachers with NULL moduleId

	        try (PreparedStatement pst = conn.prepareStatement(query);
	             ResultSet rs = pst.executeQuery()) {

	            DefaultTableModel model = (DefaultTableModel) table_teachers.getModel();
	            model.setRowCount(0); // Clear existing rows

	            while (rs.next()) {
	                int id = rs.getInt("teacherId");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String phone = rs.getString("phone");
	                String module = rs.getString("classCode");

	                model.addRow(new Object[]{id, name, email, phone, module});
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	private void populateTeachersTable(Connection conn) {
		try {
	        String query = "SELECT t.teacherId, u.name, u.email, u.phone, m.classCode " +
	                        "FROM teacher t JOIN user u ON t.teacherId = u.id " +
	                        "LEFT JOIN module m ON t.moduleId = m.id;"; // Use LEFT JOIN to include teachers with NULL moduleId

	        try (PreparedStatement pst = conn.prepareStatement(query);
	             ResultSet rs = pst.executeQuery()) {

	            DefaultTableModel model = (DefaultTableModel) table_teachers.getModel();
	            model.setRowCount(0); // Clear existing rows

	            while (rs.next()) {
	                int id = rs.getInt("teacherId");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String phone = rs.getString("phone");
	                String module = rs.getString("classCode");

	                model.addRow(new Object[]{id, name, email, phone, module});
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	private void populateModulesTable() {
	    try (Connection conn = DbConn.dbConn()) {
	        String query = "SELECT m.id, c.name AS course, m.classCode AS module FROM module m JOIN course c ON m.courseId = c.id";
	        try (PreparedStatement pst = conn.prepareStatement(query);
	             ResultSet rs = pst.executeQuery()) {

	            DefaultTableModel model = (DefaultTableModel) table_modules.getModel();
	            model.setRowCount(0); // Clear existing rows

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String course = rs.getString("course");
	                String module = rs.getString("module");

	                model.addRow(new Object[]{id, course, module});
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	private void populateCoursesTable() {
	    try (Connection conn = DbConn.dbConn()) {
	        String query = "SELECT * FROM course;";
	        try (PreparedStatement pst = conn.prepareStatement(query);
	             ResultSet rs = pst.executeQuery()) {

	            DefaultTableModel model = (DefaultTableModel) table_Courses.getModel();
	            model.setRowCount(0); // Clear existing rows

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                int seats = rs.getInt("seats");
	                String batch = rs.getString("batch");
	                int years = rs.getInt("years");

	                model.addRow(new Object[]{id, name, seats, batch, years});
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	private void populateStudentsTable() {
	    try (Connection conn = DbConn.dbConn()) {
	    	String query = "SELECT s.id, u.name, c.name AS course, u.email, u.phone " +
                    "FROM student s JOIN user u ON s.userId = u.id " +
                    "LEFT JOIN course c ON s.courseId = c.id;"; // Use LEFT JOIN to include students with NULL courseId
			    try (PreparedStatement pst = conn.prepareStatement(query);
			         ResultSet rs = pst.executeQuery()) {
			
			        DefaultTableModel model = (DefaultTableModel) table_Students.getModel();
			        model.setRowCount(0); // Clear existing rows
			
			        while (rs.next()) {
			            int id = rs.getInt("id");
			            String name = rs.getString("name");
			            String course = rs.getString("course"); // Will be NULL if no matching course found
			            String email = rs.getString("email");
			            String phone = rs.getString("phone");
			
			            model.addRow(new Object[]{id, name, course, email, phone});
			        }
			    }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
}
