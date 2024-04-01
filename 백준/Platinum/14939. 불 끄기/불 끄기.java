// 14939. [P4] 불 끄기.
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] bulbs = new int[10][10];
    static int[][] toggle = new int[10][10];
    static int[] dx = new int[]{-1, 0, 0, 0, 1};
    static int[] dy = new int[]{0, -1, 0, 1, 0};
    static int count, answer;
    public static void main(String[] args) throws IOException {
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            String input = br.readLine();
            for (int j = 0; j < 10; j++) {
                if (input.charAt(j) == 'O') {
                    bulbs[i][j] = 1;
                }
            }
        }
        // 2^10개의 상황체크하기.
        Bits:
        for (int bit = 0; bit < (1 << 10); bit++) {
            count = 0;

            // 0행을 조정하기.
            for (int chk = 0; chk < 10; chk++) {
                toggle[0][chk] = (bit & (1 << chk)) == 0 ? 0 : 1;
                count += toggle[0][chk];
            }
            // 토글 결정하기.
            for (int row = 1; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    toggle[row][col] ^= check(row-1, col);
                    count += toggle[row][col];
                }
            }
            // 마지막 행 검사하기.
            for (int col = 0; col < 10; col++) {
                if (check(9, col) == 1) {
                    continue Bits;
                }
            }
            answer = Math.min(answer, count);
        }
        if (answer == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(answer));
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int check(int x, int y) {
        int chk = 0;
        for (int i = 0; i < 5; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= 10 || ny < 0 || ny >= 10) {
                continue;
            }
            chk += toggle[nx][ny];
        }
        if ((chk % 2) == bulbs[x][y]) {
            return 0;
        }
        return 1;
    }
}