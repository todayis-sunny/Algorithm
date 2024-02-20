// [SWEA] 01225. 암호생성기
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static Queue<Integer> queue;

	public static void main(String[] args) throws IOException {
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			queue = new LinkedList<Integer>();
			while(st.hasMoreTokens()) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			for (int d = 0; ; d++) {
				int dd = d % 5 + 1;
				int tmp = queue.poll();
				int num = tmp - dd;
				if(num <= 0) {
					num = 0;
				}
				queue.offer(num);
				if(num == 0) {
					break;
				}
			}
			bw.write("#" + tc);
			while(!queue.isEmpty()) {
				bw.write(" " + queue.poll());
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
