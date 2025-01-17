import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    String Email;
    String Password;
    public String Semester;
    ArrayList<Course> enrolledCourses;
    private static final int MAX_COURSE_CAPACITY = 300;
    private static final String DROP_DEADLINE = "2025-01-09";

    public User(String Email, String Password, String semester) {
        this.Email = Email;
        this.Password = Password;
        this.Semester = semester;
        this.enrolledCourses = new ArrayList<>();
    }

    public boolean login(String email, String password) {
        return this.Email.equals(email) && this.Password.equals(password);
    }

    public void printSemester() {
        System.out.println("Your Current Semester: " + this.Semester);
    }

    public void CheckComplaints(ArrayList<Complaint> complaints) {
        boolean hasComplaints = false;
        for (Complaint complaint : complaints) {
            if (complaint.getStudentEmail().equals(this.Email)) {
                System.out.println("Complaint #" + complaint.getId() + " Status: " + complaint.getStatus());
                hasComplaints = true;
            }
        }
        if (!hasComplaints) {
            System.out.println("You have no registered complaints.");
        }
    }

    public void addCourse(Map<String, ArrayList<Course>> semesterCourses) throws CourseFullException {
        if (semesterCourses.containsKey(this.Semester)) {
            Scanner zz = new Scanner(System.in);
            while (true) {
                System.out.println("Available courses for Semester " + this.Semester + ":");
                ArrayList<Course> courses = semesterCourses.get(this.Semester);
                for (Course course : courses) {
                    System.out.println(course.courseName);
                }

                System.out.println("Enter the course name to enroll (or type 'exit' to stop enrolling):");
                String selectedCode = zz.nextLine();

                if (selectedCode.equalsIgnoreCase("exit")) {
                    break;
                }

                boolean courseFound = false;
                for (Course course : courses) {
                    if (course.courseName.equals(selectedCode)) {
                        if (enrolledCourses.size() >= MAX_COURSE_CAPACITY) {
                            throw new CourseFullException("Course capacity reached. Cannot enroll in more courses.");
                        }
                        enrolledCourses.add(course);
                        System.out.println("Enrolled in: " + course.courseName);
                        courseFound = true;
                        break;
                    }
                }

                if (!courseFound) {
                    System.out.println("Invalid course name. Please try again.");
                }
            }
        } else {
            System.out.println("No courses available for this semester.");
        }
    }


    public void viewEnrolledCourses() {
        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            System.out.println("Enrolled courses:");
            for (Course course : enrolledCourses) {
                System.out.println(course);
            }
        }
    }

    public void CgpaSgpa(Map<String, ArrayList<Course>> semesterCourses) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> grades = new HashMap<>();

        Map<String, Map<String, Double>> GradesStudent1 = new HashMap<>();
        Map<String, Map<String, Double>> GradesStudent2 = new HashMap<>();
        Map<String, Map<String, Double>> GradesStudent3 = new HashMap<>();

        Map<String, Double> sem1GradesStudent1 = new HashMap<>();
        Map<String, Double> sem2GradesStudent1 = new HashMap<>();
        Map<String, Double> sem1GradesStudent2 = new HashMap<>();
        Map<String, Double> sem2GradesStudent2 = new HashMap<>();
        Map<String, Double> sem1GradesStudent3 = new HashMap<>();
        Map<String, Double> sem2GradesStudent3 = new HashMap<>();

        // Semester 1 grades for student 2
        sem1GradesStudent2.put("CSE-101", 7.0);
        sem1GradesStudent2.put("CSE-102", 8.5);
        sem1GradesStudent2.put("MTH-101", 6.5);
        sem1GradesStudent2.put("CSE-103", 7.5);
        sem1GradesStudent2.put("COM-101", 8.0);

        // Semester 2 grades for student 2
        sem2GradesStudent2.put("CSE-111", 7.5);
        sem2GradesStudent2.put("CSE-112", 8.0);
        sem2GradesStudent2.put("MTH-201", 6.0);
        sem2GradesStudent2.put("CSE-113", 7.0);
        sem2GradesStudent2.put("COM-201", 8.5);

        // Semester 1 grades for student 3
        sem1GradesStudent3.put("CSE-101", 6.5);
        sem1GradesStudent3.put("CSE-102", 7.5);
        sem1GradesStudent3.put("MTH-101", 5.5);
        sem1GradesStudent3.put("CSE-103", 6.0);
        sem1GradesStudent3.put("COM-101", 7.0);

        // Semester 2 grades for student 3
        sem2GradesStudent3.put("CSE-111", 6.0);
        sem2GradesStudent3.put("CSE-112", 7.0);
        sem2GradesStudent3.put("MTH-201", 5.0);
        sem2GradesStudent3.put("CSE-113", 6.5);
        sem2GradesStudent3.put("COM-201", 7.5);

        // Storing grades based on student email and semester
        GradesStudent2.put("1", sem1GradesStudent2);
        GradesStudent2.put("2", sem2GradesStudent2);
        GradesStudent3.put("1", sem1GradesStudent3);
        GradesStudent3.put("2", sem2GradesStudent3);

        Map<String, Map<String, Double>> hardcodedGrades;

        // Retrieve hardcoded grades based on email
        hardcodedGrades = GradesStudent2;
        if (this.Email.equals("003@Email.com")) {
            hardcodedGrades = GradesStudent3;
        } else {

            // Professor's grading section
            if (this.Semester.equals("prof1") || this.Semester.equals("ta1") || this.Semester.equals("ta3")) {
                System.out.println("Are you a professor or TA? (prof/ta)");
                String isProfessorTA = scanner.nextLine().toLowerCase();

                if (isProfessorTA.equals("prof1")) {

                    System.out.println("Enter student email:");
                    String studentEmail = scanner.nextLine();
                    System.out.println("Enter semester (1 or 3):");
                    String semester = scanner.nextLine();
                    if(this.Semester.equals("prof1") && semester.equals("1")) {
                        System.out.println("Enter course code:");
                        String courseCode = scanner.nextLine();
                        System.out.println("Enter grade (4 - 10):");
                        double grade = scanner.nextDouble();

                        String key = studentEmail + "_" + semester + "_" + courseCode;
                        grades.put(key, grade);
                        System.out.println("Grade assigned successfully.");
                        return;
                    }
                    else{
                        System.out.println("student not assigned to you");
                        return;
                    }
                }
                else if (isProfessorTA.equals("ta")) {


                    System.out.println("Enter student email:");
                    String studentEmail = scanner.nextLine();
                    System.out.println("Enter semester (1 or 3):");
                    String semester = scanner.nextLine();
                    if(this.Semester.equals("ta1") && semester.equals("1")) {
                        System.out.println("Enter course code:");
                        String courseCode = scanner.nextLine();
                        System.out.println("Enter grade (4 - 10):");
                        double grade = scanner.nextDouble();

                        String key = studentEmail + "_" + semester + "_" + courseCode;
                        grades.put(key, grade);
                        System.out.println("Grade assigned successfully.");
                        return;
                    }
                    else if(this.Semester.equals("ta3") && semester.equals("3")) {
                        System.out.println("Enter course code:");
                        String courseCode = scanner.nextLine();
                        System.out.println("Enter grade (4 - 10):");
                        double grade = scanner.nextDouble();

                        String key = studentEmail + "_" + semester + "_" + courseCode;
                        grades.put(key, grade);
                        System.out.println("Grade assigned successfully.");
                        return;
                    }
                    else{
                        System.out.println("student not assigned to you");
                        return;
                    }

                }
            }
        }

        double cgpa = 0.0;
        double sgpa = 0.0;
        int totalCredits = 0;
        double totalGradePoints = 0.0;

        if (this.Semester.equals("1")) {
            System.out.println("Grades for Semester 1:");
            for (Course course : enrolledCourses) {
                String key = this.Email + "_1_" + course.getCourseCode();
                if (grades.containsKey(key)) {
                    double grade = grades.get(key);
                    int credits = Integer.parseInt(course.getCredits());
                    totalCredits += credits;
                    totalGradePoints += grade * credits;
                    System.out.println(course.getCourseName() + ": " + grade);
                } else {
                    System.out.println(course.getCourseName() + ": Grade not assigned yet");
                }
            }

            if (totalCredits > 0) {
                sgpa = totalGradePoints / totalCredits;
                cgpa = sgpa;
                System.out.println("Your SGPA for Semester 1 is: " + String.format("%.2f", sgpa));
                System.out.println("Your CGPA is: " + String.format("%.2f", cgpa));
            } else {
                System.out.println("No grades assigned yet.");
            }
        } else if (this.Semester.equals("3")) {
            // Display grades for Semester 1 and 2
            for (int sem = 1; sem <= 2; sem++) {
                System.out.println("\nGrades for Semester " + sem + ":");
                Map<String, Double> semGrades = hardcodedGrades.get(String.valueOf(sem));
                for (Map.Entry<String, Double> entry : semGrades.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                    // Assume all courses have 4 credits for simplicity
                    totalCredits += 4;
                    totalGradePoints += entry.getValue() * 4;
                }
            }

            // Calculate SGPA for Semester 3
            System.out.println("\nGrades for Semester 3:");
            double sem3Credits = 0;
            double sem3GradePoints = 0;
            for (Course course : enrolledCourses) {
                String key = this.Email + "_3_" + course.getCourseCode();
                if (grades.containsKey(key)) {
                    double grade = grades.get(key);
                    int credits = Integer.parseInt(course.getCredits());
                    sem3Credits += credits;
                    sem3GradePoints += grade * credits;
                    System.out.println(course.getCourseName() + ": " + grade);
                } else {
                    System.out.println(course.getCourseName() + ": Grade not assigned yet");
                }
            }

            if (sem3Credits > 0) {
                sgpa = sem3GradePoints / sem3Credits;
                System.out.println("\nYour SGPA for Semester 3 is: " + String.format("%.2f", sgpa));
            } else {
                System.out.println("\nNo grades assigned for Semester 3 yet.");
            }

            // Calculate overall CGPA
            totalCredits += sem3Credits;
            totalGradePoints += sem3GradePoints;
            if (totalCredits > 0) {
                cgpa = totalGradePoints / totalCredits;
                System.out.println("Your overall CGPA is: " + String.format("%.2f", cgpa));
            } else {
                System.out.println("Unable to calculate CGPA. No grades available.");
            }
        }
    }



    public void dropCourse() throws DropDeadlinePassedException {
        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses to drop.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Date deadline;

        try {
            deadline = sdf.parse(DROP_DEADLINE);
        } catch (ParseException e) {
            System.out.println("Error parsing drop deadline. Please contact admin.");
            return;
        }

        if (currentDate.after(deadline)) {
            throw new DropDeadlinePassedException("The course drop deadline has passed.");
        }

        while (true) {
            System.out.println("Enter the course name to drop (or type 'exit' to stop dropping):");
            String dropCourseName = scanner.nextLine();

            if (dropCourseName.equalsIgnoreCase("exit")) {
                break;
            }

            boolean courseFound = false;
            for (Course course : enrolledCourses) {
                if (course.courseName.equals(dropCourseName)) {
                    enrolledCourses.remove(course);
                    System.out.println("Dropped course: " + course.courseName);
                    courseFound = true;
                    break;
                }
            }

            if (!courseFound) {
                System.out.println("Course not found. Please try again.");
            }

            if (enrolledCourses.isEmpty()) {
                System.out.println("You have no more enrolled courses.");
                break;
            }
        }
    }

    public void GetComplaints(ArrayList<Complaint> complaints) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your complaint:");
        String complaintText = scanner.nextLine();

        // Register the complaint with the student's email
        Complaint newComplaint = new Complaint(complaintText, this.Email);  // Accessing Email directly
        complaints.add(newComplaint);

        System.out.println("Your complaint has been registered successfully.");
    }





    public void printCourseDetailsByProfessor(String professorName, Map<String, ArrayList<Course>> semesterCourses) {
        for (Map.Entry<String, ArrayList<Course>> entry : semesterCourses.entrySet()) {
            ArrayList<Course> courses = entry.getValue();

            for (Course course : courses) {
                if (course.getProfessor().equalsIgnoreCase(professorName)) {
                    System.out.println("Details of course assigned to you: ");
                    System.out.println("Course Code: " + course.getCourseCode());
                    System.out.println("Course Name: " + course.getCourseName());
                    System.out.println("Credits: " + course.getCredits());
                    System.out.println("Schedule: " + course.getSchedule());
                    System.out.println("Room: " + course.getRoom());
                    System.out.println("Prerequisites: " + course.getPrerequisites());
                    System.out.println("--------------------------------");
                }
            }
        }
    }


    public void displayCourseRatings(ArrayList<GenericPair<String, Integer>> ratingsforcourse) {
        if (ratingsforcourse.isEmpty()) {
            System.out.println("No ratings available.");
        } else {
            System.out.println("Course Ratings:");
            for (GenericPair<String, Integer> rating : ratingsforcourse) {
                System.out.println("Course: " + rating.getValue() + ", Rating: " + rating.getotherrValue() + "/5");
            }
        }
    }

    public void updateCourseDetails(String professorName, Map<String, ArrayList<Course>> semesterCourses) {
        Scanner bb = new Scanner(System.in);

        ArrayList<Course> profCourses = new ArrayList<>();
        for (ArrayList<Course> courses : semesterCourses.values()) {
            for (Course course : courses) {
                if (course.getProfessor().equalsIgnoreCase(professorName)) {
                    profCourses.add(course);
                }
            }
        }

        if (profCourses.isEmpty()) {
            System.out.println("No courses found for Professor " + professorName);
            return;
        }

        System.out.println("Courses taught by " + professorName + ":");
        for (Course course : profCourses) {
            System.out.println(course.getCourseCode() + ": " + course.getCourseName());
        }

        System.out.println("Enter the course code of the course you want to update:");
        String courseCode = bb.nextLine();

        Course selectedCourse = null;
        for (Course course : profCourses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse == null) {
            System.out.println("Course not found.");
            return;
        }

        while (true) {
            System.out.println("What do you want to update?");
            System.out.println("1. Course Name");
            System.out.println("2. Credits");
            System.out.println("3. Time");
            System.out.println("4. Place/Room");
            System.out.println("5. Exit");
            String choice = bb.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter new course name:");
                    String newName = bb.nextLine();
                    selectedCourse.setCourseName(newName);
                    System.out.println("Course name updated to " + newName);
                    break;
                case "2":
                    System.out.println("Enter new credits:");
                    String newCredits = bb.nextLine();
                    selectedCourse.setCredits(newCredits);
                    System.out.println("Credits updated to " + newCredits);
                    break;
                case "3":
                    System.out.println("Enter new schedule (e.g., Mon/Wed 10:00-11:30):");
                    String newTime = bb.nextLine();
                    selectedCourse.setSchedule(newTime);
                    System.out.println("Schedule updated to " + newTime);
                    break;
                case "4":
                    System.out.println("Enter new place/room:");
                    String newRoom = bb.nextLine();
                    selectedCourse.setRoom(newRoom);
                    System.out.println("Place/Room updated to " + newRoom);
                    break;
                case "5":
                    System.out.println("Exiting course update.");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
