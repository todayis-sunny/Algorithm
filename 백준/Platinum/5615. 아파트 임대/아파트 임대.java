// 05615. [P1] 아파트 임대.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N; // 입력 개수
    static long[] kValues; // 입력받은 k 값들을 저장할 배열
    static int ans = 0;    // 최종 정답

    // 밀러-라빈 테스트에 사용할 밑수들
    static final BigInteger[] PRIME_BASES = {
            new BigInteger("2"), new BigInteger("3"), new BigInteger("5"),
            new BigInteger("7"), new BigInteger("11")
    };

    // 자주 사용하는 BigInteger 상수들
    static final BigInteger ZERO = BigInteger.ZERO;
    static final BigInteger ONE = BigInteger.ONE;
    static final BigInteger TWO = new BigInteger("2");

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /** 입력 처리 */
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        kValues = new long[N];
        for (int i = 0; i < N; i++) {
            kValues[i] = Long.parseLong(br.readLine());
        }
    }

    /** 핵심 로직 처리 */
    static void solve() {
        for (long k : kValues) {
            BigInteger n = BigInteger.valueOf(k).multiply(TWO).add(ONE);

            // 2보다 작은 수는 소수가 아님
            if (n.compareTo(TWO) < 0) {
                continue;
            }

            boolean isPrime = true;
            for (BigInteger base : PRIME_BASES) {
                // n이 밑수와 같으면 소수
                if (n.equals(base)) {
                    isPrime = true;
                    break;
                }
                // 밀러-라빈 테스트 실패 시
                if (!millerRabin(n, base)) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                ans++;
            }
        }
    }

    /** 출력 처리 */
    static void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    /**
     * 밀러-라빈 소수 판별법 (BigInteger 버전)
     */
    static boolean millerRabin(BigInteger n, BigInteger a) {
        BigInteger r = ZERO;
        BigInteger d = n.subtract(ONE); // d = n - 1

        // d가 짝수인 동안 (d % 2 == 0)
        while (!d.testBit(0)) {
            r = r.add(ONE);      // r += 1
            d = d.shiftRight(1); // d /= 2
        }

        BigInteger x = a.modPow(d, n); // x = powmod(a, d, n)

        if (x.equals(ONE) || x.equals(n.subtract(ONE))) {
            return true;
        }

        // for (int i = 0; i < r - 1; i++)
        for (BigInteger i = ZERO; i.compareTo(r.subtract(ONE)) < 0; i = i.add(ONE)) {
            x = x.modPow(TWO, n); // x = powmod(x, 2, n)
            if (x.equals(n.subtract(ONE))) {
                return true;
            }
        }
        return false;
    }
}
