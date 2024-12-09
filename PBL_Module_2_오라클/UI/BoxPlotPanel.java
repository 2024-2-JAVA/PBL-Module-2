package UI;

import Logic.BoxPlotStatistics;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BoxPlotPanel extends JPanel {
    private BoxPlotStatistics stats;
    private final int PADDING = 50;
    private final int BOX_HEIGHT = 40;

    public BoxPlotPanel(double[] data) {
        stats = new BoxPlotStatistics(data);
        setPreferredSize(new Dimension(600, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double[] data = stats.getData();
        double min = data[0];
        double max = data[data.length - 1];
        double range = max - min;

        // 스케일 계산
        int width = getWidth() - 2 * PADDING;
        double scale = width / range;

        // Y 위치 계산
        int y = getHeight() / 2;

        // 박스 그리기
        int x1 = PADDING + (int)((stats.getQ1() - min) * scale);
        int x2 = PADDING + (int)((stats.getQ3() - min) * scale);
        int xMedian = PADDING + (int)((stats.getMedian() - min) * scale);

        // 박스 그리기
        g2.setColor(new Color(200, 200, 255));
        g2.fillRect(x1, y - BOX_HEIGHT/2, x2-x1, BOX_HEIGHT);
        g2.setColor(Color.BLACK);
        g2.drawRect(x1, y - BOX_HEIGHT/2, x2-x1, BOX_HEIGHT);

        // 중앙선 그리기
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(xMedian, y - BOX_HEIGHT/2, xMedian, y + BOX_HEIGHT/2);

        // 수염 그리기
        int whiskerLeft = PADDING + (int)((data[0] - min) * scale);
        int whiskerRight = PADDING + (int)((data[data.length-1] - min) * scale);
        g2.drawLine(whiskerLeft, y, x1, y);
        g2.drawLine(whiskerRight, y, x2, y);
        g2.drawLine(whiskerLeft, y - 10, whiskerLeft, y + 10);
        g2.drawLine(whiskerRight, y - 10, whiskerRight, y + 10);

        // 이상치 그리기
        g2.setColor(Color.RED);
        List<Double> outliers = stats.getOutliers();
        for (Double outlier : outliers) {
            int xOutlier = PADDING + (int)((outlier - min) * scale);
            g2.fillOval(xOutlier-3, y-3, 6, 6);
        }

        // 통계값 표시
        g2.setColor(Color.BLACK);
        g2.drawString(String.format("중앙값: %.2f", stats.getMedian()), 10, 20);
        g2.drawString(String.format("Q1: %.2f", stats.getQ1()), 10, 40);
        g2.drawString(String.format("Q3: %.2f", stats.getQ3()), 10, 60);
        g2.drawString(String.format("IQR: %.2f", stats.getIQR()), 10, 80);
    }
}