// 01202. [G2] 보석 도둑.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K, M, V, limit, bag, idx;
    static long answer;
    static Gem[] gems;
    static int[] bags;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		answer = 0;
		gems = new Gem[N+1];
		bags = new int[K];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			gems[n] = new Gem(M, V);
		}
		gems[N] = new Gem(100_000_001, 0);
		
		for (int k = 0; k < K; k++) {
			bags[k] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(gems, Comparator.comparing(Gem::getWeight));
		Arrays.sort(bags);
		answer = 0;
		pq = new PriorityQueue<>(Collections.reverseOrder());
		idx = 0;
		for (int k = 0; k < K; k++) {
			bag = bags[k];
			for (int i = idx; i < N; i++) {
				if (bag < gems[i].weight) {
					break;
				}
				pq.offer(gems[i].price);
				idx = i+1;
			}
			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
			
		}
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
    
    static class Gem {
    	int weight;
    	int price;
    	Gem(int weight, int price) {
    		this.weight = weight;
    		this.price = price;
    	}
		public int getWeight() {
			return weight;
		}
    	
    }
}