// 02637. [G2] 장난감 조립.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[][] dp; // dp[i][j] : 부품 i를 만들 때 필요한 기본 부품 j의 수
    static ArrayList<ArrayList<Component>> compList;
    static boolean[] notBasic;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1];
        compList = new ArrayList<>();
        notBasic = new boolean[N + 1];
        for (int n = 0; n <= N; n++) {
            compList.add(new ArrayList<>());
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            compList.get(Y).add(new Component(X, K));
            dp[X][0]++;
            notBasic[X] = true;
        }
        Queue<Integer> queue = new LinkedList<>();
        // 진입 차수가 0인 기본 부품들 큐에 추가
        for (int n = 1; n <= N; n++) {
            if (dp[n][0] == 0) {
                queue.offer(n);
                dp[n][n] = 1; // 자기 자신을 만드는 데 1개 필요로 설정
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (Component comp : compList.get(curr)) {
                int next = comp.part;
                int quantity = comp.cnt;
                for (int n = 1; n <= N; n++) {
                    dp[next][n] += dp[curr][n] * quantity;
                }
                dp[next][0]--;
                if (dp[next][0] == 0) {
                    queue.offer(next);
                }
            }
        }
        for (int n = 1; n <= N; n++) {
            if (!notBasic[n] && dp[N][n] > 0) {
                bw.write(n + " " + dp[N][n] + "\n");
            }
        }
        bw.flush();
    }

    static class Component {
        int part;
        int cnt;

        public Component(int part, int cnt) {
            this.part = part;
            this.cnt = cnt;
        }
    }

}
