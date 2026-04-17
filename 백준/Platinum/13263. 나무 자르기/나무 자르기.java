// 13263. [P2] 나무 자르기
import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static long[] a, b; // a: 나무 높이 배열, b: 충전 비용 배열
    static long[] dp;
    static Stack<Node> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        a = new long[N];
        b = new long[N];
        dp = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            b[i] = Long.parseLong(st.nextToken());
        }

    }

    static void solve() {
        stack.push(new Node(b[0], 0));
        for (int i = 1; i < N; i++) {
            dp[i] = bs(a[i]);
            insert(new Node(b[i], dp[i]));
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(dp[N - 1]));
        bw.flush();
    }

    static long cross(Node f, Node g) {
        return (long) (f.y - g.y) / (g.x - f.x);
    }

    static void insert(Node node) {
        stack.push(node);
        int size = stack.size();
        while (size > 2 && cross(stack.get(size - 3), stack.get(size - 2)) > cross(stack.get(size - 2), stack.get(size - 1))) {
            stack.remove(size - 2);
            size = stack.size();
        }
    }

    static long bs(long h) {
        int left = 0;
        int right = stack.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (cross(stack.get(mid), stack.get(mid + 1)) <= h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        Node node = stack.get(left);
        return node.x * h + node.y;
    }

    static class Node {
        long x, y;

        Node(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
