// 14719. [G5] 빗물.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int H, W, ans;
    static int[] heights;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        heights = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        ans = 0;
    }

    static void solve() {
        for (int i = 1; i < W - 1; i++) { // 처음벽과 마지막벽은 물이 고이지 않음
            int curr = heights[i]; // 현재 벽의 높이
            int leftMax = curr; // 좌측 벽
            int rightMax = curr; // 우측 벽
            for (int j = i - 1; j >= 0; j--) { // 좌측 벽 최대 높이 탐색
                if (heights[j] > curr) {
                    leftMax = Math.max(leftMax, heights[j]);
                }
            }
            for (int j = i + 1; j < W; j++) { // 우측 벽 최대 높이 탐색
                if (heights[j] > curr) {
                    rightMax = Math.max(rightMax, heights[j]);
                }
            }
            if (Math.min(leftMax, rightMax) > curr) { // 현재 벽보다 높은 벽이 양쪽에 있는 경우
                ans += (Math.min(leftMax, rightMax) - curr);
            }
        }
    }
}
