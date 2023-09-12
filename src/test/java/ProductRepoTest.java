import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @org.junit.jupiter.api.Test
    void getProducts() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        List<Product> actual = repo.getProducts();

        //THEN
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "Apfel"));
        expected.add(new Product("2", "Birne"));
        expected.add(new Product("3", "Banane"));
        expected.add(new Product("4", "Kiwi"));
        expected.add(new Product("5", "Orange"));
        expected.add(new Product("6", "Mango"));
        expected.add(new Product("7", "Ananas"));
        expected.add(new Product("8", "Pfirsich"));
        expected.add(new Product("9", "Pflaume"));
        expected.add(new Product("10", "Kirsche"));
        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void getProductById() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        Product actual = repo.getProductById("1").get();

        //THEN
        Product expected = new Product("1", "Apfel");
        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("11", "Himbeere");

        //WHEN
        Product actual = repo.addProduct(newProduct);

        //THEN
        Product expected = new Product("11", "Himbeere");

        assertEquals(actual, expected);
        assertEquals(repo.getProductById("11").get(), expected);
    }

    @org.junit.jupiter.api.Test
    void removeProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        repo.removeProduct("1");

        //THEN
        assertNull(repo.getProductById("1"));
    }
}
