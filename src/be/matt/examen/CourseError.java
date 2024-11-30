package be.matt.examen;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CourseError extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CourseError() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblError = new JLabel("The course/lesson couldn't be created. Make sure every inputs are filled.");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(10, 10, 467, 13);
		contentPane.add(lblError);
		
		JLabel lblPrecision = new JLabel("The same instructor cannot create two course/lesson of the same sport for the same age.");
		lblPrecision.setForeground(new Color(255, 0, 0));
		lblPrecision.setBounds(10, 33, 467, 13);
		contentPane.add(lblPrecision);
	}

}
