// 14572. [P5] 스터디 그룹.

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K, D; // N: 학생의 수, K: 알고리즘의 수, D: 1등과 꼴지 학생의 실력 차
    static Student[] students;
    static StudyGroup studyGroup;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // N, K, D 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        // 배열 초기화
        students = new Student[N];
        // 스터디 그룹 초기화
        studyGroup = new StudyGroup();
        // 학생 입력
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int algo = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            int know = 0;
            st = new StringTokenizer(br.readLine());
            for (int a = 0; a < algo; a++) {
                int bit = (1 << Integer.parseInt(st.nextToken()));
                know |= bit; // OR 연산으로 알고있는 알고리즘 관리
            }
            students[n] = new Student(val, know);
        }
    }

    static void solve() {
        // 실력차로 정렬
        Arrays.sort(students, Comparator.comparing(Student::getValue));
        // 투 포인터
        int left = 0, right = 0;
        // 1번 학생을 1인 그룹으로 시작
        studyGroup.addStudent(students[right]);
        ans = studyGroup.getValue();
        // 우측 포인터가 마지막 범위를 벗어날 때까지
        while (true) {
            // 학생의 실력차가 D 초과인 경우
            if (students[right].value - students[left].value > D) {
                studyGroup.deleteStudent(students[left++]);
                continue;
            }
            ans = Math.max(ans, studyGroup.getValue());
            if (++right == N) break;
            studyGroup.addStudent(students[right]);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Student {
        int value; // 학생 실력
        int know; // 알고 있는 알고리즘 (bit로 관리)

        public Student(int value, int know) {
            this.value = value;
            this.know = know;
        }

        public int getValue() {
            return value;
        }
    }

    static class StudyGroup {
        int[] algorithm;// 알고리즘 수 (1-based)
        int algoS = 0; // 그룹 내의 학생들이 아는 모든 알고리즘의 수
        int knowS = 0; // 그룹 내의 모든 학생들이 아는 알고리즘의 수
        int studentS = 0; // 전체 학생수

        public StudyGroup() {
            this.algorithm = new int[K + 1];
            this.algoS = 0;
            this.knowS = 0;
            this.studentS = 0;
        }

        public int getValue() {
            return (algoS - knowS) * studentS;
        }

        public void addStudent(Student student) {
            for (int i = 1; i <= K; i++) {
                // 해당 스터디원이 알고 있지 않는 알고리즘
                if ((student.know & (1 << i)) == 0) continue;
                // 해당 스터디원이 알고 있는 알고리즘
                algorithm[i]++;
            }
            studentS++;
            calculateAlgorithm();
        }

        public void deleteStudent(Student student) {
            for (int i = 1; i <= K; i++) {
                // 해당 스터디원이 알고 있지 않는 알고리즘
                if ((student.know & (1 << i)) == 0) continue;
                // 해당 스터디원이 알고 있는 알고리즘
                algorithm[i]--;
            }
            studentS--;
            calculateAlgorithm();
        }

        public void calculateAlgorithm() {
            algoS = knowS = 0;
            for (int i = 1; i <= K; i++) {
                // 아무도 모르는 알고리즘이면 스킵
                if (algorithm[i] == 0) continue;
                // 알고 있는 알고리즘
                algoS++;
                // 모두가 알 때,
                if (algorithm[i] == studentS) knowS++;
            }
        }
    }
}
/*
 # 풀이
 - 실력의 오름차순으로 학생들을 정렬
 - 투 포인터로 그룹원을 추가하고 제거하는 로직
 - 학생이라는 클래스를 만들고 know라는 bit형태로 알고있는 알고리즘을 관리
 */
