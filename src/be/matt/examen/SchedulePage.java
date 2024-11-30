package be.matt.examen;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.matt.examen.POJO.Instructor;
import be.matt.examen.POJO.Lesson;
import be.matt.examen.POJO.LessonType;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SchedulePage extends JFrame {
	Instructor instructor;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnAddCourse;
	private JButton btnRefresh;

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
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SchedulePage.askSchedule(instructor.getUsername(), instructor.getPassword());
        		SchedulePage.this.dispose();
        	}
        });
        btnRefresh.setBounds(10, 415, 146, 21);
        contentPane.add(btnRefresh);
        
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

	class NewCourseForm extends JFrame {
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
					
					boolean result = Instructor.newCourse(sport, level, child, time, instructor);
					
					if(!result)
					{
						try {
							CourseError error = new CourseError();
							error.setVisible(true);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			});
			btnConfirm.setBounds(104, 131, 85, 21);
			contentPane.add(btnConfirm);
		}

	}
}
