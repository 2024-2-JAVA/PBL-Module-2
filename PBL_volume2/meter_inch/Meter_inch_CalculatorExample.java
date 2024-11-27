package meter_inch;
import javax.swing.*;
import java.awt.*;

public class Meter_inch_CalculatorExample extends JFrame {
    // 텍스트 필드 및 버튼 선언
    public static JTextField meterField = new JTextField();
    public static JTextField meterResultField = new JTextField();
    public static JTextField inchField = new JTextField();
    public static JTextField inchResultField = new JTextField();

    public Meter_inch_CalculatorExample() {
        // 프레임 설정
        setTitle("Meter-Inch Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));

        // 라벨 및 텍스트 필드 추가
        add(new JLabel("Meters to Inches:"));
        add(meterField);

        add(new JLabel("Inches Result:"));
        meterResultField.setEditable(false); // 결과 필드는 수정 불가
        add(meterResultField);

        JButton convertButton = new JButton("Convert Meters");
        convertButton.setActionCommand("Convert_meters");
        add(convertButton);

        JButton resetButton = new JButton("Reset Meters");
        resetButton.setActionCommand("Reset_meters");
        add(resetButton);

        add(new JLabel("Inches to Meters:"));
        add(inchField);

        add(new JLabel("Meters Result:"));
        inchResultField.setEditable(false); // 결과 필드는 수정 불가
        add(inchResultField);

        JButton convertButton1 = new JButton("Convert Inches");
        convertButton1.setActionCommand("Convert_inches");
        add(convertButton1);

        JButton resetButton1 = new JButton("Reset Inches");
        resetButton1.setActionCommand("Reset_inches");
        add(resetButton1);

        // ActionListener 연결
        MyActionListener actionListener = new MyActionListener();
        convertButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);
        convertButton1.addActionListener(actionListener);
        resetButton1.addActionListener(actionListener);

        // 프레임 표시
        setVisible(true);
    }

    // main 메서드: 외부에서도 이 클래스가 열릴 수 있도록 메인 메서드를 유지합니다.
    public static void main(String[] args) {
        // 클래스를 외부에서 열 때 사용할 수 있도록 프레임 생성
        new Meter_inch_CalculatorExample();
    }
}
