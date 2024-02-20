// [SWEA] 01238. Contact
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static Queue<Integer> queue = new LinkedList<>();
	static boolean[][] arr;
	static int[] visited;
	static int maximum;
	static int answer;
	public static void main(String[] args) throws IOException {
		for (int tc = 1; tc <= 10; tc++) {
			answer = 0;
			maximum = 0;
			arr = new boolean[101][101];
			visited = new int[101];
			Arrays.fill(visited, -1);
			st = new StringTokenizer(br.readLine());	
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			visited[start] = 0;
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				arr[from][to] = true;
			}
			bfs(start);
			for(int i = 100; i >= 0; i--) {
				if(maximum < visited[i]) {
					maximum = visited[i];
					answer = i;
				}
			}
			bw.write("#" + tc + " " + answer + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void bfs(int from) {
		visited[from] = 0;
		queue.offer(from);
		while (!queue.isEmpty()) {
			int newFrom = queue.poll();
			for (int i = 0; i <= 100; i++) {
				if(arr[newFrom][i] && visited[i] == -1) {
					visited[i] = visited[newFrom]+1;
					queue.offer(i);	
				}
			}
		}
	}
}
