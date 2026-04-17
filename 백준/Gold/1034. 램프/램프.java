// 01034. [G4] 램프.

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, K, ans; // N: 행의 개수 M: 열의 개수, K: 스위치를 누를 수 있는 횟수
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int n = 0; n < N; n++) {
            String status = br.readLine();
            map.put(status, map.getOrDefault(status, 0) + 1);
        }
        K = Integer.parseInt(br.readLine());
    }

    static void solve() {
        ans = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int cnt = countZero(key);
            if (cnt > K) { // K개 보다 많으면 안됨
                continue;
            }
            if (cnt % 2 != K % 2) { // 홀수와 짝수가 맞으면 안됨
                continue;
            }
            ans = Math.max(ans, entry.getValue());
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int countZero(String status) {
        return (int) status.chars().filter(c -> c == '0').count();
    }
}
