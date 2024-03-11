// 17299. [G3] 오등큰수
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
        int[] count = new int[1_000_001];
        int[] index = new int[N];
        int[] answer = new int[N];
        st = new StringTokenizer(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int idx = 0; idx < N; idx++) {
            int tmp = Integer.parseInt(st.nextToken());
            index[idx] = tmp;
            count[tmp]++;
        }

        for(int idx = 0; idx < N; idx++) {
            while (!stack.isEmpty() && count[index[idx]] > count[index[stack.peek()]]) {
                answer[stack.pop()] = index[idx];
            }
            stack.push(idx);
        }
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }
        for (int idx = 0; idx < N; idx++) {
            bw.write(answer[idx] + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}