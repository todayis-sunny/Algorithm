// 02252. [G3] 줄 세우기.
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static Queue<Integer> queue;
    static int[] students;
    static ArrayList<Integer>[] member;
    static int N, M, A, B;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        students = new int[N+1];
        member = new ArrayList[N+1];
        queue = new LinkedList<>();
        for (int n = 1; n <= N; n++) {
            member[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            students[B] += 1;
            member[A].add(B);
        }

        for (int n = 1; n <= N; n++) {
            if (students[n] == 0) {
                queue.offer(n);
            }
        }

        while (!queue.isEmpty()) {
            int student = queue.poll();
            bw.write(student + " ");
            for (int i = 0; i < member[student].size(); i++) {
                students[member[student].get(i)] -= 1;
                if (students[member[student].get(i)] == 0) {
                    queue.offer(member[student].get(i));
                }
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }

}
