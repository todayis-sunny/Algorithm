// 02515. [G2] 전시장.

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, S; // N: 그림의 개수, S: 판매가능 그림의 세로길이
    static List<Painting> exterior = new ArrayList<>(); // 전시장
    static long[] dp;
    static int limit;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        limit = Integer.MIN_VALUE;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken()); // 그림의 높이
            int c = Integer.parseInt(st.nextToken()); // 그림의 가격
            exterior.add(new Painting(h, c));
            limit = Math.max(limit, h);
        }
        dp = new long[limit + 1];
        exterior.sort(Comparator.comparing(Painting::getHeight).thenComparing(Painting::getValue));

    }

    static void solve() {
        Painting prev = new Painting(limit, 0);
        for (Painting curr : exterior) {
            if (prev.height - curr.height < S) {
                if (curr.height + S <= limit) {
                    dp[curr.height] = Math.max(dp[prev.height], dp[curr.height + S] + curr.value);
                } else {
                    dp[curr.height] = Math.max(dp[prev.height], curr.value);
                }
            } else {
                dp[curr.height] = dp[prev.height] + curr.value;
            }
            for (int i = prev.height - 1; i > curr.height; i--) {
                dp[i] = dp[i + 1];
            }
            prev = curr;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[exterior.get(N - 1).height]));
        bw.flush();
    }

    static class Painting {
        int height;
        int value;

        public Painting(int height, int value) {
            this.height = height;
            this.value = value;
        }

        public int getHeight() { // 내림차순
            return -height;
        }

        public int getValue() { // 내림차순
            return -value;
        }
    }
}
