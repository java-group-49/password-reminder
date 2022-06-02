package dao;

import model.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbRequests {

    public static List<Record> getAllRecords() {
        List<Record> out = new ArrayList<>();
        String sql = "SELECT * FROM resources";

        try (Connection connection = DbManagement.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String source = resultSet.getString(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                out.add(new Record(source, login, password));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return out;
    }

    public static void save(Record record) {
        String sql = "INSERT INTO resources (source, login, password) VALUES (?, ?, ?)";

        try (Connection connection = DbManagement.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, record.getResource());
            preparedStatement.setString(2, record.getLogin());
            preparedStatement.setString(3, record.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Record getByResource(String resource) {
        String sql = "SELECT * FROM resources WHERE source = ?";

        try (Connection connection = DbManagement.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setString(1, resource);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.first())
                return null;

            String source = resultSet.getString(1);
            String login = resultSet.getString(2);
            String password = resultSet.getString(3);

            return new Record(source, login, password);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static boolean remove(String resource) {
        String sql = "DELETE FROM resources WHERE source = ?";

        try (Connection connection = DbManagement.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, resource);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            return resultSet.next();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static void update(Record record) {
        String sql = "UPDATE resources SET login = ?, password = ? WHERE source = ?";

        try (Connection connection = DbManagement.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, record.getLogin());
            preparedStatement.setString(2, record.getPassword());
            preparedStatement.setString(3, record.getResource());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
