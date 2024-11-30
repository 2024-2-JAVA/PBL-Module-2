package UI;

import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame {

    private final JPanel contentPanel;
    private final DefaultListModel<String> recordListModel;

    public MainApplication() {
        // 메인 애플리케이션 설정
        setTitle("Multifunctional Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 좌측 메뉴 패널 생성
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.LIGHT_GRAY);
        menuPanel.setPreferredSize(new Dimension(150, getHeight()));

        // 메뉴 버튼 생성
        JButton calculatorButton = new JButton("Basic Calculator");
        JButton graphingToolButton = new JButton("Graphing Tool");
        JButton bmiCalculatorButton = new JButton("BMI Calculator");

        // 메뉴 버튼 스타일 설정
        calculatorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        graphingToolButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bmiCalculatorButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 버튼을 메뉴 패널에 추가
        menuPanel.add(Box.createVerticalGlue());
        menuPanel.add(calculatorButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(graphingToolButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(bmiCalculatorButton);
        menuPanel.add(Box.createVerticalGlue());

        add(menuPanel, BorderLayout.WEST);

        // 콘텐츠 패널 생성
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // 기록 패널 생성
        JPanel recordPanel = new JPanel(new BorderLayout());
        recordListModel = new DefaultListModel<>();
        JList<String> recordList = new JList<>(recordListModel);
        JScrollPane scrollPane = new JScrollPane(recordList);

        // 기록 편집 및 삭제 버튼 추가
        JPanel recordButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = new JButton("Delete Record");
        JButton editButton = new JButton("Edit Record");
        recordButtonPanel.add(editButton);
        recordButtonPanel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            int selectedIndex = recordList.getSelectedIndex();
            if (selectedIndex != -1) {
                recordListModel.remove(selectedIndex); // 선택된 기록 삭제
            }
        });

        editButton.addActionListener(e -> {
            int selectedIndex = recordList.getSelectedIndex();
            if (selectedIndex != -1) {
                String newRecord = JOptionPane.showInputDialog(this, "Edit Record:", recordListModel.get(selectedIndex));
                if (newRecord != null && !newRecord.isBlank()) {
                    recordListModel.set(selectedIndex, newRecord); // 기록 수정
                }
            }
        });

        recordPanel.add(new JLabel("Calculation History", SwingConstants.CENTER), BorderLayout.NORTH);
        recordPanel.add(scrollPane, BorderLayout.CENTER);
        recordPanel.add(recordButtonPanel, BorderLayout.SOUTH);
        recordPanel.setPreferredSize(new Dimension(200, getHeight()));
        add(recordPanel, BorderLayout.EAST);

        // 메뉴 버튼 동작 추가
        calculatorButton.addActionListener(e -> loadFeature("Calculator"));
        graphingToolButton.addActionListener(e -> loadFeature("Graphing Tool"));
        bmiCalculatorButton.addActionListener(e -> loadFeature("BMI Calculator"));

        // 기본 콘텐츠 로드
        loadFeature("Calculator");

        setVisible(true);
    }

    private void loadFeature(String feature) {
        contentPanel.removeAll(); // 현재 콘텐츠 제거

        switch (feature) {
            case "Calculator":
                contentPanel.add(new CalculatorInterface(recordListModel), BorderLayout.CENTER);
                break;
            case "Graphing Tool":
                contentPanel.add(new JLabel("Graphing Tool 준비 중!", SwingConstants.CENTER));
                break;
            case "BMI Calculator":
                contentPanel.add(new JLabel("BMI Calculator 준비 중!", SwingConstants.CENTER));
                break;
            default:
                contentPanel.add(new JLabel("기능을 찾을 수 없습니다!", SwingConstants.CENTER));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}