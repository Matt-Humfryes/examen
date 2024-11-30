package be.matt.examen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 92);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnSignIn = new JButton("Sign in as a skier");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SkierSignIn signInForm = new SkierSignIn();
					signInForm.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(btnSignIn);
		
		JButton btnNewInstructor = new JButton("Sign in as an instructor");
		btnNewInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InstructorFormPage instructorFormPage = new InstructorFormPage();
					instructorFormPage.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewInstructor);
		
		JButton btnLogIn = new JButton("Log in");
		contentPane.add(btnLogIn);
	}

}
