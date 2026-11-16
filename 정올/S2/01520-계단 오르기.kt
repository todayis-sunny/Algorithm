// [S2] 01520-계단 오르기
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        lateinit var stairs: IntArray
        lateinit var dp: Array<IntArray>
        var N = 0
        var ans = 0
        val sb = StringBuilder()
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. 입력
            N = readln().toInt()
            stairs = IntArray(N + 2)
            dp = Array(3) { IntArray(N + 2) }
            repeat(N) {
                stairs[it + 2] = readln().toInt()
            }
        }

        fun solve() {
            // 1. 메모제이션
            for (i in 2..N + 1) {
                // 1-1. 연속의 수
                for (j in 0..2) {
                    when (j) {
                        1 -> { // 불연속
                            dp[j][i] = dp.maxOf { it[i - 2] } + stairs[i]
                        }

                        2 -> { // 연속
                            dp[j][i] = maxOf(dp[j - 1][i - 1] + stairs[i], dp[j][i])
                        }
                    }
                }
            }
            ans = dp.maxOf { it[N + 1] }
            sb.append(ans)
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}
