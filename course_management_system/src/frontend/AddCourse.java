package frontend;

import backend.DbConn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddCourse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_seats;
	private JTextField textField_batch;
	private JTextField textField_years;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse frame = new AddCourse();
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
	public AddCourse() {
		Connection conn = DbConn.dbConn();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(0, 0, 644, 737);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		textField_name.setBackground(Color.GRAY);
		textField_name.setBounds(94, 98, 433, 49);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(94, 71, 145, 24);
		contentPane.add(lblNewLabel);
		
		textField_seats = new JTextField();
		textField_seats.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		textField_seats.setColumns(10);
		textField_seats.setBackground(Color.GRAY);
		textField_seats.setBounds(94, 219, 433, 49);
		contentPane.add(textField_seats);
		
		JLabel lblSeats = new JLabel("Seats");
		lblSeats.setForeground(Color.WHITE);
		lblSeats.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblSeats.setBounds(94, 192, 145, 24);
		contentPane.add(lblSeats);
		
		textField_batch = new JTextField();
		textField_batch.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		textField_batch.setColumns(10);
		textField_batch.setBackground(Color.GRAY);
		textField_batch.setBounds(94, 334, 433, 49);
		contentPane.add(textField_batch);
		
		JLabel lblBatch = new JLabel("Batch");
		lblBatch.setForeground(Color.WHITE);
		lblBatch.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblBatch.setBounds(94, 307, 145, 24);
		contentPane.add(lblBatch);
		
		textField_years = new JTextField();
		textField_years.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		textField_years.setColumns(10);
		textField_years.setBackground(Color.GRAY);
		textField_years.setBounds(94, 455, 433, 49);
		contentPane.add(textField_years);
		
		JLabel lblYears = new JLabel("Years");
		lblYears.setForeground(Color.WHITE);
		lblYears.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblYears.setBounds(94, 428, 145, 24);
		contentPane.add(lblYears);
		
		JButton btn_add = new JButton("Add");
		btn_add.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String addCourseQuery = "INSERT INTO course (name, seats, batch, years) VALUES (?, ?, ?, ?);";
		            PreparedStatement addCoursePst = conn.prepareStatement(addCourseQuery);

		            // Use correct indices for each parameter
		            addCoursePst.setString(1, textField_name.getText());
		            addCoursePst.setInt(2, Integer.parseInt(textField_seats.getText())); // Assuming 'seats' is an integer
		            addCoursePst.setString(3, textField_batch.getText()); // Assuming 'batch' is a string
		            addCoursePst.setInt(4, Integer.parseInt(textField_years.getText())); // Assuming 'years' is an integer

		            // Execute the query
		            addCoursePst.executeUpdate();

		            // Close the PreparedStatement after use
		            addCoursePst.close();
		            
		            AddCourse.this.dispose();

		        } catch (SQLException | NumberFormatException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btn_add.setBackground(Color.LIGHT_GRAY);
		btn_add.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		btn_add.setBounds(193, 556, 219, 72);
		contentPane.add(btn_add);
	}
}
