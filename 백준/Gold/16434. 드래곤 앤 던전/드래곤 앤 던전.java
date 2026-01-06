// 16434. [G4] 드래곤 앤 던전.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int DG_MONSTER = 1, DG_BUFF = 2;
    static int N, H;
    static Dungeon[] dungeons;
    static Hero hero;
    static long ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        dungeons = new Dungeon[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            dungeons[n] = new Dungeon(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        long left = 1, right = Long.MAX_VALUE - 1;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (savePrincess(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        ans = left;
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static boolean savePrincess(long maxHp) {
        hero = new Hero(maxHp, H);
        for (Dungeon dg : dungeons) {
            switch (dg.t) {
                case DG_MONSTER:
                    // 필요한 공격 횟수 (올림 처리)
                    long attackCount = (dg.h + hero.attack - 1)  / hero.attack;
                    // 용사가 N번 공격하면 클리어 가능할때, 몬스터의 N-1번 공격을 버텨낼수 있는지 체크
                    long totalDamage = (attackCount - 1) * dg.a;
                    if (hero.currHp <= totalDamage) return false;
                    // 버텨냈다면 그만큼 체력 감소
                    hero.currHp -= totalDamage;
                    break;
                case DG_BUFF:
                    hero.attack += dg.a;
                    hero.currHp = Math.min(hero.currHp + dg.h, hero.maxHp);
                    break;
            }
        }
        return true;
    }

    static class Dungeon {
        int t, a, h;

        public Dungeon(int t, int a, int h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }

    static class Hero {
        long maxHp; // 용사의 최대 체력
        long currHp; // 용사의 현재 체력
        long attack; // 용사의 공격력

        public Hero(long maxHp, long attack) {
            this.maxHp = maxHp;
            this.currHp = maxHp; // 현재 체력을 최대 체력으로 초기화
            this.attack = attack;
        }
    }
}
