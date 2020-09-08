package com.sampleproject.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;

    static {
        Properties properties=new Properties();
        try {
            InputStream inputStream=new FileInputStream("src/main/resources/database.properties");
            properties.load(inputStream);
            driver= properties.getProperty("db_driver");
            url=properties.getProperty("db_url");
            username=properties.getProperty("db_username");
            password=properties.getProperty("db_password");
        } catch (FileNotFoundException e) {
            System.err.println("Properties file not found..."+e);
        } catch (IOException e) {
            System.err.println("Properties file load failed..."+e);
        }
    }

    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName(driver);
            System.out.println("Driver found...");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found...");
        }
        try {
            connection=(Connection) DriverManager.getConnection(url,username,password);
            System.out.println("Connection successful...");
        } catch (SQLException throwables) {
            System.err.println("Connection failed..."+throwables);
        }
        return connection;
    }
    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
                System.out.println("ResultSet successfully closed...");
            } catch (SQLException throwables) {
                System.err.println("ResultSet close failed..." + throwables);
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                System.out.println("PreparedStatement successfully closed...");
            } catch (SQLException throwables) {
                System.err.println("PreparedStatement close failed..." + throwables);
            }
        }
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection successfully closed...");
            } catch (SQLException throwables) {
                System.err.println("Connection close failed...");
            }

        }
    }
}
