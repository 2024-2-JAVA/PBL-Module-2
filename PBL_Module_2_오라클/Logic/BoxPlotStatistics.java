package Logic;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class BoxPlotStatistics {
    private double[] data;
    private double q1;
    private double q3;
    private double median;
    private double iqr;
    private List<Double> outliers;

    public BoxPlotStatistics(double[] inputData) {
        this.data = inputData.clone();
        Arrays.sort(this.data);
        calculateStatistics();
    }

    private void calculateStatistics() {
        // 중앙값 계산
        int n = data.length;
        if (n % 2 == 0) {
            median = (data[n/2-1] + data[n/2]) / 2.0;
        } else {
            median = data[n/2];
        }

        // Q1, Q3 계산
        if (n % 2 == 0) {
            q1 = calculateMedian(Arrays.copyOfRange(data, 0, n/2));
            q3 = calculateMedian(Arrays.copyOfRange(data, n/2, n));
        } else {
            q1 = calculateMedian(Arrays.copyOfRange(data, 0, n/2));
            q3 = calculateMedian(Arrays.copyOfRange(data, n/2 + 1, n));
        }

        // IQR 계산
        iqr = q3 - q1;

        // 이상치 찾기
        findOutliers();
    }

    private double calculateMedian(double[] arr) {
        int n = arr.length;
        if (n % 2 == 0) {
            return (arr[n/2-1] + arr[n/2]) / 2.0;
        } else {
            return arr[n/2];
        }
    }

    private void findOutliers() {
        outliers = new ArrayList<>();
        double lowerFence = q1 - 1.5 * iqr;
        double upperFence = q3 + 1.5 * iqr;

        for (double value : data) {
            if (value < lowerFence || value > upperFence) {
                outliers.add(value);
            }
        }
    }

    // Getter 메소드들
    public double getQ1() { return q1; }
    public double getQ3() { return q3; }
    public double getMedian() { return median; }
    public double getIQR() { return iqr; }
    public List<Double> getOutliers() { return outliers; }
    public double[] getData() { return data; }
}