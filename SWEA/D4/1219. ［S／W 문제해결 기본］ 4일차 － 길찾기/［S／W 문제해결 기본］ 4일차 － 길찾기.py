// [SWEA] 01219. 길찾기
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
	static int[][] arr;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		for(int tc = 1; tc <= 10; tc++) {
			visited = new boolean[100];
			arr = new int[100][2];
			br.readLine();
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(arr[a][0] != 0) {
					arr[a][1] = b;
				} else {
					arr[a][0] = b;
				}
			}
			visited[0] = true;
			dfs(0);
			if(visited[99]) {
				bw.write("#" + tc + " 1\n");
			} else {
				bw.write("#" + tc + " 0\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int v) {
		int a = arr[v][0];
		int b = arr[v][1];
		if(a != 0 && !visited[a]) {
			visited[a] = true;
			dfs(a);
		}
		if(b != 0 && !visited[b]) {
			visited[b] = true;
			dfs(b);
		}
		return;
	}
}
