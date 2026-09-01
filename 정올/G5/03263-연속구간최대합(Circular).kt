// [G5] 03263-연속구간최대합(Circular)
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        lateinit var arr: LongArray
        var total = 0L
        var ans = Long.MIN_VALUE
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
            // 2. 원형 수열 입력
            st = StringTokenizer(readln())
            arr = LongArray(N) { st.nextToken().toLong() }
        }

        fun solve() {
            // 1. 전체 총합
            total = arr.sum()
            // 2. 일반적인 중간 최대 연속 부분합
            var currMax = 0L
            var maxSum = Long.MIN_VALUE
            for (a in arr) {
                currMax = maxOf(currMax + a, a)
                maxSum = maxOf(maxSum, currMax)
            }
            ans = maxOf(ans, maxSum)
            // 3-1. 최소 연속 부분합
            var currMin = 0L
            var minSum = Long.MAX_VALUE
            for (a in arr) {
                currMin = minOf(currMin + a, a)
                minSum = minOf(minSum, currMin)
            }
            // 3-2. 원형 최대 부분합
            // 전체가 제외하는 경우는 사용하지 않음
            if (minSum != total) {
                ans = maxOf(ans, total - minSum)
            }

            // 4. 정답
            sb.append(ans)
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}
