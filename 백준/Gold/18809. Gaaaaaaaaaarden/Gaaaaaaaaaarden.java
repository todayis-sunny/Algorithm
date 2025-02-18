// 18809. [G1] Gaaaaaaaaaarden.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, G, R, LIMIT; // N: 열, M: 행, G: 녹색, R: 적색, LIMIT: G+R
    static final int LAKE = 0, IMPOSSIBLE = 1, POSSIBLE = 2;
    static final int GREEN = 3, RED = 4, FLOWER = 5;
    static int[][] garden; // 0: 호수, 1: 땅에 베양액 X, 2: 땅에 배양액 O, 3: 녹색 배양액, 4: 적색 배양액, 5: 꽃
    static List<Land> possibleLands = new ArrayList<>(); // 베양액 뿌릴수 있는곳
    static boolean[] selectedPossibleLands; // 조합 생성에 필요한 check 배열
    static Land[] selectedLands; // 배양액 뿌릴곳으로 선정된 G+R개의 땅 배열
    static boolean[] selectedGreen; // 선정된 G+R개의 땅 배열에 어떤 배양액을 뿌렸는지 (ture: 녹색, false: 적색)
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        LIMIT = G + R;
        garden = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                garden[n][m] = Integer.parseInt(st.nextToken());
                if (garden[n][m] == POSSIBLE) {
                    possibleLands.add(new Land(n, m, POSSIBLE));
                }
            }
        }
        selectedPossibleLands = new boolean[possibleLands.size()];
        selectedLands = new Land[G + R];
        selectedGreen = new boolean[G + R];
    }

    static void solve() {
        selectLand(0, 0);
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Land {
        int x, y;
        int state;

        Land(int x, int y, int state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }
    // 배양액을 뿌릴수 있는 땅 R+G개 선택 메서드
    static void selectLand(int depth, int idx) {
        if (depth == LIMIT) {
            selectGreen(0, 0);
            return;
        }

        for (int i = idx; i < possibleLands.size(); i++) {
            selectedPossibleLands[i] = true;
            selectedLands[depth] = possibleLands.get(i);
            selectLand(depth + 1, i + 1);
            selectedPossibleLands[i] = false;
        }
    }
    // R+G개의 땅 중 R을 심을 땅 조합 메서드
    static void selectGreen(int depth, int idx) {
        if (depth == G) {
            ans = Math.max(ans, bfs());
            return;
        }

        for (int i = idx; i < selectedLands.length; i++) {
            selectedGreen[i] = true;
            selectGreen(depth + 1, i + 1);
            selectedGreen[i] = false;
        }
    }
    /** 메인로직: 배양액 퍼트려 만들수 있는 꽃의 개수 리턴 메서드 */
    static int bfs() {
        int[][] tmpGarden = copyGarden();
        int[][] time = new int[N][M];
        Queue<Land> q = new LinkedList<>();

        // 1. 녹색 배양액
        for (int i = 0; i < selectedLands.length; i++) {
            Land land = selectedLands[i];
            if (selectedGreen[i]) {
                q.offer(new Land(land.x, land.y, GREEN));
                tmpGarden[land.x][land.y] = GREEN;
            }
        }

        // 2. 적색 배양액
        for (int i = 0; i < selectedLands.length; i++) {
            Land land = selectedLands[i];
            if (!selectedGreen[i]) {
                q.offer(new Land(land.x, land.y, RED));
                tmpGarden[land.x][land.y] = RED;
            }
        }

        int cnt = 0;
        while (!q.isEmpty()) {
            Land curr = q.poll();
            // 현재 위치에 꽃이 자랐으면 무시
            if (tmpGarden[curr.x][curr.y] == FLOWER) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (outOfGarden(nx, ny)) {
                    continue;
                }
                if (validate(nx, ny, tmpGarden)) {

                    if (curr.state == GREEN) {
                        tmpGarden[nx][ny] = GREEN;
                    } else {
                        tmpGarden[nx][ny] = RED;
                    }
                    q.offer(new Land(nx, ny, curr.state));
                    time[nx][ny] = time[curr.x][curr.y] + 1;
                } else if (curr.state == RED && tmpGarden[nx][ny] == GREEN && time[nx][ny] == time[curr.x][curr.y] + 1) {
                    // 현재 배양액 적색, 다음 지점에 녹색, 동시에 도달시
                    tmpGarden[nx][ny] = FLOWER;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /**
     * 가든의 밖인지 확인하는 메서드
     */
    static boolean outOfGarden(int x, int y) {
        return (x < 0 || x >= N || y < 0 || y >= M);
    }

    /**
     * 배양액이 퍼질수 있는지 확인하는 메서드
     */
    static boolean validate(int x, int y, int[][] gd) {
        return gd[x][y] == IMPOSSIBLE || gd[x][y] == POSSIBLE;
    }

    static int[][] copyGarden() {
        int[][] tmpGarden = new int[N][M];
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                tmpGarden[n][m] = garden[n][m];
            }
        }
        return tmpGarden;
    }
}
