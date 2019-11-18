package io.turntabl;

import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        JDBC jdbc = new JDBC();
        jdbc.readCustomerData();

        /*System.out.println("Enter name: >>> ");
        Scanner inp = new Scanner(System.in);
        String name = inp.nextLine();
        jdbc.searchCustomerName(name);*/
//        jdbc.searchCategoryByName("Beverages");
//        jdbc.searchCategoryByName(name);


//        dao
        ProductDAOImpl productDAO = new ProductDAOImpl();
//        productDAO.showAllProductsByCustomer("Karin Josephs");
//        for (ProductTO prod : productDAO.showAllProductsByCustomer("Karin Josephs")) {
//            System.out.printf("%10s %50s", prod.getProductName(), prod.getPrice());
//            System.out.println();
//        }

        // products bought by customer
        productDAO.showAllProductsByCustomer("Karin Josephs").stream()
                .forEach(c -> System.out.printf( "%30s %20s", c.getProductName(), c.getPrice() + "\n"));

//        sales report
        SalesDAOImpl salesDAO = new SalesDAOImpl();
        salesDAO.weeklySalesReport(new Date(1996 ,6 ,10)).stream()
                .forEach(e -> System.out.printf("%10s %35s %25s %20s"
                        , e.getOrderDate()
                        , e.getCustomerName()
                        , e.getProductName()
                        , e.getAmount())
                );



    }
}
