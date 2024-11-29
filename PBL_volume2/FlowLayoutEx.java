import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FlowLayoutEx extends JFrame {
    public FlowLayoutEx() {
        setTitle("FlowLayoutSample");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 컨테이너와 레이아웃 설정
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40));

        // String 배열 정의
        String[] buttons = {"meter_inch", "C_F", "formation", "discount", "calc"};

        // 버튼 추가
        for (String label : buttons) {
            JButton button = new JButton(label);
            button.addActionListener(new MyActionListener());
            c.add(button); // 배열의 각 요소를 버튼 텍스트로 설정
        }

        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlowLayoutEx();
    }
}