package C_F;

import java.awt.*;
import javax.swing.*;

public class C_F_CalculatorExample extends JFrame {
    // 결과를 표시할 텍스트 필드 (입력 및 계산 결과)
    public static JTextField FahrenheitField = new JTextField(20);
    public static JTextField Fahrenheit_result_Field = new JTextField(20);
    public static JTextField CelsiusField = new JTextField(20);
    public static JTextField Celsius_result_Field = new JTextField(20);

    public C_F_CalculatorExample() {
        super("섭씨/화씨 변환기"); // 프레임 제목 설정
        setSize(400, 300); // 프레임 크기 설정
        setLayout(null); // 절대 위치 사용

        // 화씨 -> 섭씨 변환 컴포넌트 추가
        JLabel fahrenheitLabel = new JLabel("화씨 (°F):");
        fahrenheitLabel.setBounds(30, 30, 100, 30);
        add(fahrenheitLabel);

        FahrenheitField.setBounds(130, 30, 100, 30);
        add(FahrenheitField);

        JButton fahrenheitButton = new JButton("변환");
        fahrenheitButton.setBounds(240, 30, 80, 30);
        fahrenheitButton.addActionListener(new MyActionListener());
        add(fahrenheitButton);

        JLabel fahrenheitResultLabel = new JLabel("섭씨 (°C):");
        fahrenheitResultLabel.setBounds(30, 70, 100, 30);
        add(fahrenheitResultLabel);

        Fahrenheit_result_Field.setBounds(130, 70, 190, 30);
        Fahrenheit_result_Field.setEditable(false); // 결과 필드는 편집 불가
        add(Fahrenheit_result_Field);

        // 섭씨 -> 화씨 변환 컴포넌트 추가
        JLabel celsiusLabel = new JLabel("섭씨 (°C):");
        celsiusLabel.setBounds(30, 120, 100, 30);
        add(celsiusLabel);

        CelsiusField.setBounds(130, 120, 100, 30);
        add(CelsiusField);

        JButton celsiusButton = new JButton("변환");
        celsiusButton.setBounds(240, 120, 80, 30);
        celsiusButton.addActionListener(new MyActionListener());
        add(celsiusButton);

        JLabel celsiusResultLabel = new JLabel("화씨 (°F):");
        celsiusResultLabel.setBounds(30, 160, 100, 30);
        add(celsiusResultLabel);

        Celsius_result_Field.setBounds(130, 160, 190, 30);
        Celsius_result_Field.setEditable(false); // 결과 필드는 편집 불가
        add(Celsius_result_Field);
    }
}