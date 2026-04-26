// 22866. [G3] 탑 보기.

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static Building building;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        building = new Building();
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            building.height[n] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        building.search(true);
        building.search(false);
    }

    static void output() throws IOException {
        for (int i = 1; i <= N; i++) {
            if (building.count[i] == 0) {
                sb.append("0");
            } else {
                sb.append(building.count[i]).append(" ").append(building.near[i]);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static class Building {
        int[] height, near, count;

        public Building() {
            this.height = new int[N + 1];
            this.near = new int[N + 1];
            this.count = new int[N + 1];
            Arrays.fill(near, 200_001);
        }

        void search(boolean isLeft) {
            int start, end, alpha;
            if (isLeft) {
                start = 1;
                end = N + 1;
                alpha = 1;
            } else {
                start = N;
                end = 0;
                alpha = -1;
            }
            Stack<Integer> stack = new Stack<>();
            for (int i = start; i != end; i += alpha) {
                int curr = height[i];
                // 스택이 비어있지 않고, 자신이랑 같거나 작은 높이의 빌딩을 제거
                while (!stack.isEmpty() && curr >= height[stack.peek()]) {
                    stack.pop();
                }
                count[i] += stack.size();
                if (!stack.isEmpty()) {
                    int currDist = Math.abs(i - stack.peek());
                    int prevDist = Math.abs(i - near[i]);
                    if (currDist < prevDist) {
                        near[i] = stack.peek();
                    } else if (currDist == prevDist) {
                        near[i] = Math.min(near[i], stack.peek());
                    }
                }
                stack.push(i);
            }
        }
    }
}
