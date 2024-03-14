// 20040. [G4] 사이클 게임.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n, m, a, b;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n];
        for (int i = 1; i < n; i++) {
            parent[i] = i;
        }
        boolean flag = false;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if(!flag && find(a) == find(b)) {
                bw.write(String.valueOf(i));
                flag = true;
            }
            if (!flag) {
                union(a, b);
            }
        }
        if(!flag) {
            bw.write("0");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

}