package formation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class MyActionListener implements ActionListener {
    // 각 진법에 해당하는 텍스트 필드
    public static JTextField decimal_numberField = FormCalculatorExample.decimal_numberField;
    public static JTextField binary_numberField = FormCalculatorExample.binary_numberField;
    public static JTextField octal_numberField = FormCalculatorExample.octal_numberField;
    public static JTextField hexadecimal_numberField = FormCalculatorExample.hexadecimal_numberField;

    @Override
    public void actionPerformed(ActionEvent e) {
        calculate();
    }

    // 계산 로직을 수행하는 메서드
    public void calculate() {
        try {
            // 10진수 입력 값 가져오기
            String decimalText = decimal_numberField.getText();

            // 10진수에서 다른 진법으로 변환
            int decimalNumber = Integer.parseInt(decimalText); // 10진수로 변환

            // 2진수 변환
            String binary = Integer.toBinaryString(decimalNumber);
            binary_numberField.setText(binary);

            // 8진수 변환
            String octal = Integer.toOctalString(decimalNumber);
            octal_numberField.setText(octal);

            // 16진수 변환
            String hexadecimal = Integer.toHexString(decimalNumber).toUpperCase(); // 대문자로 출력
            hexadecimal_numberField.setText(hexadecimal);
        } catch (NumberFormatException ex) {
            // 잘못된 입력 처리 (숫자가 아닌 값을 입력했을 때)
            binary_numberField.setText("Invalid input");
            octal_numberField.setText("Invalid input");
            hexadecimal_numberField.setText("Invalid input");
        }
    }
}

