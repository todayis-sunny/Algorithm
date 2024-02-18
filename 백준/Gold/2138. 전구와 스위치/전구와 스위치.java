// [BOJ] 02138. 전구와 스위치
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int count;
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		String input = br.readLine();
		String answer = br.readLine();
		
		int[] bulb = new int[N];
		int[] mainSwch = new int[N];
		if (input.equals(answer)) {
			bw.write("0");
			bw.flush();
			bw.close();
			br.close();
			return;
		}
		boolean flag = false;
		
		for(int n = 0; n < N; n++) {
			if(input.charAt(n) != answer.charAt(n)) {
				bulb[n] = 1;
			}
		}
		
		for (int t = 0; t < 2; t++) {
			// 0번 스위치를 OFF 또는 ON 해보기.
			int[] swch = mainSwch.clone();
			swch[0] = t;
			count = t;
			for(int i = 1; i < N; i++) {
				if(i == 1) {
					if((swch[0] + swch[1]) % 2 == bulb[0]) {
						continue;
					} else {
						swch[1] = 1;
						count++;
					}
				} else {
					if((swch[i-2] + swch[i-1] + swch[i]) % 2 == bulb[i-1]) {
						continue;
					} else {
						swch[i] = 1;
						count++;
					}
				}
			}
			if((swch[N-2] + swch[N-1]) % 2 == bulb[N-1]) {
				bw.write(String.valueOf(count));
				bw.flush();
				bw.close();
				br.close();
				return;
			}
		}
		bw.write("-1");
		bw.flush();
		bw.close();
		br.close();
		return;
	}
}