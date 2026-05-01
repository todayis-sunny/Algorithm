// 05549. [G5] 행성 탐사.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int M, N;
    static int K;
    static Planet planet;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // M, N, K 입력
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        planet = new Planet();
        // 행성 입력
        for (int r = 1; r <= M; r++) {
            String input = br.readLine();
            for (int c = 1; c <= N; c++) {
                char ch = input.charAt(c - 1);
                switch (ch) {
                    case 'J':
                        planet.info[0][r][c] = 1;
                        break;
                    case 'O':
                        planet.info[1][r][c] = 1;
                        break;
                    case 'I':
                        planet.info[2][r][c] = 1;
                        break;
                }
            }
        }
        // 쿼리 입력
        for (int k = 0; k < K; k++) {
            int r1, c1, r2, c2;
            st = new StringTokenizer(br.readLine());
            r1 = Integer.parseInt(st.nextToken());
            c1 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            c2 = Integer.parseInt(st.nextToken());
            planet.queries[k] = new Query(r1, c1, r2, c2);
        }
    }

    static void solve() {
        planet.calculatePrefixSum();
        planet.calculateQueries();
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static class Planet {
        int[][][] info;
        int[][][] pSum;
        Query[] queries;

        public Planet() {
            this.info = new int[3][M + 1][N + 1];
            this.pSum = new int[3][M + 1][N + 1];
            this.queries = new Query[K];
        }

        void calculatePrefixSum() {
            for (int t = 0; t < 3; t++) {
                for (int r = 1; r <= M; r++) {
                    for (int c = 1; c <= N; c++) {
                        pSum[t][r][c] = info[t][r][c] + (pSum[t][r - 1][c] + pSum[t][r][c - 1] - pSum[t][r - 1][c - 1]);
                    }
                }
            }
        }

        void getPrefixSum(Query query) {
            for (int t = 0; t < 3; t++) {
                int sum = pSum[t][query.r2][query.c2] - (pSum[t][query.r1 - 1][query.c2] + pSum[t][query.r2][query.c1 - 1] - pSum[t][query.r1 - 1][query.c1 - 1]);
                sb.append(sum).append(" ");
            }
            sb.append("\n");
        }

        void calculateQueries() {
            for (int k = 0; k < K; k++) {
                Query query = queries[k];
                getPrefixSum(query);
            }
        }
    }

    static class Query {
        int r1, c1, r2, c2;

        public Query(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }
    }
}
