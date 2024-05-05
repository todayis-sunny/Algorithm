// 01781. [G2] 컵라면.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, deadLine, cupNoodle, answer;
    static ArrayList<Item> problem;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        problem = new ArrayList<>();
        pq = new PriorityQueue<>(Comparator.reverseOrder());
        answer = 0;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            deadLine = Integer.parseInt(st.nextToken());
            cupNoodle = Integer.parseInt(st.nextToken());
            problem.add(new Item(deadLine, cupNoodle));
        }
        problem.add(new Item(0, 0));
        problem.sort(Comparator.comparing(Item::getIdx).reversed());
        
        int right = 200_001;
        int left = -1;
        int cnt = 0;

        for (int n = 0; n <= N; n++) {
            left = problem.get(n).idx;
            if (left != right) {
                cnt = right - left;
                for (int i = 0; i < cnt; i++) {
                    if (pq.isEmpty()) {
                        break;
                    } else {
                        answer += pq.poll();
                    }
                }
            }
            right = left;
            int cup = problem.get(n).cnt;
            pq.offer(cup);
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }

    static class Item{
        int idx;
        int cnt;

        public Item(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }

        public int getIdx() {
            return idx;
        }

        public int getCnt() {
            return cnt;
        }
    }

}