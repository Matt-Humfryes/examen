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
import be.matt.examen.POJO.Skier;

public class GetAllCourses extends JFrame {
	
	private Skier skier;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	public static void askCourses(Skier s) {
		ArrayList<Lesson> lessons = Lesson.getCourses();
		
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
				GetAllCourses frame = new GetAllCourses(s, lessons);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public GetAllCourses(Skier s, ArrayList<Lesson> ll) {
		skier = s;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 799, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		model = new DefaultTableModel(new String[]{"Instructor", "Sport's name", "Level", "For", "During", "Price", "Minimum", "Maximum", "Students"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 416, 243);
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
        	
        	model.addRow(new Object[]{i.getName() + " " + i.getFirstname(), lt.getSportName(), lt.getLevel(), studentAge, dayTime, lt.getPrice(), l.getMinBookings(), l.getMaxBookings(), l.getAmountStudent()});
        }
	}

}
