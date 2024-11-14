// 01516. [G3] 게임 개발.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static Queue<Integer> queue = new LinkedList<>();
    static PriorityQueue<Building> pq;
    static int[][] arr; // [x][0] : 진입 차수, [x][1] : 건물 건설 시간, , [x][2] : 건물 건설 완료 시간
    static List<List<Integer>> data = new ArrayList<>();
    static int N, buildTime = 1, completeTime = 2;

    public static void main(String[] args) throws IOException {
        input();
        // 진입 차수가 0인 것들 건물 짓기 시작
        for (int n = 1; n <= N; n++) {
            if (arr[n][0] == 0) {
                arr[n][completeTime] = arr[n][buildTime];
                queue.offer(n);
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            List<Integer> nextList = data.get(curr);
            for (int i = 0; i < nextList.size(); i++) {
                int next = nextList.get(i);
                --arr[next][0];
                arr[next][completeTime] = Math.max(arr[next][completeTime], arr[curr][completeTime] + arr[next][buildTime]);
                if (arr[next][0] == 0) {
                    queue.offer(next);
                }
            }
        }
        for (int n = 1; n <= N; n++) {
            bw.write(arr[n][completeTime] + "\n");
        }
        bw.flush();
    }
    
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][3];
        for (int n = 0; n <= N; n++) {
            data.add(new ArrayList<>());
        }
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
                data.get(key).add(n); // 필요한 건물에 진입차수를 감소시키므로 체크
            }
        }
    }

    static class Building {
        int number;
        int time;

        Building(int number, int time) {
            this.number = number;
            this.time = time;
        }
    }
}
