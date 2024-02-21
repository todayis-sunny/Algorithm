//[SWEA] _01232. 사칙연산
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] arr = new String[N+1];
			double [] numbers = new double[N+1];
			int[][] index = new int[N+1][2];
			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				String tmp = st.nextToken();
				if (!"+-*/".contains(tmp)) {
					numbers[n] = Double.parseDouble(tmp);
					continue;
				}
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[n] = tmp;
				index[n][0] = a;
				index[n][1] = b;
			}
			
			for (int n = N; n >= 1; n--) {
				String op = arr[n];
				if(op == null) {
					continue;
				}
				int a = index[n][0];
				int b = index[n][1];
				switch(op) {
					case "+":
						numbers[n] = numbers[a] + numbers[b];
						break;
					case "-":
						numbers[n] = numbers[a] - numbers[b];
						break;
					case "*":
						numbers[n] = numbers[a] * numbers[b];
						break;
					case "/":
						numbers[n] = numbers[a] / numbers[b];
						break;
					default:
						break;
				}
			}
			bw.write("#" + tc + " " + (int) numbers[1] + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}