// 01260. [S2] DFS와 BFS
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] arr;
    static boolean[] visited;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        arr = new boolean[N+1][N+1];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = true;
            arr[b][a] = true;
        }
        visited = new boolean[N+1];
        dfs(V);
        bw.newLine();
        visited = new boolean[N+1];
        bfs(V);
        bw.flush();
        bw.close();
    }

    public static void dfs(int v) throws IOException {
        visited[v] = true;
        bw.write(v + " ");

        for (int i = 1; i < arr.length; i++) {
            if (arr[v][i] && !visited[i]) {
                dfs(i);
            }
        }

    }

    public static void bfs(int v) throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        while (!queue.isEmpty()){
            int current = queue.poll();
            bw.write(current + " ");

            for (int i = 1; i < arr.length; i++) {
                if (arr[current][i] && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}