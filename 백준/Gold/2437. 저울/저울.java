// 02437. [G2] 저울.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] data;
    /*
    구하고자 하는건 최솟값을 구해야한다. 적당하게 큰 값은 배열에 저장할 필요가 있을까?
    트리맵을 사용?
     */

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        data = new int[N];
        for (int n = 0; n < N; n++) {
            data[n] = Integer.parseInt(st.nextToken());
        }
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

}
