package info.sjd.dao;

import info.sjd.model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    public static Cart create(Cart cart){

        String sql = "INSERT INTO carts (time, closed, user_id) VALUES (?,?,?)";

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, cart.getCreationTime());
            statement.setBoolean(2, cart.getClosed());
            statement.setInt(3, cart.getUserId());

            statement.executeUpdate();

            cart.setCartId(getCartByUserId(cart.getUserId()).getCartId());

            return cart;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Cart fingByCartId(Integer cartId){
        String sql = "SELECT *FROM carts WHERE cart_id=?";

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            Cart cart = new Cart();

            while (resultSet.next()){
                cart.setCartId(resultSet.getInt("cart_id"));
                cart.setCreationTime(resultSet.getLong("time"));
                cart.setUserId(resultSet.getInt("user_id"));
                cart.setClosed(resultSet.getBoolean("closed"));
            }
            return cart;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Cart> findAll() {
        String sql = "SELECT * FROM carts";
        List<Cart> carts = new ArrayList<Cart>();
        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Cart cart = new Cart();
                cart.setCartId(resultSet.getInt("cart_id"));
                cart.setCreationTime(resultSet.getLong("time"));
                cart.setUserId(resultSet.getInt("user_id"));
                cart.setClosed(resultSet.getBoolean("closed"));
                carts.add(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    public static List<Cart> findAllByPeriod(Long start, Long end){

        String sql = "SELECT cart_id, time, closed, user_id" +
                "FROM carts WHERE time>? AND time<?";
        List<Cart> carts = new ArrayList<Cart>();

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setLong(1, start);
            statement.setLong(2, end);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Cart cart = new Cart();
                cart.setCartId(resultSet.getInt("cart_id"));
                cart.setCreationTime(resultSet.getLong("time"));
                cart.setUserId(resultSet.getInt("user_id"));
                cart.setClosed(resultSet.getBoolean("closed"));
                carts.add(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    public static Cart getCartByUserId(Integer userId) {

        String sql = "SELECT * FROM carts WHERE user_id=?";

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.setCartId(resultSet.getInt("cart_id"));
                cart.setCreationTime(resultSet.getLong("time"));
                cart.setUserId(resultSet.getInt("user_id"));
                cart.setClosed(resultSet.getBoolean("closed"));
                return cart;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Cart update(Cart cart) {
        String sql = "UPDATE carts SET time=?, user_id=?, closed=? WHERE cart_id=?";

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setLong(1, cart.getCreationTime());
            statement.setInt(2, cart.getUserId());
            statement.setBoolean(3, cart.getClosed());
            statement.setInt(4, cart.getCartId());

            statement.executeUpdate();

            return cart;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Cart cart) {
        String sql = "DELETE FROM carts WHERE cart_id=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, cart.getCartId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
