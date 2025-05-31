import java.sql.*;

public class Next {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:";  // H2 in-memory database
        try (Connection conn = DriverManager.getConnection(url)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
