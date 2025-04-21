// 01027. [G4] 고층 건물

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N; // 빌딩의 수
    static int[] data; // 빌딩의 높이 (1-based)
    static int ans = 0; // 최소 0이 정답이므로, max로 답을 비교

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        data = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            data[i] = Integer.parseInt(st.nextToken()); // 빌딩의 높이 입력
        }
    }

    static void solve() {
        // 모든 빌딩들을 조사하기
        for (int i = 1; i <= N; i++) {
            int cnt = 0; // 볼수 있는 좌측의 빌딩 + 볼수 있는 우측의 빌딩
            // 좌측 조사
            float left = Float.MAX_VALUE; // 극한으로 보냄
            for (int l = i - 1; l >= 1; l--) {
                float curr = calculateInclination(i, l);
                if (curr >= left) continue; // 기울기가 점점 양(+) -> 음(-)해야 하므로 그 반대는 무시
                left = curr;
                cnt++;
            }
            // 우측 조사
            float right = -Float.MAX_VALUE; // 극한으로 보냄 (-로 붙여줌으로써, 최소)
            for (int r = i + 1; r <= N; r++) {
                float curr = calculateInclination(i, r);
                if (curr <= right) continue; // 기울기가 점점 음(-) -> 양(+)해야 하므로 그 반대는 무시
                right = curr;
                cnt++;
            }
            ans = Math.max(ans, cnt);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    /**
     * 기울기 계산
     * @param a 기준
     * @param b 대상
     * @return 기울기 값
     */
    static float calculateInclination(int a, int b) {
        return (float) (data[a] - data[b]) / (a - b);
    }
}
