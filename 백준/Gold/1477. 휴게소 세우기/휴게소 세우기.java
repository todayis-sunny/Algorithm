//01477. [G4] 휴게소 세우기.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
 static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 static StringTokenizer st;
 static int N, M, L;
 static int left, right, mid, cnt;
 static int[] space, init, trans, count;

 public static void main(String[] args) throws IOException {
     st = new StringTokenizer(br.readLine());
     int N = Integer.parseInt(st.nextToken());
     int M = Integer.parseInt(st.nextToken());
     int L = Integer.parseInt(st.nextToken());
     space = new int[N + 2];
     init = new int[N + 1];
     trans = new int[N + 1];
     count = new int[N + 1];
     space[N + 1] = L;

     st = new StringTokenizer(br.readLine());

     for (int n = 1; n <= N; n++) {
         space[n] = Integer.parseInt(st.nextToken());
     }
     Arrays.sort(space);
     int max = Integer.MAX_VALUE;
     for (int i = 0; i <= N; i++) {
         init[i] = trans[i] = space[i + 1] - space[i];  
     }
     left = 1;
     right = L;
     
     while (left <= right) {
    	 boolean flag = true;
    	 cnt = 0;
    	 mid = (left + right) / 2;
    	 for (int n = 0; n < N+1; n++) {
    		 int tmp = (int) Math.ceil(init[n] / (double) mid) - 1;
    		 if (tmp < 0) {
    			 tmp = 0;
    		 }
    		 cnt += tmp;
    		 if (cnt > M) {
    			 left = mid + 1;
    			 flag = false;
    			 break;
    		 }
    		 
    	 }
    	 if (flag) {
    		 right = mid - 1;
    	 }
     }
     bw.write(String.valueOf(left));
     bw.flush();
     bw.close();
     br.close();
 }
 
}