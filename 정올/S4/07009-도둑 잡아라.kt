// [S4] 07009. 도둑 잡아라
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        var Q = 0
        val resident = hashSetOf<Int>()
        lateinit var suspect: IntArray
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N, Q 입력
            readln().split(" ").map { it.toInt() }.apply {
                N = this[0]
                Q = this[1]
            }
            // 2. 주민 입력
            st = StringTokenizer(readln())
            repeat(N) {
                resident.add(st.nextToken().toInt())
            }
            // 3. 용의자 입력
            st = StringTokenizer(readln())
            suspect = IntArray(Q) { st.nextToken().toInt() }
        }

        fun solve() {
            // 1. 용의자 리딩
            for (idx in suspect) {
                if (!resident.contains(idx)) {
                    sb.append(idx).append(" ")
                }
            }
            // 2. 정답처리
            if (sb.isEmpty()) {
                sb.append(-1)
            }
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}
