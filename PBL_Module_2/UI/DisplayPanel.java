package UI;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private final JTextField formulaDisplay;
    private final JTextField display;

    public DisplayPanel() {
        setLayout(new GridLayout(2, 1));

        // Formula display
        formulaDisplay = createTextField(Font.ITALIC, 18, Color.LIGHT_GRAY);
        add(formulaDisplay);

        // Number/result display
        display = createTextField(Font.BOLD, 24, Color.WHITE);
        add(display);
    }

    private JTextField createTextField(int fontStyle, int fontSize, Color bgColor) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", fontStyle, fontSize));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);
        textField.setBackground(bgColor);
        return textField;
    }

    public JTextField getFormulaDisplay() {
        return formulaDisplay;
    }

    public JTextField getDisplay() {
        return display;
    }
}
