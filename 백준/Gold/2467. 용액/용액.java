// 02467. [G5] 용액.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] solution = new int[N];
        int[] answer = new int[2];
        int min = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int sol = Integer.parseInt(st.nextToken());
            solution[i] = sol;
        }
        int left = 0;
        int right = N-1;
        while (left < right) {
            int tmp = solution[left] + solution[right];
            if (Math.abs(tmp) < min) {
                min = Math.abs(tmp);
                answer[0] = solution[left];
                answer[1] = solution[right];
            }
            if (tmp < 0) {
                if(solution[right] < 0) {
                    left = right - 1;
                    int check = solution[left] + solution[right];
                    if (Math.abs(check) < min) {
                        answer[0] = solution[left];
                        answer[1] = solution[right];
                    }
                    break;
                } else {
                    left++;
                }
            } else if (tmp > 0) {
                if(solution[left] > 0) {
                    right = left + 1;
                    int check = solution[left] + solution[right];
                    if (Math.abs(check) < min) {
                        answer[0] = solution[left];
                        answer[1] = solution[right];
                    }
                    break;
                } else {
                    right--;
                }
            } else {
                bw.write(solution[left] + " " + solution[right]);
                bw.flush();
                bw.close();
                br.close();
                return;
            }

        }
        bw.write(answer[0] + " " + answer[1]);
        bw.flush();
        bw.close();
        br.close();
    }

}