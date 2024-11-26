// 01669. [G5] 멍멍이 쓰다듬기.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int ans;

    public static void main(String[] args) throws IOException {
          input();
          bw.write(String.valueOf(ans));
          bw.flush();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        ans = solution(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    static int solution(int x, int y) {
        if (x == y) {
            return 0;
        }
        double doubleTypeSol = Math.sqrt((y-x));
        int intTypeSol = (int) doubleTypeSol;
        if (doubleTypeSol == intTypeSol) {
            return intTypeSol * 2 - 1;
        }
        if (y - x <= intTypeSol * intTypeSol + intTypeSol) {
            return intTypeSol * 2;
        }
        return intTypeSol * 2 + 1;
    }
}
