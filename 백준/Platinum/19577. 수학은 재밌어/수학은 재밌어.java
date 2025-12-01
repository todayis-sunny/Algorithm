// 19577. [P5] 수학은 재밌어.

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static List<Integer> eulerP = new ArrayList<>(Arrays.asList(1, 2));
    static int N, ans = -1;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void solve() {
        // 1, 2인 경우는 그대로 N 반환
        if (N <= 2) ans = N;
        // 홀수인 경우는 -1 반환
        else if (N % 2 == 1) {
            return;
        }
        // 그 외의 경우는 계산
        else {
            for (int num = 3; num <= (int) Math.sqrt(N); num++) {
                if (N % num == 0) eulerP.add(num);
            }
            for (int i = eulerP.size() - 1; i >= 0; i--) {
                if ((N / eulerP.get(i)) != eulerP.get(i)) eulerP.add(N / eulerP.get(i));
            }
            for (int i = 2; i < eulerP.size(); i++) {
                if (euler(eulerP.get(i)) * eulerP.get(i) == N) {
                    ans = eulerP.get(i);
                    return;
                }
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int euler(int curr) {
        int res = curr;
        for (int num = 2; num <= (int) Math.sqrt(curr); num++) {
            if (curr % num == 0) {
                res /= num;
                res *= num - 1;
                while (curr % num == 0) {
                    curr /= num;
                }
            }
        }
        if (curr > 1) {
            res /= curr;
            res *= curr - 1;
        }
        return res;
    }

}
