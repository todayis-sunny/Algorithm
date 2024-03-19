// 02473. [G3] 세 용액.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        long[] solution = new long[N];
        long[] answer = new long[3];
        long min = Long.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long sol = Long.parseLong(st.nextToken());
            solution[i] = sol;
        }
        Arrays.sort(solution);
        
        for (int fix = 0; fix < N-2; fix++) {
        	
        	int left = fix+1;
            int right = N-1;
            while (left < right) {
                long tmp = solution[fix] + solution[left] + solution[right];
                if (Math.abs(tmp) < min) {
                    min = Math.abs(tmp);
                    answer[0] = solution[fix];
                    answer[1] = solution[left];
                    answer[2] = solution[right];
                }
                if (tmp < 0) {
                    left++;
                } else if (tmp > 0) {
                    right--;
                } else {
                    bw.write(solution[fix] + " " + solution[left] + " " + solution[right]);
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }

            }
            
        }
        bw.write(answer[0] + " " + answer[1] + " " + answer[2]);
        bw.flush();
        bw.close();
        br.close();
          
    }

}