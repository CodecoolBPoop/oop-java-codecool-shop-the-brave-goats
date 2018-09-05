package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.sql.ConnectingDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    private List<ProductCategory> data = new ArrayList<>();
    private static ProductCategoryDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoJDBC() {
    }

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {

//        category.setId(data.size() + 1);
//        data.add(category);
    }

    @Override
    public ProductCategory find(int id) {
        try {
            ResultSet result = ConnectingDB.executeQuery("SELECT * FROM product_categories WHERE id = " + id );
            while(result.next()){
                ProductCategory pr = new ProductCategory(result.getString("name"), result.getString("department"), result.getString("description"));
                return pr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        //return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        ConnectingDB.executeQuery("DELETE FROM  product_categories WHERE id = " + id);
    }

    @Override
    public List<ProductCategory> getAll() {
        ResultSet result = ConnectingDB.executeQuery("SELECT * FROM product_categories");
        return (List)result;
        //return data;
    }

    public ProductCategory findString(String name) { return data.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);}
}
