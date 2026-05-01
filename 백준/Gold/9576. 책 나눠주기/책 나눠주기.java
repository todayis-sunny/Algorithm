// 09576. [G2] 책 나눠주기.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC;
    static int N, M;
    static boolean[] books; // 1-based
    static Student[] students;

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    static void input() throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            // 학생 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            books = new boolean[N + 1];
            students = new Student[M];
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                students[m] = new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            solve();
        }
    }

    static void solve() {
        int cnt = 0;
        Arrays.sort(students, Comparator.comparing(Student::getEnd));
        for (Student student : students) {
            for (int i = student.start; i <= student.end; i++) {
                // 대출한 학생이 있으면 스킵
                if (books[i]) continue;
                // 대출한 학생이 없으면 학생 수 증가
                books[i] = true;
                cnt++;
                break;
            }
        }
        sb.append(cnt).append("\n");
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static class Student {
        int start, end;

        public Student(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getEnd() {
            return end;
        }
    }
}
/*
 끝나는 페이지가 빠른것부터 정렬

 */
