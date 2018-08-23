package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier northKorea = new Supplier("North Korea", "Dictatorial regime.");
        supplierDataStore.add(northKorea);
        Supplier russia = new Supplier("Russia", "Wannabe superpower.");
        supplierDataStore.add(russia);
        Supplier hungary = new Supplier("Hungary", "Illiberal catholic democracy.");
        supplierDataStore.add(hungary);

        //setting up a new product category
        ProductCategory weapons = new ProductCategory("Weapons", "We'll see", "Weapons.");
        productCategoryDataStore.add(weapons);
        ProductCategory fakeDegrees = new ProductCategory("Fake Degrees", "We'll see", "Fake degrees,");
        productCategoryDataStore.add(fakeDegrees);

        //setting up products and printing it
        productDataStore.add(new Product("Uranium", 1200, "USD", "Fantastic price. Large content ecosystem. Helpful technical support.", weapons, northKorea));
        productDataStore.add(new Product("Kalashnikov AK-47", 300, "USD", "A gas-operated, 7.62 * 39 mm assault rifle.", weapons, russia));
        productDataStore.add(new Product("Hit men", 100, "USD", "The toughest guys from the Russian mob.", weapons, russia));
        productDataStore.add(new Product("Green Fox", 14400, "USD", "What does the fox say?", fakeDegrees, hungary));
    }
}
