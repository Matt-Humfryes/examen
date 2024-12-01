package be.matt.examen;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.matt.examen.POJO.Booking;
import be.matt.examen.POJO.Instructor;
import be.matt.examen.POJO.Lesson;
import be.matt.examen.POJO.LessonType;
import be.matt.examen.POJO.Skier;

public class BookingList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the frame.
	 */
	public BookingList(ArrayList<Lesson> ll) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 905, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		model = new DefaultTableModel(new String[]{"Instructor", "Sport's name", "Level", "For", "During", "Price", "Minimum", "Maximum", "Students", "Enough students"}, 0);
        contentPane.setLayout(null);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 871, 426);
        contentPane.add(scrollPane);
        
        for(Lesson l : ll)
        {
        	LessonType lt = l.getLessonType();
        	Instructor i = l.getInstructor();
        	
        	String studentAge = "Adult";
        	if(lt.getChildCourse())
        	{
        		studentAge = "Children";
        	}
        	
        	String dayTime = "Afternoon";
        	if(l.getMorning())
        	{
        		dayTime = "Morning";
        	}
        	
        	String enough = "No";
        	if(l.getAmountStudent() >= l.getMinBookings())
        	{
        		enough = "Yes";
        	}
        	
        	model.addRow(new Object[]{i.getName() + " " + i.getFirstname(), lt.getSportName(), lt.getLevel(), studentAge, dayTime, lt.getPrice(), l.getMinBookings(), l.getMaxBookings(), l.getAmountStudent(), enough});
        }
	}

}
