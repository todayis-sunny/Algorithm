// 12851. [G4] 숨바꼭질 2.
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

	static int N, K, time, count;
	static int[] visited, memo;
	static Queue<Integer> queue;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		time = 0;
		count = 0;
		queue = new LinkedList<>();
		visited = new int[100_001];
		memo = new int[3];
		bfs();
		bw.write(time + "\n");
		bw.write(count + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void bfs() {
		queue.add(N);
		boolean flag = false;
		time = -1;
		while(!flag) {
			time++;
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				int pointer = queue.poll();
				if(pointer == K) {
					flag = true;
					count++;
				}
				memo[0] = pointer - 1;
				memo[1] = pointer + 1;
				memo[2] = pointer * 2;
				for(int newPointer : memo) {
					if(newPointer < 0 || newPointer > 100000) {
						continue;
					}
					if(visited[newPointer] != 0 && visited[newPointer] < time) {
						continue;
					}
					visited[newPointer] = time;
					queue.add(newPointer);
				}
			}
		}
	}
	
}
