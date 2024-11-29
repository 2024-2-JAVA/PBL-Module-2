import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        String label = e.getActionCommand();
        switch (label) {
            case "meter_inch" -> {
                displayManager.meter_inch_calculator.setVisible(true);
            }
            case "C_F" -> {
                displayManager.c_f_calculator.setVisible(true);
            }
            case "formation" -> {
                displayManager.formCalculator.setVisible(true);
            }
            case "discount" -> {
                displayManager.disCalculator.setVisible(true);
            }
            case "calc" -> {
                displayManager.calculator.setVisible(true);
            }
            default -> System.out.println("Unknown action.");
        }
    }
}