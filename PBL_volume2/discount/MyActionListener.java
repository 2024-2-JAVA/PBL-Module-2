package discount;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class MyActionListener implements ActionListener {
    // DisCalculatorExample 클래스에서 정의된 resultField와 footerLabel 참조
    public static JTextField resultField = DisCalculatorExample.resultField;
    public static JTextField costField = DisCalculatorExample.costField;
    public static JTextField footerLabel = DisCalculatorExample.footerLabel;

    @Override
    public void actionPerformed(ActionEvent e) {
        calculate();
    }
    
    // 계산 로직을 수행하는 메서드
    public void calculate() {
        try {
            // 원가와 할인율 값 가져오기
            String costText = costField.getText(); // 원가 입력 값
            String discountText = resultField.getText(); // 할인율 입력 값
            
            // 문자열을 숫자로 변환
            double cost = Double.parseDouble(costText);
            double discount = Double.parseDouble(discountText);
    
            // 할인율 유효성 검사 (0% ~ 100%)
            if (discount < 0 || discount > 100) {
                footerLabel.setText("할인율은 0에서 100 사이의 값이어야 합니다.");
                return;
            }
    
            // 할인된 가격 계산
            double discountedPrice = cost - (cost * discount / 100);
    
            // 결과 출력
            footerLabel.setText("할인된 가격: " + String.format("%.2f", discountedPrice));
        } catch (NumberFormatException ex) {
            // 잘못된 입력값 처리
            footerLabel.setText("올바른 숫자를 입력하세요.");
        }
    }
}
