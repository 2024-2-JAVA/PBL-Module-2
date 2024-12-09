package Logic;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;  // 이 줄 추가
import java.util.Collections;

public class FBOLogic {
    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    public double calculateDiscount(double originalPrice, double discountRate) {
        if (originalPrice < 0 || discountRate < 0 || discountRate > 100) {
            throw new IllegalArgumentException("Invalid input values");
        }
        return originalPrice * (1 - discountRate / 100);
    }

    public double meterToInch(double meters) {
        return meters * 39.3701;
    }

    public double inchToMeter(double inches) {
        return inches / 39.3701;
    }

    public String decimalToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    public String decimalToOctal(int decimal) {
        return Integer.toOctalString(decimal);
    }

    public String decimalToHex(int decimal) {
        return Integer.toHexString(decimal).toUpperCase();
    }

    // 통계 관련 메소드들
    public double calculateMean(List<Double> numbers) {
        return numbers.stream()
        .mapToDouble(Double::doubleValue)
        .average()
        .orElse(0.0);
    }

    public double calculateMedian(List<Double> numbers) {
        List<Double> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        int n = sorted.size();
        if (n % 2 == 0) {
            return (sorted.get(n/2-1) + sorted.get(n/2)) / 2.0;
        } else {
            return sorted.get(n/2);
        }
    }

    public String calculateMode(List<Double> numbers) {
        Map<Double, Integer> frequencyMap = new HashMap<>();
        for (Double num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int maxFrequency = Collections.max(frequencyMap.values());
        List<Double> modes = new ArrayList<>();
        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modes.add(entry.getKey());
            }
        }

        if (modes.size() == numbers.size()) {
            return "없음";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < modes.size(); i++) {
            result.append(String.format("%.2f", modes.get(i)));
            if (i < modes.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    public double calculateStandardDeviation(List<Double> numbers) {
        double mean = calculateMean(numbers);
        double sumSquaredDiff = numbers.stream()
            .mapToDouble(num -> Math.pow(num - mean, 2))
            .sum();
        return Math.sqrt(sumSquaredDiff / numbers.size());
    }

    // 박스 플롯 관련 메서드들
    public double calculateQ1(List<Double> numbers) {
        List<Double> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        int n = sorted.size();

        if (n % 2 == 0) {
            return calculateMedian(sorted.subList(0, n/2));
        } else {
            return calculateMedian(sorted.subList(0, n/2));
        }
    }

    public double calculateQ3(List<Double> numbers) {
        List<Double> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        int n = sorted.size();

        if (n % 2 == 0) {
            return calculateMedian(sorted.subList(n/2, n));
        } else {
            return calculateMedian(sorted.subList(n/2 + 1, n));
        }
    }

    public double calculateIQR(List<Double> numbers) {
        return calculateQ3(numbers) - calculateQ1(numbers);
    }

    public List<Double> findOutliers(List<Double> numbers) {
        double q1 = calculateQ1(numbers);
        double q3 = calculateQ3(numbers);
        double iqr = calculateIQR(numbers);

        double lowerFence = q1 - 1.5 * iqr;
        double upperFence = q3 + 1.5 * iqr;

        List<Double> outliers = new ArrayList<>();
        for (Double value : numbers) {
            if (value < lowerFence || value > upperFence) {
                outliers.add(value);
            }
        }
        return outliers;
    }

    public double[] getWhiskers(List<Double> numbers) {
        double q1 = calculateQ1(numbers);
        double q3 = calculateQ3(numbers);
        double iqr = calculateIQR(numbers);

        double lowerFence = q1 - 1.5 * iqr;
        double upperFence = q3 + 1.5 * iqr;

        List<Double> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);

        // 수염(whisker) 계산
        double lowerWhisker = sorted.get(0);
        double upperWhisker = sorted.get(sorted.size() - 1);

        // 이상치가 아닌 최소/최대값 찾기
        for (Double value : sorted) {
            if (value >= lowerFence) {
                lowerWhisker = value;
                break;
            }
        }

        for (int i = sorted.size() - 1; i >= 0; i--) {
            if (sorted.get(i) <= upperFence) {
                upperWhisker = sorted.get(i);
                break;
            }
        }

        return new double[] {lowerWhisker, upperWhisker};
    }
    // ... 기존 코드 ...

    // 운동 관련 상수
    private static final double BENCH_PRESS_K = 0.05;
    private static final double WALKING_MET = 3.8;
    private static final double JOGGING_MET = 7.5;
    private static final double CYCLING_MET = 10.0;
    private static final double SWIMMING_MET = 8.0;

    // 벤치프레스 칼로리 계산
    public double calculateBenchPressCalories(double weight, int reps) {
        return (weight * reps) * BENCH_PRESS_K;
    }

    // 유산소 운동 칼로리 계산
    public double calculateAerobicCalories(String exercise, double weight, int time) {
        double metValue = getMETValue(exercise);
        return metValue * weight * 0.0175 * time;
    }

    // 운동 종류에 따른 MET 값 반환
    private double getMETValue(String exercise) {
        return switch (exercise) {
            case "Walking" -> WALKING_MET;
            case "Jogging" -> JOGGING_MET;
            case "Cycling" -> CYCLING_MET;
            case "Swimming" -> SWIMMING_MET;
            default -> 0;
        };
    }
}