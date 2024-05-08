// 10282. [G4] 해킹.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static PriorityQueue<Computer> pq;
	static int TC, a, b, s, n, d, c;
	static int cnt, sec;
	static boolean[] infection;
	static ArrayList<Computer>[] data;
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		// 컴퓨터 갯수 n, 의존성 개수 d, 해킹당한 컴퓨터의 번호 c
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			infection = new boolean[n+1];
			infection[c] = true;
			cnt = 1;
			sec = 0;
			data = new ArrayList[n+1];
			for (int i = 0; i <= n; i++) {
				data[i] = new ArrayList<Computer>();
			}
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());
				data[b].add(new Computer(a, s));
			}
			pq = new PriorityQueue<>(Comparator.comparing(Computer::getTime));
			
			for (int i = 0; i < data[c].size(); i++) {
				pq.offer(data[c].get(i));
			}
			
			while(!pq.isEmpty()) {
				Computer com = pq.poll();
				// 이미 감염되었으면 무시.
				if (infection[com.next]) {
					continue;
				} else {
					infection[com.next] = true;
					cnt++;
					sec = com.time;
					for (int i = 0; i < data[com.next].size(); i++) {
						// 감염되지 않은것들 pq에 넣기.
						int next = data[com.next].get(i).next;
						int time = data[com.next].get(i).time;
						if (!infection[next]) {
							pq.offer(new Computer(next, time + sec));
						}
					}
				}
			}
			bw.write(cnt + " " + sec + "\n");
		}
		bw.flush();
		br.close();
		bw.close();
		
		
	}
	
	static class Computer{
		int next;
		int time;
		
		public Computer(int next, int time) {
			this.next = next;
			this.time = time;
		}

		public int getTime() {
			return time;
		}
		
	}
}