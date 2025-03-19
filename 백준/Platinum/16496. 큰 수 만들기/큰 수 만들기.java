// 16496. [P5] 큰 수 만들기.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;
    static int N;
    static String[] data;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        data = new String[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = st.nextToken();
        }
    }

    static void solve() {
        Arrays.sort(data, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        sb = new StringBuilder();
        for (String s : data) {
            sb.append(s);
        }
    }

    static void output() throws IOException {
        if (sb.charAt(0) == '0') {
            bw.write("0");
        } else {
            bw.write(sb.toString());
        }
        bw.flush();
    }
}
