import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import meter_inch.Meter_inch_CalculatorExample;
import C_F.C_F_CalculatorExample;
import formation.FormCalculatorExample;
import discount.DisCalculatorExample;
import calc.CalculatorExample;

class MyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        switch (label) {
            case "meter_inch" -> {
                Meter_inch_CalculatorExample frame = new Meter_inch_CalculatorExample();
                frame.setVisible(true); // JFrame을 화면에 보이도록 설정
            }
            case "C_F" -> {
                C_F_CalculatorExample frame = new C_F_CalculatorExample();
                frame.setVisible(true); // JFrame을 화면에 보이도록 설정
            }
            case "formation" -> {
                FormCalculatorExample frame = new FormCalculatorExample();
                frame.setVisible(true); // JFrame을 화면에 보이도록 설정
            }
            case "discount" -> {
                DisCalculatorExample frame = new DisCalculatorExample();
                frame.setVisible(true); // JFrame을 화면에 보이도록 설정
            }
            case "calc" -> {
                CalculatorExample frame = new CalculatorExample();
            }
            default -> System.out.println("Unknown action.");
        }
    }
}