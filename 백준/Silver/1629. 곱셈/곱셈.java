// 01629. [S1] 곱셈.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int A, B, C;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        bw.write(String.valueOf(pow(A, B, C)));
        bw.flush();
        bw.close();
        br.close();
    }
    static long pow(int a, int b, int mod) {
        if (b == 0) {
            return 1;
        }
        long n = pow(a, b/2, mod);
        if (b % 2 == 0) {
            return n * n % mod;
        } else {
            return (n * n % mod) * a % mod;
        }
    }

}
