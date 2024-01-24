// 02503. [S3] 숫자야구
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<String> game = new ArrayList<>();
        ArrayList<String> initList = createNumberList();
        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++){
            String input = br.readLine().trim();
            game.add(input);
        }
        for (int i = 0; i < num; i++){
            String[] tmp = game.get(i).split(" ");

            String target = tmp[0];
            int strike = Integer.parseInt(tmp[1]);
            int ball = Integer.parseInt(tmp[2]);
            for (int j = initList.size()-1; j >= 0; j-- ){
                ArrayList<Integer> sb = countMatchNumber(target, initList.get(j));
                int s = sb.get(0);
                int b = sb.get(1);
                if(strike == s && ball == b){
                    continue;
                } else {
                    initList.remove(j);
                }
            }
        }
        bw.write(String.valueOf(initList.size()));
        bw.flush();
        bw.close();
    }

    static public ArrayList<String> createNumberList(){
        ArrayList<String> initList = new ArrayList<>();
        for (int n1 = 1; n1 <= 9; n1 ++){
            for (int n2 = 1; n2 <= 9; n2 ++) {
                if (n1 == n2) {
                    continue;
                }
                for (int n3 = 1; n3 <= 9; n3 ++){
                    if (n1 == n3 || n2 == n3){
                        continue;
                    } else {
                        initList.add(String.valueOf(n1*100 + n2*10 + n3));
                    }
                }
            }
        }
        return initList;
    }

    static public ArrayList<Integer> countMatchNumber(String target, String number){
        ArrayList<Integer> count = new ArrayList<>();
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < 3; i++){
            if (target.charAt(i) == number.charAt(i)){
                strike++;
            } else if (number.contains(String.valueOf(target.charAt(i)))){
                ball++;
            } else {
                continue;
            }

        }
        count.add(strike);
        count.add(ball);
        return count;
    }
}
