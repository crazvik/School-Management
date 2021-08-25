package se.ecutb.data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ecutb.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoListTest {
    private static StudentDaoList testStudents;
    private final Student student1 = new Student(1, "Student1", "Student.1", "someplace");
    private final Student student2 = new Student(2, "Student2", "Student.2", "there");
    private final Student student3 = new Student(3, "Student3", "Student.3", "over here");
    private final Student student4 = new Student(4, "Student4", "Student.4", "somewhere");
    private final Student student5 = new Student(5, "Student5", "Student.5", "cabbage patch");

    @BeforeEach
    void setUp() {
        testStudents = new StudentDaoList();
    }

    @Test
    void saveStudent() {
        testStudents.saveStudent(student1);
        assertNotNull(testStudents);
        testStudents.saveStudent(student2);
        assertEquals(testStudents.findAll().get(0), student1);
    }

    @Test
    void findByEmail() {
        testStudents.saveStudent(student3);
        testStudents.saveStudent(student1);
        assertEquals(testStudents.findByEmail("Student.1").getEmail(), testStudents.findAll().get(1).getEmail());
        assertNotEquals(testStudents.findByEmail("Student.3").getEmail(), testStudents.findAll().get(1).getEmail());
    }

    @Test
    void findByName() {
        testStudents.saveStudent(student4);
        testStudents.saveStudent(student5);
        assertEquals(testStudents.findByName("Student5").get(0), testStudents.findAll().get(1));
        assertNotEquals(testStudents.findByName("Student5").get(0), testStudents.findAll().get(0));
    }

    @Test
    void findById() {
        testStudents.saveStudent(student4);
        testStudents.saveStudent(student2);
        assertEquals(testStudents.findById(4), testStudents.findAll().get(0));
        assertNotEquals(testStudents.findById(2), testStudents.findAll().get(0));
    }

    @Test
    void findAll() {
        testStudents.saveStudent(student1);
        testStudents.saveStudent(student2);
        testStudents.saveStudent(student3);
        testStudents.saveStudent(student4);
        testStudents.saveStudent(student5);
        Iterable<Student> studentIterator1 = testStudents.findAll();
        StudentDaoList findSomeStudents = new StudentDaoList();
        findSomeStudents.saveStudent(student1);
        findSomeStudents.saveStudent(student2);
        findSomeStudents.saveStudent(student3);
        findSomeStudents.saveStudent(student4);
        findSomeStudents.saveStudent(student5);
        Iterable<Student> studentIterator2 = findSomeStudents.findAll();
        assertIterableEquals(studentIterator1, studentIterator2);
    }

    @Test
    void deleteStudent() {
        testStudents.saveStudent(student3);
        testStudents.saveStudent(student5);
        assertTrue(testStudents.deleteStudent(student3));
        assertFalse(testStudents.deleteStudent(student1));
    }
}