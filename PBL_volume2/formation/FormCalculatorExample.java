package formation;


import java.awt.*;
import javax.swing.*;

public class FormCalculatorExample extends JFrame {
    // 결과를 표시할 텍스트 필드 (입력 및 계산 결과)
    public static JTextField decimal_numberField = new JTextField(30);
    public static JTextField binary_numberField = new JTextField(10);
    public static JTextField octal_numberField = new JTextField(10);
    public static JTextField hexadecimal_numberField = new JTextField(10);

    public FormCalculatorExample() {
        super("자바 스윙 계산기"); // 프레임 제목 설정
        Container c = getContentPane(); // 컨테이너 가져오기
        c.setLayout(new BorderLayout()); // BorderLayout으로 레이아웃 설정

        // 프레임에 각각의 패널 추가
        c.add(new CenterPanel(), BorderLayout.CENTER); // 중앙 패널 (버튼)
        c.add(new SouthPanel(), BorderLayout.SOUTH); // 하단 패널

        setSize(700, 400); // 프레임 크기 설정
    }

    // CenterPanel 클래스: 상단 패널 (입력 필드 및 버튼)
    class CenterPanel extends JPanel {
        public CenterPanel() {
            setBackground(Color.gray); // 배경색 설정
            setLayout(new FlowLayout()); // FlowLayout으로 컴포넌트 정렬
            add(new JLabel("10진수 ")); // "계산기" 레이블 추가
            add(decimal_numberField); // 10진수 입력 필드 추가
            JButton button = new JButton("계산");
            button.addActionListener(new MyActionListener()); // 버튼에 ActionListener 추가
            add(button);
        }
    }

    // SouthPanel 클래스: 하단 패널 (결과 표시)
    class SouthPanel extends JPanel {
        public SouthPanel() {
            setBackground(Color.YELLOW); // 배경색 설정
            setLayout(new FlowLayout()); // FlowLayout으로 컴포넌트 정렬
            add(new JLabel("2진수")); // "계산 결과" 레이블 추가
            add(binary_numberField); // 2진수 결과 필드
            add(new JLabel("8진수")); // "계산 결과" 레이블 추가
            add(octal_numberField); // 8진수 결과 필드
            add(new JLabel("16진수")); // "계산 결과" 레이블 추가
            add(hexadecimal_numberField); // 16진수 결과 필드
        }
    }
}


