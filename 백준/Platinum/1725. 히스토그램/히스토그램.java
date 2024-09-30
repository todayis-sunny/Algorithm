// 01725. [P5] 히스토그램.

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static long ans;
    static int[] histogram;
    static long[] left, right;
    static Stack<Integer> leftStack, rightStack;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        histogram = new int[N + 2];
        // 각 칸의 높이의 최솟값은 0이 므로 양 끝은 -1로 초기화 
        histogram[0] = -1;
        histogram[N + 1] = -1;
        left = new long[N + 1];
        right = new long[N + 1];
        leftStack = new Stack<>();
        rightStack = new Stack<>();
        // 데이터 입력
        for (int i = 1; i <= N; i++) {
            histogram[i] = Integer.parseInt(br.readLine());
        }
        // left 탐색
        leftStack.push(0);
        for (int i = 1; i <= N; i++) {
            int currHeight = histogram[i]; // 현재의 높이
            while (!leftStack.isEmpty()) {
                int prevIndex = leftStack.peek(); // 이전의 인덱스
                int prevHeight = histogram[prevIndex]; // 이전의 높이
                if (currHeight <= prevHeight) { // 현재 높이보다 높거나 같은 것들 꺼내기
                    leftStack.pop();
                } else { // 현재 높이보다 낮나면
                    leftStack.push(i); // 스택에 현재 인덱스 넣기
                    left[i] = (long) (i - prevIndex) * currHeight; // 현재의 높이 * |(현재 인덱스 - 최상위 인덱스)|
                    break;
                }
            }
        }

        // right 탐색
        rightStack.push(N + 1);
        for (int i = N; i >= 1; i--) {
            int currHeight = histogram[i]; // 현재의 높이
            while (!rightStack.isEmpty()) {
                int prevIndex = rightStack.peek(); // 이전의 인덱스
                int prevHeight = histogram[prevIndex]; // 이전의 높이
                if (currHeight <= prevHeight) { // 현재 높이보다 높거나 같은 것들 꺼내기
                    rightStack.pop();
                } else { // 현재 높이보다 낮나면
                    rightStack.push(i); // 스택에 현재 인덱스 넣기
                    right[i] = (long) (prevIndex - i) * currHeight; // 현재의 높이 * |(최상위 인덱스 - 현재 인덱스)|
                    break;
                }
            }
        }

        ans = Long.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            long area = left[i] + right[i] - histogram[i]; // 좌측 넓이 + 우측 넓이 - 중복된 넓이
            ans = Math.max(ans, area);
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

}
