package PBL_Module_2;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Food {
    String name;
    int calories; // 1회 제공량 기준 칼로리
    double protein; // 단백질 (g)
    double carbs;   // 탄수화물 (g)
    double fat;     // 지방 (g)

    public Food(String name, int calories, double protein, double carbs, double fat) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    @Override
    public String toString() {
        return name + " - 칼로리: " + calories + "kcal, 단백질: " + protein + "g, 탄수화물: " + carbs + "g, 지방: " + fat + "g";
    }
}

public class HealthManagementCalculator {
    public static void main(String[] args) {
        HealthManagementCalculator calculator = new HealthManagementCalculator();
        HashMap<String, Food> foodDatabase = new HashMap<>();

        // 음식 데이터를 파일에서 읽어오기
        calculator.loadFoodData("C:/Temp/foodData2024.txt", foodDatabase);

        Scanner scanner = new Scanner(System.in);

        // 메뉴 출력
        while (true) {
            System.out.println("\n건강 관리 계산기");
            System.out.println("1. 음식 데이터 확인");
            System.out.println("2. BMI 계산");
            System.out.println("3. 운동에 따른 칼로리 소모 계산");
            System.out.println("4. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    calculator.displayFoodData(foodDatabase);
                    break;
                case 2:
                    calculator.calculateBMI(scanner);
                    break;
                case 3:
                    calculator.calculateExerciseCalories(scanner);
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 옵션을 선택하세요.");
            }
        }
    }

    // 파일에서 음식 데이터를 읽어오는 메소드
    public void loadFoodData(String filePath, HashMap<String, Food> foodDatabase) {
        try (FileReader fr = new FileReader(filePath); Scanner scanner = new Scanner(fr)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                String name = data[0].trim();
                int calories = Integer.parseInt(data[1].trim());
                double protein = Double.parseDouble(data[2].trim());
                double carbs = Double.parseDouble(data[3].trim());
                double fat = Double.parseDouble(data[4].trim());
                foodDatabase.put(name.toLowerCase(), new Food(name, calories, protein, carbs, fat));
            }
            System.out.println("음식 데이터를 성공적으로 로드했습니다.");
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류 발생: " + e.getMessage());
        }
    }

    // 음식 데이터를 출력하는 메소드
    public void displayFoodData(HashMap<String, Food> foodDatabase) {
        System.out.println("\n음식 데이터:");
        for (String key : foodDatabase.keySet()) {
            System.out.println(foodDatabase.get(key));
        }
    }

    // BMI 계산 메소드
    public void calculateBMI(Scanner scanner) {
        System.out.print("키를 입력하세요 (cm): ");
        double height = scanner.nextDouble() / 100;
        System.out.print("몸무게를 입력하세요 (kg): ");
        double weight = scanner.nextDouble();

        if (height > 0 && weight > 0) {
            double bmi = weight / (height * height);
            System.out.printf("BMI: %.2f - ", bmi);
            if (bmi < 18.5) {
                System.out.println("저체중");
            } else if (bmi < 24.9) {
                System.out.println("정상 체중");
            } else if (bmi < 29.9) {
                System.out.println("과체중");
            } else {
                System.out.println("비만");
            }
        } else {
            System.out.println("올바른 값을 입력하세요.");
        }
    }

    // 운동에 따른 칼로리 소모 계산 메소드
    public void calculateExerciseCalories(Scanner scanner) {
        System.out.print("운동 시간을 입력하세요 (분): ");
        int minutes = scanner.nextInt();
        System.out.print("운동 강도를 입력하세요 (1: 낮음, 2: 보통, 3: 높음): ");
        int intensity = scanner.nextInt();
        System.out.print("체중을 입력하세요 (kg): ");
        double weight = scanner.nextDouble();

        if (minutes > 0 && intensity > 0 && weight > 0) {
            double caloriesBurned = 0;
            switch (intensity) {
                case 1:
                    caloriesBurned = weight * 3.5 * minutes / 200;
                    break;
                case 2:
                    caloriesBurned = weight * 5.5 * minutes / 200;
                    break;
                case 3:
                    caloriesBurned = weight * 7.5 * minutes / 200;
                    break;
                default:
                    System.out.println("올바른 강도를 선택하세요.");
                    return;
            }
            System.out.printf("운동으로 소모된 칼로리: %.2f kcal\n", caloriesBurned);
        } else {
            System.out.println("올바른 값을 입력하세요.");
        }
    }
}