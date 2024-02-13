package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class AboutMe extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AboutMe() {
		setBounds(700, 300, 630, 415);
		setModalityType(ModalityType.APPLICATION_MODAL); // Set modality type to APPLICATION_MODAL
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); // Set the default close operation to HIDE_ON_CLOSE
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(375, 10, 231, 358);
        contentPanel.add(panel);
        panel.setLayout(null);
        
        JLabel lbl_Help = new JLabel("Help and Guidelines");
        lbl_Help.setForeground(Color.WHITE);
        lbl_Help.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lbl_Help.setBounds(10, 10, 211, 55);
        panel.add(lbl_Help);
        
        JButton btn_tutorial = new JButton("Knowledge Tree: Tutorial");
        btn_tutorial.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    Desktop.getDesktop().browse(new URI("https://github.com/SujanPrasadPandey"));
                } catch (Exception e_githubOpen) {
                	e_githubOpen.printStackTrace();
                }
        	}
        });
        btn_tutorial.setBackground(Color.LIGHT_GRAY);
        btn_tutorial.setForeground(Color.BLACK);
        btn_tutorial.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn_tutorial.setBounds(10, 85, 211, 55);
        panel.add(btn_tutorial);
        
        JButton btn_Issues = new JButton("Report Issues");
        btn_Issues.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String recipientEmail = "88sujanpandey@gmail.com";

        	    try {
        	        URI uri = new URI("mailto:" + recipientEmail);
        	        Desktop.getDesktop().mail(uri);
        	    } catch (Exception e_mailToCreator) {
        	        e_mailToCreator.printStackTrace();
        	    }
        	}
        });
        btn_Issues.setBackground(Color.LIGHT_GRAY);
        btn_Issues.setForeground(Color.BLACK);
        btn_Issues.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn_Issues.setBounds(10, 167, 211, 55);
        panel.add(btn_Issues);
        
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(127, 310, 94, 38);
        panel.add(closeButton);
        closeButton.setForeground(Color.BLACK);
        closeButton.setBackground(Color.LIGHT_GRAY);
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide the dialog instead of disposing it
            }
        });
        
        JLabel lbl_creator = new JLabel("Knowledge Tree - Creator");
        lbl_creator.setForeground(Color.WHITE);
        lbl_creator.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        lbl_creator.setBounds(10, 10, 349, 55);
        contentPanel.add(lbl_creator);
        
        JLabel lbl_name = new JLabel("Sujan Prasad Pandey");
        lbl_name.setForeground(Color.WHITE);
        lbl_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lbl_name.setBounds(10, 75, 199, 38);
        contentPanel.add(lbl_name);
        
        JLabel lbl_uni = new JLabel("Wolverhampton University");
        lbl_uni.setForeground(Color.WHITE);
        lbl_uni.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbl_uni.setBounds(10, 147, 190, 21);
        contentPanel.add(lbl_uni);
        
        JLabel lbl_thisMakeYear = new JLabel("2nd Year, 3rd Sem");
        lbl_thisMakeYear.setForeground(Color.WHITE);
        lbl_thisMakeYear.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbl_thisMakeYear.setBounds(10, 167, 190, 21);
        contentPanel.add(lbl_thisMakeYear);
        
        JButton btn_meroGithub = new JButton("");
        btn_meroGithub.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\github.png"));
        btn_meroGithub.setBackground(Color.LIGHT_GRAY);
        btn_meroGithub.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    Desktop.getDesktop().browse(new URI("https://github.com/SujanPrasadPandey"));
                } catch (Exception e_githubOpen) {
                	e_githubOpen.printStackTrace();
                }
        	}
        });
        btn_meroGithub.setBounds(10, 298, 70, 70);
        contentPanel.add(btn_meroGithub);
        
        JLabel lbl_meroPhoto = new JLabel("");
        lbl_meroPhoto.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\sujan.jpg"));
        lbl_meroPhoto.setBounds(219, 70, 140, 140);
        contentPanel.add(lbl_meroPhoto);
        
        JButton btn_meroGmail = new JButton("");
        btn_meroGmail.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String recipientEmail = "88sujanpandey@gmail.com";

        	    try {
        	        URI uri = new URI("mailto:" + recipientEmail);
        	        Desktop.getDesktop().mail(uri);
        	    } catch (Exception e_mailToCreator) {
        	        e_mailToCreator.printStackTrace();
        	    }
        	}
        });
        btn_meroGmail.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\gmail.png"));
        btn_meroGmail.setBackground(Color.LIGHT_GRAY);
        btn_meroGmail.setBounds(105, 298, 70, 70);
        contentPanel.add(btn_meroGmail);
        
        JButton btn_meroLinkedIn = new JButton("");
        btn_meroLinkedIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/sujan-pandey-8a8665253/"));
                } catch (Exception e_linkedInOpen) {
                	e_linkedInOpen.printStackTrace();
                }
        	}
        });
        btn_meroLinkedIn.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\linkedIn.png"));
        btn_meroLinkedIn.setBackground(Color.LIGHT_GRAY);
        btn_meroLinkedIn.setBounds(203, 298, 70, 70);
        contentPanel.add(btn_meroLinkedIn);
        
        JButton btn_meroInsta = new JButton("");
        btn_meroInsta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    Desktop.getDesktop().browse(new URI("https://www.instagram.com/88sujanpandey/?hl=en"));
                } catch (Exception e_instagramOpen) {
                	e_instagramOpen.printStackTrace();
                }
        	}
        });
        btn_meroInsta.setIcon(new ImageIcon("C:\\Users\\88suj\\eclipse-workspace\\course_management_system\\img\\instagram.png"));
        btn_meroInsta.setBackground(Color.LIGHT_GRAY);
        btn_meroInsta.setBounds(295, 298, 70, 70);
        contentPanel.add(btn_meroInsta);
	}
}
