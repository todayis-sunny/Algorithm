// 09251. [G5] LCS
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		String str1 = br.readLine();
		String str2 = br.readLine();
		int[][] dp = new int[str1.length()+1][str2.length()+1];
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
		bw.write(String.valueOf(dp[str1.length()][str2.length()]));
		bw.flush();
		bw.close();
		br.close();
	}
}
