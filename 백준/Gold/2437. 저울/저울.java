// 02437. [G2] 저울.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] data;
    // 수학적 귀납법으로 접근.
    public static void main(String[] args) throws IOException {
        input();
        Arrays.sort(data);
        int target = 1;
        for (int n = 0; n < N; n++) {
            if(target < data[n]) {
                break;
            }
            target += data[n];
        }
        bw.write(String.valueOf(target));
        bw.flush();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        data = new int[N];
        for (int n = 0; n < N; n++) {
            data[n] = Integer.parseInt(st.nextToken());
        }
    }
}
