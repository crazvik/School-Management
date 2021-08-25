package se.ecutb;

import se.ecutb.data_access.CourseDaoList;
import se.ecutb.data_access.StudentDaoList;

import java.time.LocalDate;
import java.util.*;

public class SchoolManagementMain {
	private static final StudentDaoList studentList = new StudentDaoList();
	private static final CourseDaoList courseList = new CourseDaoList();
	private static boolean session = true;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(session) {
			System.out.print("What do you want to do?" + "\nEnter 1 to add or find students, "
					+ "\nEnter 2 to create new course or find an existing one, \nEnter 3 to register students to course or "
					+ "to edit student or course.\nEnter 4 to quit \nSelection: ");
			String firstAnswer=in.nextLine();
			switch(firstAnswer) {
			case "1":
				System.out.print("\nEnter 1 to add a student, \nEnter 2 to find from Id, "
						+ "\nEnter 3 to find from name, \nEnter 4 to find from email, "
						+ "\nEnter 5 to show all registered students, \nEnter 6 to remove selected student.\nSelection: ");
				String answer = in.nextLine();
				switch(answer) {
				case "1":
					System.out.println("\nEnter Id, name, email and address in this order: ");
					int id = Integer.parseInt(in.nextLine());
					String name = in.nextLine();
					String email = in.nextLine();
					String address = in.nextLine();
					Student student = new Student(id, name, email, address);
					System.out.println("Student added: \n	" + studentList.saveStudent(student).toString() + "\n");
					break;
				case "2":
					Iterator<Student> read = studentList.findAll().iterator();
					System.out.println();
					while(read.hasNext()) {
						System.out.println("Id: " + read.next().getId());
					}
					System.out.print("\nId: ");
					id = Integer.parseInt(in.nextLine());
					System.out.println("Student: \n	" + (studentList.findById(id)).toString()+"\n");
					break;
				case "3":
					read = studentList.findAll().iterator();
					System.out.println();
					while(read.hasNext()) {
						System.out.println("Name: " + read.next().getName());
					}
					System.out.print("\nName: ");
					name = in.nextLine();
					read = studentList.findByName(name).iterator();
					while(read.hasNext()) {
						System.out.println("Student: \n	" + read.next().toString() +"\n");
					}
					break;
				case "4":
					read = studentList.findAll().iterator();
					System.out.println();
					while(read.hasNext()) {
						System.out.println("Email: " + read.next().getEmail());
					}
					System.out.print("\nEmail: ");
					email = in.nextLine();
					System.out.println("Student: \n	" + studentList.findByEmail(email).toString()+"\n");
					break;
				case "5":
					read = studentList.findAll().iterator();
					System.out.println("\nRegistered students: ");
					while(read.hasNext()) {
						System.out.println("	" + read.next().toString());
					}
					System.out.println();
					break;
				case "6":
					if(!studentList.findAll().isEmpty()) {
						read = studentList.findAll().iterator();
						System.out.println("Select registered student to remove by typing their id: ");
						while(read.hasNext()) {
							System.out.println("	" + read.next().toString());
						}
						System.out.print("\nId: ");
						id = Integer.parseInt(in.nextLine());
						if(studentList.deleteStudent(studentList.findById(id))) {
							System.out.println("\nStudent removed\n");
						}
						else {
							System.out.println("\nNo student found\n");
						}
					}
					break;
				default:
					System.out.println("Invalid input!\n");
				}
				break;
			case "2":
				System.out.print("\nEnter 1 to add a course, \nEnter 2 to find from Id, "
						+ "\nEnter 3 to find from name, \nEnter 4 to find from date, "
						+ "\nEnter 5 to show all registered courses, \nEnter 6 to remove selected course.\nSelection: ");
				answer =in.nextLine();
				switch(answer) {
				case "1":
					System.out.println("\nEnter Id, name, date (format YYYY-MM-DD) and week duration in this order: ");
					int id = Integer.parseInt(in.nextLine());
					String name = in.nextLine();
					LocalDate date = LocalDate.parse(in.nextLine());
					int duration = Integer.parseInt(in.nextLine());
					Course course = new Course(id, name, date, duration);
					courseList.saveCourse(course);
					Iterator<Course> read = courseList.findAll().iterator();
					if(read.hasNext()) {
						System.out.println("Course added: \n	" + courseList.findAll().get(courseList.findAll().size()-1).toString());
					}
					System.out.println();
					break;
				case "2":
					read = courseList.findAll().iterator();
					System.out.println();
					while(read.hasNext()) {
						System.out.println("Id: " + read.next().getId());
					}
					System.out.print("\nId: ");
					id = Integer.parseInt(in.nextLine());
					System.out.println("Course: \n	" + courseList.findById(id).toString()+"\n");
					break;
				case "3":
					read = courseList.findAll().iterator();
					System.out.println();
					while(read.hasNext()) {
						System.out.println("Name: " + read.next().getCourseName());
					}
					System.out.print("\nCourse name: ");
					name = in.nextLine();
					read = courseList.findByName(name).iterator();
					while(read.hasNext()) {
						System.out.println("Course: \n	" + read.next().toString()+"\n");
					}
					break;
				case "4":
					read = courseList.findAll().iterator();
					System.out.println();
					while(read.hasNext()) {
						System.out.println("Start date: " + read.next().getStartDate());
					}
					System.out.print("\nCourse start: ");
					String ymd = in.nextLine();
					if(ymd.length()<10) {
						date = LocalDate.parse("1111-11-11");
					}
					else {
						date = LocalDate.parse(ymd);
					}
					read = courseList.findByDate(date).iterator();
					while(read.hasNext()) {
						System.out.println("Course: \n	" + read.next().toString()+"\n");
					}
					break;
				case "5":
					read = courseList.findAll().iterator();
					System.out.println("\nRegistered courses: ");
					while(read.hasNext()) {
						System.out.println("	" + read.next().toString());
					}
					System.out.println();
					break;
				case "6":
					read = courseList.findAll().iterator();
					System.out.println("Select registered course to remove by typing their id: ");
					while(read.hasNext()) {
						System.out.println("	" + read.next().toString());
					}
					System.out.print("\nId: ");
					id = Integer.parseInt(in.nextLine());
					if(courseList.removeCourse(courseList.findById(id))) {
						System.out.println("\nCourse removed\n");
					}
					else {
						System.out.println("\nNo course found\n");
					}
					break;
				default:
					System.out.println("Invalid input!\n");
				}
				break;
			case "3":
				System.out.print("\nEnter 1 to add a student to a course, \nEnter 2 to show registered students, "
						+ "\nEnter 3 to remove student, \nEnter 4 to edit student, \nEnter 5 to edit course\nSelection: ");
				answer =in.nextLine();
				switch(answer) {
				case "1": 
					Iterator<Course> readCourse = courseList.findAll().iterator();
					System.out.println();
					while(readCourse.hasNext()) {
						System.out.println("	" + readCourse.next().toString());
					}
					System.out.print("Enter the id of the course: ");
					int courseId = Integer.parseInt(in.nextLine());
					Iterator<Student> readStudent = studentList.findAll().iterator();
					while(readStudent.hasNext()) {
						System.out.println("	" + readStudent.next().toString());
					}
					System.out.print("Enter the id of the student to add: ");
					int studentId = Integer.parseInt(in.nextLine());
					courseList.findById(courseId).register(studentList.findById(studentId));
					break;
				case "2":
					readCourse = courseList.findAll().iterator();
					while(readCourse.hasNext()) {
						System.out.println("	" + readCourse.next().toString());
					}
					System.out.print("Enter the id of the course: ");
					courseId = Integer.parseInt(in.nextLine());
					readStudent = courseList.findById(courseId).getStudents().iterator();
					while(readStudent.hasNext()) {
						System.out.println("	" + readStudent.next().toString());
					}
					System.out.println();
					break;
				case "3":
					readCourse = courseList.findAll().iterator();
					while (readCourse.hasNext()) {
						System.out.println("	" + readCourse.next().toString());
					}
					System.out.print("Enter the id of the course: ");
					courseId = Integer.parseInt(in.nextLine());
					readStudent = courseList.findById(courseId).getStudents().iterator();
					while (readStudent.hasNext()) {
						System.out.println("	" + readStudent.next().toString());
					}
					System.out.print("Enter the id of the student to remove: ");
					studentId = Integer.parseInt(in.nextLine());
					courseList.findById(courseId).unregister(studentList.findById(studentId));
					break;
				case "4":
					if(studentList.findAll().size()>0) {
						readStudent = studentList.findAll().iterator();
						System.out.println("\nRegistered students: ");
						while (readStudent.hasNext()) {
							System.out.println("	" + readStudent.next().toString());
						}
						System.out.print("Enter the id of the student you want to edit: ");
						studentId = Integer.parseInt(in.nextLine());
						System.out.println(studentList.findById(studentId).toString());
						System.out.print("What do you want to edit?\nEnter 1 for id, \nEnter 2 for name, "
								+ "\nEnter 3 for email, \nEnter 4 for address \nSelection: ");
						answer = in.nextLine();
						switch (answer) {
							case "1":
								System.out.print("Old id: " + studentList.findById(studentId).getId() + "\nNew id: ");
								studentList.findById(studentId).setId(Integer.parseInt(in.nextLine()));
								System.out.println();
								break;
							case "2":
								System.out.print("Old name: " + studentList.findById(studentId).getName() + "\nNew name: ");
								studentList.findById(studentId).setName(in.nextLine());
								System.out.println();
								break;
							case "3":
								System.out.print("Old email: " + studentList.findById(studentId).getEmail() + "\nNew email: ");
								studentList.findById(studentId).setEmail(in.nextLine());
								System.out.println();
								break;
							case "4":
								System.out.print("Old address: " + studentList.findById(studentId).getAddress() + "\nNew address: ");
								studentList.findById(studentId).setAddress(in.nextLine());
								System.out.println();
								break;
						}
					}
					else {
						System.out.println("	No students registered\n");
					}
					break;
					case "5":
					if(courseList.findAll().size()>0) {
						readCourse = courseList.findAll().iterator();
						System.out.println("\nRegistered courses: ");
						while (readCourse.hasNext()) {
							System.out.println("	" + readCourse.next().toString());
						}
						System.out.print("Enter the id of the course you want to edit: ");
						courseId = Integer.parseInt(in.nextLine());
						System.out.println(courseList.findById(courseId).toString());
						System.out.print("What do you want to edit?\nEnter 1 for id, \nEnter 2 for name, "
								+ "\nEnter 3 for start date, \nEnter 4 for duration \nSelection: ");
						answer = in.nextLine();
						switch (answer) {
							case "1":
								System.out.print("Old id: " + courseList.findById(courseId).getId() + "\nNew id: ");
								courseList.findById(courseId).setId(Integer.parseInt(in.nextLine()));
								System.out.println();
								break;
							case "2":
								System.out.print("Old name: " + courseList.findById(courseId).getCourseName() + "\nNew name: ");
								courseList.findById(courseId).setCourseName(in.nextLine());
								System.out.println();
								break;
							case "3":
								System.out.print("Old start date: " + courseList.findById(courseId).getStartDate() + "\nNew start date: ");
								courseList.findById(courseId).setStartDate(LocalDate.parse(in.nextLine()));
								System.out.println();
								break;
							case "4":
								System.out.print("Old duration: " + courseList.findById(courseId).getDuration() + "\nNew duration: ");
								courseList.findById(courseId).setDuration(Integer.parseInt(in.nextLine()));
								System.out.println();
								break;
						}
					}
					else {
						System.out.println("	No courses registered\n");
					}
					break;
					default:
					System.out.println("Invalid input!\n");
				}
				break;
			case "4":
				System.out.println("Manager closed");
				session = false;
				break;
			default:
				System.out.println("Invalid input!\n");
			}
		}
		in.close();
	}
}