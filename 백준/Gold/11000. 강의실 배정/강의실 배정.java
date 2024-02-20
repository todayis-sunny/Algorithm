// 11000. [G5] 강의실 배정.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int[][] lectures = new int[N][2];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			
			lectures[n][0] = S;
			lectures[n][1] = T;
		}
		Arrays.sort(lectures, ((x, y) -> x[0] - y[0]));
		pq.offer(lectures[0][1]);
		for (int n = 1; n < N; n++) {
			if(pq.peek() <= lectures[n][0]) {
				pq.poll();
			}
			pq.offer(lectures[n][1]);
		}

		int answer = pq.size();
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}