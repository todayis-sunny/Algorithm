// 01103. [G2] 게임.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int answer;

    static int N;
    static int M;
    static char[][] arr;
    static boolean[][] visited;
    static int[][] dp;
    static boolean inf = false;

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        visited = new boolean[N][M];
        dp = new int[N][M];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                arr[n][m] = input.charAt(m);
            }
        }
        answer = 0;
        visited[0][0] = true;
        dfs(0, 0, 0);
        if (inf) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(answer));
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int x, int y, int cnt) {
        if (inf) { // 무한 반복 가능하면,
            return;
        }
        dp[x][y] = cnt;


        for (int i = 0; i < 4; i++) {
            int length = Character.getNumericValue(arr[x][y]);
            int nx = x + dx[i] * length;
            int ny = y + dy[i] * length;
            // 범위가 벗어나거나 구멍으로 빠진다면,
            // (앞에 부터 순차적으로 검사하기 때문에 arr[nx][ny]는 주어진 범위 내에서 조건 검사)
            if (nx < 0 || nx >= N || ny < 0 || ny >= M || arr[nx][ny] == 'H') {
                answer = Math.max(answer, cnt + 1);
                continue;
            }
            // 이미 방문된 곳이라면, 무한루프 판정.
            if (visited[nx][ny]) {
                inf = true;
                continue;
            }
            // 메모 되어있는 cnt가 더 크다면, 해볼 필요없음.
            if (cnt + 1 <= dp[nx][ny]) {
                continue;
            }

            visited[nx][ny] = true;
            dfs(nx, ny, cnt + 1);
            visited[nx][ny] = false;
        }

    }

}