package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    private static ProductCategoryDao productCategory;

    private static ProductCategory category1;
    private static ProductCategory category2;

    @BeforeAll
    static void initAll(){

        category1 = new ProductCategory("Test Category1","Test Department1", "Test descirption1");
        category2 = new ProductCategory("Test Category2","Test Department2", "Test descirption2");
    }

    @BeforeEach
    void initTest(){
        productCategory = ProductCategoryDaoMem.getInstance();

        List<ProductCategory> allCategorys = productCategory.getAll();

        int counter = 0;
        while (allCategorys.size() != 0) {
            productCategory.remove(counter++);
        }
    }

    @Test
    void testAddOneCategory(){

        productCategory.add(category1);
        List<ProductCategory> allCategories = productCategory.getAll();
        assertEquals(1, allCategories.size());
    }

    @Test
    void testAddTwoCategories(){
        productCategory.add(category1);
        productCategory.add(category2);
        List<ProductCategory> allCategorys = productCategory.getAll();

        assertEquals(2, allCategorys.size());

    }

    @Test
    void testForNonExistingCategory(){
        productCategory.add(category1);
        productCategory.add(category2);
        ProductCategory category = productCategory.find(3);

        assertNull(category);
    }

    @Test
    void testForFindExistingCategory(){
        productCategory.add(category1);
        productCategory.add(category2);
        ProductCategory category = productCategory.find(2);

        assertEquals(category2, category);
    }

    @Test
    void testForRemoveCategorySizeIsCorrect() {
        productCategory.add(category1);
        productCategory.add(category2);
        productCategory.remove(2);
        List<ProductCategory> allCategories = productCategory.getAll();
        assertEquals(1, allCategories.size());
    }

    @Test
    void testForRemovedCategoryIsCorrect(){
        productCategory.add(category1);
        productCategory.add(category2);
        productCategory.remove(1);
        List<ProductCategory> allCategories = productCategory.getAll();
        assertEquals(category2, allCategories.get(0));
    }

    @Test
    void testForNonExistingProduct() {
        ProductCategory category = productCategory.find(0);
        assertNull(category);
    }
}