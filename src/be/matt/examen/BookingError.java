package be.matt.examen;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BookingError extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingError frame = new BookingError();
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
	public BookingError() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 686, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblError = new JLabel("Something went wrong. Make sure the course/lesson still has places. Use refresh to check");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(10, 10, 652, 13);
		contentPane.add(lblError);
		
		JLabel lblPrecision = new JLabel("The same user cannot take two place for the same week. You may also check it is for your age.");
		lblPrecision.setForeground(new Color(255, 0, 0));
		lblPrecision.setBounds(10, 33, 652, 13);
		contentPane.add(lblPrecision);
	}

}
