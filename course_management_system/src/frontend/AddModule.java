package frontend;

import backend.DbConn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddModule extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_module;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddModule frame = new AddModule();
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
	public AddModule() {
		Connection conn = DbConn.dbConn();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 649, 670);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_module = new JLabel("Module");
		lbl_module.setForeground(Color.WHITE);
		lbl_module.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_module.setBounds(86, 146, 164, 24);
		contentPane.add(lbl_module);
		
		textField_module = new JTextField();
		textField_module.setForeground(Color.WHITE);
		textField_module.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField_module.setColumns(10);
		textField_module.setBackground(Color.GRAY);
		textField_module.setBounds(86, 171, 458, 51);
		contentPane.add(textField_module);
		
		JComboBox<String> comboBox_course = new JComboBox<String>();
		comboBox_course.setForeground(Color.WHITE);
		comboBox_course.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		comboBox_course.setBackground(Color.GRAY);
		comboBox_course.setBounds(86, 320, 458, 51);
		contentPane.add(comboBox_course);
		
		JLabel lbl_course = new JLabel("Course");
		lbl_course.setForeground(Color.WHITE);
		lbl_course.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_course.setBounds(86, 295, 164, 28);
		contentPane.add(lbl_course);
		
		try {
            String query = "SELECT name FROM course;";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String moduleName = rs.getString("name");
                comboBox_course.addItem(moduleName);
            }
        } catch (Exception exc_comboBox_module) {
        	exc_comboBox_module.printStackTrace();
        }
		
		JButton btn_addModule = new JButton("Add");
		btn_addModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DbConn.dbConn()) {
		            String classCode = textField_module.getText();
		            String courseName = (String) comboBox_course.getSelectedItem();

		            // Find courseId based on selected course name
		            int courseId = 0;
		            try (PreparedStatement pst = conn.prepareStatement("SELECT id FROM course WHERE name = ?")) {
		                pst.setString(1, courseName);
		                try (ResultSet rs = pst.executeQuery()) {
		                    if (rs.next()) {
		                        courseId = rs.getInt("id");
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Course not found.");
		                        return;
		                    }
		                }
		            }

		            // Insert new module
		            PreparedStatement insertModule = conn.prepareStatement("INSERT INTO module (classCode, courseId) VALUES (?, ?)");
		            insertModule.setString(1, classCode);
		            insertModule.setInt(2, courseId);
		            int affectedRows = insertModule.executeUpdate();

		            if (affectedRows == 1) {
		                JOptionPane.showMessageDialog(null, "Module added successfully!");
		                textField_module.setText(""); // Clear the text field
		                comboBox_course.setSelectedIndex(0); // Reset the combobox
		                AddModule.this.dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error adding module.");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
		        }
		    }
		});
		btn_addModule.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		btn_addModule.setBackground(Color.LIGHT_GRAY);
		btn_addModule.setBounds(192, 475, 221, 64);
		contentPane.add(btn_addModule);
	}
}
