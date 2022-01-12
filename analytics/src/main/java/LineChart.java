import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

public interface LineChart {
    XYSeriesCollection addValues();
    void createChart(XYDataset dataset);
    void SaveChartsAsPNG(JFreeChart chart);
}
