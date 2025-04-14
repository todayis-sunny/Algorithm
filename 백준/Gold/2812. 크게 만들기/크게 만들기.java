// 02812. [G3] 크게 만들기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K, P, idx; // N: 숫자 자리수, K: 소거할 숫자 개수, P: 남길 숫자 개수(N - K), idx: 마지막에 사용된 index
    static String inputNum; // 입력할 숫자 String
    static char ch, tmp; // ch: 문자(사실은 숫자), tmp: 비교할 임시값
    static StringBuilder sb; // 정답
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
        P = N - K;
        sb = new StringBuilder(P);
        idx = -1;
    }

    static void solve() {
        while (P > 0) { // P가 숫자가 들어갈수 있는 공간이라고 생각 -> 공간이 다 채워지면 종료
            tmp = '/'; // '0' 바로 직전
            int limit = N - P;
            for (int i = idx + 1; i <= limit; i++) {
                ch = inputNum.charAt(i);
                if (ch > tmp) {
                    tmp = ch;
                    idx = i;
                    if (ch == '9') {
                        break;
                    }
                }
            }
            sb.append(tmp);
            P--;
        }
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}
