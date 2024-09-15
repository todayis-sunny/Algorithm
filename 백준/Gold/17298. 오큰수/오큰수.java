import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] answer = new int[n];
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Integer> stack = new Stack();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(st.nextToken());
            map.put(i, val);
        }
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            int up = map.get(i);
            while (true) {

                int value = map.get(stack.peek());
                if (value < up) {
                    arr[stack.peek()] = map.get(i);
                    stack.pop();
                } else {
                    stack.push(i);
                    break;
                }
                if (stack.isEmpty()) {
                    stack.push(i);
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                bw.write("-1 ");
            } else {
                bw.write(arr[i] + " ");
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
