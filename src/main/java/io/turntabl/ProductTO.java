package io.turntabl;

public class ProductTO {
    private String productName;
    private Double unitPrice;   // not double
//    Todo: try include foreign fields wrt inner join

    public ProductTO() {}

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "ProductTO{" +
                "productName='" + productName + '\'' +
                ", price=" + unitPrice +
                '}';
    }
}
