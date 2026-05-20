// [G5] 08051. 멜론 수박 호박
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        val MAX = 10_001
        var N = 0
        val fruitArr = intArrayOf(1, 2, 3)
        lateinit var orderArr: IntArray
        val dp = IntArray(MAX)

        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
            // 2. order 입력
            orderArr = IntArray(N)
            st = StringTokenizer(readln())
            for (i in 0 until N) {
                orderArr[i] = st.nextToken().toInt()
            }
        }

        fun solve() {
            // 1. dp 초기화
            dp[0] = 1
            // 멜론, 수박, 호박
            for (fruit in fruitArr) {
                for (i in fruit until MAX) {
                    dp[i] += dp[i - fruit]
                }
            }

            // 2. 가능한 경우의 수
            for (order in orderArr) {
                sb.append(dp[order]).append(" ")
            }
        }

        fun output() {
            println(sb)
        }
    }
    Solution().execute()
}
