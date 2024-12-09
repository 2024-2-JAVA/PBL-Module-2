package UI;

import Logic.FBOLogic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainApplication extends JFrame {
    private final JPanel contentPanel;
    private final DefaultListModel<String> recordListModel;
    private final FBOLogic logic = new FBOLogic(); // FBOLogic 인스턴스 추가

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
        JButton calorieButton = new JButton("칼로리 계산");
        JButton graphingToolButton = new JButton("통계");

        // 메뉴 버튼 스타일 설정 부분에 추가
        calculatorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        meterInchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tempButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        numberSystemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        discountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bmiCalculatorButton.setAlignmentX(Component.CENTER_ALIGNMENT); // 추가
        calorieButton.setAlignmentX(Component.CENTER_ALIGNMENT); // 추가
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
        menuPanel.add(calorieButton); // 추가
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(graphingToolButton); // 추가
        menuPanel.add(Box.createVerticalGlue());

        add(menuPanel, BorderLayout.WEST);

        // 콘텐츠 패널 생성
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // 기록 패널 생성
        JPanel recordPanel = new JPanel(new BorderLayout());
        recordListModel = new DefaultListModel<>();
        // 기록 목록 생성 부분에서
        JList<String> recordList = new JList<>(recordListModel);
        recordList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (contentPanel.getComponent(0) instanceof CalculatorInterface) {
                        String selected = recordList.getSelectedValue();
                        if (selected != null) {
                            ((CalculatorInterface) contentPanel.getComponent(0))
                            .loadCalculationFromHistory(selected);
                        }
                    }
                }
            });
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
                        String newRecord = JOptionPane.showInputDialog(this, "Edit Record:",
                                recordListModel.get(selectedIndex));
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
                contentPanel.add(createTemperaturePanel(), BorderLayout.CENTER); // 수정된 부분
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
                contentPanel.add(createGraphPanel(), BorderLayout.CENTER);
                break;
            case "Calorie":
                contentPanel.add(createCaloriePanel(), BorderLayout.CENTER);
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
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 설명 패널 추가
        JTextArea descriptionArea = new JTextArea(
                "온도 변환기 사용 방법:\n\n" +
                "1. 화씨 → 섭씨 변환\n" +
                "   - 화씨(°F) 입력창에 화씨 온도를 입력하세요\n" +
                "   - 변환 버튼을 클릭하면 섭씨로 변환됩니다\n" +
                "   - 변환 공식: °C = (°F - 32) × 5/9\n\n" +
                "2. 섭씨 → 화씨 변환\n" +
                "   - 섭씨(°C) 입력창에 섭씨 온도를 입력하세요\n" +
                "   - 변환 버튼을 클릭하면 화씨로 변환됩니다\n" +
                "   - 변환 공식: °F = (°C × 9/5) + 32\n\n" +
                "예시:\n" +
                "- 물의 끓는점: 100°C = 212°F\n" +
                "- 물의 어는점: 0°C = 32°F"
            );
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(new Color(240, 240, 240));
        descriptionArea.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // 변환 패널
        JPanel conversionPanel = new JPanel(null);
        conversionPanel.setPreferredSize(new Dimension(400, 200));

        // 화씨 -> 섭씨 변환 컴포넌트
        JTextField fahrenheitField = new JTextField(20);
        JTextField fahrenheitResultField = new JTextField(20);

        JLabel fahrenheitLabel = new JLabel("화씨 (°F):");
        fahrenheitLabel.setBounds(30, 30, 100, 30);
        conversionPanel.add(fahrenheitLabel);

        fahrenheitField.setBounds(130, 30, 100, 30);
        conversionPanel.add(fahrenheitField);

        JButton fahrenheitButton = new JButton("변환");
        fahrenheitButton.setBounds(240, 30, 80, 30);
        fahrenheitButton.addActionListener(e -> {
                    try {
                        double fahrenheit = Double.parseDouble(fahrenheitField.getText());
                        double celsius = logic.fahrenheitToCelsius(fahrenheit);
                        fahrenheitResultField.setText(String.format("%.2f", celsius));
                        recordListModel.addElement(String.format("%.2f°F = %.2f°C", fahrenheit, celsius));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(conversionPanel, "올바른 숫자를 입력하세요.");
                    }
            });
        conversionPanel.add(fahrenheitButton);

        JLabel fahrenheitResultLabel = new JLabel("섭씨 (°C):");
        fahrenheitResultLabel.setBounds(30, 70, 100, 30);
        conversionPanel.add(fahrenheitResultLabel);

        fahrenheitResultField.setBounds(130, 70, 190, 30);
        fahrenheitResultField.setEditable(false);
        conversionPanel.add(fahrenheitResultField);

        // 섭씨 -> 화씨 변환 컴포넌트
        JTextField celsiusField = new JTextField(20);
        JTextField celsiusResultField = new JTextField(20);

        JLabel celsiusLabel = new JLabel("섭씨 (°C):");
        celsiusLabel.setBounds(30, 120, 100, 30);
        conversionPanel.add(celsiusLabel);

        celsiusField.setBounds(130, 120, 100, 30);
        conversionPanel.add(celsiusField);

        JButton celsiusButton = new JButton("변환");
        celsiusButton.setBounds(240, 120, 80, 30);
        celsiusButton.addActionListener(e -> {
                    try {
                        double celsius = Double.parseDouble(celsiusField.getText());
                        double fahrenheit = logic.celsiusToFahrenheit(celsius);
                        celsiusResultField.setText(String.format("%.2f", fahrenheit));
                        recordListModel.addElement(String.format("%.2f°C = %.2f°F", celsius, fahrenheit));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(conversionPanel, "올바른 숫자를 입력하세요.");
                    }
            });
        conversionPanel.add(celsiusButton);

        JLabel celsiusResultLabel = new JLabel("화씨 (°F):");
        celsiusResultLabel.setBounds(30, 160, 100, 30);
        conversionPanel.add(celsiusResultLabel);

        celsiusResultField.setBounds(130, 160, 190, 30);
        celsiusResultField.setEditable(false);
        conversionPanel.add(celsiusResultField);

        // 패널 조립
        mainPanel.add(descriptionArea, BorderLayout.NORTH);
        mainPanel.add(conversionPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    // createTemperaturePanel() 메서드 다음에 추가
    private JPanel createDiscountPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 설명 패널 추가
        JTextArea descriptionArea = new JTextArea(
                "할인 계산기 사용 방법:\n" +
                "1. Original Price(원래 가격)에 상품의 원래 가격을 입력하세요.\n" +
                "2. Discount Rate(할인율)에 할인 비율을 입력하세요 (예: 20% -> 20 입력).\n" +
                "3. Calculate 버튼을 클릭하면 할인된 가격이 계산됩니다.\n\n" +
                "예시: 원래 가격 10000원, 할인율 20%\n" +
                "-> 할인된 가격: 8000원"
            );
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(new Color(240, 240, 240));
        descriptionArea.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // 입력 패널
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JTextField costField = new JTextField(10);
        JTextField discountField = new JTextField(10);
        JTextField resultField = new JTextField(10);

        // 라벨 추가
        inputPanel.add(new JLabel("원가: "));
        styleTextField(costField);
        inputPanel.add(costField);

        inputPanel.add(new JLabel("할인율 (%): "));
        styleTextField(discountField);
        inputPanel.add(discountField);

        // 계산 버튼 추가
        JButton calculateButton = new JButton("Calculate");
        styleButton(calculateButton);
        calculateButton.addActionListener(e -> {
                    try {
                        double cost = Double.parseDouble(costField.getText());
                        double discountRate = Double.parseDouble(discountField.getText());
                        double discountedPrice = logic.calculateDiscount(cost, discountRate);
                        resultField.setText(String.format("%.2f", discountedPrice));
                        recordListModel.addElement(String.format("원가: %.2f, 할인가: %.2f",
                                cost, discountRate, discountedPrice));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(mainPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
            });
        inputPanel.add(calculateButton);
        inputPanel.add(new JLabel(""));

        // 결과 필드
        inputPanel.add(new JLabel("할인가: "));
        styleTextField(resultField);
        resultField.setEditable(false);
        inputPanel.add(resultField);

        // 패널 조립
        mainPanel.add(descriptionArea, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    // 스타일링 메서드도 추가 (이미 있다면 생략)
    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        textField.setBackground(Color.WHITE);
    }

    private void styleTextField_K(JTextField textField) {
        textField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
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

    private void styleButton_K(JButton button) {
        button.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
    }

    private JPanel createMeterInchPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 설명 패널 추가
        JTextArea descriptionArea = new JTextArea(
                "미터-인치 변환기 사용 방법:\n\n" +
                "1. 미터 → 인치 변환\n" +
                "   - Meters to Inches 입력창에 미터 값을 입력하세요\n" +
                "   - Convert Meters 버튼을 클릭하면 인치로 변환됩니다\n" +
                "   - 1미터 = 39.3701인치\n\n" +
                "2. 인치 → 미터 변환\n" +
                "   - Inches to Meters 입력창에 인치 값을 입력하세요\n" +
                "   - Convert Inches 버튼을 클릭하면 미터로 변환됩니다\n" +
                "   - 1인치 = 0.0254미터\n\n" +
                "* Reset 버튼으로 입력값과 결과를 초기화할 수 있습니다"
            );
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(new Color(240, 240, 240));
        descriptionArea.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // 변환 패널
        JPanel conversionPanel = new JPanel(new GridLayout(6, 2, 10, 10));

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

        // 결과 필드 편집 불가 설정
        meterResultField.setEditable(false);
        inchResultField.setEditable(false);

        // 컴포넌트 추가
        conversionPanel.add(new JLabel("Meters to Inches:"));
        conversionPanel.add(meterField);
        conversionPanel.add(new JLabel("Inches Result:"));
        conversionPanel.add(meterResultField);
        conversionPanel.add(convertMeterButton);
        conversionPanel.add(resetMeterButton);
        conversionPanel.add(new JLabel("Inches to Meters:"));
        conversionPanel.add(inchField);
        conversionPanel.add(new JLabel("Meters Result:"));
        conversionPanel.add(inchResultField);
        conversionPanel.add(convertInchButton);
        conversionPanel.add(resetInchButton);

        // 버튼 동작 설정
        convertMeterButton.addActionListener(e -> {
                    try {
                        double meters = Double.parseDouble(meterField.getText());
                        double inches = logic.meterToInch(meters);
                        meterResultField.setText(String.format("%.4f", inches));
                        recordListModel.addElement(String.format("%.2f meters = %.4f inches", meters, inches));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "Please enter a valid number!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
            });

        convertInchButton.addActionListener(e -> {
                    try {
                        double inches = Double.parseDouble(inchField.getText());
                        double meters = logic.inchToMeter(inches);
                        inchResultField.setText(String.format("%.4f", meters));
                        recordListModel.addElement(String.format("%.2f inches = %.4f meters", inches, meters));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "Please enter a valid number!", "Error",
                            JOptionPane.ERROR_MESSAGE);
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

        // 패널 조립
        mainPanel.add(descriptionArea, BorderLayout.NORTH);
        mainPanel.add(conversionPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createNumberSystemPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setPreferredSize(new Dimension(400, 200));

        // 설명 패널 추가
        JTextArea descriptionArea = new JTextArea(
                "진법 변환기 사용 방법:\n" +
                "1. 10진수 숫자를 입력하세요.\n" +
                "2. 변환 버튼을 클릭하면 2진수, 8진수, 16진수로 자동 변환됩니다.\n\n" +
                "진법 설명:\n" +
                "- 2진수(Binary): 0과 1만 사용하는 수 체계\n" +
                "- 8진수(Octal): 0부터 7까지의 숫자를 사용하는 수 체계\n" +
                "- 16진수(Hexadecimal): 0-9와 A-F를 사용하는 수 체계\n\n" +
                "예시: 10진수 15를 변환하면\n" +
                "2진수: 1111\n" +
                "8진수: 17\n" +
                "16진수: F"
            );
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(new Color(240, 240, 240));
        descriptionArea.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Center Panel (입력 필드 및 변환 버튼)
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setBackground(new Color(230, 230, 230));

        JLabel decimalLabel = new JLabel("10진수: ", JLabel.CENTER);
        JTextField decimalField = new JTextField(20);

        JButton convertButton = new JButton("변환");
        styleButton_K(convertButton);

        centerPanel.add(decimalLabel);
        centerPanel.add(decimalField);
        centerPanel.add(convertButton);

        // South Panel (결과 표시)
        JPanel southPanel = new JPanel(new GridLayout(3, 2, 10, 5));
        southPanel.setBackground(new Color(240, 240, 240));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField binaryField = new JTextField(15);
        JTextField octalField = new JTextField(15);
        JTextField hexField = new JTextField(15);

        // 결과 필드 스타일링 및 읽기 전용 설정
        styleTextField(binaryField);
        styleTextField(octalField);
        styleTextField(hexField);
        binaryField.setEditable(false);
        octalField.setEditable(false);
        hexField.setEditable(false);

        southPanel.add(new JLabel("2진수: "));
        southPanel.add(binaryField);
        southPanel.add(new JLabel("8진수: "));
        southPanel.add(octalField);
        southPanel.add(new JLabel("16진수: "));
        southPanel.add(hexField);

        // 변환 버튼 이벤트
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
                                "진법 변환: %d(10) = %s(2) = %s(8) = %s(16)",
                                decimal, binary, octal, hex));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            mainPanel,
                            "올바른 10진수를 입력해주세요.",
                            "입력 오류",
                            JOptionPane.ERROR_MESSAGE);
                    }
            });

        // 패널 조립
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.add(centerPanel, BorderLayout.CENTER);
        contentPanel.add(southPanel, BorderLayout.SOUTH);

        mainPanel.add(descriptionArea, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

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
                "35 kg/㎡ 이상: 3단계 비만");
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
        styleTextField_K(bmiResultField);
        styleTextField_K(categoryField);

        // 결과 필드 읽기 전용 설정
        bmiResultField.setEditable(false);
        categoryField.setEditable(false);

        // 컴포넌트 배치
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("신장 (cm):"), gbc);

        gbc.gridx = 1;
        inputPanel.add(heightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("체중 (kg):"), gbc);

        gbc.gridx = 1;
        inputPanel.add(weightField, gbc);

        // 계산 버튼
        JButton calculateButton = new JButton("BMI 계산");
        styleButton_K(calculateButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        inputPanel.add(calculateButton, gbc);

        // 결과 표시
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("BMI:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(bmiResultField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("체중 분류:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(categoryField, gbc);

        // 초기화 버튼
        JButton resetButton = new JButton("초기화");
        styleButton_K(resetButton);
        gbc.gridx = 0;
        gbc.gridy = 5;
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
                                height, weight, bmi, category));
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

    // createGraphPanel 메서드 추가
    private JPanel createGraphPanel() {
        JPanel graphPanel = new JPanel(new BorderLayout(10, 10));
        graphPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 설명 패널 추가
        JTextArea graphDescriptionArea = new JTextArea(
                "박스 플롯 사용 방법:\n" +
                "1. 아래 입력창에 분석하고 싶은 숫자들을 입력하세요.\n" +
                "2. 숫자는 쉼표(,) 또는 공백으로 구분해서 입력합니다.\n" +
                "예시: 1, 2, 3, 4, 5\n" +
                "또는: 1.5 2.3 4.7 2.3 5.1\n" +
                "3. 계산 버튼을 클릭하면 박스 플롯이 표시됩니다.\n" +
                "4. 초기화 버튼으로 모든 입력과 결과를 지울 수 있습니다."
            );
        graphDescriptionArea.setEditable(false);
        graphDescriptionArea.setBackground(new Color(240, 240, 240));
        graphDescriptionArea.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        graphDescriptionArea.setWrapStyleWord(true);
        graphDescriptionArea.setLineWrap(true);
        graphDescriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // 입력 영역
        JPanel graphInputPanel = new JPanel(new BorderLayout());
        JTextArea graphNumberInput = new JTextArea(5, 30);
        graphNumberInput.setLineWrap(true);
        JScrollPane graphScrollPane = new JScrollPane(graphNumberInput);
        graphInputPanel.add(new JLabel("숫자들을 입력하세요 (쉼표 또는 공백으로 구분):"), BorderLayout.NORTH);
        graphInputPanel.add(graphScrollPane, BorderLayout.CENTER);

        // 결과를 표시할 패널들
        JPanel graphResultPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        JTextField graphMeanField = new JTextField();
        JTextField graphMedianField = new JTextField();
        JTextField graphModeField = new JTextField();
        JTextField graphStdDevField = new JTextField();
        JTextField graphMinField = new JTextField();
        JTextField graphMaxField = new JTextField();

        // 박스 플롯을 표시할 패널
        JPanel graphBoxPlotContainer = new JPanel(new BorderLayout());
        graphBoxPlotContainer.setPreferredSize(new Dimension(400, 200));

        // 결과 필드 읽기 전용 설정
        graphMeanField.setEditable(false);
        graphMedianField.setEditable(false);
        graphModeField.setEditable(false);
        graphStdDevField.setEditable(false);
        graphMinField.setEditable(false);
        graphMaxField.setEditable(false);

        // 결과 패널에 컴포넌트 추가
        graphResultPanel.add(new JLabel("평균:"));
        graphResultPanel.add(graphMeanField);;
        graphResultPanel.add(new JLabel("최빈값:"));
        graphResultPanel.add(graphModeField);
        graphResultPanel.add(new JLabel("표준편차:"));
        graphResultPanel.add(graphStdDevField);
        graphResultPanel.add(new JLabel("최소값:"));
        graphResultPanel.add(graphMinField);
        graphResultPanel.add(new JLabel("최대값:"));
        graphResultPanel.add(graphMaxField);

        // 버튼 패널
        JPanel graphButtonPanel = new JPanel(new FlowLayout());
        JButton graphCalculateButton = new JButton("계산");
        JButton graphClearButton = new JButton("초기화");
        styleButton_K(graphCalculateButton);
        styleButton_K(graphClearButton);
        graphButtonPanel.add(graphCalculateButton);
        graphButtonPanel.add(graphClearButton);

        // 계산 버튼 이벤트
        graphCalculateButton.addActionListener(e -> {
                    try {
                        String[] numbers = graphNumberInput.getText().split("[,\\s]+");
                        List<Double> values = new ArrayList<>();

                        for (String number : numbers) {
                            if (!number.trim().isEmpty()) {
                                values.add(Double.parseDouble(number.trim()));
                            }
                        }

                        if (values.isEmpty()) {
                            throw new IllegalArgumentException("데이터를 입력해주세요.");
                        }

                        // 데이터를 double 배열로 변환
                        double[] data = new double[values.size()];
                        for (int i = 0; i < values.size(); i++) {
                            data[i] = values.get(i);
                        }

                        // 통계 계산
                        double mean = logic.calculateMean(values);
                        double median = logic.calculateMedian(values);
                        String mode = logic.calculateMode(values);
                        double stdDev = logic.calculateStandardDeviation(values);
                        double min = Collections.min(values);
                        double max = Collections.max(values);

                        // 결과 표시
                        graphMeanField.setText(String.format("%.2f", mean));
                        graphMedianField.setText(String.format("%.2f", median));
                        graphModeField.setText(mode);
                        graphStdDevField.setText(String.format("%.2f", stdDev));
                        graphMinField.setText(String.format("%.2f", min));
                        graphMaxField.setText(String.format("%.2f", max));

                        // 박스 플롯 업데이트
                        graphBoxPlotContainer.removeAll();
                        BoxPlotPanel boxPlotPanel = new BoxPlotPanel(data);
                        graphBoxPlotContainer.add(boxPlotPanel, BorderLayout.CENTER);
                        graphBoxPlotContainer.revalidate();
                        graphBoxPlotContainer.repaint();

                        // 기록 추가
                        recordListModel.addElement(String.format(
                                "박스 플롯 분석 - 평균: %.2f, 중앙값: %.2f, 표준편차: %.2f",
                                mean, median, stdDev
                            ));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(graphPanel, 
                            "올바른 숫자를 입력해주세요.", 
                            "입력 오류", 
                            JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(graphPanel, 
                            ex.getMessage(), 
                            "입력 오류", 
                            JOptionPane.ERROR_MESSAGE);
                    }
            });

        // 초기화 버튼 이벤트
        graphClearButton.addActionListener(e -> {
                    graphNumberInput.setText("");
                    graphMeanField.setText("");
                    graphMedianField.setText("");
                    graphModeField.setText("");
                    graphStdDevField.setText("");
                    graphMinField.setText("");
                    graphMaxField.setText("");
                    graphBoxPlotContainer.removeAll();
                    graphBoxPlotContainer.revalidate();
                    graphBoxPlotContainer.repaint();
            });

        // 전체 레이아웃 구성
        JPanel graphTopPanel = new JPanel(new BorderLayout(0, 10));
        graphTopPanel.add(graphDescriptionArea, BorderLayout.NORTH);
        graphTopPanel.add(graphInputPanel, BorderLayout.CENTER);

        JPanel graphCenterPanel = new JPanel(new BorderLayout(10, 10));
        graphCenterPanel.add(graphResultPanel, BorderLayout.WEST);
        graphCenterPanel.add(graphBoxPlotContainer, BorderLayout.CENTER);

        graphPanel.add(graphTopPanel, BorderLayout.NORTH);
        graphPanel.add(graphCenterPanel, BorderLayout.CENTER);
        graphPanel.add(graphButtonPanel, BorderLayout.SOUTH);

        return graphPanel;
    }

    private JPanel createCaloriePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setPreferredSize(new Dimension(400, 200));

        // 설명 패널
        JTextArea descriptionArea = new JTextArea(
                "칼로리 소모량 계산기 사용 방법:\n\n" +
                "1. 벤치프레스 칼로리 계산\n" +
                "   - Babel Weight: 들어올린 바벨의 무게(kg)를 입력\n" +
                "   - Reps: 반복 횟수를 입력\n" +
                "   - 계산식: 칼로리 = 무게 × 횟수 × 0.05\n\n" +
                "2. 유산소 운동 칼로리 계산\n" +
                "   - Exercise: 운동 종류 선택 (걷기, 조깅, 사이클링, 수영)\n" +
                "   - Weight: 본인의 체중(kg)을 입력\n" +
                "   - Time: 운동 시간(분)을 입력\n" +
                "   - 계산식: 칼로리 = MET × 체중 × 0.0175 × 시간\n\n" +
                "* MET(운동강도) 값\n" +
                "  걷기: 3.8, 조깅: 7.5, 사이클링: 10.0, 수영: 8.0"
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

        // 운동 패널들을 담을 컨테이너
        JPanel exerciseContainer = new JPanel(new GridLayout(2, 1, 10, 10));

        // 벤치프레스 패널
        JPanel benchPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        benchPanel.setBorder(BorderFactory.createTitledBorder("Babel workout"));

        JTextField weightField = new JTextField();
        JTextField repsField = new JTextField();
        JTextField benchResultField = new JTextField();
        benchResultField.setEditable(false);

        JButton benchCalcButton = new JButton("Calculate");
        styleButton_K(benchCalcButton);

        benchPanel.add(new JLabel("Babel Weight (kg):"));
        benchPanel.add(weightField);
        benchPanel.add(new JLabel("Reps:"));
        benchPanel.add(repsField);
        benchPanel.add(benchCalcButton);
        benchPanel.add(benchResultField);

        benchCalcButton.addActionListener(e -> {
                    try {
                        double weight = Double.parseDouble(weightField.getText());
                        int reps = Integer.parseInt(repsField.getText());
                        double calories = logic.calculateBenchPressCalories(weight, reps);
                        benchResultField.setText(String.format("%.2f kcal", calories));
                        recordListModel.addElement(
                            String.format("벤치프레스 - 무게: %.1fkg, %d회, 소모 칼로리: %.2f kcal",
                                weight, reps, calories));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainPanel, 
                            "올바른 숫자를 입력하세요.", "입력 오류", 
                            JOptionPane.ERROR_MESSAGE);
                    }
            });

        // 유산소 운동 패널
        JPanel aerobicPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        aerobicPanel.setBorder(BorderFactory.createTitledBorder("Aerobic Exercise"));

        JComboBox<String> exerciseComboBox = new JComboBox<>(
                new String[]{"Walking", "Jogging", "Cycling", "Swimming"});
        JTextField bodyWeightField = new JTextField();
        JTextField timeField = new JTextField();
        JTextField aerobicResultField = new JTextField();
        aerobicResultField.setEditable(false);

        JButton aerobicCalcButton = new JButton("Calculate");
        styleButton_K(aerobicCalcButton);

        aerobicPanel.add(new JLabel("Exercise:"));
        aerobicPanel.add(exerciseComboBox);
        aerobicPanel.add(new JLabel("Weight (kg):"));
        aerobicPanel.add(bodyWeightField);
        aerobicPanel.add(new JLabel("Time (minutes):"));
        aerobicPanel.add(timeField);
        aerobicPanel.add(aerobicCalcButton);
        aerobicPanel.add(aerobicResultField);

        aerobicCalcButton.addActionListener(e -> {
                    try {
                        String exercise = (String) exerciseComboBox.getSelectedItem();
                        double weight = Double.parseDouble(bodyWeightField.getText());
                        int time = Integer.parseInt(timeField.getText());
                        double calories = logic.calculateAerobicCalories(exercise, weight, time);
                        aerobicResultField.setText(String.format("%.2f kcal", calories));
                        recordListModel.addElement(
                            String.format("%s - 체중: %.1fkg, %d분, 소모 칼로리: %.2f kcal",
                                exercise, weight, time, calories));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainPanel, 
                            "올바른 숫자를 입력하세요.", "입력 오류", 
                            JOptionPane.ERROR_MESSAGE);
                    }
            });

        exerciseContainer.add(benchPanel);
        exerciseContainer.add(aerobicPanel);

        mainPanel.add(exerciseContainer, BorderLayout.CENTER);

        return mainPanel;
    }
}
