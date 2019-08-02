package info.sjd.dao;

import info.sjd.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public static Item create(Item item){
        String sql = "INSERT INTO products (product_name, price, product_url, image_url, item_id) " +
                "VALUES (?,?,?,?,?)";
        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setString(3, item.getUrl());
            statement.setString(4, item.getImageUrl());
            statement.setInt(5, Integer.parseInt(item.getItemId()));

            statement.executeUpdate();

            item.setProductId(findByItemId(Integer.parseInt(item.getItemId())).getProductId());

            return item;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Item findByItemId(Integer itemId){
        String sql = "SELECT *FROM products WHERE item_id=?";
        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            Item item = new Item();

            while (resultSet.next()){
                item.setProductId(resultSet.getInt("product_id"));
                item.setName(resultSet.getString("product_name"));
                item.setPrice(resultSet.getInt("price"));
                item.setUrl(resultSet.getString("product_url"));
                item.setImageUrl(resultSet.getString("image_url"));
                item.setItemId(resultSet.getString("item_id"));
            }
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Item findByProductId(Integer productId){
        String sql = "SELECT *FROM products WHERE product_id=?";
        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            Item item = new Item();

            while (resultSet.next()){
                item.setProductId(resultSet.getInt("product_id"));
                item.setName(resultSet.getString("product_name"));
                item.setPrice(resultSet.getInt("price"));
                item.setUrl(resultSet.getString("product_url"));
                item.setImageUrl(resultSet.getString("image_url"));
                item.setItemId(resultSet.getString("item_id"));
            }
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Item> findAll(){
        String sql = "SELECT * FROM products";
        List<Item> items = new ArrayList<Item>();

        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Item item = new Item();
                item.setProductId(resultSet.getInt("product_id"));
                item.setName(resultSet.getString("product_name"));
                item.setPrice(resultSet.getInt("price"));
                item.setUrl(resultSet.getString("product_url"));
                item.setImageUrl(resultSet.getString("image_url"));
                item.setItemId(resultSet.getString("item_id"));

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static Item update(Item item){
        String sql = "UPDATE products SET product_name=?, price=?, product_url=?, image_url=?, item_id=? " +
                "WHERE product_id=?";
        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());;
            statement.setString(3, item.getUrl());
            statement.setString(4, item.getImageUrl());
            statement.setInt(5, Integer.parseInt(item.getItemId()));
            statement.setInt(6, item.getProductId());

            statement.executeUpdate();

            return item;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Item item) {
        String sql = "DELETE FROM products WHERE product_id=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, item.getProductId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
