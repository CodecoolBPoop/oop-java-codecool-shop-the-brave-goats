package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.sql.ConnectingDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoJDBC implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJDBC() {
    }

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        ConnectingDB.executeQuery("INSERT INTO product_categories (name, price, currency, supplier, product_category, description) VALUES ('" + product.getName() + "', '" + product.getPrice() + "', '" + product.getDefaultCurrency()  + "', '" + product.getProductCategory()  + "', '" + product.getDescription()+ "')");
//        category.setId(data.size() + 1);
//        data.add(category);
    }

    @Override
    public Product find(int id) {
        try {
            ResultSet result = ConnectingDB.executeQuery("SELECT * FROM products WHERE id = " + id );
            while(result.next()){
                ProductCategory pk = ProductCategoryDaoJDBC.getInstance().find(Integer.parseInt(result.getString("product_category")));
                Supplier su = SupplierDaoJDBC.getInstance().find(Integer.parseInt(result.getString("supplier")));
                Product pr = new Product(result.getString("name"), Float.parseFloat(result.getString("price")), result.getString("currency"),result.getString("description"), pk, su  );
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
        ConnectingDB.executeQuery("DELETE FROM  products WHERE id = " + id);
    }

    @Override
    public List<Product> getAll() {
        try{
            ResultSet result = ConnectingDB.executeQuery("SELECT * FROM products;");
            List<Product> allProducts = new ArrayList();
            while(result.next()){
                ProductCategory pk = ProductCategoryDaoJDBC.getInstance().find(Integer.parseInt(result.getString("product_category")));
                Supplier su = SupplierDaoJDBC.getInstance().find(Integer.parseInt(result.getString("supplier")));
                Product pr = new Product(result.getString("name"), Float.parseFloat(result.getString("price")), result.getString("currency"),result.getString("description"), pk, su  );
                allProducts.add(pr);
            }
            System.out.println("lista " + allProducts);
            return allProducts;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
        //return data;
    }

    public static void main(String[] args) {
        System.out.println(ProductDaoJDBC.getInstance().getAll());
    }
//    private ProductDaoJDBC() {
//    }
//
//    public static ProductDaoJDBC getInstance() {
//        if (instance == null) {
//            instance = new ProductDaoJDBC();
//        }
//        return instance;
//    }
//
//    @Override
//    public void add(Product product) {
//        product.setId(data.size() + 1);
//        data.add(product);
//    }
//
//    @Override
//    public Product find(int id) {
//        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
//    }
//
//    @Override
//    public void remove(int id) {
//        data.remove(find(id));
//    }
//
//    @Override
//    public List<Product> getAll() {
//        return data;
//    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
