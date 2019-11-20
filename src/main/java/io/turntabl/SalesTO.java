package io.turntabl;

import java.util.Date;

public class SalesTO {
    private String customerName;
    private String productName;
    private Double amount;

    // todo: change to setters if no job
//    public SalesTO(Date orderDate, String customerName, String productName, Double amount) {
//        this.customerName = customerName;
//        this.productName = productName;
//        this.amount = amount;
//    }

    SalesTO(){}

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setAmount(Double amount) {
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
