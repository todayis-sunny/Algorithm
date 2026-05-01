// 02285. [G4] 우체국.

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static long total = 0;
    static Village[] villages;
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        villages = new Village[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int loc = Integer.parseInt(st.nextToken());
            int peo = Integer.parseInt(st.nextToken());
            villages[n] = new Village(loc, peo);
            total += peo;
        }
    }

    static void solve() {
        Arrays.sort(villages, Comparator.comparing(Village::getLocation));
        long middle = (total + 1) / 2;
        long curr = 0;
        for (int n = 0; n < N; n++) {
            curr += villages[n].people;
            if (curr >= middle) {
                ans = villages[n].location;
                return;
            }
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Village {
        long location;
        long people;

        public Village(long location, long people) {
            this.location = location;
            this.people = people;
        }

        public long getLocation() {
            return location;
        }
    }

}
