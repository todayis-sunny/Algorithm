// 13023. [G5] ABCDE
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    static int answer;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int n = 0; n < N; n++) {
        	arr.add(new ArrayList<Integer>());
        }
        
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }
        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            dfs(arr, visited, i, 0);
            if (flag == true) {
            	bw.write(String.valueOf("1"));
                break;
            }
        }
        if (flag == false) {
        	bw.write(String.valueOf("0"));
        }
        bw.flush();
        bw.close();
    }

    public static void dfs(ArrayList<ArrayList<Integer>> arr, boolean[] visited, int num, int count) {
        if (flag == true) {
            return;
        }
        if (count == 4) {
            answer += 1;
            flag = true;
            return;
        }
        
        for (int i = 0; i < arr.get(num).size(); i++) {
        	int tmp =  arr.get(num).get(i);
            if (!visited[tmp]) {
            	visited[num] = true;
                dfs(arr, visited, tmp, count + 1);
                visited[num] = false;
            }
        }
        return;
    }
}