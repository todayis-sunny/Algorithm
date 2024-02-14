// [SWEA] 01209. Sum
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		for(int tc = 1; tc <= 10; tc++) {
			int[] row = new int[100];
			int[] col = new int[100];
			int[] dia = new int[2];
			br.readLine();
			for(int r = 0; r < 100; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < 100; c++) {
					int tmp = Integer.parseInt(st.nextToken());
					row[r] += tmp;
					col[c] += tmp;
					if(r == c) {
						dia[0] += tmp;
					}
					if(r+c == 99) {
						dia[1] += tmp;
					}
				}
			}
			Arrays.sort(row);
			Arrays.sort(col);
			Arrays.sort(dia);
			int maxRow = row[99];
			int maxCol = col[99];
			int maxDia = dia[1];
			
			int answer = Math.max(maxRow, maxCol);
			answer = Math.max(answer, maxDia);
			bw.write("#" + tc + " " + answer);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
