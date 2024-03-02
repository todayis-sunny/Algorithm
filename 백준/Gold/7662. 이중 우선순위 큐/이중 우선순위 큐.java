// 07662. [G4] 이중 우선순위 큐.
import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    static String answer;
    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            int k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                if (cmd.equals("I")) {
                    int num = Integer.parseInt(st.nextToken());
                    treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
                }
                if (cmd.equals("D")) {
                    if (!treeMap.isEmpty()) {
                        int num = Integer.parseInt(st.nextToken()) == 1? treeMap.lastKey() : treeMap.firstKey();
                        if (treeMap.put(num, treeMap.get(num) - 1) == 1) {
                            treeMap.remove(num);
                        }
                    }
                }
            }
            if (treeMap.isEmpty()) {
                answer = "EMPTY";
            } else {
                answer = treeMap.lastKey() + " " + treeMap.firstKey();
            }
            bw.write(answer + "\n");
            treeMap.clear();
        }
        bw.flush();
        bw.close();
        br.close();
    }

}