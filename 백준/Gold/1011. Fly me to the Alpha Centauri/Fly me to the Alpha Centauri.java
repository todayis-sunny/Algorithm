// 01011. [G5] Fly me to the Alpha Centauri.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int TC;
    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            bw.write(solution(x, y) + "\n");
        }
        bw.flush();
    }

    static boolean hasDecimalPart(double value) {
        return value != (int) value;
    }
    static int solution(int x, int y) {
        double doubleTypeSol = Math.sqrt((y-x));
        int intTypeSol = (int) doubleTypeSol;
        boolean hasDecimal = hasDecimalPart(doubleTypeSol);
        if (!hasDecimal) {
            return intTypeSol * 2 - 1;
        }
        if (y- x <= intTypeSol * intTypeSol + intTypeSol) {
            return intTypeSol * 2;
        }
        return intTypeSol * 2 + 1;
    }

}
