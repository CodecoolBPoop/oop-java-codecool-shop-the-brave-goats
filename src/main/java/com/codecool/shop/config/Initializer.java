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
        Supplier usa = new Supplier("USA", "Great again.");
        supplierDataStore.add(usa);

        //setting up a new product category
        ProductCategory weapons = new ProductCategory("Weapons", "We'll see", "Weapons.");
        productCategoryDataStore.add(weapons);
        ProductCategory fakeDegrees = new ProductCategory("Fake Degrees", "We'll see", "Fake degrees,");
        productCategoryDataStore.add(fakeDegrees);

        //setting up products and printing it
        productDataStore.add(new Product("Nuclear Resources", 100, "USD", "Fantastic price. Large content ecosystem. Helpful technical support.", weapons, northKorea));
        productDataStore.add(new Product("Kalashnikov AK-47", 100, "USD", "A gas-operated, 7.62 * 39 mm assault rifle.", weapons, russia));
        productDataStore.add(new Product("Hit man", 100, "USD", "The toughest guys from the Russian mob.", weapons, russia));
        productDataStore.add(new Product("Green Fox Academy", 100, "USD", "What does the fox say?", fakeDegrees, hungary));
        productDataStore.add(new Product("Codecool OOP Exam Pass", 100, "USD", "OOPs, I passed it again.", fakeDegrees, hungary));
        productDataStore.add(new Product("Theology of the Flying Spaghetti Monster", 100, "USD", "Holy Pastafarianism degree by the Church of the Flying Spaghetti Monster.", fakeDegrees, usa));
    }
}
