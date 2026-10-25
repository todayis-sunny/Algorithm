// [G5] 02183-가장 큰값 만들기
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        lateinit var numberTicket: Array<String>
        lateinit var bucket: Array<String>
        lateinit var visited: BooleanArray
        var ans = Int.MIN_VALUE
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
            numberTicket = Array(N) { "" }
            bucket = Array(N) { "" }
            visited = BooleanArray(N)
            // 2. 번호표 입력
            st = StringTokenizer(readln())
            for (i in 0 until N) {
                numberTicket[i] = st.nextToken()
            }
        }

        fun solve() {
            dfs(0)
            sb.append(ans)
        }

        fun output() {
            println(sb.toString())
        }

        fun dfs(depth: Int) {
            if (depth == N) {
                ans = maxOf(ans, bucket.joinToString("").toInt())
                return
            }
            for (i in 0 until N) {
                if (visited[i]) continue
                visited[i] = true
                bucket[depth] = numberTicket[i]
                dfs(depth + 1)
                bucket[depth] = ""
                visited[i] = false
            }
        }
    }
    Solution().execute()
}
