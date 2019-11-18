package io.turntabl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface SalesDAO {
    public List<SalesTO> weeklySalesReport(Date date) throws ClassNotFoundException, SQLException;
}
