// 19539. [G5] 사과나무.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int total, one, two;
    /*
    0 -> (0, 0)
    1 -> (0, 1)
    2 -> (0, 2) (1, 0)
    3 -> (0, 3) (1, 1)
    4 -> (0, 4) (1, 2) (2, 0)
    5 -> (0, 5) (1, 3) (2, 1)
    6 -> (0, 6) (1, 4) (2, 2) (3, 0)

     */
    public static void main(String[] args) throws IOException {
        input();
        /*
        1. total의 3의 배수가 아니면
        2. 능력 1의 물뿌리개가 더 많으면
        3. 능력 2의 물뿌리개를 능력 1의 물뿌리개만큼 빼서 필요한 능력을 구하고 3의 배수가 아니면
        불가능
        나머지는 가능
         */
        if (total % 3 != 0 || one > two || ((two - one))* 2 % 3 != 0) {
            bw.write("NO");
        } else {
            bw.write("YES");
        }
        bw.flush();

    }

    static void input() throws IOException {
        br.readLine();
        st = new StringTokenizer(br.readLine());
        total = 0;
        while (st.hasMoreTokens()) {
            int height = Integer.parseInt(st.nextToken());
            total += height;
            if (height % 2 == 1) {
                one++;
            }
            two += height / 2;

        }
    }
}
