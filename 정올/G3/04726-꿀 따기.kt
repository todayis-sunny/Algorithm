// [G3] 04726-꿀 따기
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        lateinit var honeys: IntArray // 1-based
        lateinit var pSum: IntArray
        var ans = 0
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
            // 2. 꿀 입력
            honeys = IntArray(N + 1)
            st = StringTokenizer(readln())
            for (i in 1..N) {
                honeys[i] = st.nextToken().toInt()
            }
        }

        fun solve() {
            // 1. 누적합 처리
            pSum = IntArray(N + 1)
            for (i in 1..N) {
                pSum[i] = pSum[i - 1] + honeys[i]
            }
            // 2. 탐색
            // 최소 하나의 벌은 양 끝에서 시작
            for (bee1 in intArrayOf(1, N)) {
                // 꿀통이 중간에 있는경우
                val total1 = query(2, N - 1)
                for (goal in 2 until N) {
                    val bee2 = if (bee1 == 1) N else 1
                    ans = maxOf(ans, total1 + honeys[goal])
                }
                // 꿀통이 양 끝에 있는 경우
                val total2 = if (bee1 == 1) query(2, N) else query(1, N - 1)
                val goal = if (bee1 == 1) N else 1
                for (bee2 in 2 until N) {
                    val tmp = if (goal == 1) query(1, bee2 - 1) else query(bee2 + 1, N)
                    ans = maxOf(ans, total2 - honeys[bee2] + tmp)
                }
            }
            // 3. 정답 처리
            sb.append(ans)
        }

        fun output() {
            println(sb.toString())
        }

        fun query(start: Int, end: Int): Int = pSum[end] - pSum[start - 1]
    }
    Solution().execute()
}
