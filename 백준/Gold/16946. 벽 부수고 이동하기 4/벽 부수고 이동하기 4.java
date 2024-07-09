// 16946. [G2] 벽 부수고 이동하기 4.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, M;
    static int[][] arr, answer;
    static boolean[][] wall;

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        wall = new boolean[N][M];
        answer = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                int ip = Character.getNumericValue(input.charAt(j));
                if(ip == 1) {
                    wall[i][j] = true;
                }
                arr[i][j] = ip;
            }
        }
        ArrayList<Integer> counts = new ArrayList<>();
        counts.add(0);
        counts.add(0);

        int key = 2;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    counts.add(bfs(i, j, key++));
                }
            }
        }
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (wall[x][y]) { // 벽이라면,
                    Set<Integer> set = new HashSet<>();
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            continue;
                        }
                        if (wall[nx][ny]) {
                            continue;
                        }
                        set.add(arr[nx][ny]);
                    }
                    int tmp = 1;
                    for (int element: set) {
                        tmp += counts.get(element);
                    }
                    answer[x][y] = tmp % 10;
                } else { // 벽이 아니라면,
                    answer[x][y] = 0;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(String.valueOf(answer[i][j]));
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }

    static int bfs(int x, int y, int key) {
        int cnt = 0;
        Queue<Node> q = new LinkedList<>();
        arr[x][y] = key;
        q.offer(new Node(x, y));
        cnt++;
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M ) {
                    continue;
                }
                if (arr[nx][ny] != 0) {
                    continue;
                }
                arr[nx][ny] = key;
                q.offer(new Node(nx, ny));
                cnt++;
            }
        }
        return cnt % 10;
    }

    static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}