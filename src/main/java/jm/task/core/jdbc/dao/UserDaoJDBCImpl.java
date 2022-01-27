package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS schema_113.user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name varchar(100), lastName varchar(255), age smallint)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS schema_113.user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age){
        try (Connection connection = Util.getConnection(); PreparedStatement pst = connection.prepareStatement("INSERT INTO schema_113.user (name, lastName, age) VALUES (?,?,?)")) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.executeUpdate();
            System.out.println("User с именем – "+ name +" добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
          try (Connection connection = Util.getConnection(); PreparedStatement pst = connection.prepareStatement("DELETE FROM schema_113.user WHERE id=?")) {
              pst.setLong(1,id);
          } catch (SQLException e) {
              e.printStackTrace();
          }
        }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("SELECT * FROM schema_113.user");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                list.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM schema_113.user WHERE id>0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
