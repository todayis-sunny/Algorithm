// 04386. [G3] 별자리 만들기.
import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n;
    static double x, y, total, cnt;
    static double[] X, Y;
    static boolean[] visited;
    static PriorityQueue<Star> pq;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        X = new double[n];
        Y = new double[n];
        visited = new boolean[n];
        total = 0.0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            X[i] = Double.parseDouble(st.nextToken());
            Y[i] = Double.parseDouble(st.nextToken());
        }
        pq = new PriorityQueue<>(Comparator.comparing(Star::getCost));
        cnt = n;
        pq.offer(new Star(0, 0.0));
        while (cnt > 0) {
            Star star = pq.poll();
            if (visited[star.next]) {
                continue;
            }
            visited[star.next] = true;
            total += star.cost;
            cnt--;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    continue;
                }
                double dist = getDist(X[star.next], Y[star.next], X[i], Y[i]);
                pq.offer(new Star(i, dist));
            }
        }
        total *= 100;
        total = Math.round(total);
        total /= 100.0;
        bw.write(String.valueOf(total));
        bw.flush();
        bw.close();
        br.close();
    }

    static double getDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(Math.abs(x2-x1), 2) + Math.pow(Math.abs(y2-y1), 2));
    }

    static class Star{
        int next;
        double cost;

        public Star(int next, double cost) {
            this.next = next;
            this.cost = cost;
        }

        public double getCost() {
            return cost;
        }
    }

}