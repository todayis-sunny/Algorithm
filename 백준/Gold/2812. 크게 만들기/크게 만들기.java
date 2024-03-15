// 02812. [G3] 크게 만들기.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	 static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	 static StringTokenizer st;
	 static StringBuilder sb;
	 static int N, K, P, max, idx, number;
	 static String answer, num;
	 public static void main(String[] args) throws IOException {
		answer = "";
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = N - K;
		sb = new StringBuilder(P);
		num = br.readLine();
		idx = -1;
		while (P > 0) {
			max = -1;
			for(int i = idx+1; i <= N-P; i++) {
				number = Character.getNumericValue(num.charAt(i));
				if (number > max) {
					max = number;
					idx = i;
				}
				if (number == 9) {
					break;
				}
			}
//			answer = answer.concat(String.valueOf(num.charAt(idx)));
			sb.append(String.valueOf(num.charAt(idx)));
			P--;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}