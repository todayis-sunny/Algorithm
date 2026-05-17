// [G5] 01077. 배낭채우기1
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0 // 보석의 가지 수
        var W = 0 // 가방의 최대 무게
        lateinit var JewelryArr: Array<Jewelry>
        lateinit var dp: IntArray
        var ans = 0

        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N, M 입력
            st = StringTokenizer(readln())
            N = st.nextToken().toInt()
            W = st.nextToken().toInt()
            // 2. 보석 입력
            JewelryArr = Array(N) {
                st = StringTokenizer(readln())
                Jewelry(st.nextToken().toInt(), st.nextToken().toInt())
            }
        }

        fun solve() {
            // 1. dp 초기화
            dp = IntArray(W + 1) { -1 }
            dp[0] = 0
            // 2. dp 메모제이션
            for (jewelry in JewelryArr) {
                for (w in jewelry.weight..W) {
                    if (dp[w - jewelry.weight] == -1) continue
                    dp[w] = maxOf(dp[w], dp[w - jewelry.weight] + jewelry.value)
                }
            }
            // 3. 정답 도출
            for (w in W downTo 0) {
                ans = maxOf(ans, dp[w])
            }
            sb.append(ans)
        }

        fun output() {
            println(sb)
        }

    }

    Solution().execute()
}

private data class Jewelry(val weight: Int, val value: Int)
