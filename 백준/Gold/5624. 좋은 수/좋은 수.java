// 05624. [G3] 좋은 수

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int alpha = 100_000;
    static int N;
    static Set<Integer> set = new HashSet<>();
    static boolean[] possible = new boolean[alpha * 2 + 1]; // [i] = true : i번째 수를 만들수 있음
    static int[] A;
    static int ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            query(i);
            insert(i);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    // 새로운 숫자 삽입
    static void insert(int i) {
        if (set.contains(A[i])) return;
        set.add(A[i]);
        for (int other : set) {
            int pair = A[i] + other;
            int target = pair + alpha;
            if (target < 0 || target > alpha * 2) continue;
            possible[target] = true;
        }
    }


    // 숫자를 만들수 있는지
    static void query(int i) {
        for (int num : set) {
            int goal = A[i] - num;
            int target = goal + alpha;
            if (target < 0 || target > alpha * 2) continue;
            if (possible[target]) {
                ans++;
                return;
            }
        }
    }
}
