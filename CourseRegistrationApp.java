import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Student {
    private int studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

class CourseRegistrationSystem {
    private List<Course> courseDatabase;
    private List<Student> studentDatabase;

    public CourseRegistrationSystem() {
        this.courseDatabase = new ArrayList<>();
        this.studentDatabase = new ArrayList<>();
    }

    public void displayCourseListing() {
        System.out.println("\nCourse Listing:");
        for (Course course : courseDatabase) {
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Schedule: " + course.getSchedule());
            System.out
                    .println("Available Slots: " + (course.getCapacity() - getEnrollmentCount(course.getCourseCode())));
            System.out.println("------------------------");
        }
    }

    private int getEnrollmentCount(String courseCode) {
        int enrollmentCount = 0;
        for (Student student : studentDatabase) {
            if (student.getRegisteredCourses().contains(courseCode)) {
                enrollmentCount++;
            }
        }
        return enrollmentCount;
    }

    public void registerStudentForCourse(int studentID, String courseCode) {
        Student student = getStudentByID(studentID);
        if (student != null) {
            Course course = getCourseByCode(courseCode);
            if (course != null && getEnrollmentCount(courseCode) < course.getCapacity()) {
                student.registerForCourse(courseCode);
                System.out.println("Registration successful for student " + student.getName() + " (ID: "
                        + student.getStudentID() + ") in course " + course.getTitle());
            } else {
                System.out.println("Course " + courseCode + " is full. Registration failed.");
            }
        } else {
            System.out.println("Student with ID " + studentID + " not found. Registration failed.");
        }
    }

    public void dropStudentFromCourse(int studentID, String courseCode) {
        Student student = getStudentByID(studentID);
        if (student != null) {
            Course course = getCourseByCode(courseCode);
            if (course != null && student.getRegisteredCourses().contains(courseCode)) {
                student.dropCourse(courseCode);
                System.out.println("Course " + course.getTitle() + " dropped successfully for student "
                        + student.getName() + " (ID: " + student.getStudentID() + ")");
            } else {
                System.out.println("Student " + student.getName() + " is not registered in course " + courseCode
                        + ". Drop failed.");
            }
        } else {
            System.out.println("Student with ID " + studentID + " not found. Drop failed.");
        }
    }

    private Student getStudentByID(int studentID) {
        for (Student student : studentDatabase) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private Course getCourseByCode(String courseCode) {
        for (Course course : courseDatabase) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public void addCourseToDatabase(Course course) {
        courseDatabase.add(course);
    }

    public void addStudentToDatabase(Student student) {
        studentDatabase.add(student);
    }
}

public class CourseRegistrationApp {
    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();

        Course javaCourse = new Course("CS101", "Introduction to Java", "Learn Java programming basics", 30,
                "Mon/Wed 10:00 AM - 12:00 PM");
        Course dbCourse = new Course("CS102", "Database Management", "Introduction to Database Systems", 25,
                "Tue/Thu 2:00 PM - 4:00 PM");

        Student student1 = new Student(1, "Alice");
        Student student2 = new Student(2, "Bob");

        registrationSystem.addCourseToDatabase(javaCourse);
        registrationSystem.addCourseToDatabase(dbCourse);
        registrationSystem.addStudentToDatabase(student1);
        registrationSystem.addStudentToDatabase(student2);

        registrationSystem.displayCourseListing();

        registrationSystem.registerStudentForCourse(1, "CS101");
        registrationSystem.registerStudentForCourse(2, "CS101");
        registrationSystem.registerStudentForCourse(1, "CS102");

        registrationSystem.displayCourseListing();
    }
}
