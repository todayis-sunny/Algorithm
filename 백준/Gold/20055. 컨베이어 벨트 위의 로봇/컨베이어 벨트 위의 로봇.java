// 20055. [G5] 컨베이어 벨트 위의 로봇.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K;
    static int inIdx, outIdx, answer;
    static int[] arr;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[2 * N];
        robot = new boolean[2 * N];
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        inIdx = 0;
        outIdx = N - 1;
        answer = 0;
        while (K > 0) {
            answer += 1;
            // 1. 벨트가 각 칸위에 있는 로봇과 함께 한 칸 회전한다.
            inIdx = inIdx == 0 ? inIdx + 2*N - 1 : inIdx - 1;
            outIdx = outIdx == 0 ? outIdx + 2*N - 1 : outIdx - 1;
            if (robot[outIdx]) {
                robot[outIdx] = false;
            }
            // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동.
            for (int i = outIdx; i > outIdx - N; i--) {
                int prev = i < 0 ? i + 2*N : i;
                int next = prev + 1 == 2*N ?  0 : prev + 1;
                if (robot[prev] && !robot[next] && arr[next] > 0) {
                    robot[prev] = false;
                    robot[next] = true;
                    arr[next] -= 1;
                    if (arr[next] == 0) {
                        K -= 1;
                    }
                }
                if (robot[outIdx]) {
                    robot[outIdx] = false;
                }
            }
            // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니라면 올리는 위치에 로봇을 올린다.
            if (arr[inIdx] > 0) {
                robot[inIdx] = true;
                arr[inIdx] -= 1;
                if (arr[inIdx] == 0) {
                    K -= 1;
                }
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }


}