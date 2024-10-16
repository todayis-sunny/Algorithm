// 12015. [G2] 가장 긴 증가하는 부분 수열 2.
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static ArrayList<Integer> data;
    static int left, right, mid;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        data = new ArrayList<>();
        data.add(0);

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int elemental = Integer.parseInt(st.nextToken());

            // 마지막 원소보다 새로운 원소가 크다면,
            if (data.get(data.size() - 1) < elemental) {
                data.add(elemental);
            } else {
                left = 0;
                right = data.size()-1;
                while (left <= right) {
                    mid = (left + right) >> 1;
                    if (data.get(mid) >= elemental) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                data.set(left, elemental);
            }
        }

        bw.write(String.valueOf(data.size() - 1));
        bw.flush();
        bw.close();
        br.close();
    }
}
