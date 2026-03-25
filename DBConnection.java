import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "1234"
            );

            System.out.println("Connected Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
