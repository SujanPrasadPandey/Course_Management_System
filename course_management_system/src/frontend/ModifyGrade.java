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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class ModifyGrade extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_grade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyGrade frame = new ModifyGrade(0);
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
	public ModifyGrade(int studentId) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 540, 564);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		int courseId = fetchCourseId(studentId);
		
		JLabel lbl_module = new JLabel("Module");
		lbl_module.setForeground(Color.WHITE);
		lbl_module.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_module.setBounds(33, 116, 164, 28);
		contentPane.add(lbl_module);
		
		JComboBox<String> comboBox_module = new JComboBox<String>();
		comboBox_module.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        String selectedModule = (String) comboBox_module.getSelectedItem();
		        try (Connection conn = DbConn.dbConn()) {
		            // Fetching moduleId based on the selected module name and courseId
		            String moduleIdQuery = "SELECT id FROM module WHERE courseID = ? AND classCode = ?";
		            PreparedStatement moduleIdStmt = conn.prepareStatement(moduleIdQuery);
		            moduleIdStmt.setInt(1, courseId);
		            moduleIdStmt.setString(2, selectedModule);
		            ResultSet moduleIdResult = moduleIdStmt.executeQuery();

		            if (moduleIdResult.next()) {
		                int moduleId = moduleIdResult.getInt("id");

		                // Now fetching percentage based on the studentId and moduleId
		                String gradeQuery = "SELECT percentage FROM grade WHERE studentId = ? AND moduleId = ?";
		                PreparedStatement gradeStmt = conn.prepareStatement(gradeQuery);
		                gradeStmt.setInt(1, studentId);
		                gradeStmt.setInt(2, moduleId);
		                ResultSet gradeResult = gradeStmt.executeQuery();

		                if (gradeResult.next()) {
		                    textField_grade.setText(String.valueOf(gradeResult.getBigDecimal("percentage")));
		                } else {
		                    textField_grade.setText("null");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Module not found for the selected course.");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error fetching grade: " + ex.getMessage());
		        }

		    }
		});
		comboBox_module.setForeground(Color.WHITE);
		comboBox_module.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		comboBox_module.setBackground(Color.GRAY);
		comboBox_module.setBounds(33, 141, 458, 51);
		contentPane.add(comboBox_module);
		
		textField_grade = new JTextField();
		textField_grade.setForeground(Color.WHITE);
		textField_grade.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField_grade.setColumns(10);
		textField_grade.setBackground(Color.GRAY);
		textField_grade.setBounds(33, 266, 458, 51);
		contentPane.add(textField_grade);
		
		JLabel lbl_grade = new JLabel("Grade");
		lbl_grade.setForeground(Color.WHITE);
		lbl_grade.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl_grade.setBounds(33, 243, 164, 24);
		contentPane.add(lbl_grade);
		
		JButton btn_updateGrade = new JButton("Update");
		btn_updateGrade.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String selectedModule = (String) comboBox_module.getSelectedItem();

		        // Input validation
		        if (selectedModule == null || selectedModule.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please select a module.");
		            return;
		        }

		        String gradeStr = textField_grade.getText();
		        int newGrade;
		        try {
		            newGrade = Integer.parseInt(gradeStr);
		            if (newGrade < 0 || newGrade > 100) {
		                throw new NumberFormatException("Grade must be between 0 and 100.");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid grade format: " + ex.getMessage());
		            return;
		        }

		     // Database update
		        try (Connection conn = DbConn.dbConn()) {
		            // Fetching moduleId based on the selected module name and courseId
		            String moduleIdQuery = "SELECT id FROM module WHERE courseID = ? AND classCode = ?";
		            PreparedStatement moduleIdStmt = conn.prepareStatement(moduleIdQuery);
		            moduleIdStmt.setInt(1, courseId);
		            moduleIdStmt.setString(2, selectedModule);
		            ResultSet moduleIdResult = moduleIdStmt.executeQuery();

		            if (moduleIdResult.next()) {
		                int moduleId = moduleIdResult.getInt("id");

		                // Now updating the grade based on the studentId and moduleId
		                String updateQuery = "UPDATE grade SET percentage = ? WHERE studentId = ? AND moduleId = ?";
		                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
		                updateStmt.setBigDecimal(1, new BigDecimal(newGrade)); // Assuming newGrade is a String or any numeric type
		                updateStmt.setInt(2, studentId);
		                updateStmt.setInt(3, moduleId);
		                updateStmt.executeUpdate();

		                JOptionPane.showMessageDialog(null, "Grade updated successfully!");
		                
		                ModifyGrade.this.dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Module not found for the selected course.");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error updating grade: " + ex.getMessage());
		        }

		    }
		});

		btn_updateGrade.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		btn_updateGrade.setBackground(Color.LIGHT_GRAY);
		btn_updateGrade.setBounds(140, 381, 221, 64);
		contentPane.add(btn_updateGrade);
		
		JLabel lbl_studentName = new JLabel("Name: ");
		lbl_studentName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lbl_studentName.setForeground(Color.WHITE);
		lbl_studentName.setBounds(66, 29, 392, 64);
		contentPane.add(lbl_studentName);
		
		try (Connection conn = DbConn.dbConn()) { // Replace DbConn with your database connection class
		    String nameQuery = "SELECT name FROM user WHERE id = ?";
		    PreparedStatement nameStmt = conn.prepareStatement(nameQuery);
		    nameStmt.setInt(1, studentId);
		    ResultSet nameResult = nameStmt.executeQuery();
		    if (nameResult.next()) {
		        lbl_studentName.setText("Name: " + nameResult.getString("name"));
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Error fetching student name: " + ex.getMessage());
		}

		
		
		try (Connection conn = DbConn.dbConn()) {
			String moduleQuery = "SELECT classCode FROM module WHERE courseId = ?";
		    PreparedStatement moduleStmt = conn.prepareStatement(moduleQuery);
		    moduleStmt.setInt(1, courseId);
		    ResultSet moduleResult = moduleStmt.executeQuery();
		    List<String> moduleNames = new ArrayList<>();
		    while (moduleResult.next()) {
		        moduleNames.add(moduleResult.getString("classCode"));
		    }
		    comboBox_module.setModel(new DefaultComboBoxModel<>(moduleNames.toArray(new String[0])));
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Error fetching modules: " + ex.getMessage());
		}

	}
	
	private static int fetchCourseId(int studentId) {
	    try (Connection conn = DbConn.dbConn()) {
	        String courseIdQuery = "SELECT courseId FROM student WHERE id = ?";
	        PreparedStatement courseIdStmt = conn.prepareStatement(courseIdQuery);
	        courseIdStmt.setInt(1, studentId);
	        ResultSet courseIdResult = courseIdStmt.executeQuery();
	        if (courseIdResult.next()) {
	            return courseIdResult.getInt("courseId");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error fetching courseId: " + ex.getMessage());
	    }
	    return -1; // or handle it according to your application's logic
	}

}
