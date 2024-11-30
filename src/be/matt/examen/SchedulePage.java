package be.matt.examen;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.matt.examen.POJO.Instructor;
import be.matt.examen.POJO.Lesson;
import be.matt.examen.POJO.LessonType;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SchedulePage extends JFrame {
	Instructor instructor;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnAddCourse;

	public static void askSchedule(String uname, String psswrd)
	{
		ArrayList<Lesson> lessons = Instructor.getSchedule(uname, psswrd);
		
		if(lessons.isEmpty())
		{
			try {
				CoursesListError error = new CoursesListError();
				error.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				SchedulePage frame = new SchedulePage(lessons);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public SchedulePage(ArrayList<Lesson> ll) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 799, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		model = new DefaultTableModel(new String[]{"Sport's name", "Level", "For", "During", "Price", "Minimum", "Maximum", "Students"}, 0);
        contentPane.setLayout(null);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(166, 10, 609, 426);
        contentPane.add(scrollPane);
        
        btnAddCourse = new JButton("Add a new course");
        btnAddCourse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					NewCourseForm newCourseFormPage = new NewCourseForm();
					newCourseFormPage.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
        	}
        });
        btnAddCourse.setBounds(10, 13, 146, 21);
        contentPane.add(btnAddCourse);
        
        instructor = ll.getFirst().getInstructor();
        
        for(Lesson l : ll)
        {
        	LessonType lt = l.getLessonType();
        	
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
        	
        	model.addRow(new Object[]{lt.getSportName(), lt.getLevel(), studentAge, dayTime, lt.getPrice(), l.getMinBookings(), l.getMaxBookings(), l.getAmountStudent()});
        }
	}

}
