// [SWEA] 04008. 숫자 만들기
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
	static int[] op, numbers, makeOP;
	static int answer, N;
	
	static int minN;
	static int maxN;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			minN = Integer.MAX_VALUE;
			maxN = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine());
			op = new int[4];
			numbers = new int[N];
			makeOP = new int[N - 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0);
			answer = maxN - minN;
			bw.write("#" + tc + " " + answer + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int idx) {
		if(idx == N-1) {
			calculate();
		}
		
		for (int i = 0; i < 4; i++) {
			if(op[i] == 0) {
				continue;
			}
			op[i]--;
			makeOP[idx] = i;
			dfs(idx+1);
			op[i]++;
			
		}
	}
	
	public static void calculate() {
		int num = numbers[0];
		for(int i=0; i<N-1; i++) {
			// +
			if(makeOP[i] == 0) {
				num+=numbers[i+1];
			}
			// -
			else if(makeOP[i] == 1) {
				num-=numbers[i+1];
			}
			// *
			else if(makeOP[i] == 2) {
				num*=numbers[i+1];
			}
			// /
			else if(makeOP[i] == 3) {
				num/=numbers[i+1];
			}
		}
		maxN = Math.max(num, maxN);
		minN = Math.min(num, minN);
	}
}