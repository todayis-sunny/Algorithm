// 04193. [diff_4] 수영대회 결승전.
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
    static int TC, N, answer;
    static Queue<Node> queue;
    static Node start, end;
    static int[][] pools, visited;
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
    	TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			pools = new int[N][N];
			visited = new int[N][N];
			answer = 0;
			for (int x = 0; x < N; x++) {
				st = new StringTokenizer(br.readLine());
				for (int y = 0; y < N; y++) {
					pools[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			start = new Node(x, y);
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			end = new Node(x, y);
			bfs();
			answer = visited[end.x][end.y] != 0 ? visited[end.x][end.y] - 1 : -1;
			bw.write("#" + tc + " " + answer + "\n");
		}
		bw.flush();
    	bw.close();
    	br.close();
	}
    
    static void bfs() {
    	queue = new LinkedList<>();
    	queue.offer(start);
    	visited[start.x][start.y] = 1;
    	while (!queue.isEmpty()) {
    		Node node = queue.poll();
    		for (int i = 0; i < 4; i++) {
    			int nx = node.x + dx[i];
    			int ny = node.y + dy[i];
    			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
    				continue;
    			}
    			if (pools[nx][ny] == 1) {
    				continue;
    			}
    			
    			int delay = 0;
				if (pools[nx][ny] == 2) {
					delay = (3 - visited[node.x][node.y] % 3) % 3;
				}
    			if (visited[nx][ny] != 0) {
    				if (visited[nx][ny] <= visited[node.x][node.y] + delay + 1) {
    					continue;
    				}
    			}
    			visited[nx][ny] = visited[node.x][node.y] + delay + 1;
    			queue.add(new Node(nx, ny));
    		}
    	}
    }
    
    static class Node{
    	int x;
    	int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
    	
    }
}
