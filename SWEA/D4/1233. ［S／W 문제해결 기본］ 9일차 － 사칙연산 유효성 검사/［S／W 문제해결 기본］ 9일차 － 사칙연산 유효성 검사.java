// [SWEA] 01233. 사칙연산 유효성 검사
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		for (int tc = 1; tc <= 10; tc++) {
			answer = 1;
			int N = Integer.parseInt(br.readLine());
			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				String tmp = st.nextToken();
				if (answer == 1&& n <= N / 2) {
					if (!"+-*/".contains(tmp)) {
						answer = 0;
					}
				} else {
					if ("+-*/".contains(tmp)) {
						answer = 0;
					}

				}
			}
			bw.write("#" + tc + " " + answer + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}