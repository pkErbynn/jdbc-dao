package io.turntabl;

import java.util.List;

public interface CustomerDAO {
    public List<CustomerTO> getAllCustomers();
    public int numberOfCustomers();
}
