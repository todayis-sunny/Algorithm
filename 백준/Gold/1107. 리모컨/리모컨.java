// 01107. [G5] 리모컨.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int goal = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] button = new boolean[10];
        Arrays.fill(button, true);
        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                button[Integer.parseInt(st.nextToken())] = false;
            }
        }

        int result = Math.abs(goal - 100);
        for (int i = 0; i <= 999999; i++) {
            String str = String.valueOf(i);
            int len = str.length();
            boolean flag = false;
            for (int j = 0; j < len; j++) {
                if (!button[Character.getNumericValue(str.charAt(j))]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                int min = Math.abs(goal - i) + len;
                result = Math.min(min, result);
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

}
