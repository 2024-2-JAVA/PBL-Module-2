package formation;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCalculatorExample extends JFrame {
    // 결과를 표시할 텍스트 필드
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
            add(new JLabel("10진수 ", JLabel.CENTER)); // "10진수" 레이블 추가
            add(decimal_numberField);

            JButton button = new JButton("계산");
            button.setBackground(Color.gray); // 버튼 배경색
            button.setForeground(Color.BLACK); // 버튼 텍스트 색상
            button.setFocusPainted(false); // 버튼 초점 표시 제거
            button.setBorder(BorderFactory.createLineBorder(Color.gray, 2)); // 버튼 테두리 설정

            button.addActionListener(new MyActionListener()); // 버튼에 ActionListener 추가
            add(button);
        }
    }

    // SouthPanel 클래스: 하단 패널 (결과 표시)
    class SouthPanel extends JPanel {
        public SouthPanel() {
            setBackground(Color.LIGHT_GRAY); // 배경색 설정
            setLayout(new FlowLayout()); // FlowLayout으로 컴포넌트 정렬

            add(new JLabel("2진수")); // "2진수" 레이블 추가
            binary_numberField.setBackground(Color.WHITE); // 텍스트 필드 배경색
            add(binary_numberField);

            add(new JLabel("8진수")); // "8진수" 레이블 추가
            octal_numberField.setBackground(Color.WHITE); // 텍스트 필드 배경색
            add(octal_numberField);

            add(new JLabel("16진수")); // "16진수" 레이블 추가
            hexadecimal_numberField.setBackground(Color.WHITE); // 텍스트 필드 배경색
            add(hexadecimal_numberField);
        }
    }

    // MyActionListener 클래스: 버튼 동작 정의
    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int decimal = Integer.parseInt(decimal_numberField.getText());
                binary_numberField.setText(Integer.toBinaryString(decimal));
                octal_numberField.setText(Integer.toOctalString(decimal));
                hexadecimal_numberField.setText(Integer.toHexString(decimal).toUpperCase());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "유효한 숫자를 입력하세요!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormCalculatorExample frame = new FormCalculatorExample();
            frame.setVisible(true);
        });
    }
}