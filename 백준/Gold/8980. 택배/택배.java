// 08980 [G1] 택배
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, C, M; // N: 마을 개수, C: 트럭 최대 용량, M: 반복할 개수
    static int[][] village; // [f][t] 박스 개수 | f: 보내는 마을 t: 받는 마을
    static int[] receive; // 받을 박수 개수
    static int[] delivery; // 싣고 갈 개수
    static int truck = 0; // 트럭 현재 용량
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st =  new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        village = new int[N + 1][N + 1]; // 1-based
        delivery = new int[N + 1];
        receive = new int[N + 1];
        M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            st =  new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());
            village[from][to] = box;
        }
    }

    static void solve() {
        toLoop :
        for (int t = 2; t <= N; t++) { // 도착 마을 순회
            truck = 0;
            fromLoop :
            for (int f = 1; f < t; f++) { // 출발 마을 순회
                truck += delivery[f];
                truck -= receive[f];
                int box =  village[f][t]; // 박스 검사
                if (truck + box >= C) { // 싣고갈 박스가 최대 용량 초과나 같다면
                    int reducedBox = C - truck;
                    delivery[f] += reducedBox;
                    receive[t] += reducedBox;
                    truck +=  reducedBox;
                } else {
                    delivery[f] += box;
                    receive[t] += box;
                    truck +=  box;
                }
            }
            // 박스 내리기
            ans += receive[t];
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
