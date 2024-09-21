// 13460. [G1] 구슬 탈출 2.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static int N, M, ans;
    static Node hall, redBall, blueBall;
    static char[][] board;
    public static void main(String[] args) throws IOException {
        ans = -1;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
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
    static Node move(int idx, Node node, Node stop) {
//        System.out.println("Node : " + node.x + ":" + node.y);
//        System.out.println("Stop : " + stop.x + ":" + stop.y);

        for (int i = 0; ; i++) {
            int nx = node.x + dx[idx]*i;
            int ny = node.y + dy[idx]*i;
//            System.out.println("node x : " + node.x + "node y : " + node.y);
//            System.out.println("nx : " + nx + " | ny : " + ny + " i : " + i);
//            System.out.println(i + " | "+ nx + ":" + ny);
            if(board[nx][ny] == '#') { // 벽을 만나면 그 이전으로
                nx -= dx[idx];
                ny -= dy[idx];

//                    System.out.println("벽 minus 1 nx : " + nx + " ny : " + ny);
//                    System.out.println(node.x + " " + node.y + " " + idx + " " + i);
                return new Node(nx, ny);
            } else if (board[nx][ny] == 'O') {
//                    System.out.println("구멍 minus 1 nx : " + nx + " ny : " +ny);
//                    System.out.println(node.x + " " + node.y + " " + idx + " " + i);
                return new Node(nx, ny);
            } else if (nx == stop.x&& ny == stop.y) {
                nx -= dx[idx];
                ny -= dy[idx];
//                    System.out.println("충돌충돌 minus 1 nx : " + nx + " ny : " +ny);
//                    System.out.println(node.x + " " + node.y + " " + idx + " " + i);
                return new Node(nx, ny);
            }
        }
    }
    static void bfs() {
        Queue<Game> queue = new LinkedList<>();
        queue.offer(new Game(0, redBall, blueBall));
//        System.out.println(redBall.x + " " + redBall.y);
//        System.out.println(blueBall.x + " " + blueBall.y);
        while (!queue.isEmpty()) {
            Game curr = queue.poll();
//            System.out.println(curr.cnt);
            if(success(curr.red) && !failure(curr.blue)) {
                ans = curr.cnt;
                return;
            }
            if (failure(curr.blue)) {
                continue;
            }
            for (int idx = 0; idx < 4; idx++) {
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
//                        System.out.println("좌로 이동중 :  0 :" + curr.red.x + " | " + curr.red.y + "|||"+ curr.blue.x + " | " + curr.blue.y );
                        nextRed = move(idx, curr.red, curr.blue);
//                        System.out.println("좌로 이동중 :  1 :" + nextRed.x + " | " + nextRed.y + "|||"+ curr.blue.x + " | " + curr.blue.y );
                        nextBlue = move(idx, curr.blue, nextRed);
//                        System.out.println("좌로 이동중 : 2 :" + nextRed.x + " | " + nextRed.y + "|||"+ nextBlue.x + " | " + nextBlue.y );
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
                if(curr.cnt <= 9) {
                    queue.offer(new Game(curr.cnt + 1, nextRed, nextBlue));
                }

            }
        }
    }
    static class Node{
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Game{
        int cnt;
        Node red;
        Node blue;
        Game(int cnt, Node red, Node blue) {
            this.cnt = cnt;
            this.red = red;
            this.blue = blue;
        }
    }
}