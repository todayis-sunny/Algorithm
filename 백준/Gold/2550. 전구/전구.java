// 02550. [G3] 전구.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, cnt;
    static int[] button, bulb, idxMem; // 스위치, 전구

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        button = new int[N+1]; // 스위치
        bulb = new int[N+1]; // 전구
        idxMem = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int btnNo = Integer.parseInt(st.nextToken());
            button[i] = btnNo;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int bulbNo = Integer.parseInt(st.nextToken());
            bulb[bulbNo] = i; // 전구의 위치를 저장.
        }

        ArrayList<Integer> data = new ArrayList<>();
        data.add(0); // 0으로 기준을 잡고 시작.
        cnt = 0;
        for (int i = 1; i <= N; i++) { // 순차적으로 버튼을 눌러봄
            int btn = button[i]; // 해당 순서의 버튼의 번호
            int idx = bulb[btn]; // 해당 스위치 전구의 위치
            if(idx > data.get(cnt)) { // 큰게 들어가면 그냥 진행
                data.add(idx);
                cnt++;
                idxMem[i] = cnt;
            } else { // 이분탐색 진행.
                int left = 0;
                int right = cnt;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    int find = data.get(mid);
                    if(idx > find) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                idxMem[i] = left;
                data.set(left, idx);
            }
        }
        ArrayList<Integer> answer = new ArrayList<>();
        int limit = cnt;
        bw.write(cnt + "\n");
        for (int i = N; i > 0 ; i--) {
            if(idxMem[i] == limit) {
                answer.add(button[i]);
                limit--;
            }
            if(limit == 0) {
                break;
            }
        }
        Collections.sort(answer);
        for (Integer integer : answer) {
            bw.write(integer + " ");
        }
        bw.flush();
        bw.close();
        br.close();

    }

}