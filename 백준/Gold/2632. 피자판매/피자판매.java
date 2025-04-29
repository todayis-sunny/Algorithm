// 02632. [G2] 피자판매.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int S, A, B; // S: 손님이 구매하고자 하는 피자 크기, A: A피자 조각 개수 B: B피자 조각 개수
    static int[] pizzaA, pizzaB; // 피자별 피자조각 크기
    static int[] prefixSumA, prefixSumB; // 각 누적합
    static int[] combA, combB; // 피자 크기별 가능한 개수
    static int ans = 0; // 방법의 가지 수

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        S = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        // (1-based)
        pizzaA = new int[A * 2 + 1];
        pizzaB = new int[B * 2 + 1];
        for (int a = 1; a <= A; a++) {
            pizzaA[a] = pizzaA[a + A] = Integer.parseInt(br.readLine());
        }
        for (int b = 1; b <= B; b++) {
            pizzaB[b] = pizzaB[b + B] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        // 누적합 초기화
        prefixSumA = new int[A * 2 + 1];
        prefixSumB = new int[B * 2 + 1];
        for (int a = 1; a <= A * 2; a++) {
            prefixSumA[a] = prefixSumA[a - 1] + pizzaA[a];
        }
        for (int b = 1; b <= B * 2; b++) {
            prefixSumB[b] = prefixSumB[b - 1] + pizzaB[b];
        }
        // 콤비네이션 초기화
        combA = new int[S + 1];
        combB = new int[S + 1];
        // 0은 선택하지 않은 경우니까 1개씩 존재
        combA[0] = 1;
        combB[0] = 1;
        for (int al = 1; al < A; al++) { // al : 부분합의 길이
            for (int a = 1; a <= A; a++) { // a :
                int value = prefixSumA[a + al - 1] - prefixSumA[a - 1];
                if (value > S) {
                    continue;
                }
                combA[value]++;
            }
        }
        if (prefixSumA[A] <= S) {
            combA[prefixSumA[A]]++;
        }
        for (int bl = 1; bl < B; bl++) {
            for (int b = 1; b <= B; b++) {
                int value = prefixSumB[b + bl - 1] - prefixSumB[b - 1];
                if (value > S) {
                    continue;
                }
                combB[value]++;
            }
        }
        if (prefixSumB[B] <= S) {
            combB[prefixSumB[B]]++;
        }
        // 정답구하기
        for (int s = 0; s <= S; s++) { // A피자에서 s크기 선택시 B피자는 S - s 선택
            ans += combA[s] * combB[S - s];
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
