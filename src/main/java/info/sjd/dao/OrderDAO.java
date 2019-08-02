package info.sjd.dao;

import info.sjd.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public static Order create(Order order) {

        String sql = "INSERT INTO orders (product_id, amount, cart_id) VALUES (?,?,?)";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, order.getProductId());
            statement.setInt(2, order.getAmount());
            statement.setInt(3, order.getCartId());

            statement.executeUpdate();

            order.setOrderId(findByCartId(order.getCartId()).getOrderId());

            return order;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Order findByOrderId(Integer orderId) {
        String sql = "SELECT * FROM orders WHERE order_id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            Order order = new Order();

            while (resultSet.next()) {
                order.setOrderId(resultSet.getInt("order_id"));
                order.setProductId(resultSet.getInt("product_id"));
                order.setAmount(resultSet.getInt("amount"));
                order.setCartId(resultSet.getInt("cart_id"));
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Order findByCartId(Integer cartId) {
        String sql = "SELECT * FROM orders WHERE cart_id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            Order order = new Order();

            while (resultSet.next()) {
                order.setOrderId(resultSet.getInt("order_id"));
                order.setProductId(resultSet.getInt("product_id"));
                order.setAmount(resultSet.getInt("amount"));
                order.setCartId(resultSet.getInt("cart_id"));
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Order> findAll(){
        String sql = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<Order>();

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setProductId(resultSet.getInt("product_id"));
                order.setAmount(resultSet.getInt("amount"));
                order.setCartId(resultSet.getInt("cart_id"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static Integer getSumByUserAndPeriod(Integer userId, Long start, Long end) {
        String sql = "SELECT SUM(products.price*orders.amount) AS results FROM orders " +
                "JOIN carts ON orders.cart_id=carts.cart_id " +
                "JOIN products ON orders.product_id=products.product_id " +
                "WHERE carts.creation_time>? " +
                "AND  carts.creation_time<? " +
                "AND carts.user_id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setLong(1, start);
            statement.setLong(2, end);
            statement.setInt(3, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt("results");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Order update(Order order){
        String sql = "UPDATE orders SET product_id=?, amount=?, cart_id=? WHERE order_id=?";

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, order.getOrderId());
            statement.setInt(2, order.getProductId());
            statement.setInt(3, order.getAmount());
            statement.setInt(4, order.getCartId());

            statement.executeUpdate();

            return order;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Order order){
        String sql = "DELETE FROM orders WHERE order_id=?";

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, order.getOrderId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
