// 10159. [G4] 저울.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[][] arr;
    static int N, M, a, b;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1]; // 1:보다 무거운것, -1 보다 가벼운것.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
            arr[b][a] = -1;
        }
        for (int i = 1; i <= N; i++) {
            int[] check = new int[N+1];
            check[0] = 1;
            check[i] = 1;

            Queue<Integer> heavy = new LinkedList<>();
            Queue<Integer> light = new LinkedList<>();
            for (int j = 1; j <= N; j++) {
                if (arr[i][j] == 1 && check[j] == 0) {
                    check[j] = 1;
                    heavy.offer(j);
                } else if (arr[i][j] == -1 && check[j] == 0) {
                    check[j] = -1;
                    light.offer(j);
                }
            }
            while (!heavy.isEmpty()){
                int tmp = heavy.poll();
                for (int t = 1; t <= N; t++) {
                    if (arr[tmp][t] == 1 && check[t] == 0) {
                        check[t] = 1;
                        heavy.offer(t);
                    }
                }
            }
            while (!light.isEmpty()){
                int tmp = light.poll();
                for (int t = 1; t <= N; t++) {
                    if (arr[tmp][t] == -1 && check[t] == 0) {
                        check[t] = -1;
                        light.offer(t);
                    }
                }
            }
            int cnt = 0;
            for (int k = 1; k <= N; k++) {
                if (check[k] == 0) {
                    cnt++;
                }
            }
            bw.write(cnt + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
