package io.turntabl;
/*
 * Connects to DB
 * Query
 * Return result to business layer
 * */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    //    Todo: do spring clean to removing boiler codes
   /* private JdbcTemplate getJdbcTemplate(String jdbcTemplate) {
        ApplicationContext appContx = new ClassPathXmlApplicationContext("beans");
        return (JdbcTemplate) appContx.getBean(jdbcTemplate);
    }*/

    @Override
    public List<ProductTO> getAllProducts() {
        ApplicationContext appContx = new ClassPathXmlApplicationContext("beans");
        JdbcTemplate jTemplate = (JdbcTemplate) appContx.getBean("productDAOTemplate");

        RowMapper rowMapper = BeanPropertyRowMapper.newInstance(ProductTO.class);
        List<ProductTO> products = jTemplate.query("select * from products limit 10", rowMapper);       // use query() for multi query
        return products;
    }

    @Override
    public List<ProductTO> showAllProductsByCustomer(String customerName) {
        ApplicationContext appContx = new ClassPathXmlApplicationContext("beans");
        JdbcTemplate jTemplate = (JdbcTemplate) appContx.getBean("productDAOTemplate");

        RowMapper rowMapper = BeanPropertyRowMapper.newInstance(ProductTO.class);
        String SELECT_CUSTOMER_PROD_QUERY = "select product_name, order_details.unit_price from products \" +\n" +
                "                        \"inner join order_details on products.product_id = order_details.product_id \" +\n" +
                "                        \"inner join orders on order_details.order_id = orders.order_id \" +\n" +
                "                        \"inner join customers on orders.customer_id = customers.customer_id where customers.contact_name = ? limit 5";
        /*String CUSTOMER_PROD_QUERY2 = "select product_name, order_details.unit_price from products \" +\n" +
                "                        \"inner join order_details on products.product_id = order_details.product_id \" +\n" +
                "                        \"inner join orders on order_details.order_id = orders.order_id \" +\n" +
                "                        \"inner join customers on orders.customer_id = customers.customer_id where customers.contact_name like '%?%' limit 5";
        */

        List<ProductTO> custProds = jTemplate.query(
                SELECT_CUSTOMER_PROD_QUERY,
//                new Object[]{"Thomas Hardy"},
                new Object[]{customerName},
                rowMapper
        );
        return custProds;
    }


    @Override
    public List<ProductTO> displayTop5PopularProducts() {
        ApplicationContext appContx = new ClassPathXmlApplicationContext("beans");
        JdbcTemplate jTemplate = (JdbcTemplate) appContx.getBean("productDAOTemplate");

        RowMapper rowMapper = BeanPropertyRowMapper.newInstance(ProductTO.class);
        final String SELECT_PROD_QUERY = "select products.product_name, products.unit_price, count(products.product_name) as pc " +
                "from products inner join order_details " +
                "on products.product_id = order_details.product_id " +
                "group by products.product_name, products.unit_price " +
                "order by pc " +
                "desc limit 5";
        List<ProductTO> popularProds = jTemplate.query(SELECT_PROD_QUERY, rowMapper);
        return popularProds;
    }


}
