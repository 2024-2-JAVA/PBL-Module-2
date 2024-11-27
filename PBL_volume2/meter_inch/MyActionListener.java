package meter_inch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

// ActionListener 구현
class MyActionListener implements ActionListener {

    // CalculatorExample 클래스에서 참조
    public static JTextField meterField = Meter_inch_CalculatorExample.meterField;
    public static JTextField meterResultField = Meter_inch_CalculatorExample.meterResultField;
    public static JTextField inchField = Meter_inch_CalculatorExample.inchField;
    public static JTextField inchResultField = Meter_inch_CalculatorExample.inchResultField;

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        if (name.equals("Convert_meters")) {
            calculate1(); // Meter -> Inches 변환
        } else if (name.equals("Convert_inches")) {
            calculate2(); // Inches -> Meters 변환
        } else if (name.equals("Reset_meters")) {
            reset1(); // Meter 필드 리셋
        } else if (name.equals("Reset_inches")) {
            reset2(); // Inch 필드 리셋
        }
    }

    // Meter -> Inches 변환
    private void calculate1() {
        try {
            String meterText = meterField.getText();
            if (!meterText.isEmpty()) {
                double meters = Double.parseDouble(meterText);
                double inches = meters * 39.3701; // 변환식: 1 Meter = 39.3701 Inches
                meterResultField.setText(String.format("%.2f", inches));
            } else {
                meterResultField.setText("Enter a value");
            }
        } catch (NumberFormatException ex) {
            meterResultField.setText("Invalid input");
        }
    }

    // Inches -> Meters 변환
    private void calculate2() {
        try {
            String inchText = inchField.getText();
            if (!inchText.isEmpty()) {
                double inches = Double.parseDouble(inchText);
                double meters = inches / 39.3701; // 변환식: 1 Inch = 1 / 39.3701 Meters
                inchResultField.setText(String.format("%.2f", meters));
            } else {
                inchResultField.setText("Enter a value");
            }
        } catch (NumberFormatException ex) {
            inchResultField.setText("Invalid input");
        }
    }

    // Meter 관련 필드 리셋
    private void reset1() {
        meterField.setText("");
        meterResultField.setText("");
    }

    // Inch 관련 필드 리셋
    private void reset2() {
        inchField.setText("");
        inchResultField.setText("");
    }
    // 계산 로직
    public void calculate() {
        try {
            // Meters -> Inches 변환
            String meterText = Meter_inch_CalculatorExample.meterField.getText();
            if (!meterText.isEmpty()) {
                double meters = Double.parseDouble(meterText);
                double inches = meters * 39.3701; // 1 Meter = 39.3701 Inches
                Meter_inch_CalculatorExample.meterResultField.setText(String.format("%.2f", inches));
            }

            // Inches -> Meters 변환
            String inchText = Meter_inch_CalculatorExample.inchField.getText();
            if (!inchText.isEmpty()) {
                double inches = Double.parseDouble(inchText);
                double meters = inches / 39.3701; // 1 Inch = 1/39.3701 Meters
                Meter_inch_CalculatorExample.inchResultField.setText(String.format("%.2f", meters));
            }
        } catch (NumberFormatException ex) {
            // 잘못된 입력값 처리
            Meter_inch_CalculatorExample.meterResultField.setText("Invalid input.");
            Meter_inch_CalculatorExample.inchResultField.setText("Invalid input.");
        }
    }
}