package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryJdbcImpl implements ProductRepository{
    private final DataSource ds;

    public ProductRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> list = new ArrayList<>();

        Connection connection = ds.getConnection();

        String query = "select * from product";
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            list.add(new Product(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getFloat("price")));
        }

        statement.close();
        connection.close();

        return list;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Optional<Product> optionalProduct;

        Connection connection = ds.getConnection();

        String query = "select * from product where id=" + id;
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(query);
        if (!result.next())
            throw new RuntimeException("Object not found");

        optionalProduct = Optional.of(new Product(result.getLong("id"), result.getString("name"), result.getFloat("price")));

        statement.close();
        connection.close();

        return optionalProduct;
    }

    @Override
    public void save(Product product) throws SQLException {
        String query = "INSERT INTO product (name, price) VALUES (?, ?);";

        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());

        statement.execute();

        ResultSet id = statement.getGeneratedKeys();
        id.next();

        product.setId(id.getLong(1));
    }

    @Override
    public void update(Product product) throws SQLException {
        String query = "UPDATE product SET name=?, price=? WHERE id=?";

        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setLong(3, product.getId());

        statement.execute();

    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM product WHERE id=?";

        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setLong(1, id);

        statement.execute();
    }
}
