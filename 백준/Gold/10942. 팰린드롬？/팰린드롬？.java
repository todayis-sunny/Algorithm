// 10942. [G4] 팰린드롬?
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static Stack<int[]> stack;
	static int[][] dp;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1][N+1];
		arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		stack = new Stack<int[]>();
		for (int n = 1; n <= N; n ++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			int answer = 1;
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			while(a < b) {
				if(dp[a][b] == 1) {
					break;
				}
				if(dp[a][b] == -1) {
					answer = 0;
					break;
				}
				stack.push(new int[] {a, b});
				if(arr[a++] != arr[b--]) {
					answer = 0;
					break;
				}
			}
			while(!stack.isEmpty()) {
				int[] tmp = stack.pop();
				if(answer == 1) {
					dp[tmp[0]][tmp[1]] = 1;	
				} else {
					dp[tmp[0]][tmp[1]] = -1;
				}	
			}
			bw.write(String.valueOf(answer));
			bw.newLine();
		}
		bw.close();
		br.close();
	}
}