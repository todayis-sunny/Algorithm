// 02517. [P4] 달리기.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        List<Runner> runners = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            runners.add(new Runner(n, Integer.parseInt(br.readLine())));
        }
        runners.sort(Comparator.comparing(Runner::getSkill));
        for (int n = 0; n < runners.size(); n++) {
            Runner runner = runners.get(n);
            runner.skill = n + 1;
        }
        runners.sort(Comparator.comparing(Runner::getPlayer));
        tree = new int[N+1];
        for (int n = 1; n <= N; n++) {
            int skill = runners.get(n-1).skill;
            bw.write(n - sum(skill-1) + "\n");
            update(skill, 1);
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int sum(int player) {
        int result = 0;
        while (player > 0) {
            result += tree[player];
            player -= (player & -player);
        }
        return result;
    }

    static void update(int player, int val) {
        while (player <= N) {
            tree[player] += val;
            player += (player & -player);
        }
    }

    static class Runner{
        int player;
        int skill;

        public Runner(int player, int skill) {
            this.player = player;
            this.skill = skill;
        }

        public int getPlayer() {
            return player;
        }

        public int getSkill() {
            return skill;
        }
    }
}