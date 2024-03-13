// 15663. [S2] N과 M (9)
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static HashSet<Integer> hashSet = new HashSet<>();
    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    static int[] count;
    static List<Integer> list;
    static int N, M;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(st.nextToken());
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
            hashSet.add(num);
        }
        list = new ArrayList<>(hashSet);
        list.sort(Integer::compareTo);
        count = new int[list.size()];
        dfs("", 0);
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(String answer, int cnt) throws IOException {
        if (cnt == M) {
            bw.write(answer);
            bw.newLine();
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (count[i] < hashMap.get(list.get(i))) {
                count[i]++;
                dfs(answer + list.get(i) + " " ,cnt + 1);
                count[i]--;
            }
        }
    }
}