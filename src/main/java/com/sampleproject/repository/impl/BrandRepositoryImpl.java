package com.sampleproject.repository.impl;

import com.sampleproject.connection.DBConnection;
import com.sampleproject.model.Brand;
import com.sampleproject.model.queries.BrandQueries;
import com.sampleproject.repository.BrandRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandRepositoryImpl implements BrandRepository {
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;

    @Override
    public Brand findBrandById(int id) {
        connection= DBConnection.getConnection();
        Brand brand=null;
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(BrandQueries.findBrandByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                int brandId=resultSet.getInt("brandId");
                String brandName=resultSet.getString("brandName");
                brand=new Brand(brandId,brandName);
            }
        } catch (SQLException throwables) {
            System.err.println("Brand find failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return brand;
    }

    @Override
    public List<Brand> findBrans() {
        connection=DBConnection.getConnection();
        List<Brand> brands=new ArrayList<>();
        try {
            preparedStatement=(PreparedStatement)connection.prepareStatement(BrandQueries.findBrandsQuery);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int brandId=resultSet.getInt("brandId");
                String brandName=resultSet.getString("brandName");

                Brand brand=new Brand(brandId,brandName);
                brands.add(brand);
            }
        } catch (SQLException throwables) {
            System.err.println("Brands find failed..."+throwables);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return brands;
    }
}
