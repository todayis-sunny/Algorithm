// 12101. [S1] 1, 2, 3 더하기 2
import java.io.*;
import java.util.ArrayList;


public class Main {
    static int n;
    static int k;
    static int[] dp = new int[12];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");
        ArrayList<Integer> answer = new ArrayList<>();
        n = Integer.parseInt(tokens[0]);
        k = Integer.parseInt(tokens[1]);

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < 12; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        int total = 0;
        int number = n;
        int idx = k;

        if (dp[n] < k) {
            bw.write("-1");
            bw.flush();
            bw.close();
            return;
        }
        while (total != n) {

            for (int d = 1; d <= 3; d++) {
                if (number == 1) {
                    answer.add(1);
                    total += 1;
                    break;
                }
                if (number == 2 && idx == 2) {
                    answer.add(2);
                    total += 2;
                    break;
                }

                if (number == 3 && idx == 4){
                    answer.add(3);
                    total += 3;
                    break;
                }
                if (idx <= dp[number - d]) {
                    answer.add(d);
                    total += d;
                    number -= d;
                    break;
                } else {
                    idx -= dp[number - d];
                }
            }
        }
        for(int i = 0; i < answer.size(); i++){
            bw.write(String.valueOf(answer.get(i)));
            if (i != answer.size()-1){
                bw.write("+");
            }

        }
        bw.flush();
        bw.close();
    }
}
