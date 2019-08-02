package info.sjd.dao;

import info.sjd.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static User create (User user){

        String sql = "INSERT INTO users (name, password, email) VALUES (?,?,?)";

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());

            statement.executeUpdate();

            user.setUserId(findByEmail(user.getEmail()).getUserId());

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User findByEmail(String email){
        String sql = "SELECT * FROM users WHERE email=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();

            while (resultSet.next()){
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User findById(Integer userId){
        String sql = "SELECT * FROM users WHERE user_id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();

            while (resultSet.next()){
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User findByName(String name){
        String sql = "SELECT * FROM users WHERE name=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();

            while (resultSet.next()){
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User findByNameAndPassword(String name, String password){
        String sql = "SELECT * FROM users WHERE name=? AND password=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();

            while (resultSet.next()){
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> findAll(){
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<User>();

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<User> findAllByPeriod(Long start, Long end){
        String sql = "SELECT password, name, email" +
                "FROM users u JOIN carts c ON u.user_id=c.user_id" +
                "WHERE c.time>? AND c.time<?";
        List<User> users = new ArrayList<User>();

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setLong(1, start);
            statement.setLong(2, end);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                User user = new User();
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User update(User user){
        String sql = "UPDATE users SET name=?, password=?, email=? WHERE user_id=?";

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getUserId());

            statement.executeUpdate();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(User user){
        String sql = "DELETE FROM users WHERE user_id=?";

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, user.getUserId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
