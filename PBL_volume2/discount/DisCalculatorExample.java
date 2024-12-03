package discount;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisCalculatorExample extends JFrame {
    // 텍스트 필드 선언
    public static JTextField costField = new JTextField(10);  // 원가 입력 필드
    public static JTextField discountField = new JTextField(10);  // 할인율 입력 필드
    public static JTextField resultField = new JTextField(10);  // 결과 필드

    public DisCalculatorExample() {
        super("Discount Calculator"); // 프레임 제목 설정
        setSize(400, 250); // 프레임 크기 설정
        setLayout(new GridLayout(4, 2, 10, 10)); // 레이아웃 설정

        // 라벨 추가
        add(new JLabel("Original Price: "));
        styleTextField(costField);
        add(costField);

        add(new JLabel("Discount Rate (%): "));
        styleTextField(discountField);
        add(discountField);

        // 계산 버튼 추가
        JButton calculateButton = new JButton("Calculate");
        styleButton(calculateButton);
        calculateButton.addActionListener(new CalculateActionListener());
        add(calculateButton);

        add(new JLabel(""));

        // 결과 필드
        add(new JLabel("Discounted Price: "));
        styleTextField(resultField);
        resultField.setEditable(false);
        add(resultField);
    }

    // 텍스트 필드 스타일 지정
    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        textField.setBackground(Color.WHITE);
    }

    // 버튼 스타일 지정
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
    }

    // 계산 버튼 동작 정의
    private class CalculateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double cost = Double.parseDouble(costField.getText());
                double discountRate = Double.parseDouble(discountField.getText());
                if (discountRate < 0 || discountRate > 100) {
                    throw new IllegalArgumentException("Discount rate must be between 0 and 100.");
                }
                double discountedPrice = cost - (cost * discountRate / 100);
                resultField.setText(String.format("%.2f", discountedPrice));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
