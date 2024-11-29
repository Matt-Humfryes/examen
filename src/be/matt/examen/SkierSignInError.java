package be.matt.examen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;

public class SkierSignInError extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SkierSignInError() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblError = new JLabel("The user couldn't be created. Make sure  that all inputs are filled.");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(10, 10, 416, 13);
		contentPane.add(lblError);
		
		JLabel lblPrecision = new JLabel("Two user cannot have the same username.");
		lblPrecision.setForeground(new Color(255, 0, 0));
		lblPrecision.setBounds(10, 33, 416, 13);
		contentPane.add(lblPrecision);
	}

}
