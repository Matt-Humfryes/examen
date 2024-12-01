package be.matt.examen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class BookingPrice extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BookingPrice(int price) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 101);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBooking = new JLabel("New booking successful.");
		lblBooking.setBounds(10, 10, 416, 13);
		contentPane.add(lblBooking);
		
		JLabel lblPrice = new JLabel("This lesson will cost you " + price + " euro.");
		lblPrice.setBounds(10, 33, 416, 13);
		contentPane.add(lblPrice);
	}
}
