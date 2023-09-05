package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255), " +
                "lastName VARCHAR(255), " +
                "age TINYINT)";

        try (Statement statement = getConnection().createStatement()) {

            statement.executeUpdate(createTableSQL);
            System.out.println("Таблица создана");
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String dropTableSQL = "DROP TABLE IF EXISTS users";

        try (Statement statement = getConnection().createStatement()) {

            statement.executeUpdate(dropTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) {

        String saveUserSQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(saveUserSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в таблицу");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public void removeUserById(long id) {

        String removeUserSQL = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(removeUserSQL)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectAllUsersSQL = "SELECT * FROM users";

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllUsersSQL)) {

            System.out.print("Все пользователи из базы:");

            while (resultSet.next()) {

                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        String cleanTableSQL = "DELETE FROM users";

        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(cleanTableSQL);
            System.out.println("Таблица пользователей очищена.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
