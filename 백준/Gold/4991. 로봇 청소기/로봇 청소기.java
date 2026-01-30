// 04991. [G1] 로봇 청소기.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final int INF = 1_000_000;
    static final char CH_CLEAN = '.', CH_DIRTY = '*', CH_FURNITURE = 'x', CH_ROBOT = 'o';
    static final int INT_CLEAN = -1, INT_FURNITURE = -2, INT_ROBOT = 0;
    static final int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static Room room;


    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    static void input() throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            if (W == 0) return;
            room = new Room(W, H);
            Queue<Node> queue = new LinkedList<>();
            int cnt = 0;
            for (int h = 0; h < H; h++) {
                String input = br.readLine();
                for (int w = 0; w < W; w++) {
                    char ch = input.charAt(w);
                    int data = INT_CLEAN;
                    switch (ch) {
                        case CH_DIRTY:
                            queue.offer(new Node(h, w));
                            data = ++cnt;
                            break;
                        case CH_ROBOT:
                            room.dirty.add(new Node(h, w));
                            data = INT_ROBOT;
                            break;
                        case CH_CLEAN:
                            break;
                        case CH_FURNITURE:
                            data = INT_FURNITURE;
                            break;
                    }
                    room.info[h][w] = data;
                }
            }
            while (!queue.isEmpty()) {
                room.dirty.add(queue.poll());
            }
            room.init();
            solve();
        }
    }

    static void solve() {
        room.calculateDist();
        int minimum = tsp(0, 1);
        if (minimum == INF) minimum = -1;
        sb.append(minimum).append("\n");
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static int tsp(int prev, int bit) {
        // 모든 지점을 다 방문한 경우
        if (bit == (1 << room.size) - 1) {
            // 다시 돌아갈 필요가 없으므로 0으로 리턴
            return 0;
        }
        // 이미 방문한 지점인지 확인
        if (room.bitmask[prev][bit] != INF) return room.bitmask[prev][bit];
        // 방문하지 않은 지점 탐색
        for (int i = 1; i < room.size; i++) {
            // 경로가 없는 경우 스킵
            if (room.dist[prev][i] == 0) continue;
            // 이미 방문을 한 경우 스킵
            if ((bit & (1 << i)) != 0) continue;
            // 새롭게 방문하는 경우
            int next = bit | (1 << i);
            room.bitmask[prev][bit] = Math.min(room.bitmask[prev][bit], tsp(i, next) + room.dist[prev][i]);
        }
        return room.bitmask[prev][bit];
    }

    static class Room {
        int W, H;
        List<Node> dirty; // 1-based, 0번은 로봇
        int size; // dirty 사이즈
        int[][] info;
        int[][] dist; // [i][j] : i에서 j까지의 거리
        int[][] bitmask;

        public Room(int w, int h) {
            this.W = w;
            this.H = h;
            info = new int[H][W];
            dirty = new ArrayList<>();
        }

        void init() {
            this.size = dirty.size();
            dist = new int[size][size];
            bitmask = new int[size][1 << size];
            for (int i = 0; i < size; i++) {
                Arrays.fill(bitmask[i], INF);
            }
        }

        void calculateDist() {
            for (int k = 0; k < size; k++) {
                Node start = dirty.get(k);
                Queue<Node> queue = new LinkedList<>();
                int[][] dist = new int[H][W];
                for (int h = 0; h < H; h++) {
                    Arrays.fill(dist[h], -1);
                }
                dist[start.x][start.y] = 0;
                queue.offer(start);
                while (!queue.isEmpty()) {
                    Node curr = queue.poll();
                    for (int i = 0; i < 4; i++) {
                        int nx = curr.x + dx[i];
                        int ny = curr.y + dy[i];
                        // 범위를 벗어나는 경우
                        if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                        // 방문한 경우
                        if (dist[nx][ny] != -1) continue;
                        // 벽인 경우
                        if (info[nx][ny] == INT_FURNITURE) continue;
                        // 가능한 경우
                        dist[nx][ny] = dist[curr.x][curr.y] + 1;
                        queue.offer(new Node(nx, ny));
                        // 더러운 곳인 경우
                        if (info[nx][ny] >= 0) {
                            room.dist[k][info[nx][ny]] = dist[nx][ny];
                        }
                    }
                }
            }
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
