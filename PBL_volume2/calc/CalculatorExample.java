package calc;


import java.awt.*;
import javax.swing.*;

public class CalculatorExample extends JFrame {
    // 결과를 표시할 텍스트 필드 (입력 및 계산 결과)
    public static JTextField resultField = new JTextField(20);
    // 하단 레이블로 결과 상태나 추가 메시지 표시
    public static JTextField footerLabel = new JTextField(20);

    public CalculatorExample() {
        super("자바 스윙 계산기"); // 프레임 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 클릭 시 프로그램 종료
        Container c = getContentPane(); // 컨테이너 가져오기
        c.setLayout(new BorderLayout()); // BorderLayout으로 레이아웃 설정

        // 프레임에 각각의 패널 추가
        c.add(new NorthPanel(), BorderLayout.NORTH); // 상단 패널
        c.add(new CenterPanel(), BorderLayout.CENTER); // 중앙 패널 (버튼)
        c.add(new SouthPanel(), BorderLayout.SOUTH); // 하단 패널

        setSize(330, 400); // 프레임 크기 설정
        setVisible(true); // 프레임 표시
    }

    // NorthPanel 클래스: 상단 패널 (결과 표시)
    class NorthPanel extends JPanel {
        public NorthPanel() {
            setBackground(Color.PINK); // 배경색 설정
            setLayout(new FlowLayout()); // FlowLayout으로 컴포넌트 정렬
            add(new JLabel(" 계산기")); // "계산기" 레이블 추가
            add(resultField); // 결과 표시 텍스트 필드 추가
        }
    }

    // CenterPanel 클래스: 중앙 패널 (버튼 레이아웃)
    class CenterPanel extends JPanel {
        public CenterPanel() {
            setBackground(Color.WHITE); // 배경색 설정
            setLayout(new GridLayout(5, 4, 5, 5)); // 5행 4열의 GridLayout 사용, 컴포넌트 간 간격 5px

            // 계산기 버튼 배열 정의
            String[] buttons = {"C", "UN", "BK", "/", 
                                "7", "8", "9", "x", 
                                "4", "5", "6", "-", 
                                "1", "2", "3", "+", 
                                "0", ".", "=", "%"};

            // 버튼 생성 및 패널에 추가
            for (String text : buttons) {
                JButton button = new JButton(text); // 버튼 생성
                button.addActionListener(new MyActionListener()); // ActionListener 연결
                button.setFont(new Font("Arial", Font.BOLD, 18)); // 버튼 폰트 설정
                add(button); // 버튼을 패널에 추가
            }
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
        new CalculatorExample(); // CalculatorExample 객체 생성 및 표시
    }
}
