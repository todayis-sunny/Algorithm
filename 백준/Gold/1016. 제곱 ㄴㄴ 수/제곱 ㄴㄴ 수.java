// 01016. [G1] 제곱 ㄴㄴ 수.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static long min, max;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        long range = max - min;

        check = new boolean[(int) (range + 1)];
        long answer = range + 1;  // 모든 범위를 초기에 제곱ㄴㄴ수로 가정

        for (long i = 2; i * i <= max; i++) {
            long square = i * i;
            long start = min % square == 0 ? min : min + (square - (min % square));
            
            for (long j = start; j <= max; j += square) {
                int index = (int) (j - min);
                if (!check[index]) {
                    check[index] = true;
                    answer--;
                }
            }
        }

        bw.write(Long.toString(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}