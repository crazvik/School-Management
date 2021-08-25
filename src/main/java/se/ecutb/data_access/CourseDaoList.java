package se.ecutb.data_access;

import se.ecutb.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoList implements CourseDao {
	private static List<Course> courses;

	public CourseDaoList() {
		courses = new ArrayList<>();
	}

	public Course saveCourse(Course course) {
		int i = 0;
		while(i <courses.size()) {
			if(courses.size()>0) {
				if(courses.get(i).getCourseName().equals(course.getCourseName())) {
					return (new Course(0, "[Name already taken]", LocalDate.parse("1111-11-11"), 0));
				}
				else if(courses.get(i).getId()==(course.getId())) {
					return (new Course(0, "[Id already taken]", LocalDate.parse("1111-11-11"), 0));
				}
			}
			i++;
		}
		courses.add(course);
		return course;
	}

	public Course findById(int id) {
		int i=0;
		while(i<courses.size()) {
			i++;
			if(id==courses.get(i-1).getId()) {
				return courses.get(i-1);
			}
		}
		return new Course(0, "[Course not found]", LocalDate.parse("1111-11-11"), 0);
	}

	public List<Course> findByName(String name) {
		List<Course> namesFound = new ArrayList<>();
		for (Course course : courses) {
			if (name.equalsIgnoreCase(course.getCourseName())) {
				namesFound.add(course);
			}
		}
        if(namesFound.size()==0) {
            namesFound.add((new Course(0, "[No course found]", LocalDate.parse("1111-11-11"), 0)));
        }
		return namesFound;
	}

	public List<Course> findByDate(LocalDate date) {
		List<Course> allDatesFound = new ArrayList<>();
		for (Course course : courses) {
			if (course.getStartDate().equals(date)) {
				allDatesFound.add(course);
			}
		}
        if(allDatesFound.size()==0) {
            allDatesFound.add((new Course(0, "[No course found]", LocalDate.parse("1111-11-11"), 0)));
        }
		return allDatesFound;
	}

	public List<Course> findAll() {
		return new ArrayList<>(courses);
	}

	public boolean removeCourse(Course course) {
		if(courses.contains(course) ) {
			courses.remove(course);
			return true;
		}
		else {
			return false;
		}
	}
}