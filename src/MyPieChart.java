import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;

public class MyPieChart {
    private DecimalFormat df;
    private Color[] colors = {Color.RED,Color.ORANGERED,Color.BLUE,Color.PURPLE,Color.CORNFLOWERBLUE,Color.CADETBLUE,Color.CORAL};
    private int totalGrades;
    private HashMap<Character, Double> grades;

    public MyPieChart(Connection conn, String tableName, String col, String colVal) {
        df = new DecimalFormat("#.#####");
        totalGrades = 0;
        grades = fillHashMap(conn, tableName, col, colVal);
    }

    public HashMap<Character, Double> fillHashMap(Connection conn, String tableName, String col, String colVal) {
        HashMap<Character, Double> hm = new HashMap<Character, Double>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rSet = stmt.executeQuery(("SELECT * FROM "+ tableName +" WHERE " +col+"='"+colVal+"';"));
            int colNum = 6;

            while (rSet.next()) {
                String column = rSet.getString(colNum);
                char i = column.charAt(0);
                Double j = hm.get(i);
                hm.put(i, (j == null) ? 1 : j + 1);
            }

            for (double t : hm.values()) {
                this.totalGrades += t;
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
        return hm;
    }

    public void draw() {
        double width = 900, height = 700;
        int rectX = 650, rectY = 30, textX = 710, textY = 50, counter = 0;
        double startAngle = 0.0, sumProb = 0.0, arcAngle = 0.0;
        Group root = new Group();
        Canvas canvas = new Canvas(width,height);
        Stage primaryStage = new Stage();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(Font.getDefault());
        Button doneButton = new Button("Done.");

        for (HashMap.Entry<Character, Double> key : grades.entrySet()){
            startAngle = sumProb * 360;
            arcAngle = ((key.getValue() / totalGrades) * 360);
            gc.setFill(colors[counter++]);
            gc.fillArc(60, 100, 400, 400, startAngle, arcAngle, ArcType.ROUND);
            gc.fillText("= " + key.getKey() + ", " + df.format(key.getValue() / totalGrades), textX, textY);
            textY += 40;
            rectY += 40;
            sumProb += (key.getValue() / totalGrades);
        }

        primaryStage.setTitle("Pie Chart.");
        root.getChildren().addAll(canvas, doneButton);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        doneButton.setOnAction(click -> {
            primaryStage.close();
        });
    }
}