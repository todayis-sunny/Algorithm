// 11404. [G4] 플로이드.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[][] arr;
    static int n, m, a, b, c;
    static final int INF = 200_000_000;
    public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (x == y) {
					continue;
				}
				arr[x][y] = INF;
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken());
			arr[a][b] = Math.min(arr[a][b], c);
		}
		for (int k = 0; k < n; k++) {
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					arr[x][y] = Math.min(arr[x][y], arr[x][k] + arr[k][y]);
				}
			}
		}
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (arr[x][y] >= INF) {
					arr[x][y] = 0;
				}
				bw.write(arr[x][y] + " ");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}