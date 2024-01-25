// 10974. [S3] 모든 순열
import java.io.*;

public class Main {
    static int n;
    static int[] tmp, arr;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        tmp = new int[n];
        arr = new int[n];

        int[] arr = new int[n];
        visited = new boolean[n];

        dfs(0);


        bw.flush();
        bw.close();
    }

    public static void dfs(int depth) throws IOException {
        if (depth == n) {

            for (int i = 0; i < tmp.length; i++){
                bw.write(String.valueOf(tmp[i]));
                if (i != n-1){
                    bw.write(" ");
                } else {
                    bw.write("\n");
                }
            }
            return;
        }

        for (int i = 0; i < n; i++){
            if (visited[i]){
                continue;
            }
            visited[i] = true;
            tmp[depth] = i + 1;
            dfs(depth + 1);
            visited[i] = false;
        }
    }
}