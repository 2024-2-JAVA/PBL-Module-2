package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorInterface extends JPanel {

    private final JTextField display;
    private final JTextField formulaDisplay;
    private double firstNumber = 0; // 첫 번째 피연산자 저장
    private String operator = "";  // 현재 연산자
    private boolean isNewCalculation = true; // 새로운 계산 여부

    public CalculatorInterface(DefaultListModel<String> recordListModel) {
        setLayout(new BorderLayout());

        // 디스플레이 패널 (수식 및 결과 표시)
        JPanel displayPanel = new JPanel(new GridLayout(2, 1));
        formulaDisplay = new JTextField();
        formulaDisplay.setEditable(false);
        formulaDisplay.setHorizontalAlignment(SwingConstants.RIGHT);

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        displayPanel.add(formulaDisplay);
        displayPanel.add(display);

        // 버튼 패널 (숫자 및 연산자)
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "C", "0", "=", "/",
            "Backspace"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener(recordListModel));
            buttonPanel.add(button);
        }

        // 패널 구성
        add(displayPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    // 버튼 클릭 이벤트 처리 클래스
    private class ButtonClickListener implements ActionListener {

        private final DefaultListModel<String> recordListModel;

        public ButtonClickListener(DefaultListModel<String> recordListModel) {
            this.recordListModel = recordListModel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("\\d") || command.equals(".")) {
                handleNumberInput(command); // 숫자 입력 처리
            } else if (command.matches("[+\\-*/]")) {
                handleOperatorInput(command); // 연산자 입력 처리
            } else if (command.equals("=")) {
                computeFinalResult(); // 결과 계산
            } else if (command.equals("C")) {
                clearAll(); // 전체 초기화
            } else if (command.equals("Backspace")) {
                handleBackspace(); // 입력된 숫자 삭제
            }
        }

        private void handleNumberInput(String input) {
            // 숫자 입력 처리
            if (isNewCalculation) {
                display.setText(""); // 새로운 계산 시 디스플레이 초기화
                isNewCalculation = false;
            }
            display.setText(display.getText() + input);
        }

        private void handleOperatorInput(String input) {
            // 연산자 입력 처리
            try {
                if (!display.getText().isEmpty()) {
                    firstNumber = Double.parseDouble(display.getText());
                }
                operator = input;
                formulaDisplay.setText(display.getText() + " " + operator);
                isNewCalculation = true;
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }

        private void computeFinalResult() {
            // 최종 결과 계산
            try {
                if (!display.getText().isEmpty() && !operator.isEmpty()) {
                    double secondNumber = Double.parseDouble(display.getText());
                    double result = switch (operator) {
                        case "+" -> firstNumber + secondNumber;
                        case "-" -> firstNumber - secondNumber;
                        case "*" -> firstNumber * secondNumber;
                        case "/" -> secondNumber != 0 ? firstNumber / secondNumber : Double.NaN;
                        default -> throw new IllegalArgumentException("Invalid operator");
                    };

                    // 결과와 기록 업데이트
                    formulaDisplay.setText(formulaDisplay.getText() + " " + display.getText() + " =");
                    display.setText(String.valueOf(result));

                    // 기록 추가
                    String record = formulaDisplay.getText() + " " + result;
                    recordListModel.addElement(record);

                    firstNumber = result; // 다음 계산에 대비해 결과 저장
                    isNewCalculation = true;
                }
            } catch (Exception ex) {
                display.setText("Error");
            }
        }

        private void handleBackspace() {
            // 입력된 숫자 삭제 처리
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        }

        private void clearAll() {
            // 계산기 초기화
            display.setText("");
            formulaDisplay.setText("");
            firstNumber = 0;
            operator = "";
            isNewCalculation = true;
        }
    }
}
