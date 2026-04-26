// 01043. [G4] 거짓말.

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static List<List<Integer>> party = new ArrayList<>();
    static int[] parent;
    static int boss = 0;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int [N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        // 진실 아는 사람들
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        for (int t = 0; t < T; t++) {
            int h = Integer.parseInt(st.nextToken());
            list.add(h);
        }
        if (T > 0) boss = list.get(0);
        for (int t = 1; t < T; t++) {
            union(list.get(0), list.get(t));
        }

        // 파티 목록
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            List<Integer> data = new ArrayList<>();
            for (int t = 0; t < T; t++) {
                int h = Integer.parseInt(st.nextToken());
                data.add(h);
            }
            party.add(new ArrayList<>(data));
            for (int t = 1; t < T; t++) {
                union(data.get(0), data.get(t));
            }
        }
    }

    static void solve() {
        partyLoop:
        for (int m = 0; m < M; m++) {
            for (int i = 0; i < party.get(m).size(); i++) {
                if (find(party.get(m).get(i)) == boss) continue partyLoop;
            }
            ans++;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        if (a == boss) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
