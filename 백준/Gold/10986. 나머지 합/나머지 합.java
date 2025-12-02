// 10986. [G3] 나머지 합.

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static long result;

    static int[] count;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // mod의 개수
        count = new int[M];
        // 1-based이므로 초기 0의 개수를 1로 설정(index 0에 원소 0 존재)
        count[0] = 1;
        int mod = 0;
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            mod += Integer.parseInt(st.nextToken());
            count[mod %= M]++;
        }
    }

    static void solve() {
        // Combi(cnt, 2)
        for (int mod = 0; mod < M; mod++) {
            result += ((long) count[mod] * (count[mod] - 1)) / 2;
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(result));
        bw.flush();
    }
}

/*
 # 접근 방법
 누적합을 이용해서 psum[j] % M == psum[i-1] % M을 찾는다.

 # 풀이 방법
 맨처음에 항상 0이라는 원소가 있다고 생각한다.
 (N + 1)의 수에서 연속된 부분 구간의 합을 찾는다고 했을때, (i, j)의 순서쌍은 자연스럽게 1 <= i <= j <= N + 1이 된다.
 따라서 누적합 psum[0 - 1]을 구하는 구간을 없으므로 기존 맨처음 원소부터 j까지의 합도 이용할수 있다. (0, j)를 (1, j`+1)의 꼴로 표현
 */
