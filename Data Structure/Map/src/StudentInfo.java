public class StudentInfo {

    private String studentID;
    private String studentName;

    StudentInfo(String id, String name){
        this.studentID = id;
        this.studentName = name;
    }

    public void setStudentID(String id) { this.studentID = id; }
    public void setStudentName(String name) { this.studentName = name; }
    public String getStudentID() { return this.studentID; }
    public String getStudentName() { return this.studentName; }
}
