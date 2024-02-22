// [SWEA] 01952. 수영장
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
	
	static int answer;
	static int[] price = new int[4];
	static int[] schedule = new int[12];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			answer = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				schedule[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0, 0);
			bw.write("#" + tc + " " + answer + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int n, int cost) {
		if (answer <= cost) {
			return;
		}
		
		if(n >= 12) {
			answer = Math.min(answer, cost);
			return;
		}
		
		dfs(n+1, cost + schedule[n]*price[0]);
		dfs(n+1, cost + price[1]);
		dfs(n+3, cost + price[2]);
		dfs(n+12, cost + price[3]);
	}
}