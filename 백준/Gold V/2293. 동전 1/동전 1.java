// [BOJ] 02293. 동전1
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
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n];
		int[] dp = new int[k+1];
		for(int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 1;
		for(int i = 0; i < n; i++) {
			for (int j = 1; j <= k; j++) {
				if (coin[i] <= j) {
					dp[j] += dp[j - coin[i]];
				}
			}
		}
		
		bw.write(dp[k] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
