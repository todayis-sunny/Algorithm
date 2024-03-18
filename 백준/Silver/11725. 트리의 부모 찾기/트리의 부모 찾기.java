// 11725. [S2] 트리의 부모 찾기.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb;
	static StringTokenizer st;

	static int N, a, b;
	static ArrayList<ArrayList<Integer>> tree;
	static boolean[] visited;
	static int[] parent;
	static Queue<Integer> queue;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			tree.add(new ArrayList<>());
		}
		visited = new boolean[N + 1];
		parent = new int[N + 1];

		sb = new StringBuilder(N - 1);
		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		bfs();

		for (int n = 2; n <= N; n++) {
			sb.append(parent[n] + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void bfs() {
		queue = new LinkedList<>();
		queue.add(1);
		visited[1] = true;
		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (int node : tree.get(v)) {
				if(!visited[node]) {
					visited[node] = true;
					queue.add(node);
					parent[node] = v;
				}
			}
		}
	}
}