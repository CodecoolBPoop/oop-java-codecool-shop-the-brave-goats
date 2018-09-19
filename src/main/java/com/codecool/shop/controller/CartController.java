package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/shopping-cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCard shoppingCard = ShoppingCard.getInstance();
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao supplierCategoryStore = SupplierDaoJDBC.getInstance();

        Map<Product, Integer> mapListOfProducts = shoppingCard.getShoppingCardList();
        System.out.println(mapListOfProducts);


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Product> productList = new ArrayList<>();
        for (Product key :  mapListOfProducts.keySet()) {
            Integer numberOfProduct = mapListOfProducts.get(key);

            key.setNumberOfProduct(numberOfProduct);
            productList.add(key);
            //productList.add(numberOfProduct);


            context.setVariable("numberOfProduct", numberOfProduct);
            context.setVariable("products", productList);
        }
        context.setVariable("recipient", "World");
        context.setVariable("category1", productCategoryDataStore.find(1));
        context.setVariable("category2", productCategoryDataStore.find(2));
        context.setVariable("supplier1", supplierCategoryStore.find(1));
        context.setVariable("supplier2", supplierCategoryStore.find(2));
        context.setVariable("supplier3", supplierCategoryStore.find(3));
        context.setVariable("supplier4", supplierCategoryStore.find(4));
        context.setVariable("supplier", SupplierDaoMem.getInstance().getAll());
        engine.process("product/shopping-cart.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("delete-product");
        Product procduct = ProductDaoJDBC.getInstance().find(Integer.parseInt(productId));

        ShoppingCard shoppingCard = ShoppingCard.getInstance();
        shoppingCard.removeFromShoppingCard(procduct);
        resp.sendRedirect("/shopping-cart");
    }


    }