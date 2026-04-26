// 02873. [P3] 롤러코스터.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int R, C;
    static int[][] rollerCoaster;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        rollerCoaster = new int[R][C];
        for (int r = 0; r < R; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                rollerCoaster[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        // 짝x짝 인 경우
        if (R % 2 == 0 && C % 2 == 0) {
            int minR = 0, minC = 0, value = Integer.MAX_VALUE;
            // 탐색 체스판의 시작칸과 종료칸이 백칸이라고 했을때, 흑칸의 최소를 찾아내기
            for (int r = 0; r < R; r++) {
                // 2칸씩 탐색
                for (int c = (r + 1) % 2; c < C; c += 2) {
                    if (rollerCoaster[r][c] < value) {
                        minR = r;
                        minC = c;
                        value = rollerCoaster[r][c];
                    }
                }
            }
            // 상단 진행 R .. -> D -> L ..
            int topR = (minR / 2) * 2;
            for (int r = 0; r < topR; r++) {
                for (int c = 0; c < C - 1; c++) {
                    sb.append((r % 2 == 0) ? "R" : "L");
                }
                sb.append("D");
            }
            // 중단 진행 (꼬불꼬불)
            // 찾기 전
            for (int c = 0; c < minC; c++) {
                if (c % 2 == 0) sb.append("DR");
                else sb.append("UR");
            }
            // 찾은 후
            for (int c = minC; c < C - 1; c++) {
                if (c % 2 == 0) sb.append("RD");
                else sb.append("RU");
            }
            if ((minR) / 2 != (R / 2) - 1) sb.append("D");
            // 하단 진행 L .. -> D -> R ..
            int bottomR = topR + 2;
            for (int r = bottomR; r < R; r++) {
                for (int c = 0; c < C - 1; c++) {
                    sb.append((r % 2 == 0) ? "L" : "R");
                }
                if (r == R - 1) break;
                sb.append("D");
            }
            return;
        }
        // 홀x홀 or 홀x짝 인 경우
        if (R % 2 == 1) {
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C - 1; c++) {
                    sb.append((r % 2 == 0) ? "R" : "L");
                }
                if (r == R - 1) break;
                sb.append("D");
            }
        } else {
            for (int c = 0; c < C; c++) {
                for (int r = 0; r < R - 1; r++) {
                    sb.append((c % 2 == 0) ? "D" : "U");
                }
                if (c == C - 1) break;
                sb.append("R");
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(sb));
        bw.flush();
    }
}
