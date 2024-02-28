// 11054. [G4] 가장 긴 바이토닉 부분 수열.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] arr, dpLR, dpRL;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dpLR = new int[N+1];
        dpRL = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            dpLR[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dpLR[i] = Math.max(dpLR[j] + 1, dpLR[i]);
                }
            }
        }

        for (int i = N; i >= 1; i--) {
            dpRL[i] = 1;
            for (int j = N; j > i; j--) {
                if (arr[i] > arr[j]) {
                    dpRL[i] = Math.max(dpRL[j] + 1, dpRL[i]);
                }
            }
        }
        int answer = 0;
        for (int i = 1; i<= N; i++) {
            answer = Math.max(answer, dpLR[i] + dpRL[i]);
        }

        bw.write(String.valueOf(answer-1));
        bw.flush();
        bw.close();
        br.close();
    }

}