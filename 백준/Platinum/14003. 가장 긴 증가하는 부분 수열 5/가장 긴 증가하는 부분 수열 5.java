// 14003. [P5] 가장 긴 증가하는 부분 수열 5.
import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans;
    static int[] idxMemo, data, arr;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        data = new int[N];
        idxMemo = new int[N];
        arr = new int[N+1];
        arr[0] = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            data[n] = Integer.parseInt(st.nextToken());
        }
        ans = 0;
        for (int i = 0; i < N; i++) {
            int curr = data[i];
            if (arr[ans] < curr) {
                idxMemo[i] = ++ans;
                arr[ans] = curr;
            } else {
                int left = 0;
                int right = ans;
                int mid = (left + right) / 2;
                while (left <= right) {
                    mid = (left + right) / 2;
                    if (curr < arr[mid]) {
                        right = mid -1;
                    } else if (curr > arr[mid]) {
                        left = mid + 1;
                    } else {
                        left = mid;
                        break;
                    }
                }
                idxMemo[i] = left;
                arr[left] = curr;
            }
        }
//        for (int i = 0; i < N; i++) {
//            System.out.println(i + " " + idxMemo[i]);
//        }
        Stack<Integer> stack = new Stack<>();
        int limit = ans ;

        for (int i = N-1; limit > 0; i--) {
//            System.out.println(i + ":" + idxMemo[i] + "-" + limit);
            if (i == -1) {
                break;
            }
            if (idxMemo[i] == limit) {
                stack.push(data[i]);
                limit--;
            }
        }
        bw.write(ans + "\n");
//        System.out.println(stack.size());
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");

        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }

}