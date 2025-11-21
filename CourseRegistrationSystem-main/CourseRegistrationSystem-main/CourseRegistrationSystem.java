import java.util.*;

class Course {
    int courseId;
    String courseName;

    Course(int id, String name) {
        this.courseId = id;
        this.courseName = name;
    }

    @Override
    public String toString() {
        return courseId + " - " + courseName;
    }
}

class Student {
    int studentId;
    String studentName;

    Student(int id, String name) {
        this.studentId = id;
        this.studentName = name;
    }

    @Override
    public String toString() {
        return studentId + " - " + studentName;
    }
}

class Registration {
    Student student;
    Course course;

    Registration(Student s, Course c) {
        this.student = s;
        this.course = c;
    }

    @Override
    public String toString() {
        return student.studentName + " registered for " + course.courseName;
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Course> courseList = new ArrayList<>();
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<Registration> registrationList = new ArrayList<>();

        // Predefined data (optional)
        courseList.add(new Course(1, "Java Basics"));
        courseList.add(new Course(2, "Python Programming"));
        studentList.add(new Student(101, "Jayasri"));
        studentList.add(new Student(102, "Priya"));

        while (true) {
            System.out.println("\n==== Course Registration System ====");
            System.out.println("1. Add New Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Register Student for a Course");
            System.out.println("4. View All Registrations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Course ID: ");
                    int cid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Course Name: ");
                    String cname = sc.nextLine();
                    courseList.add(new Course(cid, cname));
                    System.out.println("✅ Course added successfully!");
                    break;

                case 2:
                    if (courseList.isEmpty())
                        System.out.println("No courses available.");
                    else {
                        System.out.println("\nAvailable Courses:");
                        for (Course c : courseList)
                            System.out.println(c);
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Student Name: ");
                    String sname = sc.nextLine();
                    Student newStudent = new Student(sid, sname);
                    studentList.add(newStudent);

                    System.out.println("\nAvailable Courses:");
                    for (Course c : courseList)
                        System.out.println(c);

                    System.out.print("Enter Course ID to Register: ");
                    int regId = sc.nextInt();
                    Course selectedCourse = null;

                    for (Course c : courseList) {
                        if (c.courseId == regId) {
                            selectedCourse = c;
                            break;
                        }
                    }

                    if (selectedCourse != null) {
                        registrationList.add(new Registration(newStudent, selectedCourse));
                        System.out.println("🎓 Registration Successful!");
                    } else {
                        System.out.println("❌ Invalid Course ID!");
                    }
                    break;

                case 4:
                    if (registrationList.isEmpty())
                        System.out.println("No registrations yet.");
                    else {
                        System.out.println("\nAll Registrations:");
                        for (Registration r : registrationList)
                            System.out.println(r);
                    }
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
