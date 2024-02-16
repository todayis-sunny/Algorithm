// [SWEA] 01224. 계산기3
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Stack<String> stack;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		for(int tc = 1; tc <= 10; tc++) {
			stack = new Stack<String>();
			int N = Integer.parseInt(br.readLine());
			String input = br.readLine();
			String exp = "";
			for(int n = 0; n < input.length(); n++) {
				String tmp = input.substring(n, n+1);
				switch(tmp) {
					case "(":
						stack.push(tmp);
						break;
					case ")":
						while(!stack.isEmpty() && !stack.peek().equals("(")) {
							exp = exp.concat(stack.pop());
						}
						stack.pop();
						break;
					case "+":
						while(!stack.isEmpty() &&  priority(tmp) <= priority(stack.peek())) {
							exp = exp.concat(stack.pop());

						}
						stack.push(tmp);
						break;
					case "*":
						while(!stack.isEmpty() &&  priority(tmp) <= priority(stack.peek())) {
							exp = exp.concat(stack.pop());
						}
						stack.push(tmp);
						break;
					default:
						exp = exp.concat(tmp);
						break;
				}
				
			}
			while(!stack.isEmpty()) {
				exp = exp.concat(stack.pop());
			}
			
			Stack<Integer> numbers = new Stack<>();
			for(int j = 0; j < exp.length(); j++) {
				String tmp = exp.substring(j, j+1);
				switch(tmp) {
					case "+":
						numbers.push(numbers.pop() + numbers.pop());
						break;
					case "*":
						numbers.push(numbers.pop() * numbers.pop());
						break;
					default:
						numbers.push(Integer.parseInt(tmp));
				}
			}
			bw.write("#" + tc + " " + numbers.pop() + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	static int priority(String s) {
		if(s.equals("+")) {
			return 1;
		}
		if(s.equals("*")) {
			return 2;
		}
		return 0;
	}
}