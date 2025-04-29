// 02632. [G2] 피자판매.

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int S, A, B; // S: 손님이 구매하고자 하는 피자 크기, A: A피자 조각 개수 B: B피자 조각 개수
    static int[] pizzaA, pizzaB; // 피자별 피자조각 크기
    static int[] prefixSumA, prefixSumB; // 각 누적합
    static Map<Integer, Integer> setA, setB; // 가능한 크기 조합 개수
    static int ans = 0; // 방법의 가지 수

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        S = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        pizzaA = new int[A];
        pizzaB = new int[B];
        for (int i = 0; i < A; i++) {
            pizzaA[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < B; i++) {
            pizzaB[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        prefixSumA = new int[A];
        prefixSumB = new int[B];
        // A를 기준으로 오름차순 탐색
        setA = new TreeMap<>();
        setB = new HashMap<>();
        // 0은 초기화
        prefixSumA[0] = pizzaA[0];
        prefixSumB[0] = pizzaB[0];
        // 피자 크기를 0을 만드는 방법은 1개씩 존재 -> 아무것도 선택하지 않았음
        setA.put(0, 1);
        setB.put(0, 1);
        // 누적합 초기화
        for (int a = 1; a < A; a++) {
            prefixSumA[a] = prefixSumA[a - 1] + pizzaA[a];
        }
        for (int b = 1; b < B; b++) {
            prefixSumB[b] = prefixSumB[b - 1] + pizzaB[b];
        }
        // 피자 크기를 맥스로 하는 방법은 1개씩 존재 -> 원형인거를 생각
        setA.put(prefixSumA[A - 1], 1);
        setB.put(prefixSumB[B - 1], 1);
        // A피자 누적합 계산
        for (int a = 0; a < A; a++) {
            for (int plus = 0; plus < A - 1; plus++) {
                int key = getPrefixSum(true, a, a + plus);
                setA.put(key, setA.getOrDefault(key, 0) + 1);
            }
        }
        // B피자 누적합 계산
        for (int b = 0; b < B; b++) {
            for (int plus = 0; plus < B - 1; plus++) {
                int key = getPrefixSum(false, b, b + plus);
                setB.put(key, setB.getOrDefault(key, 0) + 1);
            }
        }
        for (int aKey : setA.keySet()) {
            if (aKey > S) {
                break;
            }
            int bKey = S - aKey;
            ans += setA.get(aKey) * setB.getOrDefault(bKey, 0);
        }
    }

    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static int getPrefixSum(boolean isA, int start, int end) {
        int[] target;
        int size;
        if (isA) {
            target = prefixSumA;
            size = A;
        } else {
            target = prefixSumB;
            size = B;
        }
        if (end > target.length - 1) {
            return target[size - 1] - target[start - 1] + target[end % size];
        } else if (start == 0 && end == 0) {
            return target[0];
        } else if (start == 0) {
            return target[end];
        } else {
            return target[end] - target[start - 1];
        }
    }
}
