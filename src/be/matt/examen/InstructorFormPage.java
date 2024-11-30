package be.matt.examen;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import be.matt.examen.POJO.Instructor;
import be.matt.examen.POJO.Skier;
import javax.swing.JCheckBox;

public class InstructorFormPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldFirstname;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private final JButton btnSignIn = new JButton("Sign in");
	private JTextField textFieldSport;
	private JTextField textFieldLevel;
	private JCheckBox chckbxChild;
	private JCheckBox chckbxTime;

	/**
	 * Create the frame.
	 */
	public InstructorFormPage() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 463, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 13, 86, 13);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(106, 10, 96, 19);
		textFieldName.setColumns(10);
		
		textFieldFirstname = new JTextField();
		textFieldFirstname.setBounds(106, 33, 96, 19);
		textFieldFirstname.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(lblName);
		contentPane.add(textFieldName);
		contentPane.add(textFieldFirstname);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setBounds(10, 36, 86, 13);
		contentPane.add(lblFirstname);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 59, 86, 13);
		contentPane.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(106, 56, 96, 19);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(10, 82, 86, 13);
		contentPane.add(lblAge);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 105, 86, 13);
		contentPane.add(lblPassword);
		
		JSpinner spinnerAge = new JSpinner();
		spinnerAge.setModel(new SpinnerNumberModel(18, 18, 100, 1));
		spinnerAge.setBounds(106, 79, 96, 20);
		contentPane.add(spinnerAge);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(106, 102, 96, 19);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String name = textFieldName.getText();
					String firstname = textFieldFirstname.getText();
					String username = textFieldUsername.getText();
					String password = textFieldPassword.getText();
					int age = (int)spinnerAge.getValue();
					
					String sport = textFieldSport.getText().toLowerCase();
					String level = textFieldLevel.getText().toLowerCase();
					boolean child = chckbxChild.isSelected();
					boolean time = chckbxTime.isSelected();
					
					boolean result = Instructor.newInstructor(name, firstname, username, age, password, sport, level, child, time);
					
					if(!result)
					{
						try {
							InstructorSignInError error = new InstructorSignInError();
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
		btnSignIn.setBounds(308, 158, 96, 19);
		contentPane.add(btnSignIn);
		
		JLabel lblSport = new JLabel("Sport's name");
		lblSport.setBounds(212, 13, 86, 13);
		contentPane.add(lblSport);
		
		JLabel lblLevel = new JLabel("Sport's level");
		lblLevel.setBounds(212, 36, 86, 13);
		contentPane.add(lblLevel);
		
		textFieldSport = new JTextField();
		textFieldSport.setBounds(308, 10, 96, 19);
		contentPane.add(textFieldSport);
		textFieldSport.setColumns(10);
		
		textFieldLevel = new JTextField();
		textFieldLevel.setBounds(308, 33, 96, 19);
		contentPane.add(textFieldLevel);
		textFieldLevel.setColumns(10);
		
		chckbxChild = new JCheckBox("For children");
		chckbxChild.setBounds(308, 78, 135, 21);
		contentPane.add(chckbxChild);
		
		chckbxTime = new JCheckBox("During morning");
		chckbxTime.setBounds(308, 55, 135, 21);
		contentPane.add(chckbxTime);
	}
}
