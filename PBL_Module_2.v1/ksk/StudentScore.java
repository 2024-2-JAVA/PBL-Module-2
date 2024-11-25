package ksk;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StudentScore {
    String filePath;

    // 기본 생성자
    public StudentScore() {
    }

    // 매개변수를 가진 생성자
    public StudentScore(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        // 매개변수를 통해 파일 경로를 지정하여 객체 생성
        StudentScore studentScore = new StudentScore("C:/Temp/inputData2024.txt");

        // HashMap 준비: 이름을 key로, 성적을 value
        HashMap<String, Integer> studentScores = new HashMap<>();

        // 파일에서 성적 데이터를 읽어와 HashMap에 저장
        studentScore.loadScoresFromFile(studentScores);

        // 성적이 존재하는지 확인하고 총점 및 평균 계산 후 출력
        studentScore.printTotalAndAverageScores(studentScores);

        // 각 학생의 성적과 성취도 및 등수 출력
        studentScore.printStudentGradesWithRank(studentScores);
    }

    // 파일에서 데이터를 읽어와 HashMap에 저장하는 메소드
    public void loadScoresFromFile(HashMap<String, Integer> studentScores) {
        try {
            // FileReader 객체를 이용하여 Scanner 객체를 생성
            FileReader fr = new FileReader(filePath);
            Scanner scanner = new Scanner(fr);

            // 파일에서 데이터 읽기
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                String name = data[0]; // 이름
                Integer score = Integer.parseInt(data[1]); // 성적 (정수형)

                // HashMap에 이름과 성적 저장
                studentScores.put(name, score);
            }

            // 파일 읽기 완료 후 Scanner와 FileReader 닫기

            scanner.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("파일 처리 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    // 총점과 평균을 계산하고 출력하는 메소드
    public void printTotalAndAverageScores(HashMap<String, Integer> studentScores) {
        int totalScore = 0;
        // iterator를 사용하여 총점 계산
        for (String name : studentScores.keySet()) {
            totalScore += studentScores.get(name); // 성적을 총점에 누적
        }

        // 평균 계산 (HashMap의 size() 메소드를 사용)
        if (studentScores.size() > 0) {
            double averageScore = (double) totalScore / studentScores.size(); // 분모에 size() 메소드 사용
            System.out.println("------계산 결과------");
            System.out.println("총점: " + totalScore);
            System.out.println("평균: " + averageScore);
        } else {
            System.out.println("학생 성적 정보가 없습니다.");
        }
    }

    // 각 학생의 성적과 학점을 계산 및 등수 출력하는 메소드
    public void printStudentGradesWithRank(HashMap<String, Integer> studentScores) {
        // 성적을 내림차순으로 정렬하기 위해 List<Map.Entry<String, Integer>> 생성
        List<Map.Entry<String, Integer>> scoreList = new ArrayList<>(studentScores.entrySet());

        // 성적을 기준으로 내림차순 정렬
        scoreList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        int rank = 1; // 등수 변수

        for (Map.Entry<String, Integer> entry : scoreList) {
            String name = entry.getKey();
            int score = entry.getValue();
            String grade;

            // 성취도 계산
            if (score >= 95) {
                grade = "A+";
            } else if (score >= 90) {
                grade = "A";
            } else if (score >= 80) {
                grade = "B";
            } else if (score >= 70) {
                grade = "C";
            } else if (score >= 60) {
                grade = "D";
            } else {
                grade = "E";
            }

            // 등수와 성적 출력
            System.out.println(rank + "등: " + name + "의 성적: " + score + " 학점: " + grade);
            rank++;
        }
    }
}
