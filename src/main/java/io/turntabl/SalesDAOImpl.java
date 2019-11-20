package io.turntabl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SalesDAOImpl implements SalesDAO {

    @Override
    public SalesDAO getSales() {
        ApplicationContext appContx = new ClassPathXmlApplicationContext("beans");
        JdbcTemplate jTemplate = (JdbcTemplate) appContx.getBean("productDAOTemplate");
        RowMapper rowMapper = BeanPropertyRowMapper.newInstance(SalesDAO.class);
        List<SalesTO> sales = jTemplate.query(
                "select customers.contact_name, products.product_name, sum(order_details.unit_price) from orders " +
                        "inner join order_details on order_details.order_id=orders.order_id " +
                        "inner join customers on customers.customer_id = orders.customer_id " +
                        "inner join products on products.product_id=order_details.product_id " +
                        "group by customers.contact_name, products.product_name",
                rowMapper
        );
        return null;
    }

    @Override
    public List<SalesTO> weeklySalesReport(Date date) {
        ApplicationContext appContx = new ClassPathXmlApplicationContext("beans");
        JdbcTemplate jTemplate = (JdbcTemplate) appContx.getBean("productDAOTemplate");
        RowMapper rowMapper = BeanPropertyRowMapper.newInstance(SalesDAO.class);
        final String salesRepQuery = "select order_date as dates, customers.contact_name, products.product_name,order_details.unit_price " +
                "from orders inner join order_details on order_details.order_id=orders.order_id " +
                "inner join customers on customers.customer_id = orders.customer_id " +
                "inner join products on products.product_id=order_details.product_id " +
                "where order_date between date('1997-07-08') - '7 day'::interval and '1997-07-08'";
        List<SalesTO> sales = jTemplate.query(salesRepQuery, rowMapper);
        return sales;
    }


}
