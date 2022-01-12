import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ExportDatabaseToExcel {

    public void exporter(String path) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + path + "/weather.db");

            PrintWriter pw = new PrintWriter(new FileGenerator().filePath(System.getProperty("user.dir") + "/analytics/predictor/weather.csv"));
            StringBuilder sb = new StringBuilder();

            PreparedStatement ps = connection.prepareStatement("SELECT ts,temperature FROM weather");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sb.append(rs.getLong("ts")).append(",").append(rs.getDouble("temperature")).append("\n");
            }

            pw.write(sb.toString());
            pw.close();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
