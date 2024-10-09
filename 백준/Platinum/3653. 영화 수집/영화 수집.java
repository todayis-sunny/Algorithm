// 03653. [P4] 영화 수집.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    static int TC, N, M;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int[] movies = new int[N+1];
            tree = new int[N+M+1];

            for (int idx = 1; idx <= N; idx++) {
                movies[idx] = M+idx;
                update(movies[idx], 1);
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int select = Integer.parseInt(st.nextToken());
                int idx = movies[select];
                bw.write(sum(idx-1) + " ");
                update(idx, -1);
                movies[select] = M - i;
                update(movies[select], 1);
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void update(int idx, int val) {
        while (idx <= N+M) {
            tree[idx] += val;
            idx += (idx & -idx);
        }
    }

    static int sum(int idx) {
        int result = 0;
        while (idx > 0) {
            result += tree[idx];
            idx -= (idx & -idx);
        }
        return result;
    }

}