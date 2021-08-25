package se.ecutb.data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ecutb.Course;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CourseDaoListTest {
    private static CourseDaoList testCourse;
    private final Course course1 = new Course(1, "Course1", LocalDate.parse("2019-01-01"), 10);
    private final Course course2 = new Course(2, "Course2", LocalDate.parse("2019-05-15"), 10);
    private final Course course3 = new Course(500, "Course3", LocalDate.parse("2019-07-25"), 10);
    private final Course course4 = new Course(100, "Course4", LocalDate.parse("2019-12-10"), 10);
    private final Course course5 = new Course(20, "Course5", LocalDate.parse("2019-03-30"), 10);

    @BeforeEach
    void setUp() {
        testCourse = new CourseDaoList();
    }

    @Test
    void saveCourse() {
        testCourse.saveCourse(course1);
        assertNotNull(testCourse);
        testCourse.saveCourse(course2);
    }

    @Test
    void findById() {
        testCourse.saveCourse(course3);
        assertEquals((testCourse.findById(500).toString()), (testCourse.findAll().get(0).toString()));
    }

    @Test
    void findByName() {
        testCourse.saveCourse(course2);
        testCourse.saveCourse(course4);
        testCourse.saveCourse(course5);
        assertEquals(testCourse.findByName("Course2").get(0), testCourse.findAll().get(0));
        assertTrue(testCourse.findAll().contains(testCourse.findByName("Course4").get(0)));
    }

    @Test
    void findByDate() {
        testCourse.saveCourse(course1);
        testCourse.saveCourse(course3);
        testCourse.saveCourse(course4);
        assertEquals(testCourse.findByDate(LocalDate.parse("2019-12-10")).get(0), testCourse.findAll().get(2));
    }

    @Test
    void findAll() {
        testCourse.saveCourse(course1);
        testCourse.saveCourse(course2);
        testCourse.saveCourse(course3);
        testCourse.saveCourse(course4);
        testCourse.saveCourse(course5);
        Iterable<Course> courseIterator1 = testCourse.findAll();
        CourseDaoList findSomeCourses = new CourseDaoList();
        findSomeCourses.saveCourse(course1);
        findSomeCourses.saveCourse(course2);
        findSomeCourses.saveCourse(course3);
        findSomeCourses.saveCourse(course4);
        findSomeCourses.saveCourse(course5);
        Iterable<Course> courseIterator2 = findSomeCourses.findAll();
        assertIterableEquals(courseIterator1, courseIterator2);
    }

    @Test
    void removeCourse() {
        testCourse.saveCourse(course2);
        testCourse.saveCourse(course4);
        assertTrue(testCourse.removeCourse(course2));
        assertFalse(testCourse.removeCourse(course1));
    }
}