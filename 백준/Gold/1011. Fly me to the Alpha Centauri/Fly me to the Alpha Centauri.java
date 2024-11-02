// 01011. [G5] Fly me to the Alpha Centauri.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int TC;
    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int xK = 0, yK = 0;
            int cnt = 0;
            while (x < y) {
                x += ++xK;
                cnt++;
                if(x >= y) {
                    break;
                }
                y += --yK;
                cnt++;
            }
            bw.write(cnt + "\n");
        }
        bw.flush();
    }

}
