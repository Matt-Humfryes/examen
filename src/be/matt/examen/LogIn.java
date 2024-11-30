package be.matt.examen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.matt.examen.POJO.Person;
import be.matt.examen.POJO.Skier;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 259, 121);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 10, 81, 13);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 33, 81, 13);
		contentPane.add(lblPassword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(101, 7, 96, 19);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(101, 30, 96, 19);
		contentPane.add(passwordFieldPassword);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String username = textFieldUsername.getText();
					char[] passwordChar = passwordFieldPassword.getPassword();
					String password = "";
					
					for(char i : passwordChar)
					{
						password += i;
					}
					
					String personType = Person.checkLog(username, password);
					
					if(personType.equals("none"))
					{
						try {
							LogInError error = new LogInError();
							error.setVisible(true);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
				catch(NullPointerException ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		});
		btnLogIn.setBounds(101, 59, 96, 21);
		contentPane.add(btnLogIn);
	}

}
