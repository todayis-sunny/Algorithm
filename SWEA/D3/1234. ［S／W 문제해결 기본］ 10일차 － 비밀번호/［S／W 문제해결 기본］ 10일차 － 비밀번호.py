// [SWEA] 01234. 스택2
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static Stack<String> stack;
	
	public static void main(String[] args) throws IOException {
		for(int tc = 1; tc <= 10; tc++) {
			stack = new Stack<>();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String input = st.nextToken();
			for(int n = 0; n < N; n++) {
				String tmp = String.valueOf(input.charAt(n));
				if(!stack.isEmpty() && tmp.equals(stack.peek())) {
					stack.pop();
				} else {
					stack.push(tmp);	
				}
			}
			bw.write("#" + tc + " ");
			String answer = "";
			while(!stack.isEmpty()) {
				String tmp = stack.pop();
				answer = tmp + answer;
			}
			bw.write(answer);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
