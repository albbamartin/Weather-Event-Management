import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface Database {
    Connection connection() throws IOException, SQLException;
    void createTable() throws IOException, SQLException;
    void dataInserter(Long ts, Double lat, Double lon, Double temp, Double pressure, Double humidity) throws SQLException;
}
