// [SWEA] 01861. 정사각형 방
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
	static int[][] board;
	static int N, room, maximum;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };
	static Queue<int[]> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			room = Integer.MAX_VALUE;
			maximum = Integer.MIN_VALUE;
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j, 1);
				}
			}
			bw.write("#" + tc + " " + room + " " + maximum + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static void bfs(int xx, int yy, int ccnt) {
		int[] tmp = new int[] { xx, yy, ccnt };
		queue = new LinkedList<>();
		queue.offer(tmp);
		int cnt = ccnt;
		int x = xx;
		int y = yy;
		while (!queue.isEmpty()) {
			int[] xyc = queue.poll();
			x = xyc[0];
			y = xyc[1];
			cnt = xyc[2];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				if(board[x][y] != board[nx][ny] - 1) {
					continue;
				}
				queue.offer(new int[] {nx, ny, cnt+1});
			}
			
		}
		if(cnt == maximum) {
			if (board[xx][yy] < room) {
				room = board[xx][yy];
			}
		} else if (cnt > maximum) {
			maximum = cnt;
			room = board[xx][yy];
		}
	}
}