package io.turntabl;

import java.util.Date;

public class SalesTO {
    private String customerName;
    private String productName;
    private Double amount;

    public SalesTO(Date orderDate, String customerName, String productName, Double amount) {
        this.customerName = customerName;
        this.productName = productName;
        this.amount = amount;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getProductName() {
        return this.productName;
    }

    public Double getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "SalesTO{" +
                "customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
