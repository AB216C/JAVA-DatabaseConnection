import java.sql.*;
public class JDBCtest {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:";

        try(Connection conn = DriverManager.getConnection(url)){
            Statement stmt = conn.createStatement();

            //Create table

            String createTableSQL = "CREATE TABLE users(id INT PRIMARY KEY, name VARCHAR(100))";
            stmt.executeUpdate(createTableSQL);

            Person person1= new Person(1,"Briana");
            Person person2 = new Person(2,"Nema");

            //OUR SQL statement that we need to modify into a prepared statement
            //It takes 2 parameters that we represent with "?"
            String insertDataSQL = "INSERT INTO users (id,name) VALUES(?,?)";

            PreparedStatement insertStmt = conn.prepareStatement(insertDataSQL);

            insertStmt.setInt(1,person1.getId());
            insertStmt.setString(2,person1.getName());
            //Execute our statement for the first created user
            insertStmt.executeUpdate();

            insertStmt.setInt(1, person2.getId());
            insertStmt.setString(2, person2.getName());

            //Execute our statement for the second created user
            insertStmt.executeUpdate();

            String query = "SELECT * FROM users";
            ResultSet rs=stmt.executeQuery(query);

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + "_" + name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
