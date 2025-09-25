// 14267. [G4] 회사 문화 1.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M; // N: 직원 수, M: 칭찬의 횟수
    static int[] parent;
    static int[] total; // 칭찬의 수치 합 1-based

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // 1. N, M 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 2. 직속상사 입력
        parent = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            parent[n] = Integer.parseInt(st.nextToken());
        }
        // 3. 칭찬 입력
        total = new int[N + 1];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int emp = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            total[emp] += val;
        }
    }

    static void solve() {
        for (int i = 2; i <= N; i++) {
            total[i] += total[parent[i]];
        }
    }

    static void output() throws IOException {
        for (int i = 1; i <= N; i++) {
            bw.write(total[i] + " ");
        }
        bw.flush();
    }
}
