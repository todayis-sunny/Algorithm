// 01799. [G1] 비숍.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[][] visited;
    static boolean[] diagonalL; // 좌상향 대각선.
    static boolean[] diagonalR; // 우상향 대각선.
    static boolean[][] board;
    static int N, answer;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        diagonalL = new boolean[2*N-1];
        diagonalR = new boolean[2*N-1];
        board = new boolean[N][N];
        visited = new int[N][N];
        answer = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) {
                    board[i][j] = true;
                }
            }
        }
        dfs(0, 0);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int depth, int count) {
        if (depth == 2 * N - 1) {
            answer = Math.max(answer, count);
            return;
        }
        boolean flag = false;
        for (int i = 0; i < N - Math.abs(depth - (N-1)); i++) {
            int x = N - 1 <= depth ? (N - 1) - i : depth - i;
            int y = N - 1 <= depth ? depth - (N - 1) + i : i;
            if (board[x][y] && !diagonalL[(N-1) + (x- y)] && !diagonalR[depth]) {
                flag = true;
                diagonalL[(N-1) + (x- y)] = true;
                diagonalR[depth] = true;
                dfs(depth+1, count+1);
                diagonalL[(N-1) + (x- y)] = false;
                diagonalR[depth] = false;
            }
        }
        if (!flag) {
            dfs(depth+1, count);
        }

    }
}