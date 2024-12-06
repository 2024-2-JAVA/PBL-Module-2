package Logic;

public class FBOLogic {

    // Basic calculator operations
    public double performOperation(double num1, double num2, String operator) throws IllegalArgumentException {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Division by zero is not allowed.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    // Temperature conversion methods
    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    // Discount calculation method
    public double calculateDiscount(double originalPrice, double discountRate) {
        if (discountRate < 0 || discountRate > 100) {
            throw new IllegalArgumentException("Discount rate must be between 0 and 100.");
        }
        return originalPrice - (originalPrice * discountRate / 100);
    }
    // Meter-Inch conversion methods
    public double meterToInch(double meter) {
        return meter * 39.3701;  // 1 meter = 39.3701 inches
    }

    public double inchToMeter(double inch) {
        return inch / 39.3701;  // 1 inch = 0.0254 meters
    }

    public String decimalToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    public String decimalToOctal(int decimal) {
        return Integer.toOctalString(decimal);
    }

    public String decimalToHex(int decimal) {
        return Integer.toHexString(decimal).toUpperCase();
    }
    // FBOLogic 클래스에 추가
    public double calculateBMI(double weight, double height) {
        if (weight <= 0 || height <= 0) {
            throw new IllegalArgumentException("Weight and height must be positive numbers");
        }
        // BMI = weight(kg) / (height(m))²
        double heightInMeters = height / 100; // cm를 m로 변환
        return weight / (heightInMeters * heightInMeters);
    }

    public String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}

