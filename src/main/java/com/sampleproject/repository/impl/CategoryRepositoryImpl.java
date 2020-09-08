package com.sampleproject.repository.impl;

import com.sampleproject.connection.DBConnection;
import com.sampleproject.model.Category;
import com.sampleproject.model.queries.CategoryQueries;
import com.sampleproject.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    @Override
    public Category findCategoryById(int id) {
        connection= DBConnection.getConnection();
        Category category=null;
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(CategoryQueries.findCategoryByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                int categoryId=resultSet.getInt("categoryId");
                String categoryName=resultSet.getString("categoryName");
                category=new Category(categoryId,categoryName);
            }
        } catch (SQLException throwables) {
            System.err.println("Category find failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return category;
    }

    @Override
    public List<Category> findCategories() {
        connection= DBConnection.getConnection();
        List<Category> categories=new ArrayList<>();
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(CategoryQueries.findCategoriesQuery);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int categoryId=resultSet.getInt("categoryId");
                String categoryName=resultSet.getString("categoryName");
                Category category=new Category(categoryId,categoryName);
                categories.add(category);
            }
        } catch (SQLException throwables) {
            System.err.println("Categories find failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return categories;
    }
}
