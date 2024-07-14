// 03151. [G4] 합이 0.
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static long ans;
    static final int K = 10_000;
    static int[] memo = new int[20_001];
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        ans = 0;
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int input = Integer.parseInt(st.nextToken());

            int value = input + K;
            if(memo[value]++ == 0) {
                list.add(input);
            }
        }
        list.add(30_001);

        Collections.sort(list);
        int limit = list.size();
        for (int i = 0; i < limit; i++) {
            int curr = list.get(i);
            int left = i;
            int right = limit-1;
            while (left <= right) {
                int a = list.get(left);
                int b = list.get(right);
                int tmp = curr + a + b;
                if (tmp < 0) {
                    left++;
                } else if (tmp > 0) {
                    right--;
                } else { // 0 이라면
                    int ak = memo[a + K];
                    int bk = memo[b + K];
                    int ck = memo[curr + K];
                    if(curr == a && a == b) {
                        if(ak >= 3) {
//                            ans += combination(ak, 3);
                            ans += (ak * (ak - 1) * (ak - 2)) / 6;
                        }
                        break;
                    }
                    if (curr == a) {
                        if(memo[a + K] >= 2) {
                            ans += combination(memo[a + K], 2) * memo[b + K];
                        }
                    } else if (a == b) {
                        if(memo[a + K] >= 2) {
                            ans += combination(memo[a + K], 2) * memo[curr + K];
                        }
                        break;
                    } else {
                        ans += memo[curr + K] * memo[a + K] * memo[b + K];
                    }
                    left++;
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();

    }

    public static long combination(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        } else {
            return combination(n - 1, r - 1) + combination(n - 1, r);
        }
    }

}