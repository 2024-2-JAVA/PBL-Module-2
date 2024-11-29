package meter_inch;
import javax.swing.*;
import java.awt.*;

public class Meter_inch_CalculatorExample extends JFrame {
    // 텍스트 필드 및 버튼 선언
    public static JTextField meterField = new JTextField();
    public static JTextField meterResultField = new JTextField();
    public static JTextField inchField = new JTextField();
    public static JTextField inchResultField = new JTextField();

    private JButton convertButton;
    private JButton convertButton1;
    private JButton resetButton; 
    private JButton resetButton1;

    public Meter_inch_CalculatorExample() {
        // 프레임 설정
        setTitle("Meter-Inch Converter");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));

        // 라벨 및 텍스트 필드 추가
        convertButton = new JButton("Convert Meters");
        convertButton1 = new JButton("Convert Inches");
        resetButton = new JButton("Reset Meters");
        resetButton1 = new JButton("Reset Inches");
        
        add(new JLabel("Meters to Inches:"));
        add(meterField);
        add(new JLabel("Inches Result:"));
        add(meterResultField);
        add(convertButton);
        add(resetButton);
        add(new JLabel("Inches to Meters:"));
        add(inchField);
        add(new JLabel("Meters Result:"));
        add(inchResultField);
        add(convertButton1);
        add(resetButton1);
    
        inchResultField.setEditable(false); // 결과 필드는 수정 불가
        meterResultField.setEditable(false); // 결과 필드는 수정 불가
        
        // ActionListener 연결
        MyActionListener actionListener = new MyActionListener(meterField, meterResultField, inchField, inchResultField);
        convertButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);
        convertButton1.addActionListener(actionListener);
        resetButton1.addActionListener(actionListener);
    }
}
