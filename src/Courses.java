import java.sql.Connection;
import java.sql.PreparedStatement;

public class Courses {
    private String courseID;
    private String courseTitle;
    private String department;

    public Courses(String id, String title, String department) {
        setCourseID(id);
        setCourseTitle(title);
        setDepartment(department);
    }

    public void setCourseID(String id){ this.courseID = id; }

    public void setCourseTitle(String title){ this.courseTitle = title; }

    public void setDepartment(String department) { this.department = department; }

    public String getCourseID() { return this.courseID; }

    public String getCourseTitle() { return this.courseTitle; }

    public String getDepartment() {return this.department; }

    public boolean insertCoursesData(Connection con) throws Exception {
        try {
            String sql = "INSERT INTO Courses VALUES ('" + getCourseID() + "', '" + getCourseTitle() + "', '" + getDepartment() + "')";
            PreparedStatement insert = con.prepareStatement(sql);
            insert.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
