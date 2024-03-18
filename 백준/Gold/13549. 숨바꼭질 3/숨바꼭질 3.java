//13549. [G5] 숨바꼭질 3.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int N, K, time, size;
	static int[] visited, memo;
	static Queue<Integer> queue;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		time = 0;
		queue = new LinkedList<>();
		visited = new int[100_001];
		Arrays.fill(visited, -1);
		memo = new int[2];
		if (N == K) {
			bw.write("0");
		} else if (N > K) {
			bw.write(String.valueOf(N - K));
		} else {
			bfs();
			bw.write(visited[K] + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	static void bfs() {
		queue.add(N);
		boolean flag = false;
		time = 0;
		mainLoop: while (!flag) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int pointer = queue.poll();
				if (pointer == K) {
					flag = true;
					break mainLoop;
				}
				queue.add(pointer);
				if (pointer == 0) {
					continue;
				}
				for (int mul = 2; mul * pointer <= 100_000; mul *= 2) {
					int newPointer = mul * pointer;
					if (newPointer < 0 || newPointer > 100000) {
						continue;
					}
					if (visited[newPointer] != -1 && visited[newPointer] < time) {
						continue;
					}
					visited[newPointer] = time;
					queue.add(newPointer);
				}

			}
			time++;
			size = queue.size();
			for (int i = 0; i < size; i++) {
				int pointer = queue.poll();
				if (pointer == K) {
					break mainLoop;
				}
				memo[0] = pointer - 1;
				memo[1] = pointer + 1;
				for (int newPointer : memo) {
					if (newPointer < 0 || newPointer > 100000) {
						continue;
					}
					if (visited[newPointer] != -1 && visited[newPointer] < time) {
						continue;
					}
					visited[newPointer] = time;
					queue.add(newPointer);
				}
			}
		}
	}

}