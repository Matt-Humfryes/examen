package be.matt.examen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewCourseForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSport;
	private JTextField textFieldLevel;

	/**
	 * Create the frame.
	 */
	public NewCourseForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 259, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSport = new JLabel("Sport's name");
		lblSport.setBounds(10, 10, 84, 13);
		contentPane.add(lblSport);
		
		JLabel lblLevel = new JLabel("Sport's level");
		lblLevel.setBounds(10, 33, 84, 13);
		contentPane.add(lblLevel);
		
		textFieldSport = new JTextField();
		textFieldSport.setBounds(104, 7, 96, 19);
		contentPane.add(textFieldSport);
		textFieldSport.setColumns(10);
		
		textFieldLevel = new JTextField();
		textFieldLevel.setBounds(104, 30, 96, 19);
		contentPane.add(textFieldLevel);
		textFieldLevel.setColumns(10);
		
		JCheckBox chckbxMorning = new JCheckBox("During morning");
		chckbxMorning.setBounds(104, 55, 135, 21);
		contentPane.add(chckbxMorning);
		
		JCheckBox chckbxChildren = new JCheckBox("For children");
		chckbxChildren.setBounds(104, 78, 135, 21);
		contentPane.add(chckbxChildren);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sport = textFieldSport.getText().toLowerCase();
				String level = textFieldLevel.getText().toLowerCase();
				boolean child = chckbxChildren.isSelected();
				boolean time = chckbxMorning.isSelected();
			}
		});
		btnConfirm.setBounds(104, 131, 85, 21);
		contentPane.add(btnConfirm);
	}

}
