// 02638. [G3] 치즈.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] cheese;
	static int[][] check;
	static boolean[][] visited;
	static int N, M, count;
	
	static Queue<Node> queue;
	static Queue<Node> remove;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		count = 0;
		queue = new LinkedList<>();
		remove = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				cheese[n][m] = Integer.parseInt(st.nextToken());
				if (cheese[n][m] == 1) {
					count++;
				}
			}
			
		}
		int time = 0;
		for (time = 0; count > 0; time++) {
			bfs();
		}
		bw.write(String.valueOf(time));
		bw.flush();
		bw.close();
		br.close();
	}

	static void bfs() {
		check = new int[N][M];
		queue.add(new Node(0, 0));
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				if(check[nx][ny]++ == 0 && cheese[nx][ny] == 0) {
					queue.add(new Node(nx, ny));
				}
				if (cheese[nx][ny] == 1) {
					if (check[nx][ny] == 2) {
						remove.add(new Node(nx, ny));
					}
				}
			}
		}

		while (!remove.isEmpty()) {
			Node rm = remove.poll();
			count--;
			cheese[rm.x][rm.y] = 0;
		}
		
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
