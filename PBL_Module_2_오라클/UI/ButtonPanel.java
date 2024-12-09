package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {

    public ButtonPanel(ActionListener listener) {
        setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "Backspace", "Recall Last", "Clear", ""
            };

        for (String text : buttons) {
            add(createButton(text, listener));
        }
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));

        if (text.isEmpty()) {
            // 빈 공간을 위한 비활성화된 버튼 생성
            button.setEnabled(false);
            button.setVisible(false);
        } else {
            // 버튼에 액션 리스너 추가
            button.addActionListener(listener);
        }   
        return button;
    }
}
