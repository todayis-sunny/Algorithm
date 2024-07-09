// 01766. [G2] 문제집.
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int N, M, a, b;
    static int[] cnt;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }
        cnt = new int[N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            cnt[b] += 1;
        }

        for (int i = 1; i < N+1; i++) {
            if (cnt[i] == 0) {
                pq.offer(i);
            }
        }
        while (!pq.isEmpty()) {
            int tmp = pq.poll();
            bw.write(tmp + " ");
            for (int j = 0; j < arr.get(tmp).size(); j++) {
                int value = arr.get(tmp).get(j);
                if(--cnt[value] == 0) {
                    pq.offer(value);
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}