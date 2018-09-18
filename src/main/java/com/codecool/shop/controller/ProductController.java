package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao supplierCategoryStore = SupplierDaoJDBC.getInstance();

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariables(params);
        context.setVariable("recipient", "World");
        context.setVariable("category1", productCategoryDataStore.find(1).getName());
        context.setVariable("category2", productCategoryDataStore.find(2).getName());
        context.setVariable("category3", productCategoryDataStore.find(3).getName());
        context.setVariable("category4", productCategoryDataStore.find(4).getName());
        context.setVariable("supplier1", supplierCategoryStore.find(1).getName());
        context.setVariable("supplier2", supplierCategoryStore.find(2).getName());
        context.setVariable("supplier3", supplierCategoryStore.find(3).getName());
        context.setVariable("supplier4", supplierCategoryStore.find(4).getName());
        context.setVariable("supplier5", supplierCategoryStore.find(5).getName());
        context.setVariable("products", productDataStore.getAll());
        context.setVariable("supplier", SupplierDaoMem.getInstance().getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String productId = req.getParameter("product_name");
        ProductDaoJDBC productCategoryDaoJDBC = ProductDaoJDBC.getInstance();
        Product product = productCategoryDaoJDBC.find(Integer.parseInt(productId));

        ShoppingCard shoppingCard = ShoppingCard.getInstance();
        shoppingCard.addShoppingCard(product);
        resp.sendRedirect("/");
    }

}
