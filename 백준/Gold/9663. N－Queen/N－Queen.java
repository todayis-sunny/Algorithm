// 09663. [G4] N-Queen.
import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, answer;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        answer = 0;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        Arrays.fill(arr, -1);
        dfs(0);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int cnt) {
        for (int i = 0; i < cnt-1; i++) {
            if (arr[i] == arr[cnt-1]) {
                return;
            }
            if (Math.abs(cnt-1 - i) == Math.abs(arr[cnt-1] - arr[i])) {
                return;
            }
        }

        if (cnt == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[cnt] = i;
            dfs(cnt+1);
            arr[cnt] = -1;
        }
    }

}