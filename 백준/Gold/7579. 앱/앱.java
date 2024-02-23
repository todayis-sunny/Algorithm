// 07579. [G3] 앱
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] arr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N][2];
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				arr[n][i] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[10001];
		for (int i = 0; i < N; i++) {
			for (int j = 10000; j >= arr[i][1]; j--) {
				dp[j] = Math.max(dp[j], dp[j - arr[i][1]] + arr[i][0]);
			}
		}
		
		for (int i =0; i <= 10000; i++) {
			if (dp[i] >= M) {
				bw.write(String.valueOf(i));
				break;
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
