import java.util.ArrayList;

public class TeachingAssistant extends Student  {
    private ArrayList<Course> assignedCourses;

    public TeachingAssistant(String email, String password, String semester) {
        super(email, password, semester);
        this.assignedCourses = new ArrayList<>();
    }

    public void assignToCourse(Course course) {
        assignedCourses.add(course);
    }

    public void viewAssignedCourses() {
        if (assignedCourses.isEmpty()) {
            System.out.println("You are not assigned to any courses as a TA.");
        } else {
            System.out.println("Courses you are assigned to as a TA:");
            for (Course course : assignedCourses) {
                System.out.println(course);
            }
        }
    }
    @Override
    public String toString() {
        return "Teaching Assistant: " + this.Email + ", Semester: " + this.Semester;
    }
}
