package com.sampleproject.repository.impl;

import com.sampleproject.connection.DBConnection;
import com.sampleproject.model.Brand;
import com.sampleproject.model.Category;
import com.sampleproject.model.Product;
import com.sampleproject.model.queries.ProductQueries;
import com.sampleproject.repository.ProductRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    @Override
    public Product saveProduct(Product product) {
        connection= DBConnection.getConnection();
        try{
            LocalDateTime localDateTime=LocalDateTime.now();
            preparedStatement=(PreparedStatement)connection.prepareStatement(ProductQueries.saveProductQuery);
            preparedStatement.setInt(1,product.getProductId());
            preparedStatement.setString(2,product.getProductName());
            preparedStatement.setDouble(3,product.getUnitPrice());
            preparedStatement.setInt(4,product.getAvaible());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
            preparedStatement.setTimestamp(6,null);
            preparedStatement.setInt(7,product.getCategory().getCategoryId());
            preparedStatement.setInt(8,product.getBrand().getBrandId());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Product save failed..."+e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return product;
    }

    @Override
    public boolean saveBatchProduct(List<Product> products) {
        connection=DBConnection.getConnection();
        try {
            LocalDateTime localDateTime=LocalDateTime.now();
            preparedStatement=(PreparedStatement)connection.prepareStatement(ProductQueries.saveProductQuery);
            for(Product product:products) {
                preparedStatement.setInt(1, product.getProductId());
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setDouble(3, product.getUnitPrice());
                preparedStatement.setInt(4, product.getAvaible());
                preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
                preparedStatement.setTimestamp(6, null);
                preparedStatement.setInt(7, product.getCategory().getCategoryId());
                preparedStatement.setInt(8, product.getBrand().getBrandId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException throwables) {
            System.err.println("Products save failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return true;
    }

    @Override
    public Product updateProduct(Product product) {
        connection= DBConnection.getConnection();
        try{
            LocalDateTime localDateTime=LocalDateTime.now();
            preparedStatement=(PreparedStatement)connection.prepareStatement(ProductQueries.updateProductQuery);
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setDouble(2,product.getUnitPrice());
            preparedStatement.setInt(3,product.getAvaible());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(localDateTime));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
            preparedStatement.setInt(6,product.getCategory().getCategoryId());
            preparedStatement.setInt(7,product.getBrand().getBrandId());
            preparedStatement.setInt(8,product.getProductId());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Product update failed..."+e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return product;
    }

    @Override
    public boolean removeProduct(int id) {
        connection= DBConnection.getConnection();
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(ProductQueries.deleteUserProductQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            preparedStatement=(PreparedStatement)connection.prepareStatement(ProductQueries.deleteProductQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("Product delete failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return true;
    }

    @Override
    public Product findProductById(int id) {
        connection=DBConnection.getConnection();
        Product product=null;
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(ProductQueries.findProductByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                int productId=resultSet.getInt("productId");
                String productName=resultSet.getString("productName");
                double unitPrice=resultSet.getDouble("unitPrice");
                int avaible=resultSet.getInt("avaible");
                Date addDate=resultSet.getDate("addDate");
                Date updateDate=resultSet.getDate("updateDate");

                int categoryid=resultSet.getInt("categoryId");
                String categoryname=resultSet.getString("categoryName");

                int brandid=resultSet.getInt("brandId");
                String brandname=resultSet.getString("brandName");

                Category category=new Category(categoryid,categoryname);
                Brand brand=new Brand(brandid,brandname);

                product=new Product(productId,productName,unitPrice,avaible,addDate,updateDate,category,brand);
            }
        } catch (SQLException throwables) {
            System.err.println("Product find failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return product;
    }

    @Override
    public List<Product> findProducts() {
        connection=DBConnection.getConnection();
        List<Product> products=new ArrayList<>();
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(ProductQueries.findProductsQuery);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int productId=resultSet.getInt("productId");
                String productName=resultSet.getString("productName");
                double unitPrice=resultSet.getDouble("unitPrice");
                int avaible=resultSet.getInt("avaible");
                Date addDate=resultSet.getDate("addDate");
                Date updateDate=resultSet.getDate("updateDate");

                int categoryid=resultSet.getInt("categoryId");
                String categoryname=resultSet.getString("categoryName");

                int brandid=resultSet.getInt("brandId");
                String brandname=resultSet.getString("brandName");

                Category category=new Category(categoryid,categoryname);
                Brand brand=new Brand(brandid,brandname);

                Product product=new Product(productId,productName,unitPrice,avaible,addDate,updateDate,category,brand);
                products.add(product);
            }
        } catch (SQLException throwables) {
            System.err.println("Products find failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return products;
    }
}
