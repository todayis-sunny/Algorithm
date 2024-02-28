// 12886. [G4] 돌 그룹.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static Queue<Stone> queue;
    static boolean[][] visited = new boolean[1001][1001];
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int sum = A + B + C;
        if(sum % 3 != 0) {
            bw.write("0");
            bw.flush();
            bw.close();
            br.close();
            return;
        }
        if(A == B && B == C) {
            bw.write("1");
            bw.flush();
            bw.close();
            br.close();
            return;
        }
        queue = new LinkedList<>();

        queue.offer(new Stone(A, B));
        visited[A][B] = true;
        visited[B][A] = true;
        while (!queue.isEmpty()) {
            Stone stone = queue.poll();
            int a = stone.A;
            int b = stone.B;
            int c = sum - (a + b);
            if(a == b && b == c) {
                bw.write("1");
                bw.flush();
                bw.close();
                br.close();
                return;
            }

            if(a != b) {
                int aa = a > b ? a-b : a+a;
                int bb = b > a ? b-a : b+b;
                if(aa <= 1000 && bb <= 1000 && !visited[aa][bb]) {
                    visited[aa][bb] = true;
                    visited[bb][aa] = true;
                    queue.offer(new Stone(aa, bb));
                }
            }
            if(a != c) {
                int aa = a > c ? a-c : a+a;
                int cc = c > a ? c-a : c+c;
                if(aa <= 1000 && cc <= 1000 && !visited[aa][cc]) {
                    visited[aa][cc] = true;
                    visited[cc][aa] = true;
                    queue.offer(new Stone(aa, cc));
                }
            }
            if(b != c) {
                int bb = b > c ? b-c : b+b;
                int cc = c > b ? c-b : c+c;
                if(cc <= 1000 && bb <= 1000 && !visited[bb][cc]) {
                    visited[bb][cc] = true;
                    visited[cc][bb] = true;
                    queue.offer(new Stone(bb, cc));
                }
            }
        }
        bw.write("0");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Stone{
        int A;
        int B;
        Stone(int A, int B){
            this.A = A;
            this.B = B;
        }
    }
}