// 01414. [G3] 불우이웃돕기.

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int K;
    static int[] parent;
    static PriorityQueue<Lan> pq = new PriorityQueue<>(Comparator.comparingInt((Lan lan) -> lan.length));
    static int ans;


    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        for (int a = 0; a < N; a++) {
            String input = br.readLine();
            for (int b = 0; b < N; b++) {
                char ch = input.charAt(b);
                int len = convertLength(ch);
                if (len == 0) continue;
                pq.offer(new Lan(len, a, b));
            }
        }
    }

    static void solve() {
        Arrays.fill(parent, -1);
        while (!pq.isEmpty()) {
            Lan lan = pq.poll();
            if (!union(lan.a, lan.b)) {
                ans += lan.length;
            } else {
                K++;
            }
        }
    }

    static void output() throws IOException {
        if (K == N -1) {
            bw.write(String.valueOf(ans));
        } else {
            bw.write("-1");
        }
        bw.flush();
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }
        if (parent[a] < parent[b]) {
            parent[b] = a;
        } else if (parent[a] > parent[b]) {
            parent[a] = b;
        } else {
            parent[b] = a;
            parent[a]--;
        }
        return true;
    }

    static int find(int x) {
        if (parent[x] <= -1) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static int convertLength(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a' + 1;
        } else if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A' + 27;
        } else {
            return 0;
        }
    }

    static class Lan {
        int length;
        int a, b;

        public Lan(int length, int a, int b) {
            this.length = length;
            this.a = a;
            this.b = b;
        }
    }
}
