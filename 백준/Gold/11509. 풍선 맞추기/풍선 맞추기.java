// 11509. [G5] 풍선 맞추기.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] balloon, data;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        balloon = new int[1_000_001];
        data = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            data[n] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for (int h : data) {
            // Case 1: 현재 풍선의 높이(b)로 날아오고 있는 화살이 없는 경우
            if (balloon[h] == 0) {
                // 새로운 화살을 하나 쏜다.
                ans++;
            }
            // Case 2: 현재 풍선의 높이(b)로 이미 날아오고 있는 화살이 있는 경우
            else {
                // 기존에 높이 b로 날아오던 화살 중 하나가 풍선을 맞춤
                balloon[h]--;
            }
            balloon[h - 1]++;  // 화살은 높이 b의 풍선을 맞추고 높이 b-1이 되어 계속 날아간다.
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
