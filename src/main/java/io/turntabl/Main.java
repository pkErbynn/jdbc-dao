package io.turntabl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
//        JDBC jdbc = new JDBC();
//        jdbc.readCustomerData();

        // testing if config == success
//        ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
        ApplicationContext appctx = new ClassPathXmlApplicationContext("beans.xml");

        JdbcTemplate template = (JdbcTemplate) appctx.getBean("jdbcTemplate");
        int nr = template.queryForObject("select count(contact_name) from customers", Integer.class);
        System.out.println("count: " + nr);

        CustomerDAOImpl customerTemplate = (CustomerDAOImpl) appctx.getBean("customerDAOTemplate");
        System.out.println("All customers: " + customerTemplate.getAllCustomers());
        System.out.println("Customers count: " + customerTemplate.numberOfCustomers());
        System.out.println("Ony Customers: " +customerTemplate.getCustomerByName("John Leet"));


        ProductDAOImpl productDAOTemp = (ProductDAOImpl) appctx.getBean("productDAOTemplate");
        System.out.println("All products: " + productDAOTemp.getAllProducts());
        System.out.println("All products: " + productDAOTemp.showAllProductsByCustomer("Karin Josephs"));
        System.out.println("Top 5: " + productDAOTemp.displayTop5PopularProducts());


        SalesDAOImpl sales = (SalesDAOImpl) appctx.getBean("salesDAOTemp");
        System.out.println("Sales: " + sales.getSales());


    }
}
