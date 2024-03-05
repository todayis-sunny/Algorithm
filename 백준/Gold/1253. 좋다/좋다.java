// 01253. [G4] 좋다.
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N-1;
            while (left < right) {
                if (arr[i] == arr[left] + arr[right] && i != left && i != right) {
                    answer += 1;
                    break;
                } else if (left == i) {
                    left += 1;
                } else if (right == i) {
                    right -= 1;
                }
                if (arr[left] + arr[right] > arr[i]) {
                    right -= 1;
                } else if (arr[left] + arr[right] < arr[i]) {
                    left += 1;
                }
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

}