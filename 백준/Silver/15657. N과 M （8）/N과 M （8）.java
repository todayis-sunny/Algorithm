// 15657. [S3] N과 M (8)
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] arr;
    static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(st.nextToken());
            arr[n] = num;
        }

        Arrays.sort(arr);

        dfs("", 0,0);
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(String answer, int idx, int cnt) throws IOException {
        if (cnt == M) {
            bw.write(answer);
            bw.newLine();
            return;
        }
        for (int n = idx; n < N; n++) {
            dfs(answer + arr[n] + " ", n,cnt + 1);
        }
    }
}
