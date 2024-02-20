// [SWEA] 01226. 미로1
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

	static Queue<int[]> queue;

	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };
	static int[][] board;
	static int[] start, end;
	static int answer;

	public static void main(String[] args) throws IOException {
		for (int tc = 1; tc <= 10; tc++) {
			answer = 0;
			board = new int[16][16];
			br.readLine();
			
			for (int i = 0; i < 16; i++) {
				String input = br.readLine();
				for (int j = 0; j < 16; j++) {
					board[i][j] = Character.getNumericValue(input.charAt(j));
					if (board[i][j] == 2) {
						start = new int[] { i, j };
					} else if (board[i][j] == 3) {
						end = new int[] { i, j };
					}
				}
			}
			bfs(start[0], start[1]);
			if(board[end[0]][end[1]] == -1) {
				answer = 1;
			}
			
			bw.write("#" + tc + " " + answer + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static void bfs(int start_x, int start_y) {
		queue = new LinkedList<int[]>();
		board[start_x][start_y] = -1;
		queue.offer(new int[] { start_x, start_y });

		while (!queue.isEmpty()) {
			int[] xy = queue.poll();
			int x = xy[0];
			int y = xy[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= 16 || ny < 0 || ny >= 16) {
					continue;
				}
				if (board[nx][ny] == 1 || board[nx][ny] == -1) {
					continue;
				}
				board[nx][ny] = -1;
				queue.offer(new int[] {nx, ny});
			}
		}
	}
}
