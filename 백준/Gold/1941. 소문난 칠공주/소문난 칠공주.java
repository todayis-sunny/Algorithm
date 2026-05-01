// 01941. [G3] 소문난 칠공주.

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static char[][] board = new char[5][5];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = s.charAt(j);
            }
        }
    }

    static void solve() {
        for (int bit = 0; bit < (1 << 25); bit++) {
            ans += bfs(bit);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int bfs(int bit) {
        int teamS = 0, teamY = 0; // 이다솜파, 임도연파
        // 7명이 아니라면 종료
        if (Integer.bitCount(bit) != 7) return 0;
        // 7명이 붙어 있는지 검사
        int n;
        for (n = 0; n < 25; n++) {
            if (((1 << n) & bit) != 0) break;
        }
        Queue<Node> queue = new LinkedList<>();
        int row = n / 5, col = n % 5;
        if (board[row][col] == 'Y') {
            teamY++;
        } else {
            teamS++;
        }
        queue.offer(new Node(row, col));
        bit = bit & (~(1 << n));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                // 범위를 벗어나면 스킵
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                // bit가 켜져있지 않으면 스킵
                int idx = nx * 5 + ny;
                if (((1 << idx) & bit) == 0) continue;
                // 해당 인원 점검
                if (board[nx][ny] == 'Y') {
                    teamY++;
                } else {
                    teamS++;
                }
                queue.offer(new Node(nx, ny));
                bit = bit & (~(1 << idx));
            }
        }
        // 7공주가 안되거나 이다솜파가 적어도 4명이 아니라면 종료
        if (teamS + teamY != 7 || teamS < 4) return 0;

        return 1;
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
