// 01931. [G5] 회의실 배정.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static List<Meeting> mettingList;
    static int time, ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        mettingList = new ArrayList<>();
        ans = 0;
        time = -1;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            mettingList.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        mettingList.sort(Comparator.comparing(Meeting::getEnd).thenComparing(Meeting::getStart));
    }

    static void solve() {
        for (Meeting mt : mettingList) {
            if (mt.start < time) { // 시작시간이 과거라면 무시
                continue;
            }
            ans++;
            time = mt.end;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static class Meeting {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
