import java.sql.*;

public class DatabaseManager implements Database {

    private final String path;
    private Connection connection;

    public DatabaseManager(String path) {

        this.connection = null;
        this.path = path;

    }

    @Override
    public Connection connection() throws SQLException {

        return DriverManager.getConnection("jdbc:sqlite:" + this.path + "/weather.db");

    }

    @Override
    public void createTable() throws SQLException {

        this.connection = connection();
        this.connection.createStatement().execute("CREATE TABLE IF NOT EXISTS weather (\n" + "	ts long,\n" + "	lat double,\n" + "	lon double,\n" + "	temperature double,\n" + "	pressure double,\n" + "	humidity double\n" + ");");

    }

    @Override
    public void dataInserter(Long ts, Double lat, Double lon, Double temp, Double pressure, Double humidity) throws SQLException {

        PreparedStatement addDatabaseEntry = this.connection.prepareStatement("INSERT INTO weather(ts,lat,lon,temperature,pressure,humidity) VALUES(?,?,?,?,?,?)");
        addDatabaseEntry.setLong(1, ts);
        addDatabaseEntry.setDouble(2, lat);
        addDatabaseEntry.setDouble(3, lon);
        addDatabaseEntry.setDouble(4, temp);
        addDatabaseEntry.setDouble(5, pressure);
        addDatabaseEntry.setDouble(6, humidity);
        addDatabaseEntry.executeUpdate();

    }
}