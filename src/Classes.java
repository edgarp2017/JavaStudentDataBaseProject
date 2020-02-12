import java.sql.Connection;
import java.sql.PreparedStatement;

public class Classes {
    private int classCode;
    private String courseID;
    private int studentID;
    private int year;
    private String semester;
    private char gpa;

    public Classes(int classCode, String courseID, int studentID, int year, String semester, char gpa) {
        setClassCode(classCode);
        setCourseID(courseID);
        setStudentID(studentID);
        setYear(year);
        setSemester(semester);
        setGPA(gpa);
    }

    public void setClassCode(int classCode) { this.classCode = classCode; }

    public void setCourseID(String id) { this.courseID = id; }

    public void setStudentID(int id) { this.studentID = id; }

    public void setYear(int year) { this.year = year; }

    public void setSemester(String classCode) { this.semester = classCode; }

    public void setGPA(char gpa) { this.gpa = gpa; }

    public int getClassCode() { return this.classCode; }

    public String getCourseID() { return this.courseID; }

    public int getStudentID() { return this.studentID; }

    public int getYear() { return this.year; }

    public String getSemester() { return this.semester; }

    public char getGPA() { return this.gpa; }

    public boolean insertClassesData(Connection con) throws Exception {
        try {
            String sql = "INSERT INTO Classes VALUES ('" + getClassCode() + "', '" + getCourseID() + "', '" + getStudentID() + "', '" +
                    getYear() + "', '" + getSemester() +  "', '" + getGPA() +  "')";
            PreparedStatement insert = con.prepareStatement(sql);
            insert.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
