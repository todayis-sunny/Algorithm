// [S3] 01057-미친수열
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0L
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().trim().toLong()
        }

        fun solve() {
            // 1. 원소 찾기
            var left = 1L
            var right = 2_000_000_000L

            while (left <= right) {
                val mid = (left + right) / 2
                // 삼각수 공식
                val sum = mid * (mid + 1) / 2

                if (sum >= N) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }

            sb.append(left)
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}
