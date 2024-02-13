package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import backend.DbConn;

import javax.swing.JScrollPane;

public class Grades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grades frame = new Grades(0);
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
	public Grades(int studentId) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(0, 0, 1333, 777);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_noti = new JLabel("Grades");
		lbl_noti.setForeground(Color.WHITE);
		lbl_noti.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lbl_noti.setBounds(10, 10, 218, 55);
		contentPane.add(lbl_noti);
		
		JLabel lbl_noti_1 = new JLabel("Name: ");
		lbl_noti_1.setForeground(Color.WHITE);
		lbl_noti_1.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lbl_noti_1.setBounds(765, 10, 544, 55);
		contentPane.add(lbl_noti_1);
		
		try (Connection conn = DbConn.dbConn()) {
		    // Fetch userId based on studentId from the student table
		    String userIdQuery = "SELECT userId FROM student WHERE id = ?";
		    PreparedStatement userIdStmt = conn.prepareStatement(userIdQuery);
		    userIdStmt.setInt(1, studentId);
		    ResultSet userIdResult = userIdStmt.executeQuery();

		    if (userIdResult.next()) {
		        int userId = userIdResult.getInt("userId");

		        // Now fetch student name based on userId from the user table
		        String nameQuery = "SELECT name FROM user WHERE id = ?";
		        PreparedStatement nameStmt = conn.prepareStatement(nameQuery);
		        nameStmt.setInt(1, userId);
		        ResultSet nameResult = nameStmt.executeQuery();

		        if (nameResult.next()) {
		            String studentName = nameResult.getString("name");
		            lbl_noti_1.setText("Name: " + studentName);
		        } else {
		            lbl_noti_1.setText("Name: [Not Found]");
		        }
		    } else {
		        lbl_noti_1.setText("Name: [Not Found]");
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Error fetching student name: " + ex.getMessage());
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 75, 1289, 655);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Module", "Percentage"
			}
		));
		table.setForeground(Color.WHITE);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		table.setBackground(Color.DARK_GRAY);
		
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0); // Clear existing rows

		try (Connection conn = DbConn.dbConn()) {
		    String query = "SELECT m.classCode AS moduleName, g.percentage " +
		                   "FROM module m " +
		                   "JOIN grade g ON m.id = g.moduleId " +
		                   "WHERE g.studentId = ?";
		    PreparedStatement stmt = conn.prepareStatement(query);
		    stmt.setInt(1, studentId);

		    ResultSet resultSet = stmt.executeQuery();

		    while (resultSet.next()) {
		        String moduleName = resultSet.getString("moduleName");
		        BigDecimal percentage = resultSet.getBigDecimal("percentage");

		        // Add the data to the DefaultTableModel
		        tableModel.addRow(new Object[]{moduleName, percentage});
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Error fetching module data: " + ex.getMessage());
		}

	}
}
