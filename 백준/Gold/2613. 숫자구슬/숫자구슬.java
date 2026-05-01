// 02613. [G2] 숫자구슬.

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] bead, group;
    static int maxBead = 0;
    static Queue<Integer> groupQueue = new LinkedList<>();
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }


    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bead = new int[N];
        group = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            bead[n] = Integer.parseInt(st.nextToken());
            maxBead = Math.max(maxBead, bead[n]);
        }
    }

    static void solve() {
        int left = maxBead;
        int right = 300 * 300;
        parametricSearch:
        while (left <= right) {
            int mid = (left + right) / 2;
            groupQueue.clear();
            int currVal = 0; // 현재 조합의 값
            int currCnt = 0; // 현재 조합의 개수
            // 구슬이 있을때까지 반복
            for (int i = 0; i < N;) {
                // 구슬을 합칠수 없을때,
                if (currVal + bead[i] > mid) {
                    // 현재까지 조합한걸 넣기
                    groupQueue.offer(currCnt);
                    currVal = 0;
                    currCnt = 0;
                }
                // 구슬을 합칠수 있을때,
                else {
                    // 남은 구슬이 남은 그룹수랑 같아질 때,
                    if (N - i == M - groupQueue.size()) {
                        groupQueue.offer(++currCnt);
                        i++;
                        currVal = 0;
                        currCnt = 0;
                    } else {
                        currVal += bead[i++];
                        currCnt++;
                    }

                }
            }
            // 마지막 조합한게 있다면
            if (currCnt > 0) {
                groupQueue.offer(currCnt);
            }
            // 그룹의 수가 이하인 경우
            if (groupQueue.size() <= M) {
                result = mid;
                int i = 0;
                while (!groupQueue.isEmpty()) {
                    group[i++] = groupQueue.poll();
                }
                right = mid - 1;
            }
            // 그룹의 수가 초과한 경우
            else {
                left = mid + 1;
            }

        }
    }

    static void output() throws IOException {
        bw.write(result + "\n");
        for (int cnt: group) {
            bw.write(cnt + " ");
        }
        bw.flush();
    }
}
