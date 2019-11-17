package io.turntabl;

import java.sql.SQLException;
import java.util.List;

public interface SalesDAO {
    public List<SalesTO> weeklySalesReport() throws ClassNotFoundException, SQLException;
}
