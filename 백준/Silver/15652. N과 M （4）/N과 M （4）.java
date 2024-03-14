// 15652. [S3] N과 M(6).
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dfs("", 1, 0);
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(String answer, int prev, int cnt) throws IOException {
        if (cnt == M) {
            bw.write(answer + "\n");
            return;
        }

        for (int n = prev; n <= N; n++) {
            dfs(answer + n + " ", n, cnt + 1);
        }
    }
}