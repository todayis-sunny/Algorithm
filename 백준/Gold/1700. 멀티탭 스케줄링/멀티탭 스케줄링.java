// [BOJ] 01700. 멀티탭 스케줄링
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int answer;

	public static void main(String[] args) throws IOException {
		answer = 0;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if(K <= N) { // 사용횟수 <= 멀티탭 구멍 : 무조건 가능.		
			bw.write("0");
			bw.close();
			br.close();
			return;
		}
		
		Queue<Integer>[] queue = new LinkedList[101]; // 기기별 사용스케줄
		for(int i=0; i < 101; i++) {
			queue[i] = new LinkedList<>();
		}
		
		boolean[] used = new boolean[101]; // 기기별 사용여부
		int[] multiTab = new int[N]; // 멀티탭
		int[] sequence = new int[K]; // 순서
		st = new StringTokenizer(br.readLine());
		for (int k = 0; k < K; k++) {
			int tmp = Integer.parseInt(st.nextToken());
			sequence[k] = tmp;
			queue[tmp].offer(k);
		}
		int idx = 0; // 사용 순서
		int hole = 0; // 멀티탭 사용중인 갯수
		
		while(hole < N) { // 플러그를 다 사용할 때 까지 실행.
			if(idx == K) { // 모든 스케줄이 다 끝났다면 종료.
				bw.write("0");
				bw.close();
				br.close();
				return;
			}
			int device = sequence[idx++];
			queue[device].poll(); // 사용스케줄 제거
			if(!used[device]) { // 사용되고 있지 않으면,
				used[device] = true;
				multiTab[hole++] = device;
			}
		}
	
		
		// 플러그 꽉 차면 실행.
		for (int i = idx; i < K; i++) { 
			int newDV = sequence[i];
			queue[newDV].poll(); // 사용스케줄 제거.
			if(!used[newDV]) { // 사용되고 있지 않으면,

				int rmIdx = 0; // 제거할 Device index.
				int later = 0; // 그나마 나중에 사용될 device의 스케줄.
				
				for(int j = 0; j < N; j++) {
					int curDV = multiTab[j]; 
					if(queue[curDV].peek() == null) { // 더 이상 사용되지 않을 device라면,
						rmIdx = j;
						break;
					}
					if(later < queue[curDV].peek()) { // 더 나중에 사용될 device의 스케줄을 검사.
						rmIdx = j;
						later = queue[curDV].peek();
					}
				}
				int rmDV = multiTab[rmIdx];
				used[rmDV] = false;
				used[newDV] = true;
				multiTab[rmIdx] = newDV;
				answer += 1; // 플러그 뽑을 때 answer 1 증가.
			}
		}
		bw.write(String.valueOf(answer));
		bw.close();
		br.close();
	}
}
