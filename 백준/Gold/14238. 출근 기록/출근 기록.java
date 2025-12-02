// 14238. [G2] 출근 기록.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    // 직원
    static char[] employee = {'A', 'B', 'C'};
    // bit 유닛상태 - 0: A, 1: B, 2: C
    static final int[] bit = {0, (1 << 1), (1 << 2) + (1 << 0)};
    // 문자 개수 - 0: A, 1: B, 2: C
    static int[] count = new int[3];
    static int[] record;
    static boolean[][][][] visited;
    static int length;
    static boolean success = false;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        String input = br.readLine();
        length = input.length();
        for (int i = 0; i < length; i++) {
            count[input.charAt(i) - 'A']++;
        }
        record = new int[length];
        visited = new boolean[count[0] + 1][count[1] + 1][count[2] + 1][(1 << 3)];
        Arrays.fill(record, -1);
    }

    static void solve() {
        if (dfs(0, 0)) {
            success = true;
        }
    }

    static void output() throws IOException {
        if (!success) {
            bw.write("-1");
        } else {
            for (int i = 0; i < length; i++) {
                sb.append(employee[record[i]]);
            }
            bw.write(sb.toString());
        }
        bw.flush();
    }

    static boolean dfs(int depth, int bitmask) {
        if (depth == length) {
            return true;
        }
        if (visited[count[0]][count[1]][count[2]][bitmask]) return false;
        visited[count[0]][count[1]][count[2]][bitmask] = true;
        for (int emp = 2; emp >= 0; emp--) {
            // 직원이 없으면 스킵
            if (count[emp] == 0) continue;
            // 해당 직원이 출근이 불가능 하면 스킵
            if ((bitmask & bit[emp]) != 0) continue;
            count[emp]--;
            record[depth] = emp;
            if (dfs(depth + 1, ((bitmask >> 2) | bit[emp]))) {
                return true;
            }
            count[emp]++;
        }
        return false;
    }

}

/*
 # 접근 방법
 (어제) - (오늘) - (내일)
 bit로 출근 기록을 관리
 101 C가 오늘 출근 (C는 XC: 불가, CX: 불가)
 010 B가 오늘 출근 (B는 어제만 당장 출근한게 아니면 가능 즉 XB: 불가 BX: 가능)
 이전 근무자 2명을 보고나서 근무가 가능한지 체크
 다음 근무자를 배치할때는 우로 2칸 쉬프트
 
 (bit >> 2)를 하게 되는데
 C는 내일에도 지장있게 배치 됨
 B는 내일에는 지장없음
 

 # 풀이 과정
 dfs로 탐색
 */
