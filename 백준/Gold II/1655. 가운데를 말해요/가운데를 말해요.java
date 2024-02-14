// [BOJ] 01655. 가운데를 말해요
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> leftPQ = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> rightPQ = new PriorityQueue<>();
		
		int first = Integer.parseInt(br.readLine());
		leftPQ.offer(first);
		bw.write(String.valueOf(leftPQ.peek()));
		bw.newLine();
		for(int n = 1; n < N; n++) {
			
			int num = Integer.parseInt(br.readLine());
			
			if(rightPQ.peek() == null) { // 우측 우선순위 큐가 비어있다면,
				if(num < leftPQ.peek()) { // leftPQ에 들어가야 한다면,
					rightPQ.offer(leftPQ.poll());
					leftPQ.offer(num);
				} else { // rightPQ에 들어가야 한다면,
					rightPQ.offer(num);
				}
			
			} else {
				if(n % 2 == 1) { // 현재까지 홀수개의 숫자라면,
					if(num < leftPQ.peek()) { // leftPQ에 들어가야 한다면,
						rightPQ.offer(leftPQ.poll());
						leftPQ.offer(num);
					} else { // rightPQ에 들어가야 한다면,
						rightPQ.offer(num);
					}
				} else { // 현재까지 짝수개의 숫자라면,
					if(num < rightPQ.peek()) { // leftPQ에 들어가야 한다면,
						leftPQ.offer(num);
					} else { // rightPQ에 들어가야 한다면,
						leftPQ.offer(rightPQ.poll());
						rightPQ.offer(num);
					}
				}
			}
			bw.write(String.valueOf(leftPQ.peek()));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}