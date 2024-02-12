// 01874. [S2] 스택 수열
import java.io.*;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static Stack<Integer> stack = new Stack<>();
    static int num = 1;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            int goal = Integer.parseInt(br.readLine());

            while (num <= goal) {
                stack.push(num++);
                sb.append("+\n");
            }

            if (stack.peek() == goal) {
                stack.pop();
                sb.append("-\n");
            } else {
                System.out.println("NO");
                return;
            }
        }
        System.out.println(sb.toString());
        br.close();
    }
}
