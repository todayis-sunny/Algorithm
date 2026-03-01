// 04198. [G1] 열차정렬.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] weight;
    static int[] lis, lds; // 증가하는 부분 수열, 감소하는 부분 수열
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        // 차량 무게 입력
        for (int n = 0; n < N; n++) {
            weight[n] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        // 기준이 되는 차량 하나씩 검사
        for (int n = 0; n < N; n++) {
            // 기준 열차
            int standard = weight[n];
            // 최대치가 정답이 되어도 갱신할 수 없는 경우 탈출
            if (N - n < ans) break;
            // 기준이 되는 차량을 lis, lds에 적용
            lis = new int[N - n];
            lds = new int[N - n];
            lis[0] = standard;
            lds[0] = standard;
            int front = 0; // 전면
            int rear = 0; // 후면
            // 나머지 열차들을 구성하기
            for (int i = n + 1; i < N; i++) {
                int train = weight[i];
                // 전면에 넣을수 있을때
                if (train > standard) {
                    // 바로 넣을수 있는 경우
                    if (train > lis[front]) {
                        lis[++front] = train;
                    }
                    // 중간에 넣어야 하는 경우
                    else {
                        int left = 1; // 기준 열차는 변경할 수 없기에 0이 아닌 1
                        int right = front;
                        while(left <= right) {
                            int mid = (left + right) / 2;
                            if (train > lis[mid]) {
                                left = mid + 1;
                            } else {
                                right = mid - 1;
                            }
                        }
                        lis[left] = train;
                    }
                }
                // 후면에 넣을수 있을때
                else {
                    // 바로 넣을수 있는 경우
                    if (train < lds[rear]) {
                        lds[++rear] = train;
                    }
                    // 중간에 넣어야 하는 경우
                    else {
                        int left = 1; // 기준 열차는 변경할 수 없기에 0이 아닌 1
                        int right = rear;
                        while(left <= right) {
                            int mid = (left + right) / 2;
                            if (train < lds[mid]) {
                                left = mid + 1;
                            } else {
                                right = mid - 1;
                            }
                        }
                        lds[left] = train;
                    }
                }
            }
            ans = Math.max(ans, front + rear + 1);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}

/*
 # 풀이
 - 1. 기준이 되는 열차를 하나 정함.
 - 2. 기준열차를 가지고 LIS, LDS를 구함.
 - 3. 중복되는 원소 기준열차를 제거하고 둘의 사이즈를 계산.
 */
