import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuffer sb;

    static WireLine[] utilityPole;
    static int[] idxMemo, arr;
    static int N, A, B, ans;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        utilityPole = new WireLine[N];
        idxMemo = new int[N];
        arr = new int[N+1];  // 전깃줄을 조정해줄 arr : 0을 넣어서 초기값, 길이가 1증가된 상태.
        ans = 0;
        // 입력받기.
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            utilityPole[n] = new WireLine(A, B);
        }
        // 좌측기준 정렬.
        Arrays.sort(utilityPole, Comparator.comparing(WireLine::getLeft));

        for (int n = 0; n < N; n++) {
            int curr = utilityPole[n].right;
            // 마지막 전깃줄 비교.
            if (curr > arr[ans]) { // 크다면 ans 증가, 마지막 값 추가.
                arr[++ans] = curr;
                idxMemo[n] = ans;
            } else { // 작다면 이분탐색으로 값 최적화.
                int left = 0;
                int right = ans;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if(curr > arr[mid]) {
                        left = mid + 1;
                    } else if (curr < arr[mid]) {
                        right = mid - 1;
                    } else {
                        break;
                    }
                }

                idxMemo[n] = left;
                arr[left] = curr;
            }
        }

        int limit = ans;
        sb = new StringBuffer();
        sb.append(N - ans).append("\n");
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();


    }



    static class WireLine{
        int left, right;

        public WireLine(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int getLeft() {
            return left;
        }
    }

}