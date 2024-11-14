// 01516. [G3] 게임 개발.
import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static PriorityQueue<Building> pq;
    static int[][] arr; // [x][0] : 진입 차수, [x][N+1] : 건물 건설 시간, [x][N+2] : 건물 건설 완료 시간
    static int N, buildTime, completeTime;

    public static void main(String[] args) throws IOException {
        input();
        // 진입 차수가 0인 것들 건물 짓기 시작
        for (int n = 1; n <= N; n++) {
            if (arr[n][0] == 0) {
                pq.offer(new Building(n, arr[n][buildTime]));
            }
        }
        while (!pq.isEmpty()) {
            Building building = pq.poll();
            arr[building.number][completeTime] = building.time; // 완료 시간 기입
            for (int n = 1; n <= N; n++) { // N개의 건물 탐색
                if (arr[building.number][n] == 0) { // 상위 건물 없으면
                    continue;
                }
                if (--arr[n][0] == 0) { // 진입차수가 0이 되면 건물 넣기
                    pq.offer(new Building(n, building.time + arr[n][buildTime]));
                }
            }
        }
        for (int n = 1; n <= N; n++) {
            bw.write(arr[n][completeTime] + "\n");
        }
        bw.flush();
    }

    /*
    위상 정렬?
    우선 순위큐룰 사용하는?
    -> 1(10) -> 2(2) -> 4(2)
            -> 3(10)
     */
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        buildTime = N + 1;
        completeTime = N + 2;
        arr = new int[N+1][N+3];
        for (int n = 1; n <= N; n++) { // n번째 건물
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            arr[n][buildTime] = num;
            while (st.hasMoreTokens()) {
                int key = Integer.parseInt(st.nextToken()); // key : 필요한 건물
                if (key == -1) {
                    break;
                }
                arr[n][0]++; // 진입차수 증가
                arr[key][n]++; // 필요한 건물에 진입차수를 감소시키므로 체크
            }
        }
        pq = new PriorityQueue<>(Comparator.comparing(Building::getTime));
    }

    static class Building {
        int number;
        int time;

        Building(int number, int time) {
            this.number = number;
            this.time = time;
        }

        public int getTime() {
            return time;
        }
    }
}
