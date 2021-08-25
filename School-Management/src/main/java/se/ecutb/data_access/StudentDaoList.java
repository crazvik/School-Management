package se.ecutb.data_access;

import se.ecutb.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentDaoList implements StudentDao {
	private static List<Student> students;

	public StudentDaoList() {
		students = new ArrayList<>();
	}
	
	public Student saveStudent(Student student) {
		int i = 0;
		while(i <students.size()) {
			if(students.size()>0) {
				if(students.get(i).getEmail().equals(student.getEmail())) {
					return (new Student(0, "---", "[Email already taken]", "---"));
				}
				else if(students.get(i).getId()==(student.getId())) {
					return (new Student(0, "[Id already taken]", "---", "---"));
				}
			}
			i++;
		}
		students.add(student);
		return student;
	}

	public Student findByEmail(String email) {
		int i=0;
		while(i<students.size()) {
			if(students.get(i).getEmail().equals(email)) {
				return students.get(i);
			}
		i++;
		}
		return (new Student(0, "[No student found]", "---", "---"));
	}

	public List<Student> findByName(String name) {
		List<Student> namesFound = new ArrayList<>();
		for (Student student : students) {
			if (name.equalsIgnoreCase(student.getName())) {
				namesFound.add(student);
				break;
			}
		}
		if(namesFound.size()==0) {
			namesFound.add((new Student(0, "[No student found]", "---", "---")));
		}
		return namesFound;
	}

	public Student findById(int id) {
		int i=0;
		while(i<students.size()) {
			i++;
			if(id==students.get(i-1).getId()) {
				return students.get(i-1);
			}
		}
		return (new Student(0, "[No student found]", "---", "---"));
	}

	public List<Student> findAll() {
		return new ArrayList<>(students);
	}

	public boolean deleteStudent(Student student) {
		if(students.contains(student) ) {
			students.remove(student);
			return true;
		}
		else {
			return false;
		}
	}
}