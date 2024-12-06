package UI;

import Logic.FBOLogic;  // FBOLogic import 추가
import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame {
    private final JPanel contentPanel;
    private final DefaultListModel<String> recordListModel;
    private final FBOLogic logic = new FBOLogic();  // FBOLogic 인스턴스 추가

    public MainApplication() {
        // 메인 애플리케이션 설정
        setTitle("Multifunctional Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 좌측 메뉴 패널 생성
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.LIGHT_GRAY);
        menuPanel.setPreferredSize(new Dimension(150, getHeight()));

        // 메뉴 버튼 생성
        JButton calculatorButton = new JButton("계산기");
        JButton tempButton = new JButton("온도변환");
        JButton numberSystemButton = new JButton("진법변환");
        JButton meterInchButton = new JButton("미터인치변환");
        JButton discountButton = new JButton("할인계산기");
        JButton bmiCalculatorButton = new JButton("BMI");
        JButton statisticsButton = new JButton("통계");
        JButton calorieButton = new JButton("칼로리 계산");
        JButton graphingToolButton = new JButton("그래프");

        // 메뉴 버튼 스타일 설정 부분에 추가
        calculatorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        meterInchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tempButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        numberSystemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        discountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bmiCalculatorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        statisticsButton.setAlignmentX(Component.CENTER_ALIGNMENT);    // 추가
        calorieButton.setAlignmentX(Component.CENTER_ALIGNMENT);      // 추가
        graphingToolButton.setAlignmentX(Component.CENTER_ALIGNMENT); // 추가

        // 버튼을 메뉴 패널에 추가하는 부분에 추가
        menuPanel.add(Box.createVerticalGlue());
        menuPanel.add(calculatorButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(meterInchButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(tempButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(numberSystemButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(discountButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(bmiCalculatorButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(statisticsButton);                              // 추가
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(calorieButton);                                // 추가
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(graphingToolButton);                           // 추가
        menuPanel.add(Box.createVerticalGlue());

        add(menuPanel, BorderLayout.WEST);

        // 콘텐츠 패널 생성
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // 기록 패널 생성
        JPanel recordPanel = new JPanel(new BorderLayout());
        recordListModel = new DefaultListModel<>();
        JList<String> recordList = new JList<>(recordListModel);
        JScrollPane scrollPane = new JScrollPane(recordList);

        // 기록 편집 및 삭제 버튼 추가
        JPanel recordButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = new JButton("Delete Record");
        JButton editButton = new JButton("Edit Record");
        recordButtonPanel.add(editButton);
        recordButtonPanel.add(deleteButton);

        deleteButton.addActionListener(e -> {
                    int selectedIndex = recordList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        recordListModel.remove(selectedIndex); // 선택된 기록 삭제
                    }
            });

        editButton.addActionListener(e -> {
                    int selectedIndex = recordList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String newRecord = JOptionPane.showInputDialog(this, "Edit Record:", recordListModel.get(selectedIndex));
                        if (newRecord != null && !newRecord.isBlank()) {
                            recordListModel.set(selectedIndex, newRecord); // 기록 수정
                        }
                    }
            });

        recordPanel.add(new JLabel("Calculation History", SwingConstants.CENTER), BorderLayout.NORTH);
        recordPanel.add(scrollPane, BorderLayout.CENTER);
        recordPanel.add(recordButtonPanel, BorderLayout.SOUTH);
        recordPanel.setPreferredSize(new Dimension(200, getHeight()));
        add(recordPanel, BorderLayout.EAST);

        // 메뉴 버튼 동작 추가
        calculatorButton.addActionListener(e -> loadFeature("Calculator"));
        tempButton.addActionListener(e -> loadFeature("Temperature"));
        numberSystemButton.addActionListener(e -> loadFeature("Number System"));
        meterInchButton.addActionListener(e -> loadFeature("Meter/Inch"));
        discountButton.addActionListener(e -> loadFeature("Discount"));
        graphingToolButton.addActionListener(e -> loadFeature("Graphing Tool"));
        statisticsButton.addActionListener(e -> loadFeature("Statistics"));
        bmiCalculatorButton.addActionListener(e -> loadFeature("BMI Calculator"));
        calorieButton.addActionListener(e -> loadFeature("Calorie"));
        // 기본 콘텐츠 로드
        loadFeature("Calculator");

        setVisible(true);
    }

    private void loadFeature(String feature) {
        contentPanel.removeAll();

        switch (feature) {
            case "Calculator":
                contentPanel.add(new CalculatorInterface(recordListModel), BorderLayout.CENTER);
                break;
            case "Temperature":
                contentPanel.add(createTemperaturePanel(), BorderLayout.CENTER);  // 수정된 부분
                break;
            case "Number System":
                contentPanel.add(createNumberSystemPanel(), BorderLayout.CENTER);
                break;
            case "Meter/Inch":
                contentPanel.add(createMeterInchPanel(), BorderLayout.CENTER);
                break;
            case "Discount":
                contentPanel.add(createDiscountPanel(), BorderLayout.CENTER);
                break;
            case "Graphing Tool":
                contentPanel.add(new JLabel("그래프 기능 준비 중!", SwingConstants.CENTER));
                break;
            case "Statistics":
                contentPanel.add(new JLabel("통계 기능 준비 중!", SwingConstants.CENTER));
                break;
            case "Calorie":
                contentPanel.add(new JLabel("칼로리 계산기 준비 중!", SwingConstants.CENTER));
                break;
            case "BMI Calculator":
                contentPanel.add(createBMIPanel(), BorderLayout.CENTER);
                break;
            default:
                contentPanel.add(new JLabel("기능을 찾을 수 없습니다!", SwingConstants.CENTER));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createTemperaturePanel() {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(400, 300));

        // 화씨 -> 섭씨 변환 컴포넌트
        JTextField fahrenheitField = new JTextField(20);
        JTextField fahrenheitResultField = new JTextField(20);

        JLabel fahrenheitLabel = new JLabel("화씨 (°F):");
        fahrenheitLabel.setBounds(30, 30, 100, 30);
        panel.add(fahrenheitLabel);

        fahrenheitField.setBounds(130, 30, 100, 30);
        panel.add(fahrenheitField);

        JButton fahrenheitButton = new JButton("변환");
        fahrenheitButton.setBounds(240, 30, 80, 30);
        fahrenheitButton.addActionListener(e -> {
                    try {
                        double fahrenheit = Double.parseDouble(fahrenheitField.getText());
                        double celsius = logic.fahrenheitToCelsius(fahrenheit);
                        fahrenheitResultField.setText(String.format("%.2f", celsius));
                        recordListModel.addElement(String.format("%.2f°F = %.2f°C", fahrenheit, celsius));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "올바른 숫자를 입력하세요.");
                    }
            });
        panel.add(fahrenheitButton);

        JLabel fahrenheitResultLabel = new JLabel("섭씨 (°C):");
        fahrenheitResultLabel.setBounds(30, 70, 100, 30);
        panel.add(fahrenheitResultLabel);

        fahrenheitResultField.setBounds(130, 70, 190, 30);
        fahrenheitResultField.setEditable(false);
        panel.add(fahrenheitResultField);

        // 섭씨 -> 화씨 변환 컴포넌트
        JTextField celsiusField = new JTextField(20);
        JTextField celsiusResultField = new JTextField(20);

        JLabel celsiusLabel = new JLabel("섭씨 (°C):");
        celsiusLabel.setBounds(30, 120, 100, 30);
        panel.add(celsiusLabel);

        celsiusField.setBounds(130, 120, 100, 30);
        panel.add(celsiusField);

        JButton celsiusButton = new JButton("변환");
        celsiusButton.setBounds(240, 120, 80, 30);
        celsiusButton.addActionListener(e -> {
                    try {
                        double celsius = Double.parseDouble(celsiusField.getText());
                        double fahrenheit = logic.celsiusToFahrenheit(celsius);
                        celsiusResultField.setText(String.format("%.2f", fahrenheit));
                        recordListModel.addElement(String.format("%.2f°C = %.2f°F", celsius, fahrenheit));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "올바른 숫자를 입력하세요.");
                    }
            });
        panel.add(celsiusButton);

        JLabel celsiusResultLabel = new JLabel("화씨 (°F):");
        celsiusResultLabel.setBounds(30, 160, 100, 30);
        panel.add(celsiusResultLabel);

        celsiusResultField.setBounds(130, 160, 190, 30);
        celsiusResultField.setEditable(false);
        panel.add(celsiusResultField);

        return panel;
    }
    // createTemperaturePanel() 메서드 다음에 추가
    private JPanel createDiscountPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setPreferredSize(new Dimension(400, 250));

        JTextField costField = new JTextField(10);
        JTextField discountField = new JTextField(10);
        JTextField resultField = new JTextField(10);

        // 라벨 추가
        panel.add(new JLabel("Original Price: "));
        styleTextField(costField);
        panel.add(costField);

        panel.add(new JLabel("Discount Rate (%): "));
        styleTextField(discountField);
        panel.add(discountField);

        // 계산 버튼 추가
        JButton calculateButton = new JButton("Calculate");
        styleButton(calculateButton);
        calculateButton.addActionListener(e -> {
                    try {
                        double cost = Double.parseDouble(costField.getText());
                        double discountRate = Double.parseDouble(discountField.getText());
                        double discountedPrice = logic.calculateDiscount(cost, discountRate);
                        resultField.setText(String.format("%.2f", discountedPrice));
                        recordListModel.addElement(String.format("Original: %.2f, Discount: %.2f%%, Final: %.2f", 
                                cost, discountRate, discountedPrice));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
            });
        panel.add(calculateButton);
        panel.add(new JLabel(""));

        // 결과 필드
        panel.add(new JLabel("Discounted Price: "));
        styleTextField(resultField);
        resultField.setEditable(false);
        panel.add(resultField);

        return panel;
    }

    // 스타일링 메서드도 추가 (이미 있다면 생략)
    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        textField.setBackground(Color.WHITE);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
    }

    private JPanel createMeterInchPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setPreferredSize(new Dimension(400, 200));

        // 텍스트 필드 생성
        JTextField meterField = new JTextField();
        JTextField meterResultField = new JTextField();
        JTextField inchField = new JTextField();
        JTextField inchResultField = new JTextField();

        // 버튼 생성
        JButton convertMeterButton = new JButton("Convert Meters");
        JButton convertInchButton = new JButton("Convert Inches");
        JButton resetMeterButton = new JButton("Reset Meters");
        JButton resetInchButton = new JButton("Reset Inches");

        // 스타일 적용
        styleButton(convertMeterButton);
        styleButton(convertInchButton);
        styleButton(resetMeterButton);
        styleButton(resetInchButton);
        styleTextField(meterField);
        styleTextField(meterResultField);
        styleTextField(inchField);
        styleTextField(inchResultField);

        // 컴포넌트 추가 (원본 레이아웃과 동일하게)
        panel.add(new JLabel("Meters to Inches:"));
        panel.add(meterField);
        panel.add(new JLabel("Inches Result:"));
        panel.add(meterResultField);
        panel.add(convertMeterButton);
        panel.add(resetMeterButton);
        panel.add(new JLabel("Inches to Meters:"));
        panel.add(inchField);
        panel.add(new JLabel("Meters Result:"));
        panel.add(inchResultField);
        panel.add(convertInchButton);
        panel.add(resetInchButton);

        // 결과 필드 편집 불가 설정
        meterResultField.setEditable(false);
        inchResultField.setEditable(false);

        // 버튼 동작 설정
        convertMeterButton.addActionListener(e -> {
                    try {
                        double meters = Double.parseDouble(meterField.getText());
                        double inches = logic.meterToInch(meters);
                        meterResultField.setText(String.format("%.4f", inches));
                        recordListModel.addElement(String.format("%.2f meters = %.4f inches", meters, inches));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
            });

        convertInchButton.addActionListener(e -> {
                    try {
                        double inches = Double.parseDouble(inchField.getText());
                        double meters = logic.inchToMeter(inches);
                        inchResultField.setText(String.format("%.4f", meters));
                        recordListModel.addElement(String.format("%.2f inches = %.4f meters", inches, meters));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
            });

        resetMeterButton.addActionListener(e -> {
                    meterField.setText("");
                    meterResultField.setText("");
            });

        resetInchButton.addActionListener(e -> {
                    inchField.setText("");
                    inchResultField.setText("");
            });

        return panel;
    }

    private JPanel createNumberSystemPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 200));

        // Center Panel (입력 필드 및 변환 버튼)
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setBackground(Color.GRAY);

        JLabel decimalLabel = new JLabel("10진수 ", JLabel.CENTER);
        JTextField decimalField = new JTextField(20);

        // 버튼 텍스트를 영어로 변경
        JButton convertButton = new JButton("click");  // "계산"을 "Calculate"로 변경
        styleButton(convertButton);
        convertButton.setBackground(Color.GRAY);

        centerPanel.add(decimalLabel);
        centerPanel.add(decimalField);
        centerPanel.add(convertButton);

        // South Panel (결과 표시)
        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.setBackground(Color.LIGHT_GRAY);

        JTextField binaryField = new JTextField(10);
        JTextField octalField = new JTextField(10);
        JTextField hexField = new JTextField(10);

        // 결과 필드 스타일링 및 읽기 전용 설정
        styleTextField(binaryField);
        styleTextField(octalField);
        styleTextField(hexField);
        binaryField.setEditable(false);
        octalField.setEditable(false);
        hexField.setEditable(false);

        southPanel.add(new JLabel("Binary"));      // "2진수"를 "Binary"로 변경
        southPanel.add(binaryField);
        southPanel.add(new JLabel("Octal"));       // "8진수"를 "Octal"로 변경
        southPanel.add(octalField);
        southPanel.add(new JLabel("Hexadecimal")); // "16진수"를 "Hexadecimal"로 변경
        southPanel.add(hexField);

        // 나머지 코드는 동일
        convertButton.addActionListener(e -> {
                    try {
                        int decimal = Integer.parseInt(decimalField.getText());
                        String binary = logic.decimalToBinary(decimal);
                        String octal = logic.decimalToOctal(decimal);
                        String hex = logic.decimalToHex(decimal);

                        binaryField.setText(binary);
                        octalField.setText(octal);
                        hexField.setText(hex);

                        recordListModel.addElement(String.format(
                                "Decimal: %d = Binary: %s, Octal: %s, Hex: %s",
                                decimal, binary, octal, hex
                            ));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            mainPanel,
                            "Please enter a valid number!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
            });

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createBMIPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setPreferredSize(new Dimension(400, 200));

        // 설명 패널
        JTextArea descriptionArea = new JTextArea(
                "BMI(체질량지수)는 신장과 체중을 이용해 계산하며, 비만도를 측정하는 일반적인 지표입니다.\n" +
                "BMI = 체중(kg) / 신장(m)의 제곱\n\n" +
                "BMI에 따른 체중 분류:\n" +
                "18.5 kg/㎡ 이하: 저체중\n" +
                "18.5~22.9 kg/㎡: 정상\n" +
                "23~24.9 kg/㎡: 비만전단계\n" +
                "25~29.9 kg/㎡: 1단계 비만\n" +
                "30~34.9 kg/㎡: 2단계 비만\n" +
                "35 kg/㎡ 이상: 3단계 비만"
            );
        descriptionArea.setEditable(false);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setBackground(new Color(240, 240, 240));
        descriptionArea.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        mainPanel.add(scrollPane, BorderLayout.NORTH);

        // 입력 및 결과 패널
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 입력 필드
        JTextField heightField = new JTextField(10);
        JTextField weightField = new JTextField(10);
        JTextField bmiResultField = new JTextField(10);
        JTextField categoryField = new JTextField(15);

        // 스타일 적용
        styleTextField(heightField);
        styleTextField(weightField);
        styleTextField(bmiResultField);
        styleTextField(categoryField);

        // 결과 필드 읽기 전용 설정
        bmiResultField.setEditable(false);
        categoryField.setEditable(false);

        // 컴포넌트 배치
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("신장 (cm):"), gbc);

        gbc.gridx = 1;
        inputPanel.add(heightField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("체중 (kg):"), gbc);

        gbc.gridx = 1;
        inputPanel.add(weightField, gbc);

        // 계산 버튼
        JButton calculateButton = new JButton("BMI 계산");
        styleButton(calculateButton);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        inputPanel.add(calculateButton, gbc);

        // 결과 표시
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("BMI:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(bmiResultField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        inputPanel.add(new JLabel("체중 분류:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(categoryField, gbc);

        // 초기화 버튼
        JButton resetButton = new JButton("초기화");
        styleButton(resetButton);
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        inputPanel.add(resetButton, gbc);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // 버튼 동작 설정
        calculateButton.addActionListener(e -> {
                    try {
                        double height = Double.parseDouble(heightField.getText());
                        double weight = Double.parseDouble(weightField.getText());

                        if (weight <= 0 || height <= 0) {
                            throw new IllegalArgumentException("신장과 체중은 양수여야 합니다.");
                        }

                        double heightInMeters = height / 100;
                        double bmi = weight / (heightInMeters * heightInMeters);
                        String category = getBMICategory(bmi);

                        bmiResultField.setText(String.format("%.1f kg/㎡", bmi));
                        categoryField.setText(category);

                        recordListModel.addElement(String.format(
                                "BMI 계산 - 신장: %.1fcm, 체중: %.1fkg, BMI: %.1f kg/㎡ (%s)",
                                height, weight, bmi, category
                            ));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainPanel, 
                            "올바른 숫자를 입력해주세요.", 
                            "입력 오류", 
                            JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(mainPanel, 
                            ex.getMessage(), 
                            "입력 오류", 
                            JOptionPane.ERROR_MESSAGE);
                    }
            });

        resetButton.addActionListener(e -> {
                    heightField.setText("");
                    weightField.setText("");
                    bmiResultField.setText("");
                    categoryField.setText("");
            });

        return mainPanel;
    }

    private String getBMICategory(double bmi) {
        if (bmi <= 18.5) {
            return "저체중";
        } else if (bmi <= 22.9) {
            return "정상";
        } else if (bmi <= 24.9) {
            return "비만전단계";
        } else if (bmi <= 29.9) {
            return "1단계 비만";
        } else if (bmi <= 34.9) {
            return "2단계 비만";
        } else {
            return "3단계 비만";
        }
    }
}