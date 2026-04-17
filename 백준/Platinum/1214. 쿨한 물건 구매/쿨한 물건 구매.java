// 01214. [P5] 쿨한 물건 구매.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int D, P, Q; // D: 물건의 가격, P, Q: 두 지폐
    static int min;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        // P > Q로 설정
        if (P < Q) {
            int tmp = P;
            P = Q;
            Q = tmp;
        }
        min = Integer.MAX_VALUE;
        int limit = Math.min(D / P, Q) + 1;
        for (int i = 0; i < limit; i++) {
            min = Math.min(min, (Q - (D - P*i) % Q) % Q);
        }
        min = Math.min(min, (P - (D % P)) % P);
    }

    static void output() throws IOException {
        bw.write(String.valueOf(D + min));
        bw.flush();
    }
}
