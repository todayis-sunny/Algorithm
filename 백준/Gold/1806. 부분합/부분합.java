// 01806. [G4] 부분합.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, S, ans;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        ans = 100_001;
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N] = 100_000_001;

        int left = 0;
        int right = 0;
        int tmp = arr[0];
        while (right < N) {
            if (tmp >= S) {
               if (left == right) {
                   ans = 1;
                   break;
               } else {
                   ans = Math.min(ans, right - left + 1);
                   tmp -= arr[left++];
               }
            } else if (tmp < S) {
                if (right - left >= ans) {
                    if (left < right) {
                        tmp -= arr[left++];
                    } else {
                        tmp -= arr[left++];
                        tmp += arr[++right];
                    }
                } else {
                    tmp += arr[++right];
                }
            }
        }
        if (ans == 100_001) {
            ans = 0;
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

}