// 01092. [G5] 배.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] cranes, boxs;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        cranes = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            cranes[n] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        boxs = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            boxs[m] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(cranes);
        Arrays.sort(boxs);

        if (boxs[M - 1] > cranes[N - 1]) {
            ans = -1;
            return;
        }
        ans = (M + N - 1) / N; // 올림 처리
        int c = N;
        int b = 0;
        for (int i = 0; i < N - 1; i++) {
            // 박스를 들수 있을때까지 들기
            while (b < M && boxs[b] <= cranes[i]) {
                b++;
            }
            // 크레인 1개 사용
            c--;
            // (남은 박스 수 + 남은 크레인 수 - 1) / 남은 크레인 수 : 남은 크레인들이 박스를 옮기는데 걸리는 최소 시간
            ans = Math.max(ans , ((M - b) + c - 1) / c);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}

/*
 풀이날짜 2025. 12. 18
 소요시간 00h 30m
 */