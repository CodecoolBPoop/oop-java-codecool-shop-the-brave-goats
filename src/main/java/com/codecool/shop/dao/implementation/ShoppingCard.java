package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCard {
    private static List<Product> shoppingCardList = new ArrayList<>();
    private static ShoppingCard shoppingCardInstance = null;

    private ShoppingCard(){}

    public static ShoppingCard getInstance(){
        if(shoppingCardInstance == null){
            shoppingCardInstance = new ShoppingCard();
        }
        return shoppingCardInstance;
    }

    public void addShoppingCard(Product product){
        shoppingCardList.add(product);
    }

    public List<Product> getShoppingCardList(){
        return shoppingCardList;
    }

}
