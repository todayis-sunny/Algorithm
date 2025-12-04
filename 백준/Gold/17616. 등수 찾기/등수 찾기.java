// 17616. [G3] 등수 찾기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int WIN = 1, UNKNOW = 0, LOSE = -1;
    static int N, M, X;
    static int[][] battle; // 1-based, [a][b] : a와 b에 대해서 (1, a가 승), (0, 모름) (-1, a가 짐)
    static int high, low;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        battle = new int[N + 1][N + 1];
        // 대소관계 입력
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            battle[a][b] = WIN;
            battle[b][a] = LOSE;
        }
    }

    static void solve() {
        floydWarshall();
        high = 1;
        low = N;
        for (int y = 1; y <= N; y++) {
            // 상대적으로 이긴 경우 최저 등수가 점점 높아짐
            if (battle[X][y] == WIN) {
                low--;
            }
            // 상대적으로 진 경우 최고 등수가 점점 떨어짐
            else if (battle[X][y] == LOSE) {
                high++;
            }
        }
    }

    static void output() throws IOException {
        bw.write(high + " " + low);
        bw.flush();
    }

    static void floydWarshall() {
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int z = 1; z <= N; z++) {
                    if (battle[x][z] == WIN && battle[z][y] == WIN) {
                        battle[x][y] = WIN;
                        battle[y][x] = LOSE;
                    } else if (battle[x][z] == LOSE && battle[z][y] == LOSE) {
                        battle[x][y] = LOSE;
                        battle[y][x] = WIN;
                    }
                }
            }
        }
    }
}
/*
 # 접근 방법
 - 플로이드 워샬로 접근하기
   
 # 풀이 방법
 - 플로이드 워샬로 풀이하기
 - f(a,b)의 대소 관계를 f(a,c), f(c,b)로부터 도출
 #
 */