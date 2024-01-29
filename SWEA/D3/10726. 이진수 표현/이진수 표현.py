// 10726. [Diff_03] 이진수 표현
import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            String input = br.readLine();
            st = new StringTokenizer(input, " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int check = (1 << N) - 1;
            M &= check;
            if (M == check) {
                bw.write("#" + tc + " ON");
                bw.newLine();
            } else {
                bw.write("#" + tc + " OFF");
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}