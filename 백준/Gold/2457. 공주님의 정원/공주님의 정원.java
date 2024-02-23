// 02457. [G3] 공주님의 정원
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
	static int[] limit, transform, arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		limit = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		transform = new int[13];
		for (int i = 1; i <= 12; i++) {
			transform[i] = transform[i-1] + limit[i-1];
		}
		int N = Integer.parseInt(br.readLine());
		int lastDay = monthDay(12, 31) + 1;
		arr = new int[lastDay];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int m1 = Integer.parseInt(st.nextToken());
			int d1 = Integer.parseInt(st.nextToken());
			int m2 = Integer.parseInt(st.nextToken());
			int d2 = Integer.parseInt(st.nextToken());
			
			arr[monthDay(m1, d1)] = Math.max(arr[monthDay(m1, d1)], monthDay(m2, d2));
		}
		int start = monthDay(3, 1); // 시작
		int end = monthDay(11, 30); // 종료
		int idx = 0;
		int rast = 0;
		int cnt = 0;
		
		while (rast <= end) {
			if (idx == lastDay) { // 다 순회 했을때, 탈출.
				break;
			}
			if (idx > start) {
				break;
			}

			while (idx <= start) {
				rast = Math.max(rast, arr[idx++]);
				if (idx == lastDay) {
					break;
				}
			}
			if (rast < start) {
				break;
			}
			start = rast;

			cnt += 1;
		}
		
		if (rast <= end) { // 조건을 만족하는 꽃을을 선택할 수 없을때, 0
			cnt = 0;
		}
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int monthDay(int month, int day) {
		return transform[month] + day;
	}
}
