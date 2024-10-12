// 03015. [P5] 오아시스 재결합
import java.io.*;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static long ans;
    static int[] human;
    static Stack<Human> stack;

    public static void main(String[] args) throws IOException {
        // 0. 초기화 단계
        N = Integer.parseInt(br.readLine());
        human = new int[N];
        stack = new Stack<>();
        ans = 0;
        // 1. 입력 단계
        for (int n = 0; n < N; n++) {
            int data = Integer.parseInt(br.readLine());

            while (!stack.isEmpty() && data > stack.peek().height) {
                ans += stack.pop().count;
            }

            if (stack.isEmpty()) { // 스택이 비어 있으면, 데이터 추가.
                stack.push(new Human(data));
            } else {
                if (data < stack.peek().height) { // 더 큰 사람을 만나면 스택에 추가하고, ans 증가
                    stack.push(new Human(data));
                    ans++;
                } else { // 키가 같은 사람을 만나면 count 증가
                    ans += stack.peek().count++;
                    if (stack.size() > 1) {
                        ans ++;
                    }
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    public static class Human{
        int height;
        int count;

        public Human(int height) {
            this.height = height;
            this.count = 1;
        }
    }

}
