// 15666. [S2] N과 M (12)
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static HashSet<Integer> hashSet = new HashSet<>();
    static List<Integer> list;
    static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(st.nextToken());
            hashSet.add(num);
        }
        list = new ArrayList<>(hashSet);
        list.sort(Integer::compareTo);

        dfs("", 0,0);
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(String answer, int idx, int cnt) throws IOException {
        if (cnt == M) {
            bw.write(answer);
            bw.newLine();
            return;
        }
        for (int n = idx; n < list.size(); n++) {
            dfs(answer + list.get(n) + " ", n,cnt + 1);
        }
    }
}