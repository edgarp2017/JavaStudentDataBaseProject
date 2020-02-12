import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Main extends Application {
    private Connection con;
    private String url = "jdbc:mysql://127.0.0.1:3306/students";
    private String user = "root";
    private String password = "password";

    public void getConnection() throws Exception {
        try { this.con = DriverManager.getConnection(this.url, this.user, this.password); }
        catch (SQLException e) { e.printStackTrace(); }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        double width = 900, height = 700, buttonWidth = 400, buttonHeight = 30;
        getConnection();

        primaryStage.setTitle("Data Base Project.");
        BorderPane root = new BorderPane();

        Button studentButton = new Button("Add Student.");
        Button coursesButton = new Button("Add Courses.");
        Button classesButton = new Button("Add Classes.");
        Button drawButton = new Button("Draw Chart.");
        Button tableButton = new Button("Table.");

        FlowPane buttonPane = new FlowPane(studentButton, coursesButton, classesButton, drawButton, tableButton);
        BorderPane.setMargin(buttonPane, new Insets(1,1,1,1));
        root.setBottom(buttonPane);

        drawButton.setOnAction(action -> {
            MyPieChart pieChart = new MyPieChart(this.con,"classes","courseID","csc211");
            pieChart.draw();
        });

        studentButton.setOnAction(action -> {
            BorderPane root2 = new BorderPane();
            Scene myScene2 = new Scene(root2,width/2, height/4);
            Stage secondStage = new Stage();
            secondStage.setTitle("Student Form.");
            secondStage.setScene(myScene2);
            secondStage.show();
            Button submitButton = new Button("Add.");

            Label firstNameLabel = new Label("Student Name: ");
            Label lastNameLabel = new Label("Last Name: ");
            Label sexLabel = new Label ("Sex: ");
            Label studentIDLabel = new Label ("Student ID: ");

            TextField firstNameTextField = new TextField();
            TextField lastNameTextField = new TextField();
            TextField sexTextField = new TextField();
            TextField studentIDTextField = new TextField();

            FlowPane newButtonPane = new FlowPane(submitButton);
            FlowPane inputPane = new FlowPane(firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField,
                    sexLabel, sexTextField, studentIDLabel, studentIDTextField);
            root2.setTop(newButtonPane);
            root2.setCenter(inputPane);

            submitButton.setOnAction(click -> {
                String nameInput = firstNameTextField.getText().trim();
                String lastNameInput = lastNameTextField.getText().trim();
                String sexInput = sexTextField.getText().trim();
                String studentIdInput = studentIDTextField.getText().trim();

                try{
                    Students student = new Students(Integer.parseInt(studentIdInput), nameInput, lastNameInput, sexInput.charAt(0));
                    if(student.insertStudentData(this.con))
                        secondStage.close();
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Check your input");
                        alert.setHeaderText("Invalid input");
                        alert.show();
                    }
                } catch (Exception e) {
                    System.out.println("Error.");
                }
            });
        });

        coursesButton.setOnAction(action -> {
            BorderPane root2 = new BorderPane();
            Scene myScene2 = new Scene(root2, width/2, height/4);
            Stage secondStage = new Stage();
            secondStage.setTitle("Courses Form.");
            secondStage.setScene(myScene2);
            secondStage.show();
            Button submitButton = new Button("Add.");

            Label courseIDLabel = new Label("Course ID: ");
            Label courseTitleLabel = new Label("Course Title: ");
            Label departmentLabel = new Label ("Department: ");

            TextField courseIDTextField = new TextField();
            TextField courseTitleTextField = new TextField();
            TextField departmentTextField = new TextField();
            FlowPane newButtonPane = new FlowPane(submitButton);
            FlowPane inputPane = new FlowPane(courseIDLabel, courseIDTextField, courseTitleLabel, courseTitleTextField,
                    departmentLabel, departmentTextField);
            root2.setTop(newButtonPane);
            root2.setCenter(inputPane);

            submitButton.setOnAction(click -> {
                String courseIDInput = courseIDTextField.getText().trim();
                String courseTitleInput = courseTitleTextField.getText().trim();
                String departmentInput = departmentTextField.getText().trim();

                try{
                    Courses course = new Courses(courseIDInput, courseTitleInput, departmentInput );
                    if(course.insertCoursesData(this.con))
                        secondStage.close();
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Check your input");
                        alert.setHeaderText("Invalid input");
                        alert.show();
                    }
                } catch (Exception e) {
                    System.out.println("Error.");
                }
            });
        });

        classesButton.setOnAction(action -> {
            BorderPane root2 = new BorderPane();
            Scene myScene2 = new Scene(root2, width/2, height/4);
            Stage secondStage = new Stage();
            secondStage.setTitle("Classes Form.");
            secondStage.setScene(myScene2);
            secondStage.show();
            Button submitButton = new Button("Add.");

            Label classCodeLabel = new Label("Class Code: ");
            Label courseIDLabel = new Label("Course ID: ");
            Label studentIDLabel = new Label ("Student ID: ");
            Label yearLabel = new Label ("year: ");
            Label semesterLabel = new Label("semester: ");
            Label gpaLabel = new Label("gpa ");

            TextField classCodeTextField = new TextField();
            TextField courseIDTextField = new TextField();
            TextField studentIDTextField = new TextField();
            TextField yearTextField = new TextField();
            TextField semesterTextField = new TextField();
            TextField gpaTextField = new TextField();

            FlowPane newButtonPane = new FlowPane(submitButton);
            FlowPane inputPane = new FlowPane(classCodeLabel, classCodeTextField, courseIDLabel, courseIDTextField,
                    studentIDLabel, studentIDTextField, yearLabel, yearTextField, semesterLabel, semesterTextField,
                    gpaLabel, gpaTextField);
            root2.setTop(newButtonPane);
            root2.setCenter(inputPane);

            submitButton.setOnAction(click -> {
                String classCodeInput = classCodeTextField.getText().trim();
                String courseIDInput = courseIDTextField.getText().trim();
                String studentIDInput = studentIDTextField.getText().trim();
                String yearInput = yearTextField.getText().trim();
                String semesterInput = semesterTextField.getText().trim();
                String gpaInput = gpaTextField.getText().trim();

                try{
                    Classes addClass = new Classes(Integer.parseInt(classCodeInput), courseIDInput, Integer.parseInt(studentIDInput),
                            Integer.parseInt(yearInput), semesterInput, gpaInput.charAt(0));
                    if(addClass.insertClassesData(this.con))
                        secondStage.close();
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Check your input");
                        alert.setHeaderText("Invalid input");
                        alert.show();
                    }
                } catch (Exception e) {
                    System.out.println("Error.");
                }
            });
        });

        tableButton.setOnAction(click -> {
            try{
                Statement stmt = this.con.createStatement();

                ResultSet rSet = stmt.executeQuery(("SELECT * FROM classes WHERE  courseID='CSC211';"));
                ResultSetMetaData rsmd = rSet.getMetaData();
                int numColumns = rsmd.getColumnCount();
                String resultString = null;
                while (rSet.next())
                {
                    resultString = "";
                    int colNum = 3;
                    System.out.print(rSet.getString(colNum)+"\t");

                    colNum = 6;
                    String column = rSet.getString(colNum);
                    char i = column.charAt(0);
                    System.out.print(i);

                    System.out.println(resultString + '\n');
                }
            } catch (SQLException e) { e.printStackTrace(); }
        });

        Scene myScene = new Scene(root, buttonWidth, buttonHeight);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}