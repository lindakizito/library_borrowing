package model;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import javax.swing.*;
import java.util.List;
import java.util.Map;

public class BookChart {

    public static void displayBarChart(List<Book> topBooks) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Book book : topBooks) {
            dataset.addValue(book.getBorrowCount(), "Borrows", book.getTitle());
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Top Borrowed Books", "Book Title", "Borrow Count", dataset
        );
        showChart(barChart, "Bar Chart - Top Borrowed Books");
    }

    public static void displayPieChart(Map<String, Integer> genreMap) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : genreMap.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Genre Distribution", dataset, true, true, false
        );
        showChart(pieChart, "Pie Chart - Genre Distribution");
    }

    public static void displayHistogram(List<Integer> daysBorrowedList) {
        double[] values = daysBorrowedList.stream().mapToDouble(i -> i).toArray();
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Days Borrowed", values, 10);
        JFreeChart histogram = ChartFactory.createHistogram(
                "Borrow Duration Histogram", "Days Borrowed", "Frequency", dataset
        );
        showChart(histogram, "Histogram - Borrow Duration");
    }

    private static void showChart(JFreeChart chart, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
