import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C;
    static int[][] beehive;
    static int[][] honey;
    static int answer;

    static void dfs(int idx, int c, int total, int x, int y) {
        if (idx == M) {
            honey[x][y] = Math.max(honey[x][y], total);
            return;
        }
        if (beehive[x][y + idx] <= c) {
            dfs(idx + 1, c - beehive[x][y + idx], total + beehive[x][y + idx] * beehive[x][y + idx], x, y); // 수학하는 경우
        }
        dfs(idx + 1, c, total, x, y); // 수학하지 않는 경우
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            beehive = new int[N][N];
            honey = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    beehive[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int limit = N - M;
            int pointer = 0;
            // 벌꿀의 최대값 저장
            while (pointer < N * N) {
                int div = pointer / N;
                int mod = pointer % N;
                if (mod > limit) {
                    pointer = (div + 1) * N;
                    continue;
                }
                dfs(0, C, 0, div, mod);
                pointer++;
            }

            answer = 0;
            for (int h1 = 0; h1 < N * N; h1++) {
                int x1 = h1 / N;
                int y1 = h1 % N;
                if (honey[x1][y1] == 0) {
                    continue;
                }
                for (int h2 = h1 + M; h2 < N * N; h2++) {
                    int x2 = h2 / N;
                    int y2 = h2 % N;
                    if (honey[x1][y1] == 0) {
                        continue;
                    }
                    answer = Math.max(answer, honey[x1][y1] + honey[x2][y2]);
                }
            }

            bw.write("#" + tc + " " + answer + "\n");
        }

        br.close();
        bw.close();
    }
}
