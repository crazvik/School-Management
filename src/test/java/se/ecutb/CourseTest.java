package se.ecutb;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    Course testCourse = new Course(1, "Course1", LocalDate.parse("2019-01-01"), 10);
    Student testStudent1 = new Student(1, "Student1", "Student.1", "Student1 address");
    Student testStudent2 = new Student(2, "Student2", "Student.2", "Student2 address");
    Student testStudent3 = new Student(3, "Student3", "Student.3", "Student3 address");

    @Test
    void register() {
        testCourse.register(testStudent1);
        testCourse.register(testStudent2);
        testCourse.register(testStudent3);
        assertNotNull(testCourse.getStudents());
        testCourse.register(testStudent1);
        assertEquals(testCourse.getStudents().size(), 3);
    }

    @Test
    void unregister() {
        testCourse.register(testStudent1);
        testCourse.register(testStudent2);
        testCourse.register(testStudent3);
        assertEquals(testCourse.getStudents().size(), 3);
        testCourse.unregister(testStudent2);
        testCourse.unregister(testStudent2);
        assertEquals(testCourse.getStudents().size(), 2);
    }
}