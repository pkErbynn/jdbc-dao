package io.turntabl;

import java.util.Date;

public class SalesTO {
    private Date orderDate;
    private String customerName;
    private String productName;
    private Double amount;

    public SalesTO(Date orderDate, String customerName, String productName, Double amount) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.productName = productName;
        this.amount = amount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "SalesTO{" +
                "orderDate=" + orderDate +
                ", customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
