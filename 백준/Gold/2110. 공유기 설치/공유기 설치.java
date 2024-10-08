// 02110. [G4] 공유기 설치.
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, C;
    static int[] house;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        house = new int[N];

        for (int n = 0; n < N; n++) {
            house[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        int left = 1;
        int right = house[N-1];
        while (left <= right) {
            int mid = (left + right) / 2;

            int position = 0;
            int cnt = 1;
            for (int i = 0; i < N; i++) {
                if (house[i] - house[position] >= mid) {
                    position = i;
                    cnt++;
                }
            }
            if (cnt < C) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        bw.write(String.valueOf(right));
        bw.flush();
        bw.close();
        br.close();
    }

}
