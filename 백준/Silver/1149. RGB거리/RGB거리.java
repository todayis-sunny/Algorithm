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
	
	static int N, R, G, B, r, g, b, answer;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[3];
		answer = Integer.MAX_VALUE;
		for(int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			r = Math.min(dp[1], dp[2]) + R;
			g = Math.min(dp[0], dp[2]) + G;
			b = Math.min(dp[0], dp[1]) + B;
			
			dp[0] = r;
			dp[1] = g;
			dp[2] = b;
		}
		answer = Math.min(dp[0], Math.min(dp[1], dp[2]));
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}
