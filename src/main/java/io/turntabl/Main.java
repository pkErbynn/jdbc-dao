package io.turntabl;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
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
        productDAO.showAllProductsByCustomer("Karin Josephs").stream()
                .forEach(c -> System.out.printf( "%30s %20s", c.getProductName(), c.getPrice() + "\n"));




    }
}
