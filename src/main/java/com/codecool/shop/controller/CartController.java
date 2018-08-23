package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.ShoppingCard;
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

        Map<Product, Integer> mapListOfProducts = shoppingCard.getShoppingCardList();
        System.out.println(mapListOfProducts);


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Product> productList = new ArrayList<>();
        for (Product key :  mapListOfProducts.keySet()) {
            Integer num = mapListOfProducts.get(key);
            productList.add(key);

            context.setVariable("number", num);
            context.setVariable("products", productList);
        }
        engine.process("product/shopping-cart.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("delete-product");
        Product procduct = ProductDaoMem.getInstance().find(Integer.parseInt(productId));

        ShoppingCard shoppingCard = ShoppingCard.getInstance();
        shoppingCard.removeFromShoppingCard(procduct);
        resp.sendRedirect("/shopping-cart");
    }


    }