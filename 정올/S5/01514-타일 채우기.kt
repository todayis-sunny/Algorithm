// [S5] 01514-타일 채우기
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var W = 0L
        var H = 0L
        var ans = 0L
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. W, H 입력
            st = StringTokenizer(readln())
            W = st.nextToken().toLong()
            H = st.nextToken().toLong()
        }

        fun solve() {
            // 1. 최대 공약수 구하기
            val div = gcd(W, H)
            // 2. 정답 구하기
            ans = (W / div) * (H / div)
            sb.append(ans)
        }

        fun output() {
            println(sb.toString())
        }

        fun gcd(a: Long, b: Long): Long {
            return if (b == 0L) a else gcd(b, a % b)
        }
    }
    Solution().execute()
}
