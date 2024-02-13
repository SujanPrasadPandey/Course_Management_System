package frontend;

import backend.DbConn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTeacher extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_email;
	private JTextField textField_phone;
	private JTextField txtDefaultpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTeacher frame = new AddTeacher();
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
	public AddTeacher() {
		Connection conn = DbConn.dbConn();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 649, 670);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_name = new JTextField();
		textField_name.setForeground(Color.WHITE);
		textField_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField_name.setColumns(10);
		textField_name.setBackground(Color.GRAY);
		textField_name.setBounds(88, 35, 458, 51);
		contentPane.add(textField_name);
		
		JLabel lbl_name = new JLabel("Name");
		lbl_name.setForeground(Color.WHITE);
		lbl_name.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_name.setBounds(88, 10, 164, 24);
		contentPane.add(lbl_name);
		
		textField_email = new JTextField();
		textField_email.setForeground(Color.WHITE);
		textField_email.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField_email.setColumns(10);
		textField_email.setBackground(Color.GRAY);
		textField_email.setBounds(88, 131, 458, 51);
		contentPane.add(textField_email);
		
		JLabel lbl_email = new JLabel("Email");
		lbl_email.setForeground(Color.WHITE);
		lbl_email.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_email.setBounds(88, 106, 164, 24);
		contentPane.add(lbl_email);
		
		textField_phone = new JTextField();
		textField_phone.setForeground(Color.WHITE);
		textField_phone.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField_phone.setColumns(10);
		textField_phone.setBackground(Color.GRAY);
		textField_phone.setBounds(88, 220, 458, 51);
		contentPane.add(textField_phone);
		
		JLabel lbl_phone = new JLabel("Phone");
		lbl_phone.setForeground(Color.WHITE);
		lbl_phone.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_phone.setBounds(88, 197, 164, 24);
		contentPane.add(lbl_phone);
		
		JComboBox<String> comboBox_module = new JComboBox<String>();
		comboBox_module.setForeground(Color.WHITE);
		comboBox_module.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		comboBox_module.setBackground(Color.GRAY);
		comboBox_module.setBounds(88, 316, 458, 51);
		contentPane.add(comboBox_module);
		
		JLabel lbl_module = new JLabel("Module");
		lbl_module.setForeground(Color.WHITE);
		lbl_module.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_module.setBounds(88, 291, 164, 28);
		contentPane.add(lbl_module);
		
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
		
		txtDefaultpassword = new JTextField();
		txtDefaultpassword.setForeground(Color.LIGHT_GRAY);
		txtDefaultpassword.setBackground(Color.BLACK);
		txtDefaultpassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtDefaultpassword.setEditable(false);
		txtDefaultpassword.setText("DefaultPassword");
		txtDefaultpassword.setBounds(88, 416, 458, 51);
		contentPane.add(txtDefaultpassword);
		txtDefaultpassword.setColumns(10);
		
		JLabel lbl_password = new JLabel("Password");
		lbl_password.setForeground(Color.WHITE);
		lbl_password.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_password.setBounds(88, 389, 164, 28);
		contentPane.add(lbl_password);
		
		JButton btn_addTeacher = new JButton("Add");
		btn_addTeacher.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Retrieve values from text fields and combo box
		        String name = textField_name.getText();
		        String email = textField_email.getText();
		        String phone = textField_phone.getText();
		        String moduleName = (String) comboBox_module.getSelectedItem();

		        try (Connection conn = DbConn.dbConn()) {
		            // Step 1: Insert into the user table
		            String insertUserQuery = "INSERT INTO user (name, email, phone, password, mode, profilePic) VALUES (?, ?, ?, 'DefaultPassword', 'Teacher', null);";

		            try (PreparedStatement insertUserPst = conn.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
		                insertUserPst.setString(1, name);
		                insertUserPst.setString(2, email);
		                insertUserPst.setString(3, phone);

		                int affectedRowsUser = insertUserPst.executeUpdate();

		                if (affectedRowsUser > 0) {
		                    // Step 2: Retrieve the id of the inserted user
		                    ResultSet generatedKeys = insertUserPst.getGeneratedKeys();

		                    if (generatedKeys.next()) {
		                        int userId = generatedKeys.getInt(1);

		                        // Step 3: Insert into the teacher table
		                        String insertTeacherQuery = "INSERT INTO teacher (teacherId, moduleId) VALUES (?, (SELECT id FROM module WHERE classCode = ?));";

		                        try (PreparedStatement insertTeacherPst = conn.prepareStatement(insertTeacherQuery)) {
		                            insertTeacherPst.setInt(1, userId);
		                            insertTeacherPst.setString(2, moduleName);

		                            int affectedRowsTeacher = insertTeacherPst.executeUpdate();

		                            if (affectedRowsTeacher > 0) {
		                                // Teacher added successfully
		                                JOptionPane.showMessageDialog(null, "Teacher added successfully.");
		                                AddTeacher.this.dispose();
		                            } else {
		                                // No rows affected, addition to teacher table failed
		                                JOptionPane.showMessageDialog(null, "Failed to add teacher to teacher table.");
		                            }
		                        }
		                    }
		                } else {
		                    // No rows affected, addition to user table failed
		                    JOptionPane.showMessageDialog(null, "Failed to add teacher to user table.");
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});


		btn_addTeacher.setBackground(Color.LIGHT_GRAY);
		btn_addTeacher.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		btn_addTeacher.setBounds(186, 514, 221, 64);
		contentPane.add(btn_addTeacher);
	}
}
