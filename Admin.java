import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Admin  extends User {
    public Admin(String email, String password) {
        super(email, password, "Admin");
    }

    public void manageCatalog(Map<String, ArrayList<Course>> semesterCourses) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nCourse Catalog Management:");
            System.out.println("1. View Courses");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllCourses(semesterCourses);
                    break;
                case "2":
                    addCourse(semesterCourses, scanner);
                    break;
                case "3":
                    deleteCourse(semesterCourses, scanner);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllCourses(Map<String, ArrayList<Course>> semesterCourses) {
        for (Map.Entry<String, ArrayList<Course>> entry : semesterCourses.entrySet()) {
            System.out.println("\nSemester " + entry.getKey() + " Courses:");
            for (Course course : entry.getValue()) {
                System.out.println(course);
            }
        }
    }


    private void addCourse(Map<String, ArrayList<Course>> semesterCourses, Scanner scanner) {
        System.out.print("Enter semester: ");
        String semester = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter professor name: ");
        String profName = scanner.nextLine();
        System.out.print("Enter credits: ");
        String credit = scanner.nextLine();
        System.out.print("Enter time: ");
        String time = scanner.nextLine();
        System.out.print("Enter place: ");
        String place = scanner.nextLine();
        System.out.print("Enter prerequisites: ");
        String prerequisites = scanner.nextLine();

        Course newCourse = new Course(courseCode, courseName, profName, credit, time, place, prerequisites);
        semesterCourses.computeIfAbsent(semester, k -> new ArrayList<>()).add(newCourse);
        System.out.println("Course added successfully.");
    }

    private void deleteCourse(Map<String, ArrayList<Course>> semesterCourses, Scanner scanner) {
        System.out.print("Enter semester: ");
        String semester = scanner.nextLine();
        System.out.print("Enter course code to delete: ");
        String courseCode = scanner.nextLine();

        ArrayList<Course> courses = semesterCourses.get(semester);
        if (courses != null) {
            courses.removeIf(course -> course.getCourseCode().equals(courseCode));
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Semester not found.");
        }
    }

    public void manageStudentRecords(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nStudent Records Management:");
            System.out.println("1. View Student Records");
            System.out.println("2. Update Student Information");
            System.out.println("3. Back to Admin Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewStudentRecords(users);
                    break;
                case "2":
                    updateStudentInfo(users, scanner);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewStudentRecords(ArrayList<User> users) {
        for (User user : users) {
            if (user instanceof Student) {
                System.out.println(user);
            }
        }
    }

    private void updateStudentInfo(ArrayList<User> users, Scanner scanner) {
        System.out.print("Enter student email: ");
        String email = scanner.nextLine();
        for (User user : users) {
            if (user instanceof Student && user.Email.equals(email)) {
                Student student = (Student) user;
                System.out.print("Enter new semester (or press enter to skip): ");
                String newSemester = scanner.nextLine();
                if (!newSemester.isEmpty()) {
                    student.Semester = newSemester;
                }
                System.out.println("Student information updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void assignProfessorsToCourses(Map<String, ArrayList<Course>> semesterCourses) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAssign Professors to Courses:");
            System.out.println("1. View Course Assignments");
            System.out.println("2. Assign Professor to Course");
            System.out.println("3. Back to Admin Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewCourseAssignments(semesterCourses);
                    break;
                case "2":
                    assignProfessor(semesterCourses, scanner);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewCourseAssignments(Map<String, ArrayList<Course>> semesterCourses) {
        for (Map.Entry<String, ArrayList<Course>> entry : semesterCourses.entrySet()) {
            System.out.println("\nSemester " + entry.getKey() + " Course Assignments:");
            for (Course course : entry.getValue()) {
                System.out.println(course.getCourseCode() + ": " + course.getCourseName() + " - Professor: " + course.getProfessor());
            }
        }
    }

    private void assignProfessor(Map<String, ArrayList<Course>> semesterCourses, Scanner scanner) {
        System.out.print("Enter semester: ");
        String semester = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter new professor name: ");
        String newProfessor = scanner.nextLine();

        ArrayList<Course> courses = semesterCourses.get(semester);
        if (courses != null) {
            for (Course course : courses) {
                if (course.getCourseCode().equals(courseCode)) {
                    course.ProfName = newProfessor;
                    System.out.println("Professor assigned successfully.");
                    return;
                }
            }
            System.out.println("Course not found.");
        } else {
            System.out.println("Semester not found.");
        }
    }

    public void handleComplaints(ArrayList<Complaint> complaints) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nHandle Complaints:");
            System.out.println("1. View All Complaints");
            System.out.println("2. Update Complaint Status");
            System.out.println("3. Back to Admin Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllComplaints(complaints);
                    break;
                case "2":
                    updateComplaintStatus(complaints, scanner);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllComplaints(ArrayList<Complaint> complaints) {
        for (Complaint complaint : complaints) {
            System.out.println(complaint);
        }
    }

    private void updateComplaintStatus(ArrayList<Complaint> complaints, Scanner scanner) {
        System.out.print("Enter complaint ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new status (Pending/In Progress/Resolved): ");
        String newStatus = scanner.nextLine();

        for (Complaint complaint : complaints) {
            if (complaint.getId() == id) {
                complaint.setStatus(newStatus);
                System.out.println("Complaint status updated.");
                return;
            }
        }
        System.out.println("Complaint not found.");
    }
    class GenericClass<T> {
        private T value;

        // Constructor
        public GenericClass(T value) {
            this.value = value;
        }

        // Method to display the type and value
        public void displayType() {
            System.out.println("Type: " + value.getClass().getSimpleName() + ", Value: " + value);
        }
    }

}
