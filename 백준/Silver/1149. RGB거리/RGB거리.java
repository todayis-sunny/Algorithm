// 01149. [S1] RGB거리.
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
	
	static int N, idx1, idx2, last;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[2][3];
		for(int n = 1; n <= N; n++) {
			idx1 = n % 2;
			idx2 = (n+1) % 2;
			st = new StringTokenizer(br.readLine());
			dp[idx1][0] = Integer.parseInt(st.nextToken()) + Math.min(dp[idx2][1], dp[idx2][2]);
			dp[idx1][1] = Integer.parseInt(st.nextToken()) + Math.min(dp[idx2][0], dp[idx2][2]);
			dp[idx1][2] = Integer.parseInt(st.nextToken()) + Math.min(dp[idx2][0], dp[idx2][1]);
			
		}
		last = N % 2;
		bw.write(String.valueOf(Math.min(dp[last][0], Math.min(dp[last][1], dp[last][2]))));
		bw.flush();
		bw.close();
		br.close();
	}
}
