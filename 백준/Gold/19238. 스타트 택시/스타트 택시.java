// 19238. [G2] 스타트 택시.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int WALL = -1;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    static int N, M; // N: 격자의 한변의 크기, M: 승객의 수;
    static Taxi taxi;
    static Guest[] guests; // 1-based;
    static int[][] board;
    static int completed = 0;
    private static Pair<Integer, Integer> pair;
    static int ans = -1;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        guests = new Guest[M + 1];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = -Integer.parseInt(st.nextToken()); // 음수로 기입하여 1은 -> -1로 벽
            }
        }
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        taxi = new Taxi(new Node(x, y), fuel);
        guests[0] = new Guest(new Node(x, y), new Node(x, y));
        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            guests[m] = new Guest(new Node(x1, y1), new Node(x2, y2));
        }
        for (int i = 1; i <= M; i++) {
            Node node = guests[i].startNode;
            board[node.x][node.y] = i;
        }
    }

    static void solve() {
        while (completed != M) {
            // 승객을 선정하기
            Pair<Integer, Integer> pair = selectGuest();
            int guest = pair.first;
            int d1 = pair.second;
            // 승객을 선정할수 없는 경우
            if (guest == -1) return;
            int d2 = driveTaxi(guest);
            if (d2 == -1) return;
            // 연료가 바닥나는 경우
            if (d1 + d2 > taxi.currFuel) {
                return;
            }
            // 연료 보너스 적용
            taxi.currFuel -= (d1 - d2);
            completed++;
        }
        ans = taxi.currFuel;
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static Pair<Integer, Integer> selectGuest() {
        // 현재위치에 승객이 있는지 체크
        if (board[taxi.currNode.x][taxi.currNode.y] > 0) {
            int guest = board[taxi.currNode.x][taxi.currNode.y];
            board[taxi.currNode.x][taxi.currNode.y] = 0;
            return new Pair<>(guest, 0);
        }
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Pair<Node, Integer>> pq = new PriorityQueue<>(
                Comparator.comparing((Pair<Node, Integer> p) -> p.second)
                        .thenComparing((Pair<Node, Integer> p) -> p.first.x)
                        .thenComparing((Pair<Node, Integer> p) -> p.first.y)
        );
        pq.offer(new Pair<>(taxi.currNode, 0));
        visited[taxi.currNode.x][taxi.currNode.y] = true;
        while (!pq.isEmpty()) {
            Pair<Node, Integer> pair = pq.poll();
            Node curr = pair.first;
            int dist = pair.second;
            // 승객이 있는 경우
            if (board[curr.x][curr.y] > 0) {
                int guest = board[curr.x][curr.y];
                board[curr.x][curr.y] = 0;
                return new Pair<>(guest, dist);
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                int nextDist = dist + 1;
                // 범위 밖인 경우
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                // 벽이면 무시
                if (board[nx][ny] == WALL) continue;
                // 이미 방문한 경우
                if (visited[nx][ny]) continue;
                // 상단 케이스를 통과하면 로직실행
                pq.offer(new Pair<>(new Node(nx, ny), nextDist));
                visited[nx][ny] = true;

            }
        }
        return new Pair<>(-1, -1);
    }

    static int driveTaxi(int g) {
        // 승객의 출발지와 도착지가 같은 경우
        if (guests[g].startNode.x == guests[g].goalNode.x && guests[g].startNode.y == guests[g].goalNode.y) {
            return 0;
        }
        boolean[][] visited = new boolean[N][N];
        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(new Node(guests[g].startNode.x, guests[g].startNode.y), 0));
        visited[guests[g].startNode.x][guests[g].startNode.y] = true;
        while (!queue.isEmpty()) {
            Pair<Node, Integer> pair = queue.poll();
            Node curr = pair.first;
            int dist = pair.second;
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                int nextDist = dist + 1;
                // 범위 밖인 경우
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                // 벽이면 무시
                if (board[nx][ny] == WALL) continue;
                // 이미 방문한 경우
                if (visited[nx][ny]) continue;
                // 상단 케이스를 통과하면 로직실행
                queue.offer(new Pair<>(new Node(nx, ny), nextDist));
                visited[nx][ny] = true;
                // 도착지에 도달한 경우
                if (nx == guests[g].goalNode.x && ny == guests[g].goalNode.y) {
                    taxi.currNode = new Node(nx, ny);
                    return nextDist;
                }
            }
        }
        // 승객을 데려다 줄수 없는 경우
        return -1;
    }

    static class Taxi {
        Node currNode; // 현재 위치
        int currFuel; // 현재 연료

        public Taxi(Node currNode, int currFuel) {
            this.currNode = currNode;
            this.currFuel = currFuel;
        }
    }

    static class Guest {
        Node startNode;
        Node goalNode;

        public Guest(Node startNode, Node goalNode) {
            this.startNode = startNode;
            this.goalNode = goalNode;
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Pair<A, B> {
        public A first;
        public B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }
}
