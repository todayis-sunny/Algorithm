// 13308.[P5] 주유소

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] gasStation; // 주유소
    static List<List<Road>> distance; // from -> to의 거리
    static long[][] cost; // [city][oil] : 특정 city에서 특정 oil로 마지막 지점까지 도달하는 최소 비용
    static long ans = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        // N, M 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        gasStation = new int[N + 1];
        distance = new ArrayList<>();
        for (int n = 0; n <= N; n++) {
            distance.add(new ArrayList<>());
        }
        cost = new long[N + 1][2501];
        for (int n = 0; n <= N; n++) {
            Arrays.fill(cost[n], Long.MAX_VALUE);
        }
        // 각 도시별 주유소 가격 입력
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            gasStation[n] = Integer.parseInt(st.nextToken());
        }
        // 도로 입력
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            distance.get(city1).add(new Road(city2, dist));
            distance.get(city2).add(new Road(city1, dist));
        }
    }

    static void solve() {
        dijkstra(1);
        for (int i = 0; i <= 2500; i++) {
            ans = Math.min(ans, cost[N][i]);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void dijkstra(int start) {
        // 비용(오) -> 기름(오)
        Queue<Item> pq = new PriorityQueue<>(Comparator.comparingLong(Item::getCost).thenComparingLong(Item::getOil));
        cost[start][gasStation[start]] = 0;
        pq.offer(new Item(0, gasStation[start], start));
        while (!pq.isEmpty()) {
            Item curr = pq.poll();
            for (int i = 0; i < distance.get(curr.city).size(); i++) {
                // 다음 도시
                int nextCity = distance.get(curr.city).get(i).to;
                // 다음 거리
                int nextDist = distance.get(curr.city).get(i).dist;
                // 다음 비용 = 현재 비용 + (현재 기름값 x 이동거리)
                long nextCost = curr.cost + (long) curr.oil * nextDist;
                // 다음 기름값 = 현재 기름과 다음 도시의 기름값 비교
                int nextOil = Math.min(curr.oil, gasStation[nextCity]);
                // 다음 비용이 더 큰 경우 -> 스킵
                if (nextCost >= cost[nextCity][nextOil]) continue;

                cost[nextCity][nextOil] = nextCost;
                pq.offer(new Item(nextCost, nextOil, nextCity));
            }
        }
    }

    static class Road {
        int to;
        int dist;

        Road(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static class Item {
        long cost; // 현재 비용
        int oil; // 현재 기름
        int city; // 현재 지점

        Item(long cost, int oil, int city) {
            this.cost = cost;
            this.oil = oil;
            this.city = city;
        }

        public long getCost() {
            return cost;
        }

        public long getOil() {
            return oil;
        }
    }
}
