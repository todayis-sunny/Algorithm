import java.util.*;
import java.io.*;

// 01029. [G1] 그림교환.
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans;
    static int[][] bitmask;
    static int[][] price;
    /*
    1. 그림을 팔 때, 그림을 산 가격보다 크거나 같은 가격으로 팔아야 한다.
    2. 같은 그림을 두번 이상 사는 것은 불가능하다. ->
    */
    
    public static void main(String[] args) throws IOException {
        input();
        ans = 1;
        ans += tsp(0, 1, 0); // 0에서 출발, 0은 방문, 0의 코스트
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        bitmask = new int[N][1 << N];
        price = new int[N][N];
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int i = 0; i < N; i++) {
                price[n][i] = input.charAt(i) - '0';
                
            }
        }
    }
    
    static int tsp(int prev, int bit, int cost) {
        if (bitmask[prev][bit] != 0) {
            return bitmask[prev][bit];
        }

        int cnt = 0;
        for (int next = 0; next < N; next++) {
            if((bit & (1 << next)) != 0) { // next가 그림을 만져봄
                continue;
            }
            if(cost > price[prev][next]) { // next가 비용때문에 못 만짐
                continue;
            }
            cnt = Math.max(cnt, tsp(next, (bit | (1 << next)), price[prev][next]) + 1);
        }
        return bitmask[prev][bit] = cnt;
    }
}
