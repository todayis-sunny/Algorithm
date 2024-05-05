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
        pq = new PriorityQueue<>(Comparator.reverseOrder());  // 컵라면이 많은 것부터 처리해야 하므로 최대 우선순위 큐.
        answer = 0;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            deadLine = Integer.parseInt(st.nextToken());
            cupNoodle = Integer.parseInt(st.nextToken());
            problem.add(new Item(deadLine, cupNoodle));
        }
        problem.add(new Item(0, 0)); // 마지막 컵라면을 비교해주기 위한 의미없는 값 넣어주기.
        problem.sort(Comparator.comparing(Item::getIdx).reversed()); // index가 큰것 부터 접근하기 -> 시간순으로 볼것이 아니라, 현재에서 과거의 방향으로.

        int prev = 200_001;
        int next = -1;
        int cnt = 0;
        // prev는 기존 컵라면의 데드라인, next는 선택할 컵라면의 데드라인.
        for (int n = 0; n <= N; n++) {
            next = problem.get(n).idx;
            if (next != prev) {
                cnt = prev - next;
                for (int i = 0; i < cnt; i++) {
                    if (pq.isEmpty()) {
                        break;
                    } else {
                        answer += pq.poll();
                    }
                }
            }
            prev = next;
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