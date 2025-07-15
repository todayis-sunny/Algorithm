// 01348. [P2] 주차장

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int WALL = -1, EMPTY = -2;
    static int R, C;
    static int[][] parkingLot; // 주차장 [R][C]
    static int cars = 0, parking = 0; // 차 개수, 주차장 자리 수
    static int ans = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Node> carList = new ArrayList<>(); // 차 정보들
    static int[][] distance; // [c][p] 차량 c에서 주차공간 p까지 거리
    static boolean[] successParking; // 주차 여부
    static int[] connect; // 어떤 차량이 주차했는지

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        parkingLot = new int[R][C];
        for (int r = 0; r < R; r++) {
            String input = br.readLine();
            for (int c = 0; c < C; c++) {
                char ch = input.charAt(c);
                parkingLot[r][c] = ch;
                if (ch == 'C') {
                    cars++;
                    carList.add(new Node(r, c));
                    parkingLot[r][c] = EMPTY;
                } else if (ch == 'P') {
                    parkingLot[r][c] = parking++;
                } else if (ch == 'X') {
                    parkingLot[r][c] = WALL;
                } else {
                    parkingLot[r][c] = EMPTY;
                }
            }
        }
    }

    static void solve() {
        if (cars == 0) { // 차가 없으면
            ans = 0;
            return;
        }
        if (cars > parking) { // 주차할 수 있는 공간이 부족하다면
            ans = -1;
            return;
        }
        calculateDistance();
        // 이분 탐색
        int left = 0, right = 2500;
        loop:
        while (left <= right) {
            boolean success = true;
            int mid = (left + right) / 2;
            connect = new int[parking];
            Arrays.fill(connect, -1);
            for (int c = 0; c < cars; c++) {
                successParking = new boolean[parking];
                // 차 하나라도 주차가 불가능하면 탈출
                if (!match(c, mid)) {
                    success = false;
                    break;
                }
                ;
            }
            // 차가 전부 주차되었다면
            if (success) {
                int result = 0;
                for (int p = 0; p < parking; p++) {
                    if (connect[p] != -1) { // p번 공간에 차가 주차된 상태라면
                        int car = connect[p]; // 주차된 차량
                        result = Math.max(result, distance[car][p]); // 제일 오래걸리는 시간을 기입
                    }
                }
                ans = Math.min(ans, result); // 정답을 갱신
                right = mid - 1; // 모든 차량이 주차가 완료되었으니 시간을 더 좁혀봄
                continue loop;
            } else { // 차가 전부 주차되지 않았다면
                left = mid + 1; // 모든 차량이 주차가 완료되지 않았으니 시간을 더 늘려봄
            }
        }
        if (ans == Integer.MAX_VALUE) { // 갱신 되지 않았으면
            ans = -1; // 불가능한 경우
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    /**
     * 이분 매칭
     *
     * @param c     차량
     * @param limit 이분 탐색으로 구한 mid값 즉 거리
     * @return 매칭 성공 여부
     */
    static boolean match(int c, int limit) {
        for (int p = 0; p < parking; p++) {
            // 이미 p공간에 주차되어 있거나, c차량이 p까지 거리가 limit보다 크다면 무시
            if (successParking[p] || distance[c][p] > limit) continue;
            successParking[p] = true;
            // p공간에 연결된 차량이 없거나, p공간에 연결된 차량을 다른 공간에 배치할수 있으면
            if (connect[p] == -1 || match(connect[p], limit)) {
                connect[p] = c;
                return true;
            }
        }
        return false;
    }

    /**
     * 거리 구하기
     */
    static void calculateDistance() {
        distance = new int[cars][parking];
        for (int c = 0; c < cars; c++) {
            Arrays.fill(distance[c], Integer.MAX_VALUE);
        }
        for (int c = 0; c < cars; c++) {
            int[][] visited = new int[R][C]; // 거리를 기입 시작 1-base 실제 값 -1 필요
            Queue<Node> queue = new LinkedList<>();
            Node car = carList.get(c);
            queue.offer(car);
            visited[car.x][car.y] = 1;
            int rest = parking; // 남은 주차장
            loop:
            while (!queue.isEmpty()) {
                Node curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    Node next = new Node(curr.x + dx[i], curr.y + dy[i]);
                    // 범위 밖이면 무시
                    if (next.x < 0 || next.x >= R || next.y < 0 || next.y >= C) continue;
                    // 벽이면 무시
                    if (parkingLot[next.x][next.y] == WALL) continue;
                    // 이미 방문 했으면 무시
                    if (visited[next.x][next.y] != 0) continue;
                    visited[next.x][next.y] = visited[curr.x][curr.y] + 1; // 거리 기입
                    queue.offer(next); // 큐에 삽입

                    // 만약 해당 공간이 주차공간이라면 거리 기록
                    if (parkingLot[next.x][next.y] >= 0) {
                        int number = parkingLot[next.x][next.y]; // 주차장 번호
                        distance[c][number] = visited[next.x][next.y] - 1; // 실제 값 - 1을 적용
                        if (--rest == 0) break loop; // 남은 주차공간이 줄어들고 탐색할게 없으면 메인 loop를 탈출
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
