public class Complaint {
    private static int nextId = 1;
    private int id;
    private String description;
    private String status;
    private String studentEmail;

    public Complaint(String description, String studentEmail) {
        this.id = nextId++;
        this.description = description;
        this.status = "Pending";
        this.studentEmail = studentEmail;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    @Override
    public String toString() {
        return "Complaint #" + id + ": " + description + " (Status: " + status + ") - Filed by: " + studentEmail;
    }
}