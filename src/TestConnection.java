import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class TestConnection {


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String user = "root";
        String password = "password";


        Class.forName("java.sql.Driver");


        try (Connection conn = DriverManager.getConnection(url, user, password);
             BufferedReader sqlfile = new BufferedReader(new FileReader("/home/alexander/Рабочий стол/trash/untitled/src/Books.sql"));
             Scanner scan = new Scanner(sqlfile);
             Statement statement = conn.createStatement()) {
            String line = "";
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                if (line.endsWith(";")) {
                    line = line.substring(0, line.length() - 1);
                }
                statement.executeUpdate(line);
            }

            ResultSet rs = null;
            try {
                rs = statement.executeQuery("SELECT * FROM books");
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    Double price = rs.getDouble(3);
                    System.out.println("ID = " + id + " Name = " + name + " Price = " + price);

                }
            } catch (SQLException ex) {
                System.err.println("SQLexeption message: " + ex.getMessage());
                System.err.println("SQLexeption SQL state: " + ex.getSQLState());
                System.err.println("SQLexeption error code: " + ex.getErrorCode());
            } finally {
                if (rs != null) {
                    rs.close();
                } else {
                    System.out.println("Err read");
                }
            }

        }


    }
}
