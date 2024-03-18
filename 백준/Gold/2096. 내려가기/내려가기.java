// 02096. [G5] 내려가기.
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
	
	static int N, m0, m1, m2, M0, M1, M2, max, min;
	static int[] tmp = new int[3];
	static int[][]dp;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[2][3];
		
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < 3; m++) {
				tmp[m] = Integer.parseInt(st.nextToken()); 
			}
			m0 = Math.min(dp[0][0], dp[0][1]) + tmp[0];
			m2 = Math.min(dp[0][1], dp[0][2]) + tmp[2];
			m1 = Math.min(dp[0][0], Math.min(dp[0][1], dp[0][2])) + tmp[1];
			
			dp[0][0] = m0;
			dp[0][1] = m1;
			dp[0][2] = m2;
			
			M0 = Math.max(dp[1][0], dp[1][1]) + tmp[0];
			M2 = Math.max(dp[1][1], dp[1][2]) + tmp[2];
			M1 = Math.max(dp[1][0], Math.max(dp[1][1], dp[1][2])) + tmp[1];
			
			dp[1][0] = M0;
			dp[1][1] = M1;
			dp[1][2] = M2;
		}
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			min = Math.min(min, dp[0][i]);
			max = Math.max(max, dp[1][i]);
		}
		bw.write(max + " " + min);
		bw.flush();
		bw.close();
		br.close();
	}
}
