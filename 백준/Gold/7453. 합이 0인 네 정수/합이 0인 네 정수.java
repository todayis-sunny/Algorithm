// 07453. [G2] 합이 0인 네 정수.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[][] data;
    static long[] AB, CD;
    static int N, LIMIT;
    static long ans;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        LIMIT = N*N;
        ans = 0;
        data = new int [4][N];
        AB = new long[LIMIT];
        CD = new long[LIMIT];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            data[0][n] = Integer.parseInt(st.nextToken());
            data[1][n] = Integer.parseInt(st.nextToken());
            data[2][n] = Integer.parseInt(st.nextToken());
            data[3][n] = Integer.parseInt(st.nextToken());
        }
        // 좌측 2개 조합.
        int idx = 0;
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                AB[idx++] = data[0][a] + data[1][b];;
            }
        }
        // 우측 2개 조합.
        idx = 0;
        for (int c = 0; c < N; c++) {
            for (int d = 0; d < N; d++) {
                CD[idx++] = data[2][c] + data[3][d];;
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);
        int ab = 0;
        int cd = LIMIT-1;
        while (ab < LIMIT && cd >= 0) {
            long abValue = AB[ab];
            long cdValue = CD[cd];
            long value = abValue + cdValue;
            if(value < 0) {
                ab++;
            } else if (value > 0) {
                cd--;
            } else {
                long abCnt = 1;
                long cdCnt = 1;
                while (++ab < LIMIT && AB[ab] == abValue) {
                    abCnt++;
                }
                while (--cd >= 0 && CD[cd] == cdValue) {
                    cdCnt++;
                }
                ans += abCnt * cdCnt;
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

}
