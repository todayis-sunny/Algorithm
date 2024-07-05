// 15684. [G3] 사다리 조작.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, H, a, b, ans;
    static int[][] ladders;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladders = new int[H+1][N+1];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            // 사다리 두기.
            ladders[a][b] = 1;
            ladders[a][b+1] = -1;
//            // 사다리 갯수 증가.
//            ladders[0][b] += 1;
//            ladders[0][b+1] += 1;
        }

//        for (int i = 1; i <= H; i++) {
//            for (int j = 1; j <= N; j++) {
//                System.out.print(ladders[i][j] + "");
//            }
//            System.out.println();
//        }
        ans = -1;
        for (int l = 0; l <= 3; l++) {
            dfs(l, 0, 1, 1);
            if (ans != -1) {
                break;
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int limit, int depth, int x, int y) {
        if (ans != -1){
            return;
        }

        if (limit == depth) {
            if (run()) {
                ans = limit;
            }
            return;
        }
        for (int i = x; i <= H ; i++) {
            for (int j = y; j < N; j++) {
                if(valid(i, j)) {
                    install(true, i, j);
                    dfs(limit, depth+1, 1, 1);
                    install(false, i, j);
                }
            }
        }
    }

    public static boolean valid(int x, int y) {
//        // 가로 사다리 갯수
//        if (!(ladders[0][y] < H)) {
//            return false;
//        }
        // 양옆의 사다리 유무
//        if (y == 1) {
//            return ladders[x][2] == 0;
//        } else {
            return ladders[x][y] == 0 && ladders[x][y + 1] == 0;
//        }
    }

    public static void install(boolean key, int x, int y) {
        // key가 true면 설치, false면 제거.
        if (key) {
            ladders[x][y] = 1;
            ladders[x][y+1] = -1;
//            ladders[0][y] += 1;
//            ladders[0][y+1] += 1;
        } else {
            ladders[x][y] = 0;
            ladders[x][y+1] = 0;
//            ladders[0][y] -= 1;
//            ladders[0][y+1] -= 1;
        }
    }

    public static boolean run() {
        for (int i = 1; i <= N; i++) {
            int start = i;
            for (int j = 1; j <= H ; j++) {
                start += ladders[j][start];
            }
            if (start != i) {
                return false;
            }
        }
        return true;
    }
}