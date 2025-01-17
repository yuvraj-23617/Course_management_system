public class Course {
    public String courseCode;
    public String courseName;
    public String ProfName;
    public String Credit;
    public String Time;
    public String Place;
    public String Prerequisites;


    public Course(String courseCode, String courseName, String ProfName, String Credit, String Time, String Place, String Prerequisites) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.ProfName = ProfName;
        this.Credit = Credit;
        this.Time = Time;
        this.Place = Place;
        this.Prerequisites = Prerequisites;
    }

    // Getter methods
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public String getProfessor() { return ProfName; }
    public String getCredits() { return Credit; }
    public String getSchedule() { return Time; }
    public String getRoom() { return Place; }
    public String getPrerequisites() { return Prerequisites; }

    public String toString() {
        return courseCode + ": " + courseName + ", Professor: " + ProfName + ", Credits: " + Credit + ", Time: " + Time + ", Place: " + Place;
    }

    // Add setters in the Course class
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredits(String credits) {
        this.Credit = credits;
    }

    public void setSchedule(String time) {
        this.Time = time;
    }

    public void setRoom(String room) {
        this.Place = room;
    }

}
