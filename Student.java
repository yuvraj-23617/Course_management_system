public class Student extends User {
    public Student(String email, String password, String semester) {
        super(email, password, semester);
    }

    @Override
    public String toString() {
        return "Student: " + this.Email + ", Semester: " + this.Semester;
    }
}
