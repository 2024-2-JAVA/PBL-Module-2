package UI;

import Logic.FBOLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class CalculatorInterface extends JFrame {

    private final DisplayPanel displayPanel;
    private final FBOLogic logic; 
    private double firstNumber = 0;
    private String operator = "";
    private boolean isNewCalculation = true;
    private String lastFormula = "";
    private String lastResult = "";

    public CalculatorInterface() {
        logic = new FBOLogic();
        displayPanel = new DisplayPanel();

        setTitle("Enhanced Calculator");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(displayPanel, BorderLayout.NORTH);
        add(new ButtonPanel(new ButtonClickListener()), BorderLayout.CENTER);

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("\\d") || command.equals(".")) {
                handleNumberInput(command);
            } else if (command.matches("[+\\-*/]")) {
                handleOperatorInput(command);
            } else if (command.equals("=")) {
                computeFinalResult();
            } else if (command.equals("Backspace")) {
                handleBackspace();
            } else if (command.equals("Recall Last")) {
                recallLastCalculation();
            } else if (command.equals("Clear")) {
                clearAll();
            }
        }

        private void handleNumberInput(String input) {
            JTextField display = displayPanel.getDisplay();
            if (isNewCalculation) {
                display.setText("");
                isNewCalculation = false;
            }
            display.setText(display.getText() + input);
        }

        private void handleOperatorInput(String input) {
            JTextField display = displayPanel.getDisplay();
            if (!display.getText().isEmpty()) {
                if (!operator.isEmpty()) {
                    computeIntermediateResult();
                } else {
                    firstNumber = Double.parseDouble(display.getText());
                }
            }
            operator = input;
            updateFormulaDisplay();
            isNewCalculation = true;
        }

        //계산중인 수식 화면
        private void computeIntermediateResult() {
            try {
                JTextField display = displayPanel.getDisplay();
                double secondNumber = Double.parseDouble(display.getText());
                firstNumber = logic.performOperation(firstNumber, secondNumber, operator);
                display.setText(String.valueOf(firstNumber));
            } catch (Exception ex) {
                displayPanel.getDisplay().setText("Error");
            }
        }

        //계산이 끝난 수식 화면
        private void computeFinalResult() {
            try {
                if (!operator.isEmpty()) {
                    JTextField display = displayPanel.getDisplay();
                    JTextField formulaDisplay = displayPanel.getFormulaDisplay();
                    double secondNumber = Double.parseDouble(display.getText());
                    double result = logic.performOperation(firstNumber, secondNumber, operator);
                    lastFormula = formulaDisplay.getText() + " " + display.getText() + " =";
                    lastResult = String.valueOf(result);
                    formulaDisplay.setText(lastFormula);
                    display.setText(lastResult);
                    firstNumber = result; // Store result for next calculation
                }
            } catch (Exception ex) {
                displayPanel.getDisplay().setText("Error");
            }
        }

        
        private void updateFormulaDisplay() {
            JTextField display = displayPanel.getDisplay();
            JTextField formulaDisplay = displayPanel.getFormulaDisplay();
            String currentDisplay = display.getText();
            if (!operator.isEmpty()) {
                formulaDisplay.setText(currentDisplay + " " + operator);
            } else {
                formulaDisplay.setText("");
            }
        }

        //계산 실수 정정
        private void handleBackspace() {
            JTextField display = displayPanel.getDisplay();
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
        
        
        //이전 기록 불러오기
        private void recallLastCalculation() {
            JTextField display = displayPanel.getDisplay();
            JTextField formulaDisplay = displayPanel.getFormulaDisplay();
            if (!lastFormula.isEmpty() && !lastResult.isEmpty()) {
                formulaDisplay.setText("Last: " + lastFormula);
                display.setText(lastResult);
            }
        }
        
        //계산 초기화
        private void clearAll() {
            JTextField display = displayPanel.getDisplay();
            JTextField formulaDisplay = displayPanel.getFormulaDisplay();
            display.setText("");
            formulaDisplay.setText("");
            firstNumber = 0;
            operator = "";
            isNewCalculation = true;
        }
    }
}
