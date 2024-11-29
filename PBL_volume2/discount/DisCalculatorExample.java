package discount;


import java.awt.*;
import javax.swing.*;

public class DisCalculatorExample extends JFrame {
    // 결과를 표시할 텍스트 필드 (입력 및 계산 결과)
    public static JTextField resultField = new JTextField(30);
    public static JTextField costField = new JTextField(30);
    public static JTextField footerLabel = new JTextField(30);

    public DisCalculatorExample() {
        super("자바 스윙 계산기"); // 프레임 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 클릭 시 프로그램 종료
        Container c = getContentPane(); // 컨테이너 가져오기
        c.setLayout(new BorderLayout()); // BorderLayout으로 레이아웃 설정

        // 프레임에 각각의 패널 추가
        c.add(new CenterPanel(), BorderLayout.CENTER); // 중앙 패널 (버튼)
        c.add(new SouthPanel(), BorderLayout.SOUTH); // 하단 패널

        setSize(390, 400); // 프레임 크기 설정
    }

    // NorthPanel 클래스: 상단 패널 (결과 표시)
    class CenterPanel extends JPanel {
        public CenterPanel() {
            setBackground(Color.gray); // 배경색 설정
            setLayout(new FlowLayout()); // FlowLayout으로 컴포넌트 정렬
            add(new JLabel("   원가 ")); // "계산기" 레이블 추가
            add(resultField); // 결과 표시 텍스트 필드 추가
            add(new JLabel("할인율")); // "계산기" 레이블 추가
            add(costField); // 결과 표시 텍스트 필드 추가
            JButton button = new JButton("계산");
            button.addActionListener(new MyActionListener());
            add(button);
        }
    }

    // SouthPanel 클래스: 하단 패널 (결과 상태 표시)
    class SouthPanel extends JPanel {
        public SouthPanel() {
            setBackground(Color.YELLOW); // 배경색 설정
            setLayout(new FlowLayout()); // FlowLayout으로 컴포넌트 정렬
            add(new JLabel("계산 결과")); // "계산 결과" 레이블 추가
            add(footerLabel); // 하단 메시지를 표시할 텍스트 필드 추가
        }
    }

    // main 메서드: 프로그램 실행 진입점
    public static void main(String[] args) {
        new DisCalculatorExample(); // DisCalculatorExample 객체 생성 및 표시
    }
}
