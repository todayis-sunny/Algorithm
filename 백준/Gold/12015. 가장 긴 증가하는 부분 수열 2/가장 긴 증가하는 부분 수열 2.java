// 12015. [G2] 가장 긴 증가하는 부분 수열 2.
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] arr;
    static ArrayList<Integer> dp;
    static int left, right, mid;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new ArrayList<>();


        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            // 원소가 없거나, 마지막 원소보다 새로운 원소가 크다면,
            if (dp.isEmpty() || dp.get(dp.size() - 1) < arr[i]) {
                dp.add(arr[i]);
            } else {
                left = 0;
                right = dp.size()-1;
                while (left < right) {
                    mid = (left + right) >> 1;
                    if (dp.get(mid) >= arr[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                dp.set(right, arr[i]);
            }

        }
        bw.write(String.valueOf(dp.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}