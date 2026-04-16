// 07578. [P5] 공장.
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static long ans;
    static long[] tree;
    static Map<Integer, Integer> lineA = new HashMap<>();
    static Map<Integer, Integer> lineB = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int a = 1; a <= N ; a++) {
            lineA.put(Integer.parseInt(st.nextToken()), a);
        }

        st = new StringTokenizer(br.readLine());
        for (int b = 1; b <= N; b++) {
            lineB.put(lineA.get(Integer.parseInt(st.nextToken())), b);
        }

        tree = new long[N+1];
        ans = 0;
        for (int key: lineB.keySet()) {
            int num = lineB.get(key);
            update(num);
            ans += (key - sum(num));
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    static long sum(int idx) {
        long res = 0;
        while (idx > 0) {
            res += tree[idx];
            idx &= (idx - 1);
        }
        return res;
    }

    static void update(int idx) {
        while (idx <= N) {
            tree[idx] += 1;
            idx += (idx & -idx);
        }
    }

}