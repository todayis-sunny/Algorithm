// 01671. [P3] 상어의 저녁식사.

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans;
    static List<List<Integer>> canEatList = new ArrayList<>();
    static Shark[] sharks;
    static int[] eat;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        sharks = new Shark[N];
        eat = new int[N];
        Arrays.fill(eat, -1);
        check = new boolean[N];
        for (int n = 0; n < N; n++) {
            canEatList.add(new ArrayList<>());
        }
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int iq = Integer.parseInt(st.nextToken());
            sharks[n] = new  Shark(size, speed, iq);
        }
    }

    static void solve() {
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // i상어가 j상어를 먹을 수 있음
                if (sharks[i].canEat(sharks[j])) {
                    canEatList.get(i).add(j);
                }
                // j상어가 i상어를 먹을 수 있음
                else if (sharks[j].canEat(sharks[i])) {
                    canEatList.get(j).add(i);
                }
            }
        }
        ans = N;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(check, false);
                if (dfs(i)) ans--;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static boolean dfs(int curr) {
        for (int next : canEatList.get(curr)) {
            if (check[next]) continue;
            check[next] = true;
            if (eat[next] == -1 || dfs(eat[next])) {
                eat[next] = curr;
                return true;
            }
        }
        return false;
    }

    static class Shark {
        int size, speed, iq;

        public Shark(int size, int speed, int iq) {
            this.size = size;
            this.speed = speed;
            this.iq = iq;
        }

        public boolean canEat(Shark shark) {
            return this.size >= shark.size && this.speed >= shark.speed && this.iq >= shark.iq;
        }
    }
}
