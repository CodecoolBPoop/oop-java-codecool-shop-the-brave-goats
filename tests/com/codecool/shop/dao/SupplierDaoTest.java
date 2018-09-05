package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {
    private static SupplierDao supplierDao;
    private Supplier supplier1 = new Supplier("Test Supplier 1", "Test Supplier 1 Description");
    private Supplier supplier2 = new Supplier("Test Supplier 2", "Test Supplier 2 Description");

    @BeforeEach
    void initTest() {
        supplierDao = SupplierDaoMem.getInstance();
        List<Supplier> allSuppliers = supplierDao.getAll();

        int counter = 0;
        while (allSuppliers.size() != 0) {
            supplierDao.remove(counter++);
        }
    }

    @Test
    void testAddingOneSupplier() {
        supplierDao.add(supplier1);
        List<Supplier> allSuppliers = supplierDao.getAll();
        assertEquals(1, allSuppliers.size());
    }

    @Test
    void testAddingTwoSuppliers() {
        supplierDao.add(supplier1);
        supplierDao.add(supplier2);
        List<Supplier> allSuppliers = supplierDao.getAll();
        assertEquals(2, allSuppliers.size());
    }

    @Test
    void testForFindingNonExistingSupplier() {
        Supplier supplier = supplierDao.find(3);
        assertNull(supplier);
    }

    @Test
    void testForFindingNonExistingSupplier2() {
        supplierDao.add(supplier1);
        supplierDao.add(supplier2);
        Supplier supplier = supplierDao.find(3);
        assertNull(supplier);
    }

    @Test
    void testForFindingExistingSupplier() {
        supplierDao.add(supplier1);
        supplierDao.add(supplier2);
        Supplier supplier = supplierDao.find(2);
        assertEquals(supplier2, supplier);
    }

    @Test
    void testForRemoveSupplierSizeIsCorrect() {
        supplierDao.add(supplier1);
        supplierDao.add(supplier2);
        supplierDao.remove(2);
        List<Supplier> suppliers = supplierDao.getAll();
        assertEquals(1, suppliers.size());
    }

    @Test
    void testForRemovedSupplierIsCorrect() {
        supplierDao.add(supplier1);
        supplierDao.add(supplier2);
        supplierDao.remove(1);
        List<Supplier> suppliers = supplierDao.getAll();
        assertEquals(supplier2, suppliers.get(0));
    }
}