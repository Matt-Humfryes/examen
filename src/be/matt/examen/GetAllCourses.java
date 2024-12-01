package be.matt.examen;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GetAllCourses extends JFrame {
	
	private Skier skier;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnBooking;
	private JButton btnRefresh;
	private JButton btnSeeBookings;

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
		setBounds(100, 100, 799, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		model = new DefaultTableModel(new String[]{"Instructor", "Sport's name", "Level", "For", "During", "Price", "Minimum", "Maximum", "Students"}, 0);
        contentPane.setLayout(null);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 765, 426);
        contentPane.add(scrollPane);
        
        btnBooking = new JButton("Join selected course");
        btnBooking.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int modelRow = table.convertRowIndexToModel(selectedRow);
                    Lesson l = ll.get(modelRow);
                    
                    int foundPrice = Booking.newBooking(l, skier);
                    
                    if(foundPrice == 0)
                    {
                    	try {
            				BookingError error = new BookingError();
            				error.setVisible(true);
            			} catch (Exception ex) {
            				ex.printStackTrace();
            			}
                    }
                    else
                    {
                    	try {
            				BookingPrice result = new BookingPrice(foundPrice);
            				result.setVisible(true);
            			} catch (Exception ex) {
            				ex.printStackTrace();
            			}
                    }
                }
        	}
        });
        btnBooking.setBounds(10, 446, 179, 21);
        contentPane.add(btnBooking);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GetAllCourses.askCourses(skier);
        		GetAllCourses.this.dispose();
        	}
        });
        btnRefresh.setBounds(635, 446, 140, 21);
        contentPane.add(btnRefresh);
        
        btnSeeBookings = new JButton("See joined courses");
        btnSeeBookings.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArrayList<Lesson> booked = skier.getJoinedCourses();
        		
        		if(booked.isEmpty())
        		{
        			try {
        				CoursesListError error = new CoursesListError();
        				error.setVisible(true);
        			} catch (Exception ex) {
        				ex.printStackTrace();
        			}
        		}
        		else
        		{
        			try {
        				BookingList bookings = new BookingList(booked);
        				bookings.setVisible(true);
        			} catch (Exception ex) {
        				ex.printStackTrace();
        			}
        		}
        	}
        });
        btnSeeBookings.setBounds(199, 446, 179, 21);
        contentPane.add(btnSeeBookings);
        
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
