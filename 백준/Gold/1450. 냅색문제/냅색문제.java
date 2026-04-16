// 01450. [G1] 냅색문제.
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, C, ans;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ArrayList<Integer> weight1 = new ArrayList<>();
        ArrayList<Integer> weight2 = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (i < N / 2) {
                weight1.add(Integer.parseInt(st.nextToken()));
            } else {
                weight2.add(Integer.parseInt(st.nextToken()));
            }
        }
        ArrayList<Integer> sum1 = new ArrayList<>();
        ArrayList<Integer> sum2 = new ArrayList<>();
        dfs(0, 0, weight1, sum1);
        dfs(0, 0, weight2, sum2);
        Collections.sort(sum2);
        ans = 0;
        for (int i = 0; i < sum1.size(); i++) {
            int searchValue = C - sum1.get(i);
            ans += binarySearch(sum2, searchValue) + 1;
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int idx, int sum, ArrayList<Integer> weight, ArrayList<Integer> answer) {
        if (sum > C) {
            return;
        }
        if (idx == weight.size()) {
            answer.add(sum);
            return;
        }
        // 물건 넣기.
        dfs(idx + 1, sum + weight.get(idx), weight, answer);
        // 물건 안 넣기.
        dfs(idx + 1, sum, weight, answer);
    }

    public static int binarySearch(ArrayList<Integer> sum, int target) {
        int left = 0;
        int right = sum.size() - 1;
        int mid;
        int answer = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (sum.get(mid) <= target) {
                answer = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return answer;
    }
}