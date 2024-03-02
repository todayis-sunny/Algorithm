// 02105. [A형] 디저트 카페.

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, answer, initX, initY;
    static boolean[] visited;
    static int[][] arr;
    static int[] dx = new int[]{-1, -1, 1, 1};
    static int[] dy = new int[]{1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            visited = new boolean[101];
            answer = -1;
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    initX = i;
                    initY = j;
                    dfs(i, j, 0, 0);
                }
            }
            bw.write("#" + tc + " " + answer + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int x, int y, int idx, int cnt) {
        if (x == initX && y == initY && idx == 3) {
            answer = Math.max(answer, cnt);
            return;
        }
        for (int i = idx; i < 4; i++) {
            if (i <= idx + 1) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (availableMap(nx, ny) && !visited[arr[nx][ny]]) {
                    visited[arr[nx][ny]] = true;
                    dfs(nx, ny, i, cnt + 1);
                    visited[arr[nx][ny]] = false;
                }
            }
        }


    }

    static boolean availableMap(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return false;
        }
        return true;
    }

}