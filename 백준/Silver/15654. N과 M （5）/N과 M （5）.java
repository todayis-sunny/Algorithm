// 15654. [S3] N과 M (5)
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] arr;
    static boolean[] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(st.nextToken());
            arr[n] = num;
        }

        Arrays.sort(arr);

        dfs("", 0);
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(String answer, int cnt) throws IOException {
        if (cnt == M) {
            bw.write(answer);
            bw.newLine();
            return;
        }
        for (int n = 0; n < N; n++) {
            if (visited[n]) {
                continue;
            }
            visited[n] = true;
            dfs(answer + arr[n] + " ", cnt + 1);
            visited[n] = false;
        }
    }
}