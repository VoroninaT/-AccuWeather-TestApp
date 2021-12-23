package homework8;

import org.sqlite.JDBC;

import java.sql.*;

public class DbHandler {
    private final String PATH_TO_DB = "jdbc:sqlite:src/main/resources/cityweather.db";
    private Connection connection;


    public DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(PATH_TO_DB);
    }

    public void addCity(City city) {
        String sql = "INSERT INTO cityweather('name', 'localDate', 'weather', 'temperature') VALUES(?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getLocalDate());
            preparedStatement.setString(3, city.getWeather());
            preparedStatement.setDouble(4, city.getTemperature());

            int rows = preparedStatement.executeUpdate();
            if (rows == 1) {
                System.out.printf("Город %s записан в БД", city.getName());
                System.out.println();
            } else {
                System.out.printf("Проблема записи города %s в БД", city.getName());
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void printTable() {
        String sql = "SELECT * FROM cityweather ORDER BY id";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String date = resultSet.getString(3);
                String weather = resultSet.getString(4);
                String temperature = resultSet.getString(5);

                System.out.printf("%d %s %s %s %s \n", id, name, date, weather, temperature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
