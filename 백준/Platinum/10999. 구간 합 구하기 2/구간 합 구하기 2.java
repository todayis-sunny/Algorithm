// 10999. [P4] 구간 합 구하기 2.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, K;
    static final int UPDATE = 1;
    static final int SUM = 2;
    static long[] tree1, tree2;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        tree1 = new long[N+1];
        tree2 = new long[N+1];
        for (int n = 1; n <= N; n++) {
            long diff = Long.parseLong(br.readLine());
            rangeUpdate(n, n, diff);
        }
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            long d;
            if (a == UPDATE) {
                d = Long.parseLong(st.nextToken());
                rangeUpdate(b, c, d);
            } else{
                bw.write(intervalSum(b, c) + "\n");
            }
        }
        bw.flush();

    }
    static void update(int treeType, int i, long diff) {
        long[] tree = treeType == 1 ? tree1 : tree2;
        while (i <= N) {
            tree[i] += diff;
            i += (i & -i);
        }
    }

    static void rangeUpdate(int a, int b, long diff) {
        update(1, a, diff);
        update(1, b+1, -diff);
        update(2, a, diff * (a-1));
        update(2, b+1, -diff * b);
    }
    static long getTreeValue(int treeType, int i) {
        long[] tree = treeType == 1? tree1 : tree2;
        long result = 0L;
        while (i > 0) {
            result += tree[i];
            // 0이 아닌 마지막 비트만큼 빼가면서 이동
            i -= (i & -i);
        }
        return result;
    }
    static long prefixSum(int i) {
        return getTreeValue(1, i) * i - getTreeValue(2, i);
    }
    static long intervalSum(int start, int end) {
        return prefixSum(end) - prefixSum(start - 1);
    }
}
