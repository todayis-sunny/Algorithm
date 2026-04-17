// 01148. [G5] 단어 만들기.
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static final int ADJ = 65;
    static int[][] wordsCnt;


    public static void main(String[] args) throws IOException {
        input();
        bw.flush();
    }

    static void input() throws IOException {
        wordsCnt = new int[200_000][26];
        int w; // 입력 단어 개수
        for (w = 0; ; w++) {
            String s = br.readLine();
            if (s.equals("-")) {
                break;
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int ci = (int) c - ADJ;
                wordsCnt[w][ci]++;
            }
        }
        int n; // 입력 퍼즐 개수
        for (n = 0; ; n++) {
            String s = br.readLine();
            if (s.equals("#")) {
                break;
            }
            int[] puzzle = new int[26];
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                char c = s.charAt(i);
                int ci = (int) c - ADJ;
                puzzle[ci]++;
            }


            int[] ans = new int[26];
            Arrays.fill(ans, -1);
            for (int i = 0; i < 26; i++) { // 필수 단어 인덱스
                if (puzzle[i] == 0) { // 해당 알파벳이 없으면 무시
                    continue;
                }
                int cnt = 0;
                for (int j = 0; j < w; j++) {
                    if (wordsCnt[j][i] == 0) { // 필수 알파벳이 0개라면 무시
                        continue;
                    }
                    int temp = 1;
                    for (int k = 0; k < 26; k++) { // 알파벳 개수가 퍼즐의 개수가 더 많은지 체크
                        if (puzzle[k] < wordsCnt[j][k]) { // 더 이상 점검 불필요
                            temp = 0;
                            break;
                        }
                    }
                    cnt += temp;
                }
                min = Math.min(min, cnt); // 최소 갱신
                max = Math.max(max, cnt); // 최대 갱신
                ans[i] = cnt; // 기입
            }
            for (int i = 0; i < 26; i++) {
                if (ans[i] == min) {
                    bw.write((char) (i + ADJ));
                }
            }
            bw.write(" " + min + " ");

            for (int i = 0; i < 26; i++) {
                if (ans[i] == max) {
                    bw.write((char) (i + ADJ));
                }
            }
            bw.write(" " + max);
            bw.newLine();

        }
        bw.flush();
    }

    static void solve() {

    }
}
