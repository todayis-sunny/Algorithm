// 06549. [P5] 히스토그램에서 가장 큰 직사각형.
import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;

    static long ans;
    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) {
                break;
            }
            ans = 0;
            int[] height = new int[N + 2];
            long[] left = new long[N + 1];
            long[] right = new long[N + 1];
            Stack<Integer> leftStack = new Stack<>();
            Stack<Integer> rightStack = new Stack<>();
            height[0] = -1;
            height[N+1] = -1;
            for (int i = 1; i <= N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }
            leftStack.push(0);
            for (int i = 1; i <= N; i++) {
                int currH = height[i];
                while (!leftStack.isEmpty()) {
                    int prev = leftStack.peek(); // 이전의 인덱스
                    int prevH = height[prev]; // 이전의 높이
                    if (currH <= prevH) {
                        leftStack.pop();
                    } else {
                        leftStack.push(i);
                        left[i] = (long) (i - prev) * currH;
                        break;
                    }
                }
            }

            rightStack.push(N + 1);
            for (int i = N; i >= 1; i--) {
                int currH = height[i];
                while (!leftStack.isEmpty()) {
                    int prev = rightStack.peek(); // 이전의 인덱스
                    int prevH = height[prev]; // 이전의 높이
                    if (currH <= prevH) {
                        rightStack.pop();
                    } else {
                        rightStack.push(i);
                        right[i] = (long) (prev - i) * currH;
                        break;
                    }
                }
            }
            long ans = Long.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                long area = left[i] + right[i] - height[i];
                ans = Math.max(ans, area);
            }
            bw.write(ans + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}