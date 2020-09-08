package com.sampleproject.repository.impl;

import com.sampleproject.connection.DBConnection;
import com.sampleproject.model.Brand;
import com.sampleproject.model.Category;
import com.sampleproject.model.Product;
import com.sampleproject.model.User;
import com.sampleproject.model.queries.UserQueries;
import com.sampleproject.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public User saveUser(User user) {
        connection= DBConnection.getConnection();
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(UserQueries.saveUserQuery);
            preparedStatement.setInt(1,user.getUserId());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setDate(4,user.getBirthOfDate());
            preparedStatement.setString(5,user.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("User saving failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public boolean saveUserProduct(int userId, int productId) {
        connection= DBConnection.getConnection();
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(UserQueries.saveUserProductQuery);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,productId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("User product saving failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return true;
    }

    @Override
    public User updateUser(User user) {
        connection= DBConnection.getConnection();
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(UserQueries.updateUserQuery);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setDate(3,user.getBirthOfDate());
            preparedStatement.setString(4,user.getUsername());
            preparedStatement.setInt(5,user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("User update failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public boolean removeUser(int id) {
        connection= DBConnection.getConnection();
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(UserQueries.deleteUserProductQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            preparedStatement=(PreparedStatement)connection.prepareStatement(UserQueries.deleteUserQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("User delete failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return true;
    }

    @Override
    public User findUserById(int id) {
        connection=DBConnection.getConnection();
        User user=null;
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(UserQueries.findUserByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                int userid=resultSet.getInt("userId");
                String firstname=resultSet.getString("firstName");
                String lastname=resultSet.getString("lastName");
                Date birthofdate=resultSet.getDate("birthOfDate");
                String username=resultSet.getString("username");
                user=new User(userid,firstname,lastname,birthofdate,username);
            }
        } catch (SQLException throwables) {
            System.err.println("User find failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public User findUserProductsById(int id) {
        connection=DBConnection.getConnection();
        User user=null;
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(UserQueries.findUserProductQuery);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            boolean status=true;
            List<Product> products=new ArrayList<>();
            while(resultSet.next()){
                if (status){
                    int userid=resultSet.getInt("userId");
                    String firstname=resultSet.getString("firstName");
                    String lastname=resultSet.getString("lastName");
                    Date birthofdate=resultSet.getDate("birthOfDate");
                    String username=resultSet.getString("username");
                    user=new User(userid,firstname,lastname,birthofdate,username);
                    status=false;
                }
                int productid=resultSet.getInt("productId");
                String productname=resultSet.getString("productName");
                double unitprice=resultSet.getDouble("unitPrice");
                int avaible=resultSet.getInt("avaible");
                Date adddate=resultSet.getDate("addDate");
                Date updatedate=resultSet.getDate("updateDate");

                int categoryid=resultSet.getInt("categoryId");
                String categoryname=resultSet.getString("categoryName");

                int brandid=resultSet.getInt("brandId");
                String brandname=resultSet.getString("brandName");

                Category category=new Category(categoryid,categoryname);
                Brand brand=new Brand(brandid,brandname);
                Product product=new Product(productid,productname,unitprice,avaible,adddate,updatedate,category,brand);
                products.add(product);
            }
            user.setProducts(products);
        } catch (SQLException throwables) {
            System.err.println("User product find failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public List<User> findUsers() {
        connection=DBConnection.getConnection();
        User user=null;
        List<User> users=new ArrayList<>();
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(UserQueries.findUsersQuery);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int userid=resultSet.getInt("userId");
                String firstname=resultSet.getString("firstName");
                String lastname=resultSet.getString("lastName");
                Date birthofdate=resultSet.getDate("birthOfDate");
                String username=resultSet.getString("username");
                user=new User(userid,firstname,lastname,birthofdate,username);
                users.add(user);
            }
        } catch (SQLException throwables) {
            System.err.println("Users find failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return users;
    }
}
