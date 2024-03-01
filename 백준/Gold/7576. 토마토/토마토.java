// 07576. [G5] 토마토.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static Queue<Tomato> queue;
    static int N, M, answer;
    static int[][] arr;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M][N];
        queue = new LinkedList<>();
        answer = 0;
        int unripeTomato = 0;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                int tmp = Integer.parseInt(st.nextToken());
                arr[m][n] = tmp;
                if (tmp == 0) {
                    unripeTomato += 1;
                } else if (tmp == 1) {
                    queue.offer(new Tomato(m, n));
                }
            }
        }
        if (unripeTomato == 0) {
            answer = 0;
            bw.write("0");
            bw.flush();
            bw.close();
            br.close();
            return;
        } else {
            bfs();
        }
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                if (arr[m][n] == 0) {
                    bw.write("-1");
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
                answer = Math.max(answer, arr[m][n] - 1);
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Tomato tomato = queue.poll();
            int x = tomato.x;
            int y = tomato.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                    continue;
                }
                if (arr[nx][ny] != 0) {
                    continue;
                }
                arr[nx][ny] = arr[x][y] + 1;
                queue.offer(new Tomato(nx, ny));
            }
        }
    }

    static class Tomato {
        int x, y;

        Tomato(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}