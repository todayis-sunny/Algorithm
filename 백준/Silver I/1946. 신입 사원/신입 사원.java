// [BOJ] 01946. 신입 사원
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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N+1];
			for(int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				int rank = Integer.parseInt(st.nextToken());
				arr[idx] = rank;
			}
			int goal = N+1;
			int answer = 0;
			for(int i = 1; i <= N; i++) {
				if(arr[i] < goal) {
					goal = arr[i];
					answer += 1;
				}
			}
			bw.write(answer + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}