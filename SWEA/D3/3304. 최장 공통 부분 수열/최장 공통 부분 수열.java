// 03304. [diff_3] 최장 공통 부분 수열.
import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            String str1 = st.nextToken();
            String str2 = st.nextToken();
            int[][] dp = new int[str1.length() + 1][str2.length() + 1];
            for (int i = 1; i <= str1.length(); i++) {
                for (int j = 1; j <= str2.length(); j++) {
                    char ch1 = str1.charAt(i-1);
                    char ch2 = str2.charAt(j-1);
                    if(ch1 == ch2) {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
            int answer = dp[str1.length()][str2.length()];
            bw.write("#" + tc + " " + answer + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}