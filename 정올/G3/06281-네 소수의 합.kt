// [G3] 06281. 네 소수의 합
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        lateinit var isPrime: BooleanArray
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
        }

        fun solve() {
            // 1. 예외 처리
            if (N < 8) {
                sb.append("NONE")
                return
            }
            // 2. 소수 초기화
            isPrime = BooleanArray(N + 1) { true }
            isPrime[0] = false
            isPrime[1] = false
            // 3. 소수 구하기
            var i = 2
            while (i * i <= N) {
                if (isPrime[i]) {
                    var j = i * i
                    while (j <= N) {
                        isPrime[j] = false
                        j += i
                    }
                }
                i++
            }
            // 4. 골드바흐의 추측
            // 2보다 큰 모든 짝수는 두 소수의 합으로 표현할 수 있다.
            val first: Int
            val second: Int
            val remain: Int

            if (N % 2 == 0) {
                first = 2
                second = 2
                remain = N - 4
            } else {
                first = 2
                second = 3
                remain = N - 5
            }

            for (a in 2..remain) {
                val b = remain - a

                if (isPrime[a] && isPrime[b]) {
                    sb.append("$first $second $a $b")
                    return
                }
            }

            sb.append("NONE")
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}
