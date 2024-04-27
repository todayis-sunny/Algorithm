// 02623. [G3] 음악프로그램.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, PD, count;
    static StringBuilder sb = new StringBuilder();
    static int[][] arr;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        PD = Integer.parseInt(st.nextToken());
        count = 0;
        arr = new int [N+1][N+1];
        for (int pd = 1; pd <= PD; pd++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for (int i = 1; i < k; i++) {
                int next = Integer.parseInt(st.nextToken());
                arr[prev][next]++;
                arr[next][0]++;
                prev = next;
            }
        }
        queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (arr[i][0] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int singer = queue.poll();
            count++;
            sb.append(singer).append("\n");
            for(int s = 1; s <= N; s++) {
                if(arr[singer][s] == 0) {
                    continue;
                }
                arr[s][0] -= arr[singer][s];
                if(arr[s][0] == 0) {
                    queue.offer(s);
                }
            }
        }
        if (count == N) {
            bw.write(sb.toString());
        } else {
            bw.write("0");
        }
        bw.flush();
        br.close();
        bw.close();

    }

}