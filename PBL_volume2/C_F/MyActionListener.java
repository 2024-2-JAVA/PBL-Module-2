package C_F;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class MyActionListener implements ActionListener {
    // C_F_CalculatorExample 클래스에서 정의된 텍스트 필드 참조
    public static JTextField FahrenheitField = C_F_CalculatorExample.FahrenheitField;
    public static JTextField Fahrenheit_result_Field = C_F_CalculatorExample.Fahrenheit_result_Field;
    public static JTextField CelsiusField = C_F_CalculatorExample.CelsiusField;
    public static JTextField Celsius_result_Field = C_F_CalculatorExample.Celsius_result_Field;

    @Override
    public void actionPerformed(ActionEvent e) {
        calculate();
    }

    // 계산 로직을 수행하는 메서드
    public void calculate() {
        try {
            // Fahrenheit -> Celsius 변환
            String fahrenheitText = FahrenheitField.getText(); // 화씨 입력값 가져오기
            if (!fahrenheitText.isEmpty()) {
                double fahrenheit = Double.parseDouble(fahrenheitText);
                double celsius = (fahrenheit - 32) * 5 / 9; // 화씨를 섭씨로 변환
                Fahrenheit_result_Field.setText(String.format("%.2f", celsius));
            }

            // Celsius -> Fahrenheit 변환
            String celsiusText = CelsiusField.getText(); // 섭씨 입력값 가져오기
            if (!celsiusText.isEmpty()) {
                double celsius = Double.parseDouble(celsiusText);
                double fahrenheit = (celsius * 9 / 5) + 32; // 섭씨를 화씨로 변환
                Celsius_result_Field.setText(String.format("%.2f", fahrenheit));
            }
        } catch (NumberFormatException ex) {
            // 잘못된 입력값 처리
            Fahrenheit_result_Field.setText("숫자를 입력하세요.");
            Celsius_result_Field.setText("숫자를 입력하세요.");
        }
    }
}
