package io.turntabl;
/*
* Connects to DB
* Query
* Return result to business layer
* */

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String URL = "jdbc:postgresql:northwind";
    private static final String USERNAME = "john-erbynn";
    private static final String PASSWORD = "turntabl";


    @Override
    public List<ProductTO> showAllProductsByCustomer(String customerName) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        List<ProductTO> allProds = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//             String SEARCH_CUSTOMER_NAME_QUERY = "...";
            PreparedStatement pst = conn.prepareStatement("select product_name, order_details.unit_price from products " +
                    "inner join order_details on products.product_id = order_details.product_id " +
                    "inner join orders on order_details.order_id = orders.order_id " +
                    "inner join customers on orders.customer_id = customers.customer_id where customers.contact_name = ? limit 1"
            );
            pst.clearParameters();
            pst.setString(1, customerName);   // => name matched to the first ?
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                ProductTO pto = new ProductTO(result.getString("product_name"), result.getDouble("unit_price"));    // the exact col name ....not order_details.unit_price
                allProds.add(pto);
            }
        } catch (SQLException sqle) {
            System.err.println("Connection error: " + sqle);
        }
        return allProds;
    }


    @Override
    public List<ProductTO> displayTop5PopularProducts() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        List<ProductTO> popularProds = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            final String top5ProdQuery ="select products.product_name, products.unit_price, count(products.product_name) as pc " +
                    "from products inner join order_details " +
                    "on products.product_id = order_details.product_id " +
                    "group by products.product_name, products.unit_price " +
                    "order by pc " +
                    "desc limit 5";
            PreparedStatement pst = conn.prepareStatement(top5ProdQuery);
            pst.clearParameters();
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                ProductTO pto = new ProductTO(result.getString("product_name"), result.getDouble("unit_price"));    // the exact col name ....not order_details.unit_price
                popularProds.add(pto);
            }
        } catch (SQLException sqle) {
            System.err.println("Connection error: " + sqle);
        }
        return popularProds;
    }



//    Todo: RowMapper

}
