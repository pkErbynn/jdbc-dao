package io.turntabl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SalesDAOImpl implements SalesDAO {
    private static final String URL = "jdbc:postgresql:northwind";
    private static final String USERNAME = "john-erbynn";
    private static final String PASSWORD = "turntabl";

    @Override
    public List<SalesTO> weeklySalesReport(Date date) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        final String salesRepQuery = "select order_date as dates, customers.contact_name, products.product_name,order_details.unit_price " +
                "from orders inner join order_details on order_details.order_id=orders.order_id " +
                "inner join customers on customers.customer_id = orders.customer_id " +
                "inner join products on products.product_id=order_details.product_id " +
                "where order_date between date('1997-07-08') - '7 day'::interval and '1997-07-08'";
        List<SalesTO> sales = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement pst = conn.prepareStatement(salesRepQuery);
            pst.clearParameters();
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                SalesTO sto = new SalesTO(result.getDate("order_date")
                        , result.getString("contact_name")
                        , result.getString("product_name")
                        , result.getDouble("amount")
                );
                sales.add(sto);
            }
        } catch (SQLException sqlex) {
            System.err.println("Connection error: " + sqlex);
        }
        return sales;
    }


}
