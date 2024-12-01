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
        String[] buttons = {"미터, 인치 변환", "섭시 화시 변환", "진법 변환", "할인 계산", "기본 계산 기능","BMI계산"};

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