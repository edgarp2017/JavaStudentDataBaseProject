import java.sql.Connection;
import java.sql.PreparedStatement;

public class Students {
    private int studentID;
    private String firstName;
    private String lastName;
    private char sex;

    public Students(int id, String first, String last, char sex) {
        this.studentID = id;
        this.firstName = first;
        this.lastName = last;
        this.sex= sex;
    }

    private int getStudentID() {
        return this.studentID;
    }

    private String getFirstName() {
        return this.firstName;
    }

    private String getLastName() {
        return this.lastName;
    }

    private char getSex() {
        return this.sex;
    }

    public boolean insertStudentData(Connection con) throws Exception {
        try {
            String query = "INSERT INTO Students" + " values ('" + getStudentID() + "','" + getFirstName() + "','" + getLastName() + "','" + getSex() + "')";
            PreparedStatement insert = con.prepareStatement(query);
            insert.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
