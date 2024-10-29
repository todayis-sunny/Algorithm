// 04179. [G3] 불!.
import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] maze;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> jihoon = new LinkedList<>();
    static Queue<int[]> fire = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                maze[i][j] = line.charAt(j);
                if (maze[i][j] == 'J') {
                    jihoon.offer(new int[]{i, j, 0});
                } else if (maze[i][j] == 'F') {
                    fire.offer(new int[]{i, j});
                }
            }
        }

        int result = bfs();
        System.out.println(result == -1 ? "IMPOSSIBLE" : result);
    }

    static int bfs() {
        while (!jihoon.isEmpty()) {
            // 불 먼저 확산
            int fireSize = fire.size();
            while (fireSize-- > 0) {
                int[] f = fire.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = f[0] + dx[i];
                    int ny = f[1] + dy[i];
                    if (nx >= 0 && nx < R && ny >= 0 && ny < C && maze[nx][ny] == '.') {
                        maze[nx][ny] = 'F';
                        fire.offer(new int[]{nx, ny});
                    }
                }
            }

            // 지훈이 이동
            int jihoonSize = jihoon.size();
            while (jihoonSize-- > 0) {
                int[] j = jihoon.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = j[0] + dx[i];
                    int ny = j[1] + dy[i];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                        return j[2] + 1; // 탈출 성공
                    }
                    if (maze[nx][ny] == '.') {
                        maze[nx][ny] = 'J';
                        jihoon.offer(new int[]{nx, ny, j[2] + 1});
                    }
                }
            }
        }
        return -1; // 탈출 불가능
    }
}
