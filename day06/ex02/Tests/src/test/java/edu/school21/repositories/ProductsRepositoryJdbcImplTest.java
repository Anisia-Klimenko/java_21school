package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1l, "qweqwe", 123123),
            new Product(2l, "asdasd", 234234),
            new Product(3l, "zxczxc", 345345),
            new Product(4l, "werwer", 456456),
            new Product(5l, "sdfsdf", 567567),
            new Product(6l, "xcvxcv", 678678),
            new Product(7l, "ertert", 789789),
            new Product(8l, "dfgdfg", 890890)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1l, "qweqwe", 123123);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1l, "test", 0);
    final Product EXPECTED_SAVED_PRODUCT = new Product(9l, "new", 100);

    private DataSource ds;
    ProductRepository repository;

    @BeforeEach
    void init() {
        ds = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        repository = new ProductRepositoryJdbcImpl(ds);
    }

    @Test
    void findAllCheck() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repository.findAll());
    }

    @Test
    void findByIdCheck() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(1L).get());
    }

    @Test
    void saveCheck() throws SQLException {
        repository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, repository.findById(9L));
    }

    @Test
    void updateCheck() throws SQLException {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(EXPECTED_UPDATED_PRODUCT.getId()));
    }

    @Test
    void deleteCheck() throws SQLException {
        repository.delete(1L);
        Assertions.assertNull(repository.findById(1L));
    }

//    @AfterEach
//    void close() {
//        ds.shutdown();
//    }
}
