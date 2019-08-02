package info.sjd.service;

import au.com.bytecode.opencsv.CSVWriter;
import info.sjd.dao.ConnectionToDB;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class CSVFileManagerService {

    private static final String MAIN_DIR = System.getProperty("user.dir");
    private static final String SEPARATOR = System.getProperty("file.separator");

    static String filePath = MAIN_DIR + SEPARATOR + "data" + SEPARATOR + "cart.csv";

    public static synchronized void recordFile(boolean append) {

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));
             Connection connection = ConnectionToDB.getConnection();
             Statement statement = connection.createStatement();) {

            String lineSeparator = System.getProperty("line.separator");

            ResultSet results = statement.executeQuery("SELECT users.name, users.password, products.item_id, products.product_url, " +
                    "carts.time FROM users JOIN carts ON users.user_id=carts.cart_id JOIN orders ON carts.cart_id=orders.cart_id " +
                    "JOIN products ON orders.product_id=products.product_id ");

            writer.writeAll(results, true);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
