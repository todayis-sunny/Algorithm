// 13460. [G1] 구슬 탈출 2.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static boolean[] visited;
    static byte[] dx = new byte[]{-1, 1, 0, 0};
    static byte[] dy = new byte[]{0, 0, -1, 1};
    static int N, M, ans;
    static Node hall, redBall, blueBall;
    static char[][] board;
    public static void main(String[] args) throws IOException {
        ans = -1;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[10000];
        for (byte n = 0; n < N; n++) {
            String input = br.readLine();
            for (byte m = 0; m < M; m++) {
                char data = input.charAt(m);
                if(data == 'B') {
                    blueBall = new Node(n, m);
                    data = '.';
                } else if (data == 'R') {
                    redBall = new Node(n, m);
                    data = '.';
                } else if (data == 'O') {
                    hall = new Node(n, m);
                }
                board[n][m] = data;
            }
        }

        bfs();
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean success(Node red) {
        return red.x == hall.x && red.y == hall.y;
    }
    static boolean failure(Node blue) {
        return blue.x == hall.x && blue.y == hall.y;
    }
    static Node move(byte idx, Node node, Node stop) {

        for (byte i = 0; ; i++) {
            byte nx = (byte) (node.x + dx[idx]*i);
            byte ny = (byte) (node.y + dy[idx]*i);
            if(board[nx][ny] == '#') { // 벽을 만나면 그 이전으로
                nx -= dx[idx];
                ny -= dy[idx];

                return new Node(nx, ny);
            } else if (board[nx][ny] == 'O') {
                return new Node(nx, ny);
            } else if (nx == stop.x&& ny == stop.y) {
                nx -= dx[idx];
                ny -= dy[idx];
                return new Node(nx, ny);
            }
        }
    }
    static int visit(Node red, Node blue) {
        return red.x * 1000 + red.y * 100 + blue.x * 10 + blue.y;
    }
    static void bfs() {
        Queue<Game> queue = new LinkedList<>();
        queue.offer(new Game((byte) 0, redBall, blueBall));
        visited[visit(redBall, blueBall)] = true;
        while (!queue.isEmpty()) {
            Game curr = queue.poll();
            if(success(curr.red) && !failure(curr.blue)) {
                ans = curr.cnt;
                return;
            }
            if (failure(curr.blue)) {
                continue;
            }
            for (byte idx = 0; idx < 4; idx++) {
                Node nextBlue;
                Node nextRed;
                if(idx == 0) { // 상
                    if(curr.red.x < curr.blue.x) {
                        nextRed = move(idx, curr.red, curr.blue);
                        nextBlue = move(idx, curr.blue, nextRed);
                    } else {
                        nextBlue = move(idx, curr.blue, curr.red);
                        nextRed = move(idx, curr.red, nextBlue);
                    }
                } else if (idx == 1) { // 하
                    if(curr.red.x > curr.blue.x) {
                        nextRed = move(idx, curr.red, curr.blue);
                        nextBlue = move(idx, curr.blue, nextRed);
                    } else {
                        nextBlue = move(idx, curr.blue, curr.red);
                        nextRed = move(idx, curr.red, nextBlue);
                    }
                } else if (idx == 2) { // 좌
                    if(curr.red.y < curr.blue.y) {
                        nextRed = move(idx, curr.red, curr.blue);
                        nextBlue = move(idx, curr.blue, nextRed);
                    } else {
                        nextBlue = move(idx, curr.blue, curr.red);
                        nextRed = move(idx, curr.red, nextBlue);
                    }
                } else { // 우
                    if(curr.red.y > curr.blue.y) {
                        nextRed = move(idx, curr.red, curr.blue);
                        nextBlue = move(idx, curr.blue, nextRed);
                    } else {
                        nextBlue = move(idx, curr.blue, curr.red);
                        nextRed = move(idx, curr.red, nextBlue);
                    }
                }
                int visit = visit(nextRed, nextBlue);
                if(curr.cnt <= 9 && !visited[visit]) {
                    queue.offer(new Game((byte) (curr.cnt + 1), nextRed, nextBlue));
                    visited[visit] = true;
                }

            }
        }
    }
    static class Node{
        byte x;
        byte y;
        Node(byte x, byte y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Game{
        byte cnt;
        Node red;
        Node blue;
        Game(byte cnt, Node red, Node blue) {
            this.cnt = cnt;
            this.red = red;
            this.blue = blue;
        }
    }
}
