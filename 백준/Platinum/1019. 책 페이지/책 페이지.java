// 01019. [P5] 책 페이지.
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] ans = new int[10];
    static int N, a, b, cnt;



    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        a = 1;
        b = N;
        cnt = 1;

        while (a <= b) {
            while (b % 10 != 9 && a <= b) {
                calculate(b);
                b--;
            }
            while (a % 10 != 0 && a <= b) {
                calculate(a);
                a++;
            }
            if (a > b) {
                break;
            }

            a /= 10;
            b /= 10;
            for (int i = 0; i < 10; i++) {
                ans[i] += (b - a + 1) * cnt;
            }
            cnt *= 10;
        }

        for (int i = 0; i < 10; i++) {
            bw.write(ans[i] + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void calculate(int curr) {
        while (0 < curr) {
            ans[curr % 10] += cnt;
            curr /= 10;
        }
    }
}
