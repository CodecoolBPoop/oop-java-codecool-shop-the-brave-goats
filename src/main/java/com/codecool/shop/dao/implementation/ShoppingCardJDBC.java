package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.sql.ConnectingDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCardJDBC {
    //private static List<Product> shoppingCardList = new ArrayList<>();
    private static Map<Product, Integer> shoppingCardList = new HashMap<>();
    private static ShoppingCardJDBC shoppingCardInstance = null;
    private ShoppingCardJDBC(){}

    public static ShoppingCardJDBC getInstance(){
        if(shoppingCardInstance == null){
            shoppingCardInstance = new ShoppingCardJDBC();
        }
        return shoppingCardInstance;
    }

    public void addShoppingCard(Product newProduct) throws SQLException {
        System.out.println("name" + newProduct.getName());
        ResultSet id = ConnectingDB.executeQuery("SELECT id FROM products WHERE name = '" + newProduct.getName() + "'");
        String realId = null;
        while(id.next()){
            try {
                realId = id.getString("id");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ConnectingDB.executeQuery("INSERT INTO shopping_carts (product_id, product_quantity, total_price) VALUES (" + realId +" ," + 1 + " , "+ newProduct.getDefaultPrice() + ")");
    }

    // Call this method when you have the html for the shopping card.
    public Map<Product, Integer> getShoppingCardList(){
        return shoppingCardList;
    }

    // Delete the product
    public void removeFromShoppingCard(Product product){
        if(shoppingCardList.containsKey(product)){
            shoppingCardList.remove(product);

        }
    }

}
