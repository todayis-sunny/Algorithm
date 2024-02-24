// 03000. [diff_4] 중간값 구하기.
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int DIV_NUM = 20171109;

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        int[] board = new int[5];

        for (int tc = 1; tc <= TC; tc++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            int[] tmp = new int[] {num, num1, num2};
            Arrays.sort(tmp);
            PriorityQueue<Integer> leftPQ = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> rightPQ = new PriorityQueue<>();
            leftPQ.offer(tmp[0]);
            int mid = tmp[1];
            rightPQ.offer(tmp[2]);
            int answer = mid;
            answer %= DIV_NUM;
            for (int n = 1; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                board[0] = leftPQ.poll();
                board[1] = a;
                board[2] = mid;
                board[3] = b;
                board[4] = rightPQ.poll();
                Arrays.sort(board);
                leftPQ.offer(board[0]);
                leftPQ.offer(board[1]);
                mid = board[2];
                rightPQ.offer(board[3]);
                rightPQ.offer(board[4]);
                answer += mid;
                answer %= DIV_NUM; 
            }
            bw.write("#" + tc + " " + answer + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}