import java.util.*;
import java.util.Map;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Map<String, ArrayList<Course>> semesterCourses = new HashMap<>();
        ArrayList<Complaint> complaints = new ArrayList<>();
        ArrayList<GenericPair<String, Integer>> ratingsforcourse = new ArrayList<>();

        // Semester 1 Courses
        ArrayList<Course> semester1Courses = new ArrayList<>();
        semester1Courses.add(new Course("CSE-101", "IP", "Dr. Alpha", "4", "Mon/Wed 10:00-11:30", "c101", "NONE"));
        semester1Courses.add(new Course("CSE-102", "IHCI", "Dr. Beta", "4", "Tue/Thu 10:00-11:30", "c102", "NONE"));
        semester1Courses.add(new Course("MTH-101", "M1", "Dr. Gamma", "4", "Tue/Thu 9:00-10:30", "Room c103", "NONE"));
        semester1Courses.add(new Course("CSE-103", "DC", "Dr. Theta", "4", "Tue/Thu 9:00-10:30", "Room c104", "NONE"));
        semester1Courses.add(new Course("COM-101", "COM", "Prof. Delta", "4", "Tue/Thu 9:00-10:30", "Room c105", "NONE"));
        semesterCourses.put("1", semester1Courses);

        // Semester 3 Courses
        ArrayList<Course> semester3Courses = new ArrayList<>();
        semester3Courses.add(new Course("CSE-201", "AP", "Dr. Epsilon", "4", "Mon/Wed 11:00-12:30", "c201", "NONE"));
        semester3Courses.add(new Course("CSE-202", "NET", "Dr. Zeta", "4", "Tue/Thu 11:00-12:30", "c202", "NONE"));
        semester3Courses.add(new Course("MTH-301", "M3", "Dr. Eta", "4", "Tue/Thu 10:00-11:30", "Room c303", "M1"));
        semester3Courses.add(new Course("CSE-203", "DSA", "Dr. Iota", "4", "Tue/Thu 10:00-11:30", "Room c304", "NONE"));
        semester3Courses.add(new Course("COM-201", "COM2", "Prof. Kappa", "4", "Tue/Thu 10:00-11:30", "Room c305", "NONE"));
        semesterCourses.put("3", semester3Courses);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the college management system");
            System.out.println("");
            System.out.println("Define your Role");
            System.out.println("Student");
            System.out.println("Professor");
            System.out.println("Admin");
            System.out.println("TA");

            String role = scanner.nextLine().toLowerCase();

            ArrayList<User> users = new ArrayList<>();

            if (role.equals("student")) {
                // Initialize users for students
                users.add(new User("001@Email.com", "001", "1"));
                users.add(new User("002@Email.com", "002", "3"));
                users.add(new User("003@Email.com", "003", "3"));

                boolean isLoggedIn = false;
                while (!isLoggedIn) {
                    System.out.println("Enter your Email");
                    String email = scanner.nextLine();
                    System.out.println("Enter your Password");
                    String password = scanner.nextLine();

                    for (User user : users) {
                        if (user.login(email, password)) {
                            isLoggedIn = true;
                            System.out.println("Welcome Student");
                            user.printSemester();

                            while (true) {
                                System.out.println("1. Enroll in Courses");
                                System.out.println("2. View Enrolled Courses");
                                System.out.println("3. See your Cgpa/Sgpa");
                                System.out.println("4. Drop course");
                                System.out.println("5. Register complaint");
                                System.out.println("6. check complaint status");
                                System.out.println("7. Rate course");
                                System.out.println("8. EXIT");
                                System.out.print("Choose an option: ");
                                String studentchoice = scanner.nextLine();

                                switch (studentchoice) {
                                    case "1":
                                        try {
                                            user.addCourse(semesterCourses);
                                        } catch (CourseFullException e) {
                                            System.out.println("Error: " + e.getMessage());
                                        }
                                        break;
                                    case "2":
                                        user.viewEnrolledCourses();
                                        break;
                                    case "3":
                                        user.CgpaSgpa(semesterCourses);
                                        break;
                                    case "4":
                                        try {
                                            user.dropCourse();
                                        } catch (DropDeadlinePassedException e) {
                                            System.out.println("Error: " + e.getMessage());
                                        }
                                        break;
                                    case "5":
                                        user.GetComplaints(complaints);
                                        break;
                                    case "6":
                                        user.CheckComplaints(complaints);
                                        break;
                                    case "7":
                                        System.out.print("Enter a course code: ");
                                        String courseCode = scanner.nextLine();
                                        System.out.print("Enter a rating (out of 5): ");
                                        int rating = scanner.nextInt();
                                        scanner.nextLine();
                                        ratingsforcourse.add(new GenericPair<>(courseCode, rating));
                                        System.out.println("Rating added.");
                                        break;
                                    case "8":
                                        System.out.println("Exiting...");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                        break;
                                }

                                if (studentchoice.equals("8")) {
                                    break;
                                }
                            }
                        }
                    }

                    if (!isLoggedIn) {
                        System.out.println("Invalid Email or Password. Please try again.");
                        System.out.println("Type 'exit' to quit or press Enter to try again.");
                        String response = scanner.nextLine().toLowerCase();
                        if (response.equals("exit")) {
                            System.out.println("Exiting...");
                            scanner.close();
                            break;
                        }
                    }
                }
            }else if (role.equals("prof")) {
                ArrayList<User> profUsers = new ArrayList<>();
                profUsers.add(new User("alpha@Email.com", "Dr. alpha", "prof1"));
                profUsers.add(new User("beta@Email.com", "Dr. beta", "prof1"));

                boolean isLoggedIn = false;
                while (!isLoggedIn) {
                    System.out.println("Enter your Email");
                    String email = scanner.nextLine();
                    System.out.println("Enter your Password");
                    String password = scanner.nextLine();

                    for (User prof : profUsers) {
                        if (prof.login(email, password)) {
                            isLoggedIn = true;
                            System.out.println("Welcome " + password);

                            while (true) {
                                System.out.println("1. View your courses");
                                System.out.println("2. Update your courses");
                                System.out.println("3. See course ratings");
                                System.out.println("4. Assign grades to students");
                                System.out.println("5. EXIT");
                                String profChoice = scanner.nextLine();

                                switch (profChoice) {
                                    case "1":
                                        prof.printCourseDetailsByProfessor(prof.Password, semesterCourses);
                                        break;
                                    case "2":
                                        prof.updateCourseDetails(prof.Password, semesterCourses);
                                        break;
                                    case "3":
                                        prof.displayCourseRatings(ratingsforcourse);
                                        break;
                                    case "4":
                                        prof.CgpaSgpa(semesterCourses);
                                        break;
                                    case "5":
                                        System.out.println("Exiting Professor menu...");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Try again.");
                                }
                                if (profChoice.equals("5")) {
                                    break;
                                }
                            }
                        }
                    }

                    if (!isLoggedIn) {
                        System.out.println("Invalid Email or Password. Please try again.");
                    }
                }
            } else if (role.equals("admin")) {
                Admin admin = new Admin("admin@Email.com", "admin");
                boolean isLoggedIn = false;
                while (!isLoggedIn) {
                    System.out.println("Enter your Email");
                    String email = scanner.nextLine();
                    System.out.println("Enter your Password");
                    String password = scanner.nextLine();

                    if (admin.login(email, password)) {
                        isLoggedIn = true;
                        System.out.println("Welcome Administrator");

                        while (true) {
                            System.out.println("\nAdmin Menu:");
                            System.out.println("1. Manage Course Catalog");
                            System.out.println("2. Manage Student Records");
                            System.out.println("3. Assign Professors to Courses");
                            System.out.println("4. Handle Complaints");
                            System.out.println("5. Exit");
                            System.out.print("Choose an option: ");
                            String adminchoice = scanner.nextLine();

                            switch (adminchoice) {
                                case "1":
                                    admin.manageCatalog(semesterCourses);
                                    break;
                                case "2":
                                    admin.manageStudentRecords(users);
                                    break;
                                case "3":
                                    admin.assignProfessorsToCourses(semesterCourses);
                                    break;
                                case "4":
                                    admin.handleComplaints(complaints);
                                    break;
                                case "5":
                                    System.out.println("Exiting...");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }

                            if(adminchoice.equals("5")) {
                                break;
                            }
                        }

                    } else {
                        System.out.println("Invalid email or password. Please try again.");
                    }
                }


            }
            else if(role.equals("ta")) {
                ArrayList<TeachingAssistant> teachingAssistants = new ArrayList<>();
                teachingAssistants.add(new TeachingAssistant("ta1@Email.com", "ta1", "ta1"));
                teachingAssistants.add(new TeachingAssistant("ta2@Email.com", "ta2", "ta3"));

                // Assign TAs to courses (you might want to do this dynamically in a real system)
                teachingAssistants.get(0).assignToCourse(semester1Courses.get(0));
                teachingAssistants.get(1).assignToCourse(semester3Courses.get(1));

                boolean isLoggedIn = false;
                while (!isLoggedIn) {
                    System.out.println("Enter your Email");
                    String email = scanner.nextLine();
                    System.out.println("Enter your Password");
                    String password = scanner.nextLine();

                    for (TeachingAssistant ta : teachingAssistants) {
                        if (ta.login(email, password)) {
                            isLoggedIn = true;
                            System.out.println("Welcome Teaching Assistant");
                            ta.printSemester();

                            while (true) {
                                System.out.println("1. View Assigned Courses");
                                System.out.println("2. View or update Student Grades");
                                System.out.println("3. EXIT");
                                System.out.print("Choose an option: ");
                                String taChoice = scanner.nextLine();

                                switch (taChoice) {
                                    case "1":
                                        ta.viewAssignedCourses();
                                        break;
                                    case "2":
                                        ta.CgpaSgpa(semesterCourses);
                                        break;
                                    case "3":
                                        System.out.println("Exiting...");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                        break;
                                }

                                if (taChoice.equals("3")) {
                                    break;
                                }
                            }
                        }
                    }
                    if (!isLoggedIn) {
                        System.out.println("Invalid Email or Password. Please try again.");
                    }
                }


            }
            else if (role.equals("exit")) {
                System.out.println("Exiting the system...");
                scanner.close();
                break;
            } else {
                System.out.println("Unknown role");
            }
        }
    }
}