// [SWEA] 01217. 거듭 제곱
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		for(int tc = 1; tc <= 10; tc++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int answer = dfs(N, M, 0);
			bw.write(String.format("#%d %d\n", tc, answer));
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int dfs(int N, int M, int cnt) {
		if(cnt == M) {
			return 1;
		}
		if(cnt < M) {
			return N * dfs(N, M, cnt + 1);	
		}
		return 0;
	}
}
