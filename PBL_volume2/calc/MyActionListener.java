package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class MyActionListener implements ActionListener {
    // CalculatorExample 클래스에서 정의된 resultField와 footerLabel 참조
    public static JTextField resultField = CalculatorExample.resultField;
    public static JTextField footerLabel = CalculatorExample.footerLabel;

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand(); // 버튼의 명령 이름 가져오기
        if (name.equals("C")) { 
            // 'C' 버튼: 입력 필드를 초기화
            resultField.setText("");
            footerLabel.setText("");
        } else if (name.equals("UN")) {
            // 'UN' 버튼: 기능 없음
        } else if (name.equals("BK")) {
            // 'BK' 버튼: 입력 필드의 마지막 글자를 제거
            String currentText = resultField.getText();
            if (!currentText.isEmpty()) {
                resultField.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else if (name.equals("=")) {
            // '=' 버튼: 수식 계산
            calculate();
        } else {
            // 그 외 버튼: 기존 텍스트에 버튼의 명령 이름을 추가
            String currentString = resultField.getText();
            resultField.setText(currentString + name);
        }
    }

    // 계산 로직을 수행하는 메서드
    public void calculate() {
    String currentText = resultField.getText().trim(); // 텍스트 필드의 현재 수식을 가져옴
    currentText = currentText.replace(" ", ""); // 공백 제거

    if (currentText.isEmpty()) {
        return; // 수식이 비어 있으면 종료
    }

    // 사용할 연산자 정의
    String[] operators = {"+", "-", "x", "/", "%"}; 

    for (String operator : operators) {
        if (currentText.contains(operator)) { // 수식에 연산자가 포함되어 있는지 확인
            // 곱하기 연산자("x")를 정규식 이스케이프 처리하지 않도록 수정
            String[] parts;
            if (operator.equals("x")) {
                parts = currentText.split("x");
            } else {
                parts = currentText.split("\\" + operator); // 다른 연산자는 정규식 처리
            }

            if (parts.length == 2) { // 두 피연산자가 존재하는 경우
                try {
                    // 피연산자들을 숫자로 변환
                    double operand1 = Double.parseDouble(parts[0].trim());
                    double operand2 = Double.parseDouble(parts[1].trim());
                    double result = 0;

                    // 연산 수행
                    switch (operator) {
                        case "+": // 덧셈
                            result = operand1 + operand2;
                            break;
                        case "-": // 뺄셈
                            result = operand1 - operand2;
                            break;
                        case "x": // 곱셈
                            result = operand1 * operand2;
                            break;
                        case "/": // 나눗셈
                            if (operand2 == 0) { // 0으로 나누는 경우 처리
                                resultField.setText("");
                                footerLabel.setText("Error: Division by zero");
                                return;
                            }
                            result = operand1 / operand2;
                            break;
                        case"%":
                            result = operand1 % operand2;
                            break;
                    }

                    // 결과를 텍스트 필드와 하단 레이블에 표시
                    footerLabel.setText(Double.toString(result));
                } catch (NumberFormatException ex) {
                    // 피연산자 변환 실패 시 에러 표시
                    footerLabel.setText("Error: Invalid number");
                }
            } else {
                // 연산자가 하나 이상 포함된 경우 에러 처리
                footerLabel.setText("연산자가 하나 이상 포함되었습니다");
            }
            return; // 한 번의 연산만 처리
        }
    }

    // 연산자가 포함되지 않은 경우
    resultField.setText("");
    footerLabel.setText("Error: No operator");
}  
}
