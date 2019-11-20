package io.turntabl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    /*private JdbcTemplate getJTemp() {
        return (JdbcTemplate) new ClassPathXmlApplicationContext("beans").getBean("customerDAOTemplate");
    }*/

    @Override
    public List<CustomerTO> getAllCustomers() {
        ApplicationContext appContx = new ClassPathXmlApplicationContext("beans");
        JdbcTemplate jTemplate = (JdbcTemplate) appContx.getBean("customerDAOTemplate");
        RowMapper rowMapper = BeanPropertyRowMapper.newInstance(CustomerTO.class);
        List<CustomerTO> customers = jTemplate.query("select * from customers", rowMapper);
        return customers;
    }

    /*todo; refactor
    @Override
    public List<CustomerTO> getAllCustomers() {
        getJTemp();
        RowMapper rowMapper = BeanPropertyRowMapper.newInstance(CustomerTO.class);
        return getJTemp().query("select * from customers", rowMapper);
    }*/

    @Override
    public CustomerTO getCustomerByName(String name) {
        ApplicationContext appContx = new ClassPathXmlApplicationContext("beans");
        JdbcTemplate jTemplate = (JdbcTemplate) appContx.getBean("customerDAOTemplate");
        RowMapper rowMapper = BeanPropertyRowMapper.newInstance(CustomerTO.class);
        CustomerTO customer = (CustomerTO) jTemplate.queryForObject(
                "select * from customers where contact_name = ?",
                new Object[]{"John Steal"},
                rowMapper);     // since not selecting only one field but all, RowMapper needed
        return customer;
    }

    @Override
    public int numberOfCustomers() {

        return 0;
    }


}
