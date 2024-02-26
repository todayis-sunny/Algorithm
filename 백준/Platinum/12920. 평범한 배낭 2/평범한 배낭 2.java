// 12920. [P4] 평범한 배낭2.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] dp = new int[M+1];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            int cnt = 1;
            while (count > 0) {
                cnt = Math.min(cnt, count); // 1, 2, 4... 2의 배수로 빼면서 남은갯수를 더이상 쪼갤 수 없으면 마무리.
                int wc = weight * cnt;
                int vc = value * cnt;
                for (int m = M; m >= wc; m--) {
                    if (m >= wc) { // Top-down 방식. -> 중복된 사용을 방지.
                        dp[m] = Math.max(dp[m], dp[m - wc] + vc);
                    }
                }
                count -= cnt;
                cnt *= 2;
            }
        }
        bw.write(String.valueOf(dp[M]));
        bw.flush();
        bw.close();
        br.close();
    }

}
