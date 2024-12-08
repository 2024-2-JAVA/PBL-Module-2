package calorie;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FitnessCalculatorWithK extends JFrame {
    // MET 값 설정 (운동 종류별)
    private static final double BENCH_PRESS_K = 0.05;  // 벤치프레스의 K 값
    private static final double WALKING_MET = 3.8;    // 걷기
    private static final double JOGGING_MET = 7.5;    // 조깅
    private static final double CYCLING_MET = 10.0;   // 사이클링
    private static final double SWIMMING_MET = 8.0;   // 수영

    public FitnessCalculatorWithK() {
        super("Fitness Calorie Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);  // 화면 크기 설정
        setLayout(new GridLayout(2, 1, 10, 10)); // 두 개의 패널을 세로로 배치

        // 벤치프레스 패널 추가
        add(createBenchPressPanel());

        // 유산소 운동 패널 추가
        add(createAerobicExercisePanel());
    }

    // 벤치프레스 패널 생성
    private JPanel createBenchPressPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Babel workout")); // 패널 제목
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // 무게, 반복, 결과를 2행 3열로 배치

        // UI 요소 생성
        JTextField weightField = new JTextField(); // 바벨 무게 입력
        JTextField repsField = new JTextField(); // 반복 횟수 입력
        JTextField resultField = new JTextField(); // 결과 표시
        resultField.setEditable(false);

        JButton calculateButton = new JButton("Calculate");
        styleButton(calculateButton);

        // 패널에 컴포넌트 추가
        panel.add(new JLabel("\r\n" + "Babel Weight (kg):"));
        panel.add(weightField);
        panel.add(new JLabel("Reps:"));
        panel.add(repsField);
        panel.add(calculateButton);
        panel.add(resultField);

        // 버튼 클릭 이벤트 처리
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateBenchPressCalories(weightField, repsField, resultField);
            }
        });

        return panel;
    }

    // 벤치프레스 칼로리 계산
    private void calculateBenchPressCalories(JTextField weightField, JTextField repsField, JTextField resultField) {
        try {
            double weight = Double.parseDouble(weightField.getText());
            int reps = Integer.parseInt(repsField.getText());
            double caloriesBurned = (weight * reps) * BENCH_PRESS_K; // 소모 칼로리 계산
            resultField.setText(String.format("%.2f", caloriesBurned));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid weight and reps!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 유산소 운동 패널 생성
    private JPanel createAerobicExercisePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Aerobic Exercise")); // 패널 제목
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // 운동 종류, 체중, 시간, 결과를 2행 4열로 배치

        // UI 요소 생성
        JComboBox<String> exerciseComboBox = new JComboBox<>(new String[]{"Walking", "Jogging", "Cycling", "Swimming"}); // 운동 종류
        JTextField weightField = new JTextField(); // 체중 입력
        JTextField timeField = new JTextField(); // 운동 시간 입력
        JTextField resultField = new JTextField(); // 결과 표시
        resultField.setEditable(false);

        JButton calculateButton = new JButton("Calculate");
        styleButton(calculateButton);

        // 패널에 컴포넌트 추가
        panel.add(new JLabel("Exercise:"));
        panel.add(exerciseComboBox);
        panel.add(new JLabel("Weight (kg):"));
        panel.add(weightField);
        panel.add(new JLabel("Time (minutes):"));
        panel.add(timeField);
        panel.add(calculateButton);
        panel.add(resultField);

        // 버튼 클릭 이벤트 처리
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAerobicCalories(exerciseComboBox, weightField, timeField, resultField);
            }
        });

        return panel;
    }

    // 유산소 운동 칼로리 계산
    private void calculateAerobicCalories(JComboBox<String> exerciseComboBox, JTextField weightField, JTextField timeField, JTextField resultField) {
        try {
            String selectedExercise = (String) exerciseComboBox.getSelectedItem();
            double weight = Double.parseDouble(weightField.getText());
            int time = Integer.parseInt(timeField.getText());

            double metValue = getMETValue(selectedExercise);
            double caloriesBurned = metValue * weight * 0.0175 * time; // 칼로리 계산

            resultField.setText(String.format("%.2f", caloriesBurned));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid weight and time!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 운동 종류에 따른 MET 값 반환
    private double getMETValue(String exercise) {
        switch (exercise) {
            case "Walking":
                return WALKING_MET;
            case "Jogging":
                return JOGGING_MET;
            case "Cycling":
                return CYCLING_MET;
            case "Swimming":
                return SWIMMING_MET;
            default:
                return 0;
        }
    }

    // 버튼 스타일 설정
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.LIGHT_GRAY);
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FitnessCalculatorWithK frame = new FitnessCalculatorWithK();
            frame.setVisible(true);
        });
    }
}