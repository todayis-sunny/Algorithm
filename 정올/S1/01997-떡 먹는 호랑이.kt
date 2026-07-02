// [S1] 01997. 떡 먹는 호랑이
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var D = 0
        var K = 0
        val count = Array(3) {IntArray(31) {0}}
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. D, K 입력
            readln().split(" ").map { it.toInt() }.apply {
                D = this[0]
                K = this[1]
            }
        }

        fun solve() {
            // 1. count 초기화
            count[1][1] = 1
            count[2][2] = 1
            // 2. D까지 뻗어보기
            for (d in 3..D) {
                count[1][d] = count[1][d - 2] + count[1][d - 1]
                count[2][d] = count[2][d - 2] + count[2][d - 1]
            }
            // 3. 정답 파악
            for (a1 in 1..K) {
                val remaining = K - a1 * count[1][D]
                // a. 나누어 떨어지는 경우
                if (remaining % count[2][D] == 0) {
                    sb.append(a1).append("\n").append(remaining / count[2][D])
                    return
                }
            }
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}
