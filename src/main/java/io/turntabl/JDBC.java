package io.turntabl;

/* jdbc without dao
* */

import org.graalvm.compiler.debug.CSVUtil;

import java.sql.*;

public class JDBC {

    public static void readCustomerData() throws ClassNotFoundException {
        // load the JDBC driver from prostgresql
        Class.forName("org.postgresql.Driver");

        // build the connection string
        String dbUrl = "jdbc:postgresql:northwind";

        // get the connection object
        try (Connection dbCon = DriverManager.getConnection(dbUrl, "john-erbynn", "turntabl")) {
            // obtain statement object
            Statement s = dbCon.createStatement();

            // statement execution with cursor
            ResultSet rs = s.executeQuery("select * from customers limit 20; ");

            // headings
            System.out.println("*****************************************************************************************************************************************");
            System.out.printf("%10s %10s %30s %1s %10s %10s %10s", "CONTACT NAME", "CONTACT TITLE", "ADDRESS", "CITY", "REGION", "COUNTRY", "PHONE \n");
            System.out.println("*****************************************************************************************************************************************");

            // result manipulation
            while (rs.next()) {
                System.out.format("%10s %10s %30s %1s %10s %10s %10s",
                        rs.getString("contact_name"),
                        rs.getString("contact_title"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("region"),
                        rs.getString("country"),
                        rs.getString("phone")
                );
                System.out.println();
            }
        } catch (SQLException sqle) {
            System.err.println("Connection error: " + sqle);
        }
    }


    public static void searchCustomerName(String name) throws ClassNotFoundException {
        // load the JDBC driver from prostgresql
        Class.forName("org.postgresql.Driver");

        // build the connection string
        String dbUrl = "jdbc:postgresql:northwind";

        // get the connection object
        try (Connection db = DriverManager.getConnection(dbUrl, "john-erbynn", "turntabl")) {
            // obtain statement object
            Statement s = db.createStatement();

            // statement execution with cursor
            ResultSet rs = s.executeQuery("select * from customers where contact_name ='" + name +"'; ");
//            ResultSet rs = s.executeQuery("select * from customers where contact_name like '%"+name+"%';");

            // headings
            System.out.println("*****************************************************************************************************************************************");
            System.out.printf("%10s %10s %30s %1s %10s", "CONTACT_NAME", "TITLE", "CITY", "COUNTRY", "PHONE \n");
            System.out.println("*****************************************************************************************************************************************");

            // result manipulation
            while (rs.next()) {
                System.out.format("%10s %10s %30s %1s %10s",
                        rs.getString("contact_name"),
                        rs.getString("contact_title"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getString("phone"));
            }
            System.out.println();

        } catch (SQLException sqle) {
            System.err.println("Connection error: " + sqle);
        }

    }


    // using prepared statement
    public static void searchCategoryByName(String name) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String dbUrl = "jdbc:postgresql:northwind";
        try (Connection db = DriverManager.getConnection(dbUrl, "john-erbynn", "turntabl")) {
            PreparedStatement preparedStatement = db.prepareStatement("select * from categories where category_name = ?; ");
            preparedStatement.clearParameters();

//            preparedStatement.setString(1, "Beverages");
            preparedStatement.setString(1, name);   // => name matched to the first ?

            ResultSet result = preparedStatement.executeQuery();

            System.out.println("*****************************************************************************************************************************************");
            System.out.printf("%10s %60s", "CATEGORY_NAME", "DESCRIPTION\n");
            System.out.println("*****************************************************************************************************************************************");

            // result manipulation
            while (result.next()) {
                System.out.format("%2s %70s",
                        result.getString("category_name"),
                        result.getString("description")
                );
                System.out.println();
            }
        } catch (SQLException sqle) {
            System.err.println("Connection error: " + sqle);
        }
    }

    // TODO: create prepared statement for Product and Employee name searches

    public static void searchByProductName(String name) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String dbUrl = "jdbc:postgresql:northwind";
        try (Connection db = DriverManager.getConnection(dbUrl, "john-erbynn", "turntabl")) {
            PreparedStatement preparedStatement = db.prepareStatement("select * from products where product_name = ?; ");
            preparedStatement.clearParameters();
            preparedStatement.setString(1, name);   // => name matched to the first ?
            ResultSet result = preparedStatement.executeQuery();
            System.out.println("*****************************************************************************************************************************************");
            System.out.printf("%10s %60s", "PROD_NAME", "UNIT_PRICE\n");
            System.out.println("*****************************************************************************************************************************************");
            while (result.next()) {
                System.out.format("%2s %70s",
                        result.getString("product_name"),
                        result.getString("unit_price")
                );
                System.out.println();
            }
        } catch (SQLException sqle) {
            System.err.println("Connection error: " + sqle);
        }
    }


    public static void searchEmployeeByName(String name) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String dbUrl = "jdbc:postgresql:northwind";
        try (Connection db = DriverManager.getConnection(dbUrl, "john-erbynn", "turntabl")) {
            PreparedStatement preparedStatement = db.prepareStatement("select * from employees where first_name = ?; ");
            preparedStatement.clearParameters();
            preparedStatement.setString(1, name);   // => name matched to the first ?
            ResultSet result = preparedStatement.executeQuery();

            System.out.println("*****************************************************************************************************************************************");
            System.out.printf("%10s %60s", "FIRST_NAME", "CITY\n");
            System.out.println("*****************************************************************************************************************************************");

            while (result.next()) {
                System.out.format("%2s %70s",
                        result.getString("first_name"),
                        result.getString("city")
                );
                System.out.println();
            }
        } catch (SQLException sqle) {
            System.err.println("Connection error: " + sqle);
        }
    }


//    TODO: consider clean code :)....connection, queries & print


}
