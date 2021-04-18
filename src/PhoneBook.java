import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        ArrayList<Abonent> abonents = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/first_lesson";
        String user = "root";
        String password = "password";
        String line = "";

        Class.forName("java.sql.Driver");


        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stat = conn.createStatement();
             BufferedReader fileSQL = new BufferedReader(new FileReader("/home/alexander/Рабочий стол/trash/untitled/src/PhoneBook.sql"));// введите путь к командному файлу
             Scanner scan = new Scanner(fileSQL)) {
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                if (line.endsWith(";"))
                    line = line.substring(0, line.length() - 1);
                stat.executeUpdate(line);
            }

            // Создаем объект ResultSet и пробегаем по результирующему набору с передачей данных в конструктор
            ResultSet resultSet = null;

            try {
                resultSet = stat.executeQuery("SELECT * FROM Phoens");
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    int phone = resultSet.getInt(3);
                    abonents.add(new Abonent(id, name, phone));
                }

                // Выводим записи, соответствующие условию заданияtry {
                resultSet = stat.executeQuery("SELECT * FROM Phoens");
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    int phone = resultSet.getInt(3);
                    abonents.add(new Abonent(id, name, phone));
                }

                for (Abonent a : abonents) {
                    if (a.getId() > 3)
                        System.out.println(a);
                }

            } catch (SQLException ex) {
                System.err.println("SQLException message: " + ex.getMessage());
                System.err.println("SQLException SQL state: " + ex.getSQLState());
                System.err.println("SQLException SQL error code: " + ex.getErrorCode());

                // Освобождаем ресурсы затраченные на ResultSet
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                } else {
                    System.err.println("Ошибка чтения данных с БД");
                }
            }
        }
    }
}
