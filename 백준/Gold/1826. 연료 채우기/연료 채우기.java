// 01826. [G2] 연료 채우기.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[][] arr;
    static PriorityQueue<Integer> pq;
    static int ans;
    public static void main(String[] args) throws NumberFormatException, IOException {
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1][2];
        for (int n = 0; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            arr[n][0] = Integer.parseInt(st.nextToken());
            arr[n][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        ans = 0;
        int fuel = arr[N][1]; // 현재 가지고 있는 연료.
        int location = 0;
        boolean flag = true;
        pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i <= N; i++) {
            fuel -= arr[i][0] - location;
            location = arr[i][0];
            while(fuel < 0) {
                if(!pq.isEmpty()) {
                    ans += 1;
                    fuel += pq.poll();
                } else {
                    ans = -1;
                    bw.write(String.valueOf(ans));
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
            }
            pq.offer(arr[i][1]);
        }
        bw.write(String.valueOf(ans));
        bw.flush();
    }

}
