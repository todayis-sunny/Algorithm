//01644. [G3] 소수의 연속합.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] check;
    static ArrayList<Integer> data;
    static int N, answer, left, right, num;
    public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		check = new boolean[N+1];
		answer = 0;
		data = new ArrayList<>();
		for (int n = 2; n <= (N+1) / 2; n++) {
			if (!check[n]) {
				data.add(n);
				for (int k = 2; n*k <= N; k++) {
					check[n*k] = true;
				}
			}
		}
		for (int n = (N+1)/2 + 1; n <= N; n++) {
			if (!check[n]) {
				data.add(n);
			}
		}
		data.add(N+1);
		data.add(N+2);
		left = 0;
		right = 0;
		num = data.get(0);
		while(left <= right) {
			if (num > N) {
				num -= data.get(left++);
			} else if (num < N) {
				num += data.get(++right);
			} else {
				answer += 1;
				num -= data.get(left++);
			}
		}
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}
