// 01256. [G2] 사전.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, K, n, k;
    static final int LIMIT = 1_000_000_000;
    static boolean[] check;
    static int[] number;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        check = new boolean[N+M];
        number = new int[101];
        number[0] = 1;
        for (int i = 1; i <= 100; i++) {
            number[i] = i;
        }
        int c = N+M;
        n = M-1;
        k = M-1; // k개의 z를 선택.

        // 1개의 'z'.
        if(k == 0) {
            if(K > c) {
                bw.write("-1");
                bw.flush();
                br.close();
                bw.close();
                return;
            } else {
                check[K-1] = true;
            }
        } else { // 2개 이상의 'z'.
            long prev = 0;
            long curr = 1;
            long total = 0;
            long min = -1;
            while(k > 0) {
                total += curr;
                if (n >= c) {
                	bw.write("-1");
                    bw.flush();
                    br.close();
                    bw.close();
                    return;
                }
                if (K <= total) {
                    K -= prev;
                    check[n] = true;
                    min = n;
                    n = --k;
                    curr = 1;
                    prev = 0;
                    total = 0;
                } else {
                    prev += curr;
                    curr *= ++n;
                    int tmp = n - k == 0? 1 : n-k;
                    curr /= tmp;
                    if(curr > LIMIT) {
                        curr = LIMIT;
                    }
                }
            }
        	check[K-1] = true;
            
            
        }

        for(int i = c - 1; i >= 0; i--) {
            if (check[i]) {
                sb.append("z");
            } else {
                sb.append("a");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }
}
