package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.sql.ConnectingDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao{

    private static SupplierDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        String name = supplier.getName();
        String description = supplier.getDescription();
        ConnectingDB.executeQuery("INSERT INTO supplier (name, description) VALUES ('" + name + "', '" + description + "')");
//        supplier.setId(data.size() + 1);
//        data.add(supplier);
    }

    @Override
    public Supplier find(int id) {
        try {
            ResultSet result = ConnectingDB.executeQuery("SELECT * FROM supplier WHERE id = " + id );
            while(result.next()){
                Supplier pr = new Supplier(result.getString("name"), result.getString("description"));
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
        ConnectingDB.executeQuery("DELETE FROM  supplier WHERE id = " + id);
    }

    @Override
    public List<Supplier> getAll() {
        try{
            ResultSet result = ConnectingDB.executeQuery("SELECT * FROM supplier;");
            List allSuppliers = new ArrayList();
            while(result.next()){
                Supplier pr = new Supplier(result.getString("name"), result.getString("description"));
                allSuppliers.add(pr);
            }
            return allSuppliers;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
        //return data;
    }

    //public ProductCategory findString(String name) { return data.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);}
}
