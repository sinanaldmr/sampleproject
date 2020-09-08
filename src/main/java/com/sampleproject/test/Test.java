package com.sampleproject.test;

import com.sampleproject.model.Brand;
import com.sampleproject.model.Category;
import com.sampleproject.model.Product;
import com.sampleproject.model.User;
import com.sampleproject.service.BrandService;
import com.sampleproject.service.CategoryService;
import com.sampleproject.service.ProductService;
import com.sampleproject.service.UserService;
import com.sampleproject.service.impl.BrandServiceImpl;
import com.sampleproject.service.impl.CategoryServiceImpl;
import com.sampleproject.service.impl.ProductServiceImpl;
import com.sampleproject.service.impl.UserServiceImpl;

import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        BrandService brandService=new BrandServiceImpl();
        CategoryService categoryService=new CategoryServiceImpl();
        UserService userService=new UserServiceImpl();
        ProductService productService=new ProductServiceImpl();
        /*             // TEST OK..

        List<Brand> brands= brandService.findBrans();
        for (Brand brand:brands
             ) {
            System.out.println(brand.getBrandId()+" "+brand.getBrandName());
        }

        Brand brand=brandService.findBrandById(3);
        System.out.println(brand.getBrandName());
        --------------------------------------------------------------------------------------------------

        List<Category> categories=categoryService.findCategories();
        for (Category c:categories
             ) {
            System.out.println(c.getCategoryId()+" "+c.getCategoryName());
        }
        Category category=categoryService.findCategoryById(3);
        System.out.println(category.getCategoryName());
        ----------------------------------------------------------------------------------------------------

        List<Product> products=productService.findProducts();
        for (Product p :products
             ) {
            System.out.println(p.getProductId()+" "+p.getAvaible()+" "+p.getUnitPrice()+" "+p.getAddDate()
            +" "+p.getUpdateDate()+" "+p.getCategory().getCategoryName()+" "+p.getBrand().getBrandName());
        }
        Product product=productService.findProductById(3);
        System.out.println(product);
        ---------------------------------------------------------------------------------------------------

        List<User> users=userService.findUsers();
        for (User u :users
             ) {
            System.out.println(u);
        }
        User user=userService.findUserById(1);
        System.out.println(user);

        User user=userService.findUserProductsById(1);
        System.out.println(user);

        User user=new User(1,"Sinan","Aldemir",null,"sinanaldmr");
        userService.saveUser(user);

        User user=new User(3,"admin","admin",null,"admin");
        userService.updateUser(user);

        userService.removeUser(1);
        userService.removeUser(3);

        userService.saveUserProduct(1,3);



        ----------------------------------------------------------------------------------------------------
        Category category=categoryService.findCategoryById(2);
        Brand brand=brandService.findBrandById(3);
        Product product=new Product(4,"gio",2.50,1,new Date(),null,null,null);
        product.setCategory(category);
        product.setBrand(brand);
        productService.saveProduct(product);
        userService.saveUserProduct(3,4);
        -----------------------------------------------------------------------------------------------------

          Product product=productService.findProductById(4);
        product.setProductName("aaaaa");
        productService.updateProduct(product);
        -----------------------------------------------------------------------------------------------------
        */



    }
}
