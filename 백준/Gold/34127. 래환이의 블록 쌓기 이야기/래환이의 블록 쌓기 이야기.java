import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static boolean possible = true;
    static int N;
    static int[] building; // 1-based
    static long[] result; // 1-based


    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        building = new int[N + 1];
        result = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            building[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // 높이가 이미 오름차순인 경우 체크
        long prev = -1; // 이전 높이 기준치 : 1-based이므로 0번 인덱스는 0 죽 -1부터 하면 선형적
        keep:
        for (; true; ) {
            for (int height : building) {
                // 오름차순이 아닌 경우 탈출
                if (height <= prev) break keep;
                prev = height;
            }
            return;
        }
        // 높이가 오름차순이 아닌 경우
        long total = 0; // 변화량
        for (int i = 1; i <= N - 1; i++) {
            prev = building[i - 1] + result[i - 1];
            // 빌딩이 이미 큰 경우
            if (building[i] > prev) {
                // 1차이인 경우 스킵
                if (building[i] == prev + 1) continue;
                    // 2차이인 경우
                else if (building[i] == prev + 2) {
                    // 짝수만큼 떼줄수 있는 경우
                    if (i % 2 == 0) {
                        continue; // 떼줄수 없음을 의미
                    }
                    // 홀수만큼 떼줄수 있는 경우
                    else {
                        total -= 1;
                        result[i] = -1;
                    }
                }
                // 3차이 이상인 경우
                else {
                    // prev + 1
                    long increment = (prev + 1) - building[i];
                    // (짝 인덱스, 짝 증감) or (홀 인덱스 , 홀 증감) : xor 연산하여 0번째 비트가 0이면
                    if (((i ^ Math.abs(increment)) & 1) == 0) {
                        total += increment;
                        result[i] = increment;
                    }
                    // prev + 2
                    else {
                        total += increment + 1;
                        result[i] = increment + 1;
                    }

                }
            }
            // 빌딩이 작은 경우
            else {
                long increment = (prev + 1) - building[i];
                // (짝 인덱스, 짝 증감) or (홀 인덱스 , 홀 증감) : xor 연산하여 0번째 비트가 0이면
                if (((i ^ Math.abs(increment)) & 1) == 0) {
                    total += increment;
                    result[i] = increment;
                }
                // prev + 2
                else {
                    total += increment + 1;
                    result[i] = increment + 1;
                }
            }
        }
        // 마지막 건물에 증감 적용
        prev = building[N - 1] + result[N - 1];
        // (짝 인덱스, 짝 증감) or (홀 인덱스 , 홀 증감) : xor 연산하여 0번째 비트가 0이면
        if (total == 0) {
           result[N] = 0;
        }
        else if (((N ^ Math.abs(total)) & 1) == 0) {
            result[N] = (-total);
        } else {
            result[N] = (-(total + 1));
        }
        if (building[N] + result[N] > prev) return;
        possible = false;
    }

    static void output() throws IOException {
        if (!possible) {
            bw.write("NO");
        } else {
            bw.write("YES\n");
            for (int i = 1; i <= N; i++) {
                sb.append(result[i]).append(" ");
            }
            bw.write(sb.toString());
        }
        bw.flush();
    }
}
