import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<Integer> arr = new ArrayList<>();
        int total = 0;
        for (int i = 0; i < 9; i++){
            String input = bf.readLine().trim();
            int num = Integer.parseInt(input);
            arr.add(num);
            total += num;
        }
        boolean flag = false;
        int x, y;
        Collections.sort(arr);
        for(int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++){
                int tmp = arr.get(i) + arr.get(j);
                if (total - tmp == 100){
                    flag = true;
                    arr.remove(j);
                    arr.remove(i);
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        for(int k = 0; k < 7; k++){
            bw.write(String.valueOf(arr.get(k)));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }


}
