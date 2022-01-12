import java.awt.*;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.*;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.sql.*;

public class WeatherLineChart implements LineChart{

    private final String path;

    public WeatherLineChart(String path) {
        this.path = path;
    }

    @Override
    public XYSeriesCollection addValues() {

        XYSeries temp = new XYSeries("Temperature");
        XYSeries pressure = new XYSeries("Pressure");
        XYSeries humidity = new XYSeries("Humidity");

        try {

            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + this.path + "/weather.db");
            PreparedStatement ps = connection.prepareStatement("SELECT ts,temperature,pressure,humidity FROM weather");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                temp.add(rs.getLong("ts"), rs.getDouble("temperature"));
                pressure.add(rs.getLong("ts"), rs.getDouble("pressure"));
                humidity.add(rs.getLong("ts"), rs.getDouble("humidity"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        var dataset = new XYSeriesCollection();

        dataset.addSeries(temp);
        dataset.addSeries(pressure);
        dataset.addSeries(humidity);

        return dataset;
    }

    @Override
    public void createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("Temperature, humidity and pressure evolution", "Date", "Measure", dataset, PlotOrientation.VERTICAL, true, false, false);

        var renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.ORANGE);

        XYPlot plot = chart.getXYPlot();

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        SaveChartsAsPNG(chart);
    }

    @Override
    public void SaveChartsAsPNG(JFreeChart chart){
        try {

            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

            final File file = new FileGenerator().filePath(this.path + "/Linechart.png");

            ChartUtilities.saveChartAsPNG(file, chart, 600, 400, info);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
