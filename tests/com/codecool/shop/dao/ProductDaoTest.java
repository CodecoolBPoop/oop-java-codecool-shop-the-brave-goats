package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    private static ProductDao productDao;

    private static ProductCategory category1;
    private static Supplier supplier1;
    private static Product prod1;
    private static Product prod2;

    @BeforeAll
    static void initAll() {
        category1 = new ProductCategory("Test Category 1", "Test Category 1 Department", "Test Category 1 Description");
        supplier1 = new Supplier("Test Supplier 1", "Test Supplier 1 Description");
        prod1 = new Product("Product1", 9.9f, "USD", "Product 1 Description", category1, supplier1);
        prod2 = new Product("Product2", 4.9f, "USD", "Product 2 Description", category1, supplier1);
    }

    @BeforeEach
    void initTest() {
        productDao = ProductDaoMem.getInstance();
        List<Product> allProducts = productDao.getAll();

        int counter = 0;
        while (allProducts.size() != 0) {
            productDao.remove(counter++);
        }
    }

    @Test
    void testAddingOneProduct() {
        productDao.add(prod1);
        List<Product> products = productDao.getAll();
        assertEquals(1, products.size());
    }

    @Test
    void testAddingTwoProducts() {
        productDao.add(prod1);
        productDao.add(prod2);
        List<Product> products = productDao.getAll();
        assertEquals(2, products.size());
    }

    @Test
    void testForNonExistingSupplier() {
        productDao.add(prod1);
        Supplier nonExistingSupplier = new Supplier("Test Supplier 2", "Test Supplier 2 Description");
        List<Product> products = productDao.getBy(nonExistingSupplier);
        assertEquals(0, products.size());
    }

    @Test
    void testForExistingSupplier() {
        productDao.add(prod1);
        List<Product> products = productDao.getBy(supplier1);
        assertEquals(1, products.size());
    }

    @Test
    void testForNonExistingCategory() {
        ProductCategory nonExistingCategory = new ProductCategory("TestCategory", "TestCategoryDepartment", "Description");
        productDao.add(prod1);
        List<Product> products = productDao.getBy(nonExistingCategory);
        assertEquals(0, products.size());
    }

    @Test
    void testForExistingCategory() {
        productDao.add(prod1);
        productDao.add(prod2);
        List<Product> products = productDao.getBy(category1);
        assertEquals(2, products.size());
    }

    @Test
    void testForNonExistingProduct() {
        Product product = productDao.find(0);
        assertNull(product);
    }

    @Test
    void testForNonExistingProduct2() {
        productDao.add(prod1);
        productDao.add(prod2);
        Product product = productDao.find(3);
        assertNull(product);
    }

    @Test
    void testForFindExistingProduct() {
        productDao.add(prod1);
        productDao.add(prod2);
        Product product = productDao.find(2);
        assertEquals(prod2, product);
    }

    @Test
    void testForRemoveProductSizeIsCorrect() {
        productDao.add(prod1);
        productDao.add(prod2);
        productDao.remove(2);
        List<Product> products = productDao.getAll();
        assertEquals(1, products.size());
    }

    @Test
    void testForRemovedProductIsCorrect() {
        productDao.add(prod1);
        productDao.add(prod2);
        productDao.remove(1);
        List<Product> products = productDao.getAll();
        assertEquals(prod2, products.get(0));
    }

}