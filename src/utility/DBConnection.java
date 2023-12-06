package utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection conn = null;

    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");

            //При мен работи. Не съм я вързал към GUI. Трябва да си промениш пътя

            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/\\java\\Proekt_Razpredeleni\\Database", "sa", "123");
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
}