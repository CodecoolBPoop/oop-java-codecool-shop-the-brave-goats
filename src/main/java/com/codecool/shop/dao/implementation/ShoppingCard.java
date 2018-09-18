package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCard {
    //private static List<Product> shoppingCardList = new ArrayList<>();
    private static Map<Product, Integer> shoppingCardList = new HashMap<>();
    private static ShoppingCard shoppingCardInstance = null;

    private ShoppingCard(){}

    public static ShoppingCard getInstance(){
        if(shoppingCardInstance == null){
            shoppingCardInstance = new ShoppingCard();
        }
        return shoppingCardInstance;
    }

    public void addShoppingCard(Product newProduct){
        for (Product product : shoppingCardList.keySet()) {
            if(product.getId() == newProduct.getId()){
                shoppingCardList.put( product ,shoppingCardList.get(product) + 1);
                return;
            }
        }
        shoppingCardList.put(newProduct, 1);
    }

    // Call this method when you have the html for the shopping card.
    public Map<Product, Integer> getShoppingCardList(){
        return shoppingCardList;
    }

    // Delete the product
    public void removeFromShoppingCard(Product product){
        for (Product pr: shoppingCardList.keySet()) {
            if(pr.getId() == product.getId()){
                shoppingCardList.remove(pr);
                return;
            }
        }
    }

}
