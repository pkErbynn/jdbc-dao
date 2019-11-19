package io.turntabl;

import java.util.List;

public interface ProductDAO {
    public  List<ProductTO> getAllProducts();
    public List<ProductTO> showAllProductsByCustomer(String customerName) throws ClassNotFoundException;
    public List<ProductTO> displayTop5PopularProducts() throws ClassNotFoundException;
}
