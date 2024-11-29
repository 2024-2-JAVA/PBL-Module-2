import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        String label = e.getActionCommand();
        switch (label) {
            case "미터, 인치 변환" -> {
                displayManager.meter_inch_calculator.setVisible(true);
            }
            case "섭시 화시 변환" -> {
                displayManager.c_f_calculator.setVisible(true);
            }
            case "진법 변환" -> {
                displayManager.formCalculator.setVisible(true);
            }
            case "할인 계산" -> {
                displayManager.disCalculator.setVisible(true);
            }
            case "기본 계산 기능" -> {
                displayManager.calculator.setVisible(true);
            }
            default -> System.out.println("Unknown action.");
        }
    }
}