package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/category"})
public class FilterCategory extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao supplierCategoryStore = SupplierDaoJDBC.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String choosedCategory = req.getParameter("category");

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
        context.setVariable("products", productDataStore.getBy(((ProductCategoryDaoJDBC) productCategoryDataStore).findString(choosedCategory)));
        engine.process("product/index.html", context, resp.getWriter());

    }
}
