// 02531. [S1] 회전 초밥.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static Queue<Integer> mainQ, subQ;

    static int N, d, k, c, answer, count;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int[] sushiType = new int[d+1];
        mainQ = new LinkedList<>();
        subQ = new LinkedList<>();
        count = 0;
        answer = 0;
        for(int i = 1; i <= k; i++) {
            int sushi = Integer.parseInt(br.readLine());
            count = sushiType[sushi]++ == 0? count + 1 : count;
            int tmp = sushiType[c] == 0? count+1 : count;
            answer = Math.max(answer, tmp);
            mainQ.offer(sushi);
            subQ.offer(sushi);
        }
        for(int i = k+1; i <= N; i++) {
            int outSushi = mainQ.poll();
            count = sushiType[outSushi]-- == 1? count - 1 : count;
            int inSushi = Integer.parseInt(br.readLine());
            mainQ.offer(inSushi);
            count = sushiType[inSushi]++ == 0? count + 1 : count;
            int tmp = sushiType[c] == 0? count+1 : count;
            answer = Math.max(answer, tmp);
        }
        for(int i = N+1; i <= N+k-1; i++) {
            int outSushi = mainQ.poll();
            count = sushiType[outSushi]-- == 1? count - 1 : count;
            int inSushi = subQ.poll();
            mainQ.offer(inSushi);
            count = sushiType[inSushi]++ == 0? count + 1 : count;
            int tmp = sushiType[c] == 0? count+1 : count;
            answer = Math.max(answer, tmp);
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

}