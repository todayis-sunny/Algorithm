//02812. [G3] 크게 만들기.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;
	static int N, K, P, idx;
	static String num;
	static char ch, tmp;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = N - K;
		sb = new StringBuilder(P);
		num = br.readLine();
		idx = -1;
		while (P > 0) {
			tmp = '/'; // '0' 바로 직전.
			for (int i = idx + 1; i <= N - P; i++) {
				ch = num.charAt(i);
				if (ch > tmp) {
					tmp = ch;
					idx = i;
					if (ch == '9') {
						break;
					}
				}
			}
			sb.append(tmp);
			P--;
		}
		System.out.println(sb.toString());
	}
}