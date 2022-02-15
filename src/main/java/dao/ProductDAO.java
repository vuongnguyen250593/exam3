package dao;

import connection.ConnectToDB;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ProductDAO {
    private final ConnectToDB connectDB = new ConnectToDB();

    public ArrayList<Product> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from product");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            String color = resultSet.getString("color");
            String description = resultSet.getString("description");
            int category = resultSet.getInt("category");
            products.add(new Product(id, name, price, quantity, color, description, category));
        }
        return products;
    }

    public void insertProduct(Product product) throws SQLException {
        try {
            Connection connection = connectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into product (`name`,`price`,`quantity`,`color`,`description`,`category`) value (?,?,?,?,?,?,?)");
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setInt(6,product.getCategory());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted = false;
        try {
            Connection connection = connectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from product where id = ?;");
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated = false;
        try {
            Connection connection = connectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement("update product set name = ?, price= ?, quantity =?, color =?, description =?, category =? where id = ?;");
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getCategory());
            statement.setInt(7, product.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public Product selectProduct(int id) {
        Product product = null;
        // Step 1: Establishing a Connection
        try (Connection connection = connectDB.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement("select id,name,price,quantity,color,description,category from users where id =?");) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                double price = Double.parseDouble(rs.getString("price"));
                int quantity = Integer.parseInt(rs.getString("quantity"));
                String color = rs.getString("color");
                String description = rs.getString("description");
                int category = rs.getInt("category");
                product = new Product(id, name, price, quantity, color, description, category);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
