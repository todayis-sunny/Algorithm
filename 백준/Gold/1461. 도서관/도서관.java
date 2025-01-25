// 01461. [G4] 도서관.

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int ans;
    static List<Integer> bookShelf1, bookShelf2;
    static int bs1L, bs2L;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void input() throws IOException {
        ans = 0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bookShelf1 = new ArrayList<>();
        bookShelf2 = new ArrayList<>();
        bookShelf1.add(0);
        bookShelf2.add(0);
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int book = Integer.parseInt(st.nextToken());
            if (book < 0) {
                bookShelf1.add(-book); // 음수는 양수로 변환
            } else {
                bookShelf2.add(book);
            }
        }

        bs1L = bookShelf1.size();
        bs2L = bookShelf2.size();
        bookShelf1.sort(Collections.reverseOrder());
        bookShelf2.sort(Collections.reverseOrder());
    }

    static void solve() {
        int bs1, bs2;
        for (bs1 = 0; bs1 < bs1L; bs1 += M) {
            ans += 2 * bookShelf1.get(bs1);
        }
        for (bs2 = 0; bs2 < bs2L; bs2 += M) {
            ans += 2 * bookShelf2.get(bs2);
        }
        ans -= Math.max(bookShelf1.get(0), bookShelf2.get(0));
    }
}
