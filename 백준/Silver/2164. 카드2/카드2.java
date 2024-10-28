// 02164. [S4] 카드2.
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();
        for (int n = 1; n <= N; n++) {
            dq.offerLast(n);
        }
        int ans = 0;
        while (dq.size() > 1) {
            dq.pollFirst();
            int tmp = dq.pollFirst();
            dq.offerLast(tmp);
        }
        ans = dq.pollFirst();
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
