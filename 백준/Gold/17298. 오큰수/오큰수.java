// 17298. [G4] 오큰수
import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Stack<Integer[]> stack = new Stack<>();
        int[] answer = new int[N];
        Arrays.fill(answer, -1);
        for (int idx = 0; idx < N; idx++) {
            int tmp = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()) {
                if (stack.peek()[1] < tmp) {
                    Integer[] target = stack.pop();
                    answer[target[0]] = tmp;
                } else {
                    break;
                }
            }
            stack.push(new Integer[]{idx, tmp});
        }

        for (int idx = 0; idx < N; idx++) {
            bw.write(answer[idx] + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}