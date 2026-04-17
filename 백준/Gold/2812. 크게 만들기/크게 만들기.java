// 02812. [G3] 크게 만들기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K, P;
    static String inputNum;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        inputNum = br.readLine();
        // 남길 자릿수
        P = N - K;
    }

    static void solve() {
        // 모노토닉 스택을 배열 + top 인덱스로 구현
        char[] stack = new char[N];
        int top = -1;      // 스택의 top 인덱스
        int toRemove = K;  // 제거해야 할 숫자 개수

        for (int i = 0; i < N; i++) {
            char c = inputNum.charAt(i);
            // 스택이 비어있지 않고, 아직 제거 가능하며,
            // top에 있는 문자보다 현재 문자가 더 크면 pop
            while (top >= 0 && toRemove > 0 && stack[top] < c) {
                top--;
                toRemove--;
            }
            stack[++top] = c; // 현재 문자 push
        }
        
        sb = new StringBuilder(P);
        for (int i = 0; i < P; i++) {
            sb.append(stack[i]);
        }
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}
