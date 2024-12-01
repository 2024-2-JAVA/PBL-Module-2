package BMICalculator;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculatorExample extends JFrame {
    // 텍스트 필드 및 버튼 선언
    public static JTextField weightField = new JTextField();
    public static JTextField heightField = new JTextField();
    public static JTextField bmiResultField = new JTextField();

    private JButton calculateButton;
    private JButton resetButton;

    public BMICalculatorExample() {
        // 프레임 설정
        setTitle("BMI Calculator");
        setSize(400, 200);
        setLayout(new GridLayout(4, 2, 10, 10));

        // 라벨 및 텍스트 필드 추가
        calculateButton = new JButton("Calculate BMI");
        resetButton = new JButton("Reset");

        add(new JLabel("Weight (kg):"));
        add(weightField);
        add(new JLabel("Height (m):"));
        add(heightField);
        add(new JLabel("BMI Result:"));
        add(bmiResultField);
        add(calculateButton);
        add(resetButton);

        bmiResultField.setEditable(false); // 결과 필드는 수정 불가

        // ActionListener 연결
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double weight = Double.parseDouble(weightField.getText());
                    double height = Double.parseDouble(heightField.getText());

                    if (height <= 0 || weight <= 0) {
                        JOptionPane.showMessageDialog(null, "Height and weight must be positive numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double bmi = weight / (height * height);
                    bmiResultField.setText(String.format("%.2f", bmi));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weightField.setText("");
                heightField.setText("");
                bmiResultField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BMICalculatorExample calculator = new BMICalculatorExample();
            calculator.setVisible(true);
        });
    }
}