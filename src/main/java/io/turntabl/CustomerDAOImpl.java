package io.turntabl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
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


        /*
        // for custom row mapper
        CustomerTO cust = (CustomerTO) jTemplate.queryForObject(
                "select * from customers where contact_name = ?",
                new Object[]{"John Steal"},
                new RowMapper<CustomerTO>() {
                    @Override
                    public CustomerTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        return new CustomerTO(rs.getString("contact_name"), rs.getString("country"), rs.getString("region")); // for arg-constructor
                        CustomerTO customer = new CustomerTO();
                        customer.setContactName(rs.getString("contact_name"));
                        customer.setCountry(rs.getString("country"));
                        return customer;
                        }
                });
         */
        
        return customer;
    }

    @Override
    public int numberOfCustomers() {
        ApplicationContext appContx = new ClassPathXmlApplicationContext("beans");
        JdbcTemplate jTemplate = (JdbcTemplate) appContx.getBean("customerDAOTemplate");
        int count = jTemplate.queryForObject("select count(distinct contact_name) from customers;", Integer.class);
        return count;
    }


}




/*
// in case use custom customerTOmapper in multiple places
// create `new CreateTOMapper()` for usage
// can be marked `public` for access in diff class/package

class CustomerTOMapper implements RowMapper<CustomerTO> {
    @Override
    public CustomerTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//      return new CustomerTO(rs.getString("contact_name"), rs.getString("country"), rs.getString("region")); // for arg-constructor
        CustomerTO customer = new CustomerTO();
        customer.setContactName(rs.getString("contact_name"));
        customer.setCountry(rs.getString("country"));
        return customer;
    }
}

*/