import java.io.*;

// 01288. [Diff_02] 새로운 불면증 치료법
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        int total = (1 << 10) - 1;

        for (int t = 1; t <= tc; t++) {
            int N = Integer.parseInt(br.readLine());
            int visited = 0;
            boolean[] checked = new boolean[10];
            int count = 0;
            for (count = 1; ; count++) {
                char[] arr = String.valueOf(N * count).toCharArray();
                for (char c : arr) {
                    int num = c - '0';
                    visited = visited | (1 << num);
                    checked[num] = true;
                }
                if (visited == total) {
                    bw.write("#" + t + " " + N*count);
                    bw.newLine();
                    break;
                }

                boolean flag = true;
                for (int k = 0; k <= 9; k++) {
                    if (!checked[k]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
        }
        bw.flush();
        bw.close();
    }
}