package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class CalculatorInterface extends JPanel {
    private final JTextField display;
    private final JTextField formulaDisplay;
    private double firstNumber = 0.0;
    private String operator = "";
    private boolean isNewCalculation = true;
    private final DefaultListModel<String> recordListModel;
    private String lastCalculation = ""; // 마지막 계산 저장

    public CalculatorInterface(DefaultListModel<String> recordListModel) {
        this.recordListModel = recordListModel;
        setLayout(new BorderLayout());

        // 디스플레이 패널 생성
        JPanel displayPanel = new JPanel(new GridLayout(2, 1));
        formulaDisplay = new JTextField();
        formulaDisplay.setEditable(false);
        formulaDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        formulaDisplay.setFont(new Font("SansSerif", Font.BOLD, 28));

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("SansSerif", Font.BOLD, 38));

        displayPanel.add(formulaDisplay);
        displayPanel.add(display);
        add(displayPanel, BorderLayout.NORTH);

        // 디스플레이 패널 추가 후, 버튼 패널 코드를 다음과 같이 수정
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] buttons = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "X",
                "C", "0", "⌫", "÷"
            };

        int row = 0;
        int col = 0;

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("SansSerif", Font.PLAIN, 14));
            button.setPreferredSize(new Dimension(80, 50));
            gbc.gridx = col;
            gbc.gridy = row;
            gbc.gridwidth = 1;  // 기본 너비로 재설정

            button.addActionListener(e -> handleButtonAction(e.getActionCommand()));
            buttonPanel.add(button, gbc);

            col++;
            if (col > 3) { // 4열 배치
                col = 0;
                row++;
            }
        }

        // = 버튼 추가
        JButton equalsButton = new JButton("=");
        equalsButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        equalsButton.setBackground(new Color(0, 51, 153));
        equalsButton.setForeground(Color.WHITE);
        equalsButton.setOpaque(true);
        equalsButton.setBorderPainted(false);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 4;
        buttonPanel.add(equalsButton, gbc);

        equalsButton.addActionListener(e -> handleButtonAction("="));

        // 버튼 패널 추가
        add(buttonPanel, BorderLayout.CENTER);

        // 기록 목록 이벤트 리스너 설정
        recordListModel.addListDataListener(new ListDataListener() {
                @Override
                public void intervalAdded(ListDataEvent e) {
                    // 새로운 계산이 추가될 때 마지막 계산 업데이트
                    lastCalculation = recordListModel.getElementAt(recordListModel.getSize() - 1);
                }

                @Override
                public void intervalRemoved(ListDataEvent e) {}

                @Override
                public void contentsChanged(ListDataEvent e) {}
            });

        // 기록 목록에서 항목 선택 시 처리를 위한 메서드 추가
        JList<String> historyList = new JList<>(recordListModel);
        historyList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String selected = historyList.getSelectedValue();
                    if (selected != null) {
                        loadCalculationFromHistory(selected);
                    }
                }
            });
    }

    // loadCalculationFromHistory 메서드 수정
    // private에서 public으로 변경
    public void loadCalculationFromHistory(String historyItem) {
        try {
            // "1.00 + 2.00 = 3.00" 형식의 문자열을 처리
            String[] parts = historyItem.split(" = ");
            if (parts.length == 2) {
                // 수식 부분 (예: "1.00 + 2.00")
                String[] formulaParts = parts[0].split(" ");
                if (formulaParts.length >= 3) {
                    firstNumber = Double.parseDouble(formulaParts[0]);
                    // 연산자 변환 (* → X, / → ÷)
                    operator = formulaParts[1].equals("*") ? "X" : 
                    formulaParts[1].equals("/") ? "÷" : 
                    formulaParts[1];

                    // 화면에 표시
                    formulaDisplay.setText(parts[0]);
                    display.setText(parts[1]);

                    // 다음 계산을 위한 상태 설정
                    firstNumber = Double.parseDouble(parts[1]);
                    isNewCalculation = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "계산을 불러올 수 없습니다.",
                "오류",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleButtonAction(String command) {
        if (command.matches("[0-9]")) { // 숫자 버튼
            if (isNewCalculation) {
                display.setText(command);
                isNewCalculation = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if ("+-X÷".contains(command)) { // 연산자 버튼
            if (!display.getText().isEmpty()) {
                firstNumber = Double.parseDouble(display.getText());
                operator = command; // 여기를 수정
                formulaDisplay.setText(display.getText() + " " + command); // 여기도 수정
                isNewCalculation = true;
            }
        } else if (command.equals("=")) { // = 버튼
            if (!operator.isEmpty() && !display.getText().isEmpty()) {
                double secondNumber = Double.parseDouble(display.getText());
                double result;

                try {
                    result = switch (operator) {
                        case "+" -> firstNumber + secondNumber;
                        case "-" -> firstNumber - secondNumber;
                        case "X" -> firstNumber * secondNumber;
                        case "÷" -> {
                                if (secondNumber == 0) throw new ArithmeticException("Division by zero");
                                yield firstNumber / secondNumber;
                            }
                        default -> throw new IllegalArgumentException("Invalid operator");
                    };

                    formulaDisplay.setText(formulaDisplay.getText() + " " + display.getText() + " =");
                    display.setText(String.format("%.2f", result));
                    recordListModel.addElement(String.format("%.2f %s %.2f = %.2f",
                            firstNumber, operator, secondNumber, result));
                    firstNumber = result;
                    isNewCalculation = true;
                } catch (Exception ex) {
                    display.setText("Error");
                    isNewCalculation = true;
                }
            }

        } else if (command.equals("C")) { // C 버튼
            display.setText("");
            formulaDisplay.setText("");
            firstNumber = 0;
            operator = "";
            isNewCalculation = true;
        } else if (command.equals("⌫")) { // ⌫ 버튼
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
    }

    private void loadLastCalculation() {
        if (!lastCalculation.isEmpty()) {
            String[] parts = lastCalculation.split(" ");
            if (parts.length >= 3) {
                firstNumber = Double.parseDouble(parts[0]);
                operator = parts[1];
                display.setText(parts[2]);
                formulaDisplay.setText(parts[0] + " " + operator);
                isNewCalculation = false;
            }
        }
    }
}