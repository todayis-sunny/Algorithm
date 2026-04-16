// 02159. [G3] 케익 배달.
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static long ans;
    static Node from;
    static Queue<Node> queue;
    // 중앙 0, 상 1, 하 2, 좌 3, 우 4
    static int[] dx = new int[]{0, -1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, 0, -1, 1};
    static long[] dp1, dp2; // dp 배열을 번갈아가면서 사용하기 위함
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        from = new Node(x, y);
        dp1 = new long[5];
        dp2 = new long[5];
        Arrays.fill(dp1, 1); // 상하좌우는 1만큼 거리를 줌
        dp1[0] = 0; // 가운데는 0으로 초기화
        long[] prev;
        long[] next;
        queue = new LinkedList<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            Node to = new Node(x, y);
            prev = n % 2 == 0? dp1 : dp2;
            next = n % 2 == 0? dp2 : dp1;
            for (int i = 0; i < 5; i++) {
                next[i] = Long.MAX_VALUE;
                int toX = to.x + dx[i];
                int toY = to.y + dy[i];
                for (int j = 0; j < 5; j++) {
                    int fromX = from.x + dx[j];
                    int fromY = from.y + dy[j];
                    int dist = getDistance(fromX, fromY, toX, toY);
                    next[i] = Math.min(next[i], prev[j] + dist);
                }
            }
            from = to; // 출발지를 갱신해줌
        }
        long temp = Long.MAX_VALUE;
        long[] answer = N % 2 == 0? dp1 : dp2;
        for (int i = 0; i < 5; i++) {
            temp = Math.min(temp, answer[i]);
        }
        ans = temp;
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
