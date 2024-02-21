// [SWEA] 01231. 중위순회
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
	
	static int N;
	static char[] arr;
	static String answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		for (int tc = 1; tc <= 10; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new char[N + 1];
			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				arr[n] = st.nextToken().charAt(0);
			}
			bw.write("#" + tc + " ");
			inorderTraverse(1);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();

	}
	
	public static void inorderTraverse(int idx) throws IOException {
		if (idx > N) {
			return;
		}
		inorderTraverse(2*idx);
		bw.write(String.valueOf(arr[idx]));
		inorderTraverse(2*idx + 1);
	}
}
