// 02842. 집배원 한상덕.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, fatigue;
    static Node postOffice;
    static ArrayList<Node> houseList;
    static boolean[][] visited;
    static int[][] map;
    static Queue<Node> queue;
    static HashSet<Integer> hashSet;
    static Integer[] heights;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        hashSet = new HashSet<>();
        houseList = new ArrayList<>();
        for (int x = 0; x < N; x++) {
            String input = br.readLine();
            for (int y = 0; y < N; y++) {
                char ch = input.charAt(y);
                switch (ch) {
                    case 'P':
                        postOffice = new Node(x, y);
                        break;
                    case 'K':
                        houseList.add(new Node(x, y));
                        break;
                }
            }
        }
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
                hashSet.add(map[x][y]);
            }
        }
        heights = hashSet.toArray(new Integer[0]);
        Arrays.sort(heights);

        fatigue = Integer.MAX_VALUE;
        int length = heights.length;
        int L = 0, R = 0;
        int low = 0, high = 0;
        while (R < length) {
            low = heights[L];
            high = heights[R];
            visited = new boolean[N][N];
            bfs(low, high);
            if (complete()) {
                fatigue = Math.min(fatigue, high-low);
                if(L < R) {
                    L++;
                } else {
                    break;
                }
            } else {
                R++;
            }
        }
        bw.write(String.valueOf(fatigue));
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(int low, int high) {
        queue = new LinkedList<>();
        if(map[postOffice.x][postOffice.y] < low || map[postOffice.x][postOffice.y] > high) {
            return;
        }
        visited[postOffice.x][postOffice.y] = true;
        queue.add(postOffice);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                // 범위 밖이면 무시.
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                // 방문 했으면 무시.
                if (visited[nx][ny]) {
                    continue;
                }
                // 제한된 피로도 조건에 충족시키지 못하면 무시.
                if (map[nx][ny] < low || map[nx][ny] > high) {
                    continue;
                }
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
            }

        }
    }


    static boolean complete() {
        for (Node node : houseList) {
            if (!visited[node.x][node.y]) {
                return false;
            }
        }
        return true;
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
